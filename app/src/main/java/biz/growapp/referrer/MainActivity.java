package biz.growapp.referrer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUrl;
    private TextView tvPrefs;
    private Button btnPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tvVersionCode)).setText("Version Code " + BuildConfig.VERSION_CODE);

        tvUrl = (TextView) findViewById(R.id.text);
        tvPrefs = (TextView) findViewById(R.id.tvPrefs);
        btnPrefs = (Button) findViewById(R.id.btnPrefs);
        btnPrefs.setOnClickListener(this);

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
        if (v.getId() == R.id.btnPrefs) {
            tvPrefs.setText(Prefs.get().getString(Prefs.PREFS, "not today"));
        }
    }
}
