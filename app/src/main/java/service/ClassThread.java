package service;

import android.util.Log;

import SQL.SqlManager;
import activity.MyApplication;
import utils.Constants;
import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/2.
 */
public class ClassThread {
    private static final String TAG ="ClassThread" ;
    private SqlManager sqlManager;
    private String[] alarmstr = {"报警产生", "报警产生", "报警产生", "报警产生"};
    private int[] adress = {10, 10, 16,16};
    private String[] unit = {"MPa", "MPa", "°C", "°C"};
    private String[] explain = {"压力上限报警", "压力下限报警", "温度上限报警", "温度下限报警"};

    private int[] id = new int[6];

    private boolean[] flag = {true, true, true, true, true, true};

    public ClassThread(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public void saveAlarmRecord() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    String[] str = ReadAndWrite.ReadJni(Constants.Define.OP_BIT_M, new int[]{12, 13, 14, 15});
                    int[] limits={12,14,18,20};
                    for (int i = 0; i < str.length; i++) {
                        if (str[i].equals("1") ) {

                            String[] data = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D, new int[]{adress[i]});
                            //String.valueOf((float) Math.round( Float.parseFloat(strings[5]) * 100) / 100)

                            String[] limit = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,limits);
                            id[i] = sqlManager.insertAlarmRecord(alarmstr[i],String.valueOf((float) Math.round( Float.parseFloat( data[0]) * 100) / 100) + unit[i], explain[i],limit[i]+unit[i]);
                        }
//                        if (str[i].equals("0") && flag[i] == false) {
//                            sqlManager.upDateAlarmRecord(id[i]);
//                            flag[i] = true;
//                        }
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
