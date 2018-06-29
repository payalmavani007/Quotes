package quotes.pro.sau.quotes;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kaopiz.kprogresshud.KProgressHUD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */

public class UploadCategoryFragment extends Fragment {
    TextView txt_category;
    Button btn_upload;
    EditText editText;
    ListView lv,lvdot;
    TextView txt_id;
    ImageView dot_logout;
    LinearLayout cat_layout;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_upload_category, container, false);
        editText = view.findViewById(R.id.write_quotes);
        txt_id = view.findViewById(R.id.cat_id);
        cat_layout = view.findViewById(R.id.cat_layout);

        //------------------------------------------------Logout----------------------------------------//

        dot_logout = view.findViewById(R.id.dot_logout);
        dot_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name[] = {"Logout"};

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.listdot);
                lvdot = dialog.findViewById(R.id.lvdot);
                dialog.setCancelable(true);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, name);
                lvdot.setAdapter(adapter);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                wmlp.gravity = Gravity.TOP | Gravity.RIGHT;
                wmlp.x = 10;   //x position
                wmlp.y = 0;
                dialog.show();
                lvdot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        SharedPreferences preferences = getContext().getSharedPreferences("status", MODE_PRIVATE);

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear().apply();
                        Toast.makeText(getContext(), "clear"+
                                editor, Toast.LENGTH_SHORT).show();
                        assert getFragmentManager() != null;
                        FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                        Fragment fragment2 = new UserLoginFragment();
                        fragmentTransaction1.replace(R.id.fragment_container, new UserLoginFragment()).addToBackStack(null).commit();

                        dialog.dismiss();
                    }
                });
            }
        });

//---------------------Select Category-------------------------------------//

        txt_category = view.findViewById(R.id.upload_category);
        cat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final KProgressHUD hud = KProgressHUD.create(getContext())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.list1);
                lv = dialog.findViewById(R.id.lv);
                dialog.setCancelable(true);

                String url = "http://192.168.1.200/quotesmanagement/getdata_categories";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Dialog Responce", response);
                        try {
                            JSONObject resp = new JSONObject(response);
                            SharedPreferences preferences = getContext().getSharedPreferences("status", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();

                            if (resp.getInt("status") == 0) {
                                JSONArray data = resp.getJSONArray("data");


                                hud.dismiss();
                                final String[] stringArray = new String[data.length()];
                                final int[] intArray = new int[data.length()];
                                for (int i = 0, count = data.length(); i < count; i++) {
                                    try {
                                        JSONObject object = (JSONObject) data.get(i);
                                        String jsonString = object.getString("category_name");
                                        stringArray[i] = jsonString;
                                        int id = object.getInt("id");
                                        Log.e("id of category", String.valueOf(id));
                                        intArray[i] = id;

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, stringArray);
                                lv.setAdapter(adapter);
                                dialog.show();
                                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        TextView textView = (TextView) view;
                                         txt_category.setText(stringArray[i]);
                                        txt_id.setText(String.valueOf(id));
                                        Log.e("dfgdsg",String.valueOf(id));
                                        //txt_id.setText(String.valueOf(intArray[i]));
                                        dialog.dismiss();
                                    }
                                });

                            }
                            else
                                {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return new HashMap<>();
                    }
                };
                Volley.newRequestQueue(getContext()).add(stringRequest);

            }
        });

//-----------------------------Upload Quotes-----------------------------//

        btn_upload = view.findViewById(R.id.upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (txt_category.getText().toString().equals("Upload Category")){
                    Toast.makeText(getContext(), "Select Category.", Toast.LENGTH_SHORT).show();
                }
                else if (editText.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Write your Quotes.", Toast.LENGTH_SHORT).show();
                }
                else
                    {

                        String url = "http://192.168.1.200/quotesmanagement/upload_category?category_id="
                                +txt_id.getText().toString()+
                                "&quotes="+editText.getText().toString();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject resp = new JSONObject(response);
                                    if (resp.getInt("status") == 0){
                                        JSONArray data = resp.getJSONArray("data");
                                        JSONObject object = (JSONObject) data.get(0);
                                        SharedPreferences preferences = getContext().getSharedPreferences("status", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("id", object.getString("id"));
                                        editor.putString("quotes_name", object.getString("quotes_name"));
                                        editor.putString("categorymanagement_id", object.getString("categorymanagement_id")).apply();
                                        Toast.makeText(getContext(), "Insert Successfull.", Toast.LENGTH_SHORT).show();
                                        FragmentTransaction ft=getFragmentManager().beginTransaction();
                                        HomeFragment homeFragment=new HomeFragment();
                                        ft.replace(R.id.fragment_container,homeFragment).addToBackStack( "tag" ).commit();
                                    }
                                    else {

                                        Toast.makeText(getContext(), "Data Not Inserted.", Toast.LENGTH_SHORT).show();
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
            }
        });
        return  view;
    }
    }


