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
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daprlabs.cardstack.SwipeDeck;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import quotes.pro.sau.quotes.model.SelectCategoryDataModel;

import static java.sql.Types.NULL;

public class CategorySwipeActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    SwipeDeck category_swipe;
    ImageView share_category;
    File folder;
    String timeStamp;
    LinearLayout download1, copy;
    File file;
    String root;
    Bitmap bitmap;
    String mCatagoryId;
    RelativeLayout linearLayout;
    String position;
     Context context;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_swipe);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(R.color.colorAccent);  }
        category_swipe = findViewById(R.id.swipe_category);
        share_category = findViewById(R.id.share_category);
        category_swipe.setHardwareAccelerationEnabled(true);
        category_swipe.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardsDepleted() {

            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });

        String url = "http://192.168.1.200/quotesmanagement/swipe_card?category_id=4&SelectedCatagoryId=17";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject resp  = new JSONObject(response);
                    if (resp.getInt("status") == 0)
                    {
                        JSONArray data = resp.getJSONArray("data");
                        JSONObject object = (JSONObject) data.get(0);
                        SelectCategoryDataModel grid_model = new SelectCategoryDataModel();
                        grid_model.setImage_url(object.getString("img_url"));
                        SwipeDeckAdapter adapter=new SwipeDeckAdapter(getApplicationContext(),data,position);
                        category_swipe.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


    }
    public Bitmap getBitmap(RelativeLayout layout) {
        layout.setDrawingCacheEnabled(true);
        layout.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(layout.getDrawingCache());
        layout.setDrawingCacheEnabled(false);
        return bmp;
    }
    private void saveChart(Bitmap getbitmap, float height, float width, String name) {

        Log.e(TAG, "name: " + " http://192.168.1.200/quotesmanagement/public/uploads/" + name);
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
    public class SwipeDeckAdapter extends BaseAdapter {
        JSONArray data;
        String position;
        private Context context;

      /*  public SwipeDeckAdapter(List<SelectCategoryDataModel.DataBean> data, Context context, String position) {
            this.data = data;
            this.position = position;
            this.context = context;
        }
*/
        public SwipeDeckAdapter(Context context, JSONArray data, String position) {
            this.context = context;
            this.data = data;
            this.position = position;
        }

        @Override
        public int getCount() {
            return data.length();
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

            int name = data.get(position).getId();

            final TextView textView = (TextView) v.findViewById(R.id.sample_text);

            if (name == Integer.parseInt(mCatagoryId)) {
                textView.setText(data.get(position).getQuotes_name());
            } else {
                Log.e(TAG, "noid: ");
            }

            linearLayout =  v.findViewById(R.id.relative1);
            linearLayout.setBackgroundColor(getMatColor("600"));
            download1 = v.findViewById(R.id.download1);
            copy = v.findViewById(R.id.copy);
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE)).setText(textView.getText().toString());
                    Toast.makeText(CategorySwipeActivity.this, "Quote Copied.", Toast.LENGTH_SHORT).show();
                }
            });
            download1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bitmap = getBitmap(linearLayout);
                    saveChart(bitmap, linearLayout.getMeasuredHeight(), linearLayout.getMeasuredWidth(), data.get(position).getQuotes_image());
                    Toast.makeText(CategorySwipeActivity.this, " Download Successfull.", Toast.LENGTH_SHORT).show();
                }
            });

            share_category.setOnClickListener(new View.OnClickListener() {
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
            share_category.setOnClickListener(new View.OnClickListener() {
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
