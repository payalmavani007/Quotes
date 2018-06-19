package quotes.pro.sau.quotes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class HomeFragment extends Fragment {
    RecyclerView recyclar;
    RecyclarAdapter recyclarAdapter;

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

            recyclar=view.findViewById(R.id.recyclar);
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This quotes.pro.sau.quotes.app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            } else {
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

        }
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
                    Manifest.permission.INTERNET)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Need Internet Permission");
                builder.setMessage("This quotes.pro.sau.quotes.app needs internet permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{
                                Manifest.permission.INTERNET},1);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            } else {
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.INTERNET}, 1);
            }

        }
                recyclar.setLayoutManager(new GridLayoutManager(getContext(),1));

        Bundle b1=getArguments();
        Bundle b=getArguments();



        if (b1 != null) {
           /* if (b.containsKey("name")){*/
            final String str=b1.getString("name");
            final String id = b.getString("id");
            Toast.makeText(getContext(), "search string "+b1.get("name"), Toast.LENGTH_SHORT).show();
            final String searchurl = "http://192.168.1.200/quotesmanagement/Search?search_bit=0&id=1"+"&text="+ str;
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, searchurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.e("response ",response);
                    Log.e("url ",searchurl);

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        if (jsonObject.getInt("status")==0)
                        {
                            JSONArray dataAry=jsonObject.getJSONArray("data");
                            recyclarAdapter=new RecyclarAdapter(getContext(),dataAry,id);
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
            Volley.newRequestQueue(getContext()).add(stringRequest1);


            }else {


                final String url = "http://192.168.1.200/quotesmanagement/list_quotes";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("response ", response);
                        Log.e("final url ", url);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status") == 0) {
                                JSONArray dataAry = jsonObject.getJSONArray("data");
                                recyclarAdapter = new RecyclarAdapter(getContext(), dataAry);
                                recyclar.setAdapter(recyclarAdapter);
                            } else
                                {
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
            }

        return  view;
    }

   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
   {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");

    }
}
