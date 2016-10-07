package biz.growapp.referrer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class InstallReferrerReceiver extends BroadcastReceiver {

    public static final String REFERRER_KEY = "referrer_key";

    @Override
    public void onReceive(Context context, Intent intent) {
        String referrer = intent.getStringExtra("referrer");
        Toast.makeText(context, referrer, Toast.LENGTH_LONG).show();
//        Prefs.edit().putString(Prefs.PREFS, referrer).apply();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(REFERRER_KEY, referrer).commit();
        App.rxBus.send(referrer);
        File file;
        FileOutputStream outputStream;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "qqqqq");
            outputStream = new FileOutputStream(file);
            outputStream.write(referrer.getBytes());
            outputStream.close();
        } catch (Exception ignored) {}
    }

}
