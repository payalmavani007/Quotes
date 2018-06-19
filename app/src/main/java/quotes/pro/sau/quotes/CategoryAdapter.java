package quotes.pro.sau.quotes;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    JSONArray dataAry;
    FragmentManager fragmentManager;

    public CategoryAdapter(Context context, JSONArray dataAry, FragmentManager fragmentManager) {
        this.context = context;
        this.dataAry = dataAry;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.catlist_row, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, final int position) {
        holder.cardView.setCardBackgroundColor(Color.TRANSPARENT);
        try {
            final JSONObject o = dataAry.getJSONObject(position);
            Picasso.get().load("http://192.168.1.200/quotesmanagement/public/uploads/" + o.getString("category_image")).into(holder.imageView);
            holder.textView.setText(o.getString("category_name"));
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*DisplayCategoryFragment newFragment = new DisplayCategoryFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.frame_layout, newFragment,"Category List");
                    transaction.commit();*/

                    try {
                       /* Intent intent = new Intent(view.getContext(), CategoryImages.class);
                        intent.putExtra("name",jsonObjec.getString("id"));
                        c.startActivity(intent);*/

                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        Bundle b = new Bundle();
                        b.putString("name", o.getString("id"));
                        CategoryListFragment featureImageGrid = new CategoryListFragment();
                        featureImageGrid.setArguments(b);
                        ft.replace(R.id.fragment_container, featureImageGrid).addToBackStack("tag").commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataAry.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cattext);
            imageView = itemView.findViewById(R.id.catbcgrnd_img);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
