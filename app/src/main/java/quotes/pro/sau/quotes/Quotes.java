package quotes.pro.sau.quotes;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class Quotes  extends Application {
      private static Quotes mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/bauhaus.ttf");
    }

    public static synchronized Quotes getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
