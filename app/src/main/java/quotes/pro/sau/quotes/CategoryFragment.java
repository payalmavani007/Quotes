package quotes.pro.sau.quotes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


public class CategoryFragment extends Fragment {
    RecyclerView category_recycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        category_recycler=view.findViewById(R.id.category_list);
        setHasOptionsMenu(true);
        category_recycler.setLayoutManager(new GridLayoutManager(getContext(),3));

        String url="http://rajviinfotech.in/quotes/getdata_categories";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("response ",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getInt("status")==0)
                    {
                        JSONArray dataAry=jsonObject.getJSONArray("data");
                        CategoryAdapter Adapter=new CategoryAdapter(getContext(),dataAry,getFragmentManager());
                        category_recycler.setAdapter(Adapter);

                    }else {
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
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Category");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings, menu);
        super.onCreateOptionsMenu(menu,inflater);

        if (!getContext().getSharedPreferences("status", MODE_PRIVATE).contains("id"))
            menu.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            //       editor.clear().apply();
            SharedPreferences preferences = getContext().getSharedPreferences("status", MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.clear().apply();
            FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
            Fragment fragment2 = new UserLoginFragment();
            fragmentTransaction1.replace(R.id.fragment_container, new UserLoginFragment()).addToBackStack("tag").commit();

            return true;
            // add your action here that you want

        }

        return super.onOptionsItemSelected(item);
    }
}
