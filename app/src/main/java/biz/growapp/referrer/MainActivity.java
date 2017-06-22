package biz.growapp.referrer;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observer;

import static biz.growapp.referrer.R.id.btnPrefs;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUrl;
    private TextView tvPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tvVersionCode)).setText("Version Code " + BuildConfig.VERSION_CODE);

        tvUrl = (TextView) findViewById(R.id.text);
        tvPrefs = (TextView) findViewById(R.id.tvPrefs);
        findViewById(R.id.btnPrefs).setOnClickListener(this);
        findViewById(R.id.btnInstalledFrom).setOnClickListener(this);

        App.rxBus.observeEvents().subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                tvUrl.setText(o.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnPrefs) {
            SharedPreferences preferences = getSharedPreferences(InstallReferrerReceiver.MY_PREFS, MODE_PRIVATE);
            String referrer = preferences.getString(InstallReferrerReceiver.REFERRER_KEY, "not today");
            tvPrefs.setText(referrer);
        } else if (v.getId() == R.id.btnInstalledFrom) {
            Toast.makeText(this, getInstaller(), Toast.LENGTH_LONG).show();
        }
    }

    private String getInstaller() {
        PackageManager pm = getPackageManager();
        return pm.getInstallerPackageName(getPackageName());
    }
}
