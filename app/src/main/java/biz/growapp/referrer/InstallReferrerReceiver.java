package biz.growapp.referrer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InstallReferrerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("referrer");
        Toast.makeText(context, url, Toast.LENGTH_LONG).show();
        Prefs.edit().putString(Prefs.PREFS, url).apply();
        App.rxBus.send(url);
    }

}
