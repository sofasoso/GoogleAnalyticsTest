package sofasoso.gatest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by mac on 16/5/18.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private Button bt_GA1,bt_GA2,bt_GA3;
    public GoogleAnalytics mGoogleAnalytics;
    //Google Analytics
    public Tracker mTracker;
    //Google Analytics Tracker
    HitBuilders.EventBuilder mEventBuilder = new HitBuilders.EventBuilder();;
    //Google Analytics Tracker Event Info
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        setView();
        initGaTracker();
    }

    private void setView(){
        bt_GA1 = (Button)findViewById(R.id.bt_GA1);
        bt_GA2 = (Button)findViewById(R.id.bt_GA2);
        bt_GA3 = (Button)findViewById(R.id.bt_GA3);
        bt_GA1.setOnClickListener(this);
        bt_GA2.setOnClickListener(this);
        bt_GA3.setOnClickListener(this);
    }

    //Google Analytics 初始化
    private void initGaTracker(){
        mGoogleAnalytics = GoogleAnalytics.getInstance(this);
        //註冊Google Analytics
        mGoogleAnalytics.setLocalDispatchPeriod(10);
        //設定回傳週期 單位：秒
        mTracker = mGoogleAnalytics.newTracker("UA-53300998-2");
        //Tracker追蹤專案編號 需至Google Analytics查詢
        mTracker.enableExceptionReporting(true);
        //App exceotion追蹤
        mTracker.enableAdvertisingIdCollection(true);
        //App 廣告資訊收集(如果沒有使用AdWords可以關閉)
        mTracker.enableAutoActivityTracking(true);
        //App 追蹤用戶在何種頁面
    }

    //傳送Tracker Event至Google Analytics
    public void sendTrackerByEvent(String strEventCategory , String strEventAction , String strEventLabel){
        mTracker.send(mEventBuilder
                //Event分類
                .setCategory(strEventCategory)
                //Event行為
                .setAction(strEventAction)
                //Event標籤
                .setLabel(strEventLabel)
                .build());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_GA1:
                sendTrackerByEvent("Button", "Click", "submitBT11");
                break;
            case R.id.bt_GA2:
                sendTrackerByEvent("Button", "Click", "submitBT12");
                break;
            case R.id.bt_GA3:
                sendTrackerByEvent("Button", "Click", "submitBT13");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
        //Google Analytics 用戶啟動時候會傳送報告
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
        //Google Analytics 用戶停止時候會傳送報告
    }


}
