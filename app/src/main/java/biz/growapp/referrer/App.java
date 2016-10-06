package biz.growapp.referrer;

import android.app.Application;

public class App extends Application {

    public static RxBus rxBus;

    @Override
    public void onCreate() {
        super.onCreate();
        rxBus = new RxBus();
        Prefs.init(this);
    }
}
