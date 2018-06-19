package quotes.pro.sau.quotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
         android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
      @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        Quotes.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "";
            color = android.graphics.Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = android.graphics.Color.RED;
        }

        android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar
                .make(findViewById(R.id.coordinator_layout), message, android.support.design.widget.Snackbar.LENGTH_LONG);

        android.view.View sbView = snackbar.getView();
        android.widget.TextView textView = (android.widget.TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
}
