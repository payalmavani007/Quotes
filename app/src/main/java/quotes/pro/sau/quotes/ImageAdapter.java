package quotes.pro.sau.quotes;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import quotes.pro.sau.quotes.model.Homelist_model;

public class ImageAdapter extends PagerAdapter {

    Context context;

    View view;
    ArrayList<Homelist_model> grid_models;
    int pos;

    public ImageAdapter(Context context, ArrayList<Homelist_model> grid_models){
        this.context=context;
        this.grid_models=grid_models;
    }


    @Override
    public int getCount() {
        return grid_models.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Homelist_model grid_model=grid_models.get(position);
        view= (ViewGroup) LayoutInflater.from(context).inflate(R.layout.list,null);
        ImageView imageView = view.findViewById(R.id.bcgrnd_img);

        Picasso.get().load(grid_model.getImage_url())

                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into( imageView);
        (container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==((View)object);
    }

}
