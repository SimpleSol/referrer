package biz.growapp.referrer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class InstallReferrerReceiver extends BroadcastReceiver {

    public static final String REFERRER_KEY = "referrer_key";
    public static final String MY_PREFS = "my_prefs_111";

    @Override
    public void onReceive(Context context, Intent intent) {
        String referrer = intent.getStringExtra("referrer");

        Toast.makeText(context, referrer, Toast.LENGTH_LONG).show();

        SharedPreferences preferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        preferences.edit().putString(REFERRER_KEY, referrer).apply();

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
