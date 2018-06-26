package quotes.pro.sau.quotes;


import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kaopiz.kprogresshud.KProgressHUD;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */

public class UserLoginFragment extends Fragment {
    EditText edt_email;
    Button  btn_login;
    TextView txt_reghere;
    TextInputEditText  edt_password;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    FragmentManager fm ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user_login, container, false);
        edt_email = view.findViewById(R.id.email);
        fm  = getActivity().getSupportFragmentManager();
        setHasOptionsMenu(true);
        edt_password = view.findViewById(R.id.password);
        txt_reghere = view.findViewById(R.id.register_here);
        txt_reghere.setPaintFlags(txt_reghere.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_reghere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ft=getFragmentManager().beginTransaction();
                RegisterFragment registerFragment=new RegisterFragment();
                ft.replace(R.id.fragment_container,registerFragment).addToBackStack( "tag" ).commit();
            }
        });
        btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (edt_email.getText().toString().equals("")  )
               {
                   //edt_email.setError("Enter Email.");
                   Toast.makeText(getContext(), "Enter Email Address.", Toast.LENGTH_SHORT).show();
               }
               else if (edt_password.getText().toString().equals(""))
               {
                   Toast.makeText(getContext(), "Enter Password", Toast.LENGTH_SHORT).show();

               }
               else if (!Patterns.EMAIL_ADDRESS.matcher(edt_email.getText().toString()).matches())
               {
                   //edt_email.setError("Enter Valid E-mail.");
                   Toast.makeText(getContext(), "Enter Valid E-mail.", Toast.LENGTH_SHORT).show();
               }

               else {
                   String url = "http://192.168.1.200/quotesmanagement/user_login?"+"email="+edt_email.getText().toString()+
                           "&password="+edt_password.getText().toString();
                   final KProgressHUD hud = KProgressHUD.create(getContext())
                           .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                           .setCancellable(false)
                           .setAnimationSpeed(2)
                           .setDimAmount(0.5f)
                           .show();
                   Log.e("login url",url);
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
                                   editor.putString("firstname", object.getString("firstname"));
                                   editor.putString("lastname", object.getString("lastname"));
                                   editor.putString("email", edt_email.getText().toString());
                                   editor.putString("password", edt_password.getText().toString());
                                   editor.putString("contact",  object.getString("contact")).apply();

                                   hud.dismiss();
                                   FragmentTransaction ft=getFragmentManager().beginTransaction();

                                   UploadCategoryFragment uploadCategoryFragment=new UploadCategoryFragment();
                                   ft.replace(R.id.fragment_container,uploadCategoryFragment)
                                           .commit();


                               }
                               else {

                                   hud.dismiss();
                                   Toast.makeText(getContext(), "Invalid login", Toast.LENGTH_SHORT).show();
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
            fragmentTransaction1.replace(R.id.fragment_container, new UserLoginFragment()).commit();
   /*         fragmentTransaction1.setPrimaryNavigationFragment(fragment2);
            fragmentTransaction1.setReorderingAllowed(true);
            fragmentTransaction1.commitNowAllowingStateLoss();*/

            return true;
            // add your action here that you want

        }

        return super.onOptionsItemSelected(item);
    }
}
