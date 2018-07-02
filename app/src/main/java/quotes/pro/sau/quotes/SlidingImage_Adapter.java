package quotes.pro.sau.quotes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import quotes.pro.sau.quotes.model.HomePreviewClass;
import quotes.pro.sau.quotes.model.Homelist_model;

import static java.sql.Types.NULL;


public class SlidingImage_Adapter extends PagerAdapter {



    private LayoutInflater inflater;
    private Context context;
    List<HomePreviewClass.DataBean> data;
    String position;



    public SlidingImage_Adapter(PreviewViewPager context, List<HomePreviewClass.DataBean> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.position = position;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
       return data.size();

    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.viewpagerrow, view, false);

        assert imageLayout != null;

        final RelativeLayout frameLayout = (RelativeLayout) imageLayout.findViewById(R.id.relative);

        final ImageView download = (ImageView) imageLayout
                .findViewById(R.id.download);
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        final TextView txtQuteTex = (TextView) imageLayout
                .findViewById(R.id.txtQuteTex);
       // Picasso.get().load(data.getImage_url() + data.getData().get(position).getQuotes_image()).into(imageView);
       // Picasso.get().load(data.get(position) + data.get(position).getQuotes_image()).into(imageView);
       // Picasso.get().load(data.getClass()+ data.get(position).getQuotes_image()).into(imageView);

       // Picasso.get().load(data.get(position).getQuotes_image()).into(imageView);
       // Picasso.get().load(data.getClass()+data.get(position).getQuotes_image()).into(imageView);
        Picasso.get().load(data.get(position)+data.get(position).getQuotes_image()).into(imageView);

        txtQuteTex.setText(data.get(position).getQuotes_name());
        //txtQuteTex.setText(data.get(position).getQuotes_image());
        view.addView(imageLayout, 0);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = getBitmap(frameLayout);
                saveChart(bitmap, frameLayout.getMeasuredHeight(), frameLayout.getMeasuredWidth());
            }
        });
        return imageLayout;
    }

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
        Toast.makeText(context, "Download Successfull.", Toast.LENGTH_SHORT).show();

    }

    public Bitmap getBitmap(RelativeLayout frameLayout) {
        frameLayout.setDrawingCacheEnabled(true);
        frameLayout.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(frameLayout.getDrawingCache());
        frameLayout.setDrawingCacheEnabled(false);
        return bmp;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}