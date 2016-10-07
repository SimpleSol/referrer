package biz.growapp.referrer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class InstallReferrerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("referrer");
        Toast.makeText(context, url, Toast.LENGTH_LONG).show();
        Prefs.edit().putString(Prefs.PREFS, url).apply();
        App.rxBus.send(url);
        File file;
        FileOutputStream outputStream;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "qqqqq");
            outputStream = new FileOutputStream(file);
            outputStream.write(url.getBytes());
            outputStream.close();
        } catch (Exception ignored) {}
    }

}
