package quotes.pro.sau.quotes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorListFragment extends Fragment {
    RecyclerView recyclar;
    TextView textView;
    String quotes_name;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_author_list, container, false);
        recyclar=view.findViewById(R.id.selected_author_list);
        textView = view.findViewById(R.id.header_text_authorlist);
        recyclar.setLayoutManager(new GridLayoutManager(getContext(),1));
        Bundle b=getArguments();
        final String id=b.getString("name");

        Bundle b1=getArguments();
        assert b1 != null;
         quotes_name=b1.getString("author_name");
        textView.setText(quotes_name);
        String url="http://192.168.1.200/quotesmanagement/selected_AuthorData?author_id="+id ;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("response ",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getInt("status")==0)
                    {
                        JSONArray dataAry=jsonObject.getJSONArray("data");
                        AutListAdapter recyclarAdapter=new AutListAdapter(getContext(),dataAry, id);
                        recyclar.setAdapter(recyclarAdapter);
                    }
                    else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
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
        Volley.newRequestQueue(getContext()).add(stringRequest);
        return view;
    }

}
