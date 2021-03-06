package quotes.pro.sau.quotes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import quotes.pro.sau.quotes.util.Config;
import quotes.pro.sau.quotes.util.NotificationUtils;


public class BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ConnectivityReceiver.ConnectivityReceiverListener {
    private static final String TAG = BottomNavigation.class.getSimpleName();
    KProgressHUD hud;
    Toolbar toolbar;
    MenuItem item;
    FloatingActionButton fab;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window w = getWindow();
                w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    checkConnection();
                    fragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.fragment_container, new HomeFragment()).commit();

                    return true;
                case R.id.navigation_category:
                    checkConnection();
                    fragment = new CategoryFragment();
                    fragmentTransaction.replace(R.id.fragment_container, new CategoryFragment()).commit();
                    return true;
                case R.id.navigation_author:
                    checkConnection();
                    fragment = new AuthorFragment();
                    fragmentTransaction.replace(R.id.fragment_container, new CategoryFragment()).commit();
                    return true;
            }
            return loadFragment(fragment);
        }
    };


    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rootContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);

        return true;
    }

    *//*  SharedPreferences preferences = getApplicationContext().getSharedPreferences("status", MODE_PRIVATE);
      SharedPreferences.Editor editor = preferences.edit();*//*

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout) {
            //       editor.clear().apply();
            Log.e("sdbnfvids", "vfszdjkhv");
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("status", MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.clear().apply();
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            Fragment fragment2 = new UserLoginFragment();
            fragmentTransaction1.replace(R.id.fragment_container, new UserLoginFragment()).commit();

            return true;
            // add your action here that you want

        }

        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
      /*  toolbar = (Toolbar) findViewById(R.id.toolbarNavigation);*/
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.navigation_home);


        /*fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "onCreate: " + refreshedToken);

        BroadCastRecever();
    }

    private void BroadCastRecever() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    // txtMessage.setText(message);
                }
            }
        };
        displayFirebaseRegId();
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getSharedPreferences(Config.SHARED_PREF, MODE_PRIVATE);
        String regId = pref.getString("regId", "");

        Log.e(TAG, "Firebase reg id: " + regId);

       /* if (!TextUtils.isEmpty(regId))
           // txtRegId.setText("Firebase Reg Id: " + regId);
        else
          //  txtRegId.setText("Firebase Reg Id is not received yet!");*/
    }


    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }


    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "";

            color = Color.WHITE;
            Log.e("good", message);
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
            Log.e("responce", "done" + message);
            android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar
                    .make(findViewById(R.id.container), message, android.support.design.widget.Snackbar.LENGTH_LONG);

            android.view.View sbView = snackbar.getView();
            android.widget.TextView textView = (android.widget.TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
            Log.e("sorry", message);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
        NotificationUtils.clearNotifications(getApplicationContext());


        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
        Quotes.getInstance().setConnectivityListener(this);

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        final Fragment[] fragment = {null};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(R.color.colorAccent);  }
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.navigation_home:
                checkConnection();
             /*   setSupportActionBar(toolbar);*/
         /*       getSupportActionBar().setTitle("Home");*/
                fragment[0] = new HomeFragment();
                fragmentTransaction.replace(R.id.fragment_container, new HomeFragment()).commit();
                return true;
            case R.id.navigation_category:
                checkConnection();
             /*   setSupportActionBar(toolbar);*/
            /*    getSupportActionBar().setTitle("Category");*/
                fragment[0] = new CategoryFragment();
                fragmentTransaction.replace(R.id.fragment_container, new CategoryFragment()).commit();
                return true;
            case R.id.navigation_author:
                checkConnection();
               /* setSupportActionBar(toolbar);*/
    /*            getSupportActionBar().setTitle("Author");*/
                fragment[0] = new AuthorFragment();
                fragmentTransaction.replace(R.id.fragment_container, new AuthorFragment()).commit();
                return true;
            case R.id.navigation_userlogin:
                FragmentManager fm = getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                checkConnection();
                /*fragment = new UserLoginFragment();
                fragmentTransaction.replace(R.id.fragment_container, new UserLoginFragment()).commit();
                return true;*/


                SharedPreferences preferences = BottomNavigation.this.getSharedPreferences("status", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (preferences.contains("email") && preferences.contains("password")) {
                    String url = "http://rajviinfotech.in/quotes/user_login?email=" + preferences.getString("email", "") + "&password=" + preferences.getString("password", "");


                        /* hud = KProgressHUD.create(BottomNavigation.this)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                .setCancellable(false)
                                .setAnimationSpeed(2)
                                .setDimAmount(0.5f)
                                .show();
*/
                    Log.e("login url", url);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("fgsdgb", response);
                            try {
                                JSONObject resp = new JSONObject(response);
                                if (resp.getInt("status") == 0) {
                                    JSONArray data = resp.getJSONArray("data");
                                    JSONObject object = (JSONObject) data.get(0);

                                    // hud.dismiss();
                                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("status", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    preferences.getString("email", "email");
                                    preferences.getString("password", "password");
                                    editor.apply();
                                    fragment[0] = new UploadCategoryFragment();
                                    fragmentTransaction.replace(R.id.fragment_container, new UploadCategoryFragment()).commit();


                                } else {

                                    Fragment fragment1 = new UserLoginFragment();
                                    fragmentTransaction.replace(R.id.fragment_container, new UserLoginFragment()).addToBackStack("tag").commit();
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

                    Volley.newRequestQueue(BottomNavigation.this).add(stringRequest);

                } else {
                    setSupportActionBar(toolbar);

                    Fragment fragment4 = new UserLoginFragment();
                    fragmentTransaction.replace(R.id.fragment_container, new UserLoginFragment()).addToBackStack("tag").commit();
                    return true;
                }
        }

        return true;

    }
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            final Dialog dialog = new Dialog(BottomNavigation.this);

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.exit_dialog, null);
            dialog.setContentView(view);

            dialog.show();

            Button yes = dialog.findViewById(R.id.exit_yes);
            Button no = dialog.findViewById(R.id.exit_no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onBackPressed();
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

        }
        return true;
    }*/

}

