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

class RecyclarAdapter extends RecyclerView.Adapter<RecyclarAdapter.ViewHolder> {

    Context context;
    JSONArray array;
    String id;


    public RecyclarAdapter(Context context, JSONArray array,String id) {
        this.context = context;
        this.array = array;
        this.id = id;

    }
    public RecyclarAdapter(Context context, JSONArray array) {
        this.context = context;
        this.array = array;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            final JSONObject o = array.getJSONObject(position);
            Picasso.get().load("http://192.168.1.200/quotesmanagement/public/uploads/" + o.getString("quotes_image")).into(holder.imageView);
            //  holder.textView.setText(o.getString("quotes"));

            String upperString = o.getString("quotes").substring(0, 1).toUpperCase() + o.getString("quotes").substring(1);
            holder.textView.setText(upperString);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Preview.class);

                    try {
                        intent.putExtra("quotes_image", "http://192.168.1.200/quotesmanagement/public/uploads/" + o.getString("quotes_image"));
                        intent.putExtra("quotes", o.getString("quotes"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.startActivity(intent);
                 /*   Intent intent = new Intent(context, SwipeDeckActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);*/
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return array.length();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.bcgrnd_img);
        }
    }


}

