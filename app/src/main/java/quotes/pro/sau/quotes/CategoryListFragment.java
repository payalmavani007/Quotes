package quotes.pro.sau.quotes;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */

public class CategoryListFragment extends Fragment {
    RecyclerView recyclar;
    String id,category_name;
    TextView header_text_categorylist;
    private static final String TAG = "CategoryListFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        recyclar = view.findViewById(R.id.selected_category_list);
        header_text_categorylist = view.findViewById(R.id.header_text_categorylist);

        recyclar.setLayoutManager(new GridLayoutManager(getContext(), 1));
        Bundle b = getArguments();
        id = b.getString("name");
        Bundle b1 = getArguments();

        assert b1 != null;
        category_name = b1.getString("category_name");
        header_text_categorylist.setText(category_name);
        Log.e(TAG, "onCreateView: "+ category_name);

        String url = "http://192.168.1.200/quotesmanagement/selected_CategoryData?category_id=" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 0) {
                        JSONArray dataAry = jsonObject.getJSONArray("data");
                        CatListAdapter recyclarAdapter = new CatListAdapter(getContext(), dataAry, id);
                        recyclar.setAdapter(recyclarAdapter);
                    } else {
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
    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
       /* Objects.requireNonNull(getActivity()).setTitle(category_name);
        Log.e(TAG, "onViewCreated: ");*/
    }
}
