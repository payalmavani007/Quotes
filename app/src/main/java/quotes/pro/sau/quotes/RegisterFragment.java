package quotes.pro.sau.quotes;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterFragment extends Fragment {
    TextView fname,lname,email,num;
    Button btn;
    TextInputEditText pwd;
    FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    private RadioButton radioButtong;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        fname =  view.findViewById(R.id.fname);
        lname =  view.findViewById(R.id.lname);
        email =  view.findViewById(R.id.email);
        pwd =  view.findViewById(R.id.password);

        num =  view.findViewById(R.id.num);

        btn = view.findViewById(R.id.btn_register);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup =  view.findViewById(R.id.gender);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButtong =  view.findViewById(selectedId);
               /* Toast.makeText(getContext(),
                        radioButtong.getText(), Toast.LENGTH_SHORT).show();*/
                if (fname.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Enter First Name.", Toast.LENGTH_SHORT).show();
                }
                else  if (lname.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Enter Last Name", Toast.LENGTH_SHORT).show();

                }
                else  if (email.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();

                }
                else  if (pwd.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Enter Password", Toast.LENGTH_SHORT).show();

                }
                else  if ( num.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Enter Contact Number.", Toast.LENGTH_SHORT).show();

                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                {
                    Toast.makeText(getContext(), "Enter Valid E-mail.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = "http://192.168.1.200/quotesmanagement/form_register?"+
                            "firstname="+ fname.getText().toString()+
                            "&lastname="+lname.getText().toString()+
                            "&email="+email.getText().toString()+
                            "&password="+pwd.getText().toString()+
                            "&gender="+radioButtong.getText().toString()+
                            "&contact="+num.getText().toString();
                    final KProgressHUD hud = KProgressHUD.create(getContext())
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setCancellable(false)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();
                    Log.e("Registration url",url);
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
                                    editor.putString("name", object.getString("firstname"));
                                    editor.putString("email", object.getString("lastname"));
                                    editor.putString("password",object.getString("email"));
                                    editor.putString("gender",  object.getString("password"));
                                    editor.putString("birthdate", object.getString("contact")).apply();
                                    hud.dismiss();
                                    Toast.makeText(getContext(), "Register Successfull.", Toast.LENGTH_SHORT).show();
                                    FragmentTransaction ft=getFragmentManager().beginTransaction();
                                    UserLoginFragment userLoginFragment=new UserLoginFragment();
                                    ft.replace(R.id.fragment_container,userLoginFragment).addToBackStack( "tag" ).commit();
                                }
                                else {

                                    hud.dismiss();
                                    Toast.makeText(getContext(), "Invalid register", Toast.LENGTH_SHORT).show();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Registration");

    }



}
