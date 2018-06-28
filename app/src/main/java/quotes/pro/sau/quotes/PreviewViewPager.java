package quotes.pro.sau.quotes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import quotes.pro.sau.quotes.model.Homelist_model;
import quotes.pro.sau.quotes.retrofit.ApiClient;
import quotes.pro.sau.quotes.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;

import static java.sql.Types.NULL;

public class PreviewViewPager extends AppCompatActivity {
    ImageView download;
    TextView txt;
    RelativeLayout layout;
    Context context;
    JSONArray array;
    JSONArray dataAry;
    RecyclerView recyclar;
    RecyclarAdapter recyclarAdapter;
    ViewPager mpager;
    SlidingImage_Adapter adapter;
    private static final String TAG = "PreviewViewPager";

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.one, R.drawable.one, R.drawable.one, R.drawable.one};
    private ArrayList<SlidingImage_Adapter> ImagesArray = new ArrayList<SlidingImage_Adapter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_viewpager);
        mPager = (ViewPager) findViewById(R.id.pager);
      /*  download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = getBitmap(layout);
                saveChart(bitmap, layout.getMeasuredHeight(), layout.getMeasuredWidth());
            }
        });*/
        init();

     /*   final String url = "http://192.168.1.200/quotesmanagement/list_quotes";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("response ", response);
                Log.e("final url ", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 0) {
                        dataAry = jsonObject.getJSONArray("data");
                        mPager = new ViewPager(getApplicationContext(), (AttributeSet) dataAry);
                        mPager.setAdapter(adapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
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

*/
    }
    //-----------------------------download-----------------------------

    private void saveChart(Bitmap getbitmap, float height, float width) {


        String root = Environment.getExternalStorageDirectory().toString();
        File folder = new File(root + "/Quotes");
        boolean success = false;

        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss",
                Locale.getDefault()).format(new Date());
        File file = new File(folder.getPath() + File.separator + timeStamp + ".png");

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
        Toast.makeText(this, "Download Successfull.", Toast.LENGTH_SHORT).show();

    }

    public Bitmap getBitmap(RelativeLayout layout) {
        layout.setDrawingCacheEnabled(true);
        layout.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(layout.getDrawingCache());
        layout.setDrawingCacheEnabled(false);
        return bmp;
    }

    //-----------------------viewpager--------------


    private void init() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Homelist_model> homelist_modelCall = apiService.getDufultQutes();
        homelist_modelCall.enqueue(new Callback<Homelist_model>() {
            @Override
            public void onResponse(Call<Homelist_model> call, retrofit2.Response<Homelist_model> response) {

                mPager.setAdapter(new SlidingImage_Adapter(PreviewViewPager.this, response.body()));

            }

            @Override
            public void onFailure(Call<Homelist_model> call, Throwable t) {

            }
        });

    }
}

