package fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hitek.serial.R;

import java.util.ArrayList;
import java.util.List;

import activity.MyApplication;
import popupwindow.Pupwindow;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.Constants;
import utils.ReadAndWrite;
import utils.Utils;
import view.AnimotionSGView;

/**
 * Created by shuang.xiang on 2016/8/11.
 */
public class AnimationFragment extends Fragment {

    private static final String TAG = "AnimationFragment";
    private static final String TEST = "test";
    private AnimotionSGView sganimotion;
    private View view;
    private TextView tvnowtemperature;
    private TextView tvan2;
    private TextView tvan9;
    private TextView tvan6;
    private TextView tvan8;
    private TextView tvan4;
    private TextView tvan7;
    private TextView tvan10;
    private TextView tvnowpressure;
    private TextView tvan5;
    private DrawerLayout drawerLayout;
    private TextView tvan3;
    private TextView tvanimationsetting;
    private TextView tvan1;
    private int[] ints;
    private LinearLayout ll_anDrawer;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private CheckBox cb7;
    private CheckBox cb8;
    private CheckBox cb9;
    private CheckBox cb10;
    private ImageView iv;
    public AnimationDrawable animationDrawable;
    private String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
    private String[] allRead;
    private LinearLayout llAniInfo;
    private TextView tvAniInfo;
    private String[] alarmInfo = new String[]{"压力上限报警   ", "压力下限报警   ", "温度上限报警   ", "温度下限报警   "};
    private StringBuffer stringBuffer;
    private boolean et_flag;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private boolean frag_animation = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.animation_fragment, container, false);
        initialize();
        //XXX初始化view的各控件
        initData();
        initAnimation();
        rwCheckbox();
        write();
        return view;
    }

    private void write() {
        final int[] adds = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb1.isChecked()) {
                    openOrClose(true, adds[0]);
                } else {
                    openOrClose(false, adds[0]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb2.isChecked()) {
                    openOrClose(true, adds[1]);
                } else {
                    openOrClose(false, adds[1]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb3.isChecked()) {
                    openOrClose(true, adds[2]);
                } else {
                    openOrClose(false, adds[2]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb4.isChecked()) {
                    openOrClose(true, adds[3]);
                } else {
                    openOrClose(false, adds[3]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb5.isChecked()) {
                    openOrClose(true, adds[4]);
                } else {
                    openOrClose(false, adds[4]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb6.isChecked()) {
                    openOrClose(true, adds[5]);
                } else {
                    openOrClose(false, adds[5]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb7.isChecked()) {
                    openOrClose(true, adds[6]);
                } else {
                    openOrClose(false, adds[6]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb8.isChecked()) {
                    openOrClose(true, adds[7]);
                } else {
                    openOrClose(false, adds[7]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb9.isChecked()) {
                    openOrClose(true, adds[8]);
                } else {
                    openOrClose(false, adds[8]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
        cb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_flag = false;
                if (cb10.isChecked()) {
                    openOrClose(true, adds[9]);
                } else {
                    openOrClose(false, adds[9]);
                }
                SystemClock.sleep(1500);
                et_flag = true;
                rwCheckbox();
            }
        });
    }

    private void openOrClose(boolean b, int add) {
        int i = b ? 1 : 0;
        ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{add}, new String[]{String.valueOf(i)});

    }

    /**
     * 设置按钮的控制
     */
    private void rwCheckbox() {
        Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                int[] intsCk = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
                while (et_flag) {
                    String[] strings = ReadAndWrite.ReadJni(Constants.Define.OP_WORD_D, intsCk);


                    subscriber.onNext(strings);
                    SystemClock.sleep(1000);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final String[] strings) {
                allRead = strings;
                if (strings[0].equals("1") && s1 != strings[0]) {
                    cb1.setChecked(true);
                } else if (strings[0].equals("0") && s1 != strings[0]) {
                    cb1.setChecked(false);
                }
                if (strings[1].equals("1") && s2 != strings[1]) {
                    cb2.setChecked(true);
                } else if (strings[1].equals("0") && s2 != strings[1]) {
                    cb2.setChecked(false);
                }
                if (strings[2].equals("1") && s3 != strings[2]) {
                    cb3.setChecked(true);
                } else if (strings[2].equals("0") && s3 != strings[2]) {
                    cb3.setChecked(false);
                }
                if (strings[3].equals("1") && s4 != strings[3]) {
                    cb4.setChecked(true);
                } else if (strings[3].equals("0") && s4 != strings[3]) {
                    cb4.setChecked(false);
                }
                if (strings[4].equals("1") && s5 != strings[4]) {
                    cb5.setChecked(true);
                } else if (strings[4].equals("0") && s5 != strings[4]) {
                    cb5.setChecked(false);
                }
                if (strings[5].equals("1") && s6 != strings[5]) {
                    cb6.setChecked(true);
                } else if (strings[5].equals("0") && s6 != strings[5]) {
                    cb6.setChecked(false);
                }
                if (strings[6].equals("1") && s7 != strings[6]) {
                    cb7.setChecked(true);
                } else if (strings[6].equals("0") && s7 != strings[6]) {
                    cb7.setChecked(false);
                }
                if (strings[7].equals("1") && s8 != strings[7]) {
                    cb8.setChecked(true);
                } else if (strings[7].equals("0") && s8 != strings[7]) {
                    cb8.setChecked(false);
                }
                if (strings[8].equals("1") && s9 != strings[8]) {
                    cb9.setChecked(true);
                } else if (strings[8].equals("0") && s9 != strings[8]) {
                    cb9.setChecked(false);
                }
                if (strings[9].equals("1") && s10 != strings[9]) {
                    cb10.setChecked(true);
                } else if (strings[9].equals("0") && s10 != strings[9]) {
                    cb10.setChecked(false);
                }
            }
        });


     /*   cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s1 != allRead[0]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{0}, new String[]{"1"});
                        if (i != -1) {
                            s1 = "1";
                        }
                    } else if (!isChecked && s1 != allRead[0]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{0}, new String[]{"0"});
                        if (i != -1) {
                            s1 = "0";
                        }
                    }
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s2 != allRead[1]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{1}, new String[]{"1"});
                        if (i != -1) {
                            s2 = "1";
                        }
                    } else if (!isChecked && s2 != allRead[1]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{1}, new String[]{"0"});
                        if (i != -1) {
                            s2 = "0";
                        }
                    }
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s3 != allRead[2]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{2}, new String[]{"1"});
                        if (i != -1) {
                            s3 = "1";
                        }
                    } else if (!isChecked && s3 != allRead[2]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{2}, new String[]{"0"});
                        if (i != -1) {
                            s3 = "0";
                        }
                    }
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s4 != allRead[3]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{3}, new String[]{"1"});
                        if (i != -1) {
                            s4 = "1";
                        }
                    } else if (!isChecked && s2 != allRead[3]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{3}, new String[]{"0"});
                        if (i != -1) {
                            s4 = "0";
                        }
                    }
                }
            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s5 != allRead[4]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{4}, new String[]{"1"});
                        if (i != -1) {
                            s5 = "1";
                        }
                    } else if (!isChecked && s5 != allRead[4]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{4}, new String[]{"0"});
                        if (i != -1) {
                            s5 = "0";
                        }
                    }
                }
            }
        });
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s6 != allRead[5]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{5}, new String[]{"1"});
                        if (i != -1) {
                            s6 = "1";
                        }
                    } else if (!isChecked && s6 != allRead[5]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{5}, new String[]{"0"});
                        if (i != -1) {
                            s6 = "0";
                        }
                    }
                }
            }
        });
        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s7 != allRead[6]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{6}, new String[]{"1"});
                        if (i != -1) {
                            s7 = "1";
                        }
                    } else if (!isChecked && s7 != allRead[6]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{6}, new String[]{"0"});
                        if (i != -1) {
                            s7 = "0";
                        }
                    }
                }
            }
        });
        cb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s8 != allRead[7]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{7}, new String[]{"1"});
                        if (i != -1) {
                            s8 = "1";
                        }
                    } else if (!isChecked && s8 != allRead[7]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{7}, new String[]{"0"});
                        if (i != -1) {
                            s8 = "0";
                        }
                    }
                }
            }
        });
        cb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s9 != allRead[8]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{8}, new String[]{"1"});
                        if (i != -1) {
                            s9 = "1";
                        }
                    } else if (!isChecked && s9 != allRead[8]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{8}, new String[]{"0"});
                        if (i != -1) {
                            s9 = "0";
                        }
                    }
                }
            }
        });
        cb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Utils.isValidClick()) {
                    if (isChecked && s10 != allRead[9]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{9}, new String[]{"1"});
                        if (i != -1) {
                            s10 = "1";
                        }
                    } else if (!isChecked && s10 != allRead[9]) {
                        int i = ReadAndWrite.WriteJni(Constants.Define.OP_WORD_D, new int[]{9}, new String[]{"0"});
                        if (i != -1) {
                            s10 = "0";
                        }
                    }
                }
            }
        });*/


    }

    /**
     * 读取数据判断动画是否开动
     */
    private void initAnimation() {
        Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {

                int[] intsReadM = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15};
                while (frag_animation) {
                    String[] strings = ReadAndWrite.ReadJni(Constants.Define.OP_BIT_M, intsReadM);

                    subscriber.onNext(strings);
                    SystemClock.sleep(2000);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String[]>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String[] strings) {
                stringBuffer = new StringBuffer();
                if (strings[10].equals("1")) {
                    stringBuffer.append(alarmInfo[0]);
                }
                if (strings[11].equals("1")) {
                    stringBuffer.append(alarmInfo[1]);
                }
                if (strings[12].equals("1")) {
                    stringBuffer.append(alarmInfo[2]);
                }
                if (strings[13].equals("1")) {
                    stringBuffer.append(alarmInfo[3]);
                }
                if (stringBuffer.length() > 0) {
                    //有报警信息
                    llAniInfo.setVisibility(View.VISIBLE);
                    tvAniInfo.setText(stringBuffer.toString());
                } else if (stringBuffer.length() == 0) {
                    llAniInfo.setVisibility(View.INVISIBLE);
                }


                boolean arrive = sganimotion.isArrive();
                if (arrive) {
                    animationDrawable.start();
                    animationDrawable.setOneShot(false);
                } else {
                    animationDrawable.stop();
                }


                int length = strings.length;
                if (strings != null && length > 0) {
                    //M1
                    if (strings[0].equals("1")) {
                        sganimotion.openCircle(1);
                        Log.d(TAG, strings[0] + "{{{{{{{{{{{{{{{{{{{{{{{{{");
                    } else if (strings[0].equals("0")) {
                        sganimotion.stopCircle(1);
                    }
                    //M2
                    if (strings[1].equals("1")) {
                        sganimotion.openCircle(2);
                    } else if (strings[1].equals("0")) {
                        sganimotion.stopCircle(2);
                    }
                    //M3
                    if (strings[2].equals("1")) {
                        sganimotion.openCircle(3);
                    } else if (strings[2].equals("0")) {
                        sganimotion.stopCircle(3);
                    }
                    //M5
                    if (strings[4].equals("1")) {
                        sganimotion.openCircle(4);
                    } else if (strings[4].equals("0")) {
                        sganimotion.stopCircle(4);
                    }
                    //M6
                    if (strings[5].equals("1")) {
                        sganimotion.openCircle(5);
                    } else if (strings[5].equals("0")) {
                        sganimotion.stopCircle(5);
                    }
                    //M7
                    if (strings[6].equals("1")) {
                        sganimotion.openCircle(7);
                    } else if (strings[6].equals("0")) {
                        sganimotion.stopCircle(7);
                    }
                    //M8
                    if (strings[7].equals("1")) {
                        sganimotion.openCircle(6);
                    } else if (strings[7].equals("0")) {
                        sganimotion.stopCircle(6);
                    }
                    //M9
                    if (strings[8].equals("1")) {
                        sganimotion.openCircle(8);
                    } else if (strings[8].equals("0")) {
                        sganimotion.stopCircle(8);
                    }
                    //M10
                    if (strings[9].equals("1")) {
                        sganimotion.openCircle(9);
                    } else if (strings[9].equals("0")) {
                        sganimotion.stopCircle(9);
                    }
                }
            }
        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                frag_animation = false;
                sganimotion.stopCircle(6);
                sganimotion.stopCircle(8);
                sganimotion.stopCircle(9);


            }

            @Override
            public void onDrawerClosed(View drawerView) {
                frag_animation = true;
                initAnimation();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    /**
     * 读写数据
     */
    private void initData() {
        Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {

                ints = new int[]{10, 12, 14, 16, 18, 20};
                while (true) {
                    String[] strings = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D, ints);
                    subscriber.onNext(strings);
                    SystemClock.sleep(2000);
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String[] strings) {

                        int length = strings.length;
                        if (strings != null && length > 0) {
                            //String.valueOf((float) Math.round(v * 100) / 100)
                            tvnowpressure.setText(String.valueOf((float) Math.round(Float.parseFloat(strings[0]) * 100) / 100));
                            tvnowtemperature.setText(String.valueOf((float) Math.round(Float.parseFloat(strings[3]) * 100) / 100));
                            tvan1.setText(String.valueOf((float) Math.round(Float.parseFloat(strings[1]) * 100) / 100));
                            tvan4.setText(String.valueOf((float) Math.round(Float.parseFloat(strings[2]) * 100) / 100));
                            tvan7.setText(String.valueOf((float) Math.round(Float.parseFloat(strings[4]) * 100) / 100));
                            tvan8.setText(String.valueOf((float) Math.round(Float.parseFloat(strings[5]) * 100) / 100));
                        }

                    }
                });
        Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {


                int[] ints2 = new int[]{22, 23, 24, 25, 26, 27, 28};
                while (true) {

                    String[] strings2 = ReadAndWrite.ReadJni(Constants.Define.OP_WORD_D, ints2);

                    subscriber.onNext(strings2);
                    SystemClock.sleep(2000);
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String[] strings) {
                        tvan2.setText(strings[0]);
                        tvan3.setText(strings[1]);
                        tvan5.setText(strings[2]);
                        tvan6.setText(strings[3]);
                        tvan9.setText(strings[4]);
                        tvan10.setText(strings[5]);
                    }
                });
        showPpREAL(tvan1, 12);
        showPpREAL(tvan4, 14);
        showPpREAL(tvan7, 18);
        showPpREAL(tvan8, 20);
        showPpWORD(tvan2, 22);
        showPpWORD(tvan5, 24);

    }

    /**
     * 显示弹窗
     */
    private void showPpREAL(TextView tv, final int i) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] writeInts = {i};
                new Pupwindow(getActivity()).showPopupWindow(null, Constants.Define.OP_REAL_D, writeInts);
            }
        });
    }

    private void showPpWORD(TextView tv, final int i) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] writeInts = {i};
                new Pupwindow(getActivity()).showPopupWindow(null, Constants.Define.OP_WORD_D, writeInts);
            }
        });
    }

    private void initialize() {
        iv = (ImageView) view.findViewById(R.id.iv_animation_boiling);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        sganimotion = (AnimotionSGView) view.findViewById(R.id.sg_animotion);
        tvnowtemperature = (TextView) view.findViewById(R.id.tv_now_temperature);
        tvan2 = (TextView) view.findViewById(R.id.tv_an2);
        tvan9 = (TextView) view.findViewById(R.id.tv_an9);
        tvan6 = (TextView) view.findViewById(R.id.tv_an6);
        tvan8 = (TextView) view.findViewById(R.id.tv_an8);
        tvan4 = (TextView) view.findViewById(R.id.tv_an4);
        tvan7 = (TextView) view.findViewById(R.id.tv_an7);
        tvan10 = (TextView) view.findViewById(R.id.tv_an10);
        tvnowpressure = (TextView) view.findViewById(R.id.tv_now_pressure);
        tvan5 = (TextView) view.findViewById(R.id.tv_an5);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.dl_animation);
        tvan3 = (TextView) view.findViewById(R.id.tv_an3);
        tvanimationsetting = (TextView) view.findViewById(R.id.tv_animation_setting);
        tvan1 = (TextView) view.findViewById(R.id.tv_an1);

        cb1 = (CheckBox) view.findViewById(R.id.cb_setting1);
        cb2 = (CheckBox) view.findViewById(R.id.cb_setting2);
        cb3 = (CheckBox) view.findViewById(R.id.cb_setting3);
        cb4 = (CheckBox) view.findViewById(R.id.cb_setting4);
        cb5 = (CheckBox) view.findViewById(R.id.cb_setting5);
        cb6 = (CheckBox) view.findViewById(R.id.cb_setting6);
        cb7 = (CheckBox) view.findViewById(R.id.cb_setting7);
        cb8 = (CheckBox) view.findViewById(R.id.cb_setting8);
        cb9 = (CheckBox) view.findViewById(R.id.cb_setting9);
        cb10 = (CheckBox) view.findViewById(R.id.cb_setting10);
        llAniInfo = (LinearLayout) view.findViewById(R.id.ll_animation_alarmInfo);
        tvAniInfo = (TextView) view.findViewById(R.id.tv_animation_alarmInfo);
        sganimotion.start();
        tvanimationsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(ll_anDrawer);
            }
        });
        sganimotion.setZOrderOnTop(true);
        sganimotion.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        ll_anDrawer = (LinearLayout) view.findViewById(R.id.ll_anDrawer);


    }


    @Override
    public void onPause() {
        super.onPause();
        et_flag = false;
    }


}
