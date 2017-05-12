package fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hitek.serial.R;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import SQL.DataBaseHelper;
import SQL.SqlManager;
import adapter.AlarmRecordRecycleViewAdapter;
import adapter.RecyclerViewAdapter;
import bean.AlarmRecordData;
import bean.DoubleDatePickerDialog;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zuheng.lv on 2016/6/11.
 */
public class AlarmRecordFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private static final String TAG = "AlarmRecordFragment";
    private static final int REFRESH = 1;
    private View view;
    private AlarmRecordRecycleViewAdapter alarmRecordRecycleViewAdapter;
    private RecyclerView alarmrecord_recycle;
    private TextView alarmrecord_startdate, alarmrecord_starttime, alarmrecord_stopdate, alarmrecord_stoptime;
    private Button alarmrecord_btn_search;
    private List<AlarmRecordData> list;
    private SqlManager sqlManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager;
    private int i = 0;
    private int j = 20;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH:
                    linearLayoutManager = new LinearLayoutManager(getContext());
                    alarmrecord_recycle.setLayoutManager(linearLayoutManager);
                    alarmRecordRecycleViewAdapter = new AlarmRecordRecycleViewAdapter(getContext(), list);
                    alarmrecord_recycle.setAdapter(alarmRecordRecycleViewAdapter);
                    break;

            }
            return false;
        }
    });
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alarmrecord_layout, container, false);
        //XXX初始化view的各控件
        initView();
        //填充各控件的数据
        initData();


//        initData();
        return view;
    }


    public void initView() {

        sqlManager = new SqlManager(getContext(), "history.db", null, 1);
        alarmrecord_recycle = (RecyclerView) view.findViewById(R.id.alarmrecord_recycle);
        alarmrecord_recycle.setHasFixedSize(true);
        alarmrecord_startdate = (TextView) view.findViewById(R.id.alarmrecord_startdate);
//        alarmrecord_starttime = (TextView)view.findViewById(R.id.alarmrecord_starttime);
        alarmrecord_stopdate = (TextView) view.findViewById(R.id.alarmrecord_stopdate);
//        alarmrecord_stoptime = (TextView)view.findViewById(R.id.alarmrecord_stoptime);
        alarmrecord_btn_search = (Button) view.findViewById(R.id.alarmrecord_btn_search);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        alarmrecord_btn_search.setOnClickListener(this);
    }

    public void initData() {
        final Message obtain = Message.obtain();
        list = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                list = sqlManager.searchAlarmRecord(null, null, null, null, null, "alarmID desc", i + "," + j);
                obtain.what = REFRESH;
                handler.sendMessage(obtain);
            }
        }).start();

//        list = sqlManager.searchAlarmRecord(null, null, null, null, null, "alarmID desc");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(), "history.db", null, 1);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor1 = sqLiteDatabase.query("alarm", new String[]{"date"}, null, null, null, null, "alarmID", "1");
        Cursor cursor2 = sqLiteDatabase.query("alarm", new String[]{"date"}, null, null, null, null, "alarmID desc", "1");
        Log.d(TAG, "刷新init");
        if (cursor1.moveToFirst()) {
//            alarmrecord_startdate.setText(cursor2.getString(0));

        }
        if (cursor2.moveToFirst()) {
//            System.out.println(cursor2.getString(1));
            alarmrecord_startdate.setText(cursor2.getString(0));
            alarmrecord_stopdate.setText(cursor2.getString(0));
        }
        //下拉刷新
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                i = 0;
                j = 20;
                initData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //上拉加载
        alarmrecord_recycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == alarmRecordRecycleViewAdapter.getItemCount()) {

                    i += 20;
                    j += 20;
                    loadData(i, j);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void loadData(final int ID1, final int ID2) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = sqlManager.searchAlarmRecord(null, null, null, null, null, "alarmID desc", ID1 + "," + ID2);
            }
        }).start();
        alarmRecordRecycleViewAdapter.setData(list);
    }

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        new DoubleDatePickerDialog(getContext(), 0, new DoubleDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                  int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
                                  int endDayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String startDate = simpleDateFormat.format(simpleDateFormat.parse(startYear + "-" + (startMonthOfYear + 1) + "-" + startDayOfMonth));
                    String endDate = simpleDateFormat.format(simpleDateFormat.parse(endYear + "-" + (endMonthOfYear + 1) + "-" + endDayOfMonth));

                    list.clear();
//                    list = new SqlManager(getContext(), "history.db", null, 1).searchAlarmRecord(null, "date BETWEEN ? AND ?", new String[]{startDate, endDate}, null, null, "historyID desc");
                    AlarmRecordRecycleViewAdapter alarmRecordRecycleViewAdapter = new AlarmRecordRecycleViewAdapter(getContext(), list);
                    alarmrecord_recycle.setAdapter(alarmRecordRecycleViewAdapter);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();
    }


}
