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

import quotes.pro.sau.quotes.model.HomePreviewClass;
import quotes.pro.sau.quotes.model.Homelist_model;
import quotes.pro.sau.quotes.retrofit.ApiClient;
import quotes.pro.sau.quotes.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;

import static java.sql.Types.NULL;

public class PreviewViewPager extends AppCompatActivity {
    ImageView download;
    RelativeLayout layout;
    Context context;
    JSONArray array;
    String SelectedId,quotes;
    RecyclerView recyclar;
    SlidingImage_Adapter adapter;
    private static final String TAG = "PreviewViewPager";
    private static ViewPager mPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_viewpager);
        mPager = (ViewPager) findViewById(R.id.pager);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        SelectedId = extras.getString("SelectedId");
        quotes = extras.getString("quotes");
     

        Log.e(TAG, "iddddddddddd: "+ SelectedId);
        init();
    }

    //-----------------------viewpager--------------


    private void init() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<HomePreviewClass> homelist_modelCall = apiService.getDufultQutes(SelectedId);
        homelist_modelCall.enqueue(new Callback<HomePreviewClass>() {
            @Override
            public void onResponse(Call<HomePreviewClass> call, retrofit2.Response<HomePreviewClass> response)
            {
                mPager.setAdapter(new SlidingImage_Adapter(PreviewViewPager.this, response.body().getData()));
            }

            @Override
            public void onFailure(Call<HomePreviewClass> call, Throwable t) {

            }
        });

    }
}

