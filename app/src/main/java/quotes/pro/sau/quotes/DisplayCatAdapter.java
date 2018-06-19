package quotes.pro.sau.quotes;

import android.content.Context;
import android.content.Intent;
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

class DisplayCatAdapter extends RecyclerView.Adapter<DisplayCatAdapter.ViewHolder>{


    Context context;
    JSONArray array;

    public DisplayCatAdapter(Context context, JSONArray array)
    {
        this.context = context;
        this.array = array;
    }

    @Override
    public DisplayCatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.discatlist, parent, false);
        return new DisplayCatAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DisplayCatAdapter.ViewHolder holder, final int position) {

        try {
            final JSONObject o=array.getJSONObject(position);
            holder.textView.setText(o.getString("name"));
            Picasso.get().load("http://192.168.1.200/Mr.Gill/public/uploads/"+o.getString("picture")).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,Preview.class);

                    try {
                        intent.putExtra("grid", "http://192.168.1.200/Mr.Gill/public/uploads/"+o.getString("picture"));
                        intent.putExtra("text",o.getString("name"));
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    context.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
        return array.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.dis_img);
            textView = itemView.findViewById(R.id.cat_text);
        }
    }
}
