package quotes.pro.sau.quotes;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.util.Locale;
import static java.sql.Types.NULL;

public class Preview extends AppCompatActivity
{
ImageView img_preview,download,set_as_bcgrnd,unliked;
RelativeLayout layout;
TextView text_preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
       /* set_as_bcgrnd = findViewById(R.id.set_as_bcgrnd);

        unliked = findViewById(R.id.like);*/

        download = findViewById(R.id.download);
        text_preview = findViewById(R.id.text_preview);
        layout = findViewById(R.id.relative);
        img_preview = findViewById(R.id.img_preview);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = getBitmap(layout);
                        saveChart(bitmap, layout.getMeasuredHeight(), layout.getMeasuredWidth());
            }
        });
        Intent intent = getIntent();
        String text = intent.getStringExtra("quotes");
        text_preview.setText(text);

        Intent i = getIntent();
       String imgurl = i.getStringExtra("quotes_image");
        Picasso.get().load(imgurl).into(img_preview);

    }

    private void saveChart(Bitmap getbitmap, float height, float width) {


        String root = Environment.getExternalStorageDirectory().toString();
        File folder = new File(root + "/Quotes");
        boolean success = false;

        if (!folder.exists())
                    {
                    success = folder.mkdirs();
        }
       String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss",
                    Locale.getDefault()).format(new Date());
       File file = new File(folder.getPath() + File.separator +timeStamp+".png");

        if ( !file.exists() )
                    {
            try {
                    success = file.createNewFile();
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }
       FileOutputStream ostream = null;

        try{
           ostream = new FileOutputStream(file);
           System.out.println(ostream);
           Bitmap well = getbitmap;
                        Bitmap save = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
                        Paint paint = new Paint();
                        paint.setColor(Color.WHITE);
                        Canvas now = new Canvas(save);
                        now.drawRect(new Rect(0,0,(int) width, (int) height), paint);
                        now.drawBitmap(well,
                        new Rect(0,0,well.getWidth(),well.getHeight()),
                        new Rect(0,0,(int) width, (int) height), null);

            if(save == null) {
                        System.out.println(NULL);
                }
                        save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            }
        catch (NullPointerException e){
           e.printStackTrace();
            }
        catch (FileNotFoundException e){
           e.printStackTrace();
            }
        catch (Exception e){
           e.printStackTrace();
            }
        Toast.makeText(this, "Download Successfull.", Toast.LENGTH_SHORT).show();

    }

   public Bitmap getBitmap(RelativeLayout layout){
       layout.setDrawingCacheEnabled(true);
                    layout.buildDrawingCache();
                    Bitmap bmp = Bitmap.createBitmap(layout.getDrawingCache());
                    layout.setDrawingCacheEnabled(false);
       return bmp;
        }
    }





