package quotes.pro.sau.quotes;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import quotes.pro.sau.quotes.model.Category_SwiipeModel;
import quotes.pro.sau.quotes.retrofit.ApiClient;
import quotes.pro.sau.quotes.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.Types.NULL;


public class SwipeDeckActivitycopy extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ApiInterface apiService;
    ArrayList<String> newData;
    String mImageUrl;
    ImageView imageViewShare;
    LinearLayout download1, copy;
    String  position, mCatagoryId,mId;
    RelativeLayout layout;
    FloatingActionButton fab;
    File folder;
    String timeStamp;
    File file;
    String root;
    Bitmap bitmap;
    RelativeLayout linearLayout;
    private SwipeDeck cardStack;
    private Context context = this;
    private SwipeDeckAdapterp adapter;
    private ArrayList<String> testData;


    @SuppressLint({"InflateParams", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_deck);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(R.color.colorAccent);  }


        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        imageViewShare = findViewById(R.id.share);
        cardStack.setHardwareAccelerationEnabled(true);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Bundle extras = getIntent().getExtras();
       // assert extras != null;
        mId = extras.getString("id");

        position = extras.getString("position");

       mCatagoryId = extras.getString("catagoryId");

        Log.e(TAG, "testing: " +mId+"---->"+mCatagoryId );
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);
            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");
                //Toast.makeText(context, "No More Crads.", Toast.LENGTH_SHORT).show();
                Call<Category_SwiipeModel> modelCall = apiService.getCategorySwipeList(mId,mCatagoryId);
                modelCall.enqueue(new Callback<Category_SwiipeModel>() {
                    @Override
                    public void onResponse(Call<Category_SwiipeModel> call, Response<Category_SwiipeModel> response) {
                        //  mImageUrl = response.body().getImage_url();
                        SwipeDeckAdapterp adapter = new SwipeDeckAdapterp(response.body().getData(), context, position);
                        cardStack.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Category_SwiipeModel> call, Throwable t) {

                    }
                });
            }


            @Override
            public void cardActionDown() {
                Log.i(TAG, "cardActionDown");
            }

            @Override
            public void cardActionUp() {
                Log.i(TAG, "cardActionUp");
            }

        });

        Call<Category_SwiipeModel> modelCall = apiService.getCategorySwipeList(mId,mCatagoryId);
        modelCall.enqueue(new Callback<Category_SwiipeModel>() {
            @Override
            public void onResponse(Call<Category_SwiipeModel> call, Response<Category_SwiipeModel> response) {
                //   mImageUrl = response.body().getImage_url();
                SwipeDeckAdapterp adapter = new SwipeDeckAdapterp(response.body().getData(), context, position);
                cardStack.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Category_SwiipeModel> call, Throwable t) {

            }
        });
    }

    public Bitmap getBitmap(RelativeLayout layout) {
        layout.setDrawingCacheEnabled(true);
        layout.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(layout.getDrawingCache());
        layout.setDrawingCacheEnabled(false);
        return bmp;
    }

    private void saveChart(Bitmap getbitmap, float height, float width, String name) {

        Log.e(TAG, "name: " + " http://rajviinfotech.in/quotes/public/uploads/" + name);
        root = Environment.getExternalStorageDirectory().toString();
        folder = new File(root + "/Quotes");
        boolean success = false;

        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss",
                Locale.getDefault()).format(new Date());
        file = new File(folder.getPath() + File.separator + name + ".png");

        if (!file.exists()) {
            try {
                success = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream ostream = null;

        try {
            ostream = new FileOutputStream(file);
            System.out.println(ostream);
            Bitmap well = getbitmap;
            Bitmap save = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Canvas now = new Canvas(save);
            now.drawRect(new Rect(0, 0, (int) width, (int) height), paint);
            now.drawBitmap(well,
                    new Rect(0, 0, well.getWidth(), well.getHeight()),
                    new Rect(0, 0, (int) width, (int) height), null);

            if (save == null) {
                System.out.println(NULL);
            }
            save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class SwipeDeckAdapterp extends BaseAdapter {
        List<Category_SwiipeModel.DataBean> data;
        String position;
        private Context context;



        public SwipeDeckAdapterp(List<Category_SwiipeModel.DataBean> data, Context context, String position) {
            this.data = data;
            this.position = position;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate(R.layout.test_card2, parent, false);
            }
            ImageView imageView = (ImageView) v.findViewById(R.id.img_preview);

            String name = data.get(position).getQuotes_name();

            final TextView textView = (TextView) v.findViewById(R.id.sample_text);
            textView.setText(data.get(position).getQuotes_name());
            //      textView.setText(data.get(position).getQuotes_name());
            //    ImageView imgDownload = (ImageView) v.findViewById(R.id.download);
            linearLayout = (RelativeLayout) v.findViewById(R.id.relative1);
            linearLayout.setBackgroundColor(getMatColor("600"));
            download1 = v.findViewById(R.id.download1);
            copy = v.findViewById(R.id.copy);
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE)).setText(textView.getText().toString());
                    Toast.makeText(SwipeDeckActivitycopy.this, "Quote Copied.", Toast.LENGTH_SHORT).show();
                }
            });
            download1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bitmap = getBitmap(linearLayout);
                    saveChart(bitmap, linearLayout.getMeasuredHeight(), linearLayout.getMeasuredWidth(), data.get(position).getQuotes_image());
                    Toast.makeText(SwipeDeckActivitycopy.this, " Download Successfull.", Toast.LENGTH_SHORT).show();
                }
            });

            imageViewShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Bitmap bitmap1 = getBitmap(linearLayout);
                    saveChart(bitmap1, linearLayout.getMeasuredHeight(), linearLayout.getMeasuredWidth(), data.get(position).getQuotes_image());
                    String fileName = data.get(position).getQuotes_image() + ".png";
                    String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    String myDir = externalStorageDirectory + "/Quotes/"; // the file will be in saved_images
                    Uri uri = Uri.parse("file:///" + myDir + fileName);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    Log.e("path", "sjfgsdfgas" + uri);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Test Mail");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Quotes Images");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(shareIntent, "Share Deal"));

                }
            });
            return v;
        }

        private int getMatColor(String typeColor) {

            int returnColor = Color.BLACK;
            int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getPackageName());

            if (arrayId != 0) {
                TypedArray colors = context.getResources().obtainTypedArray(arrayId);
                int index = (int) (Math.random() * colors.length());
                returnColor = colors.getColor(index, Color.BLACK);
                colors.recycle();
            }
            return returnColor;
        }
    }
}
