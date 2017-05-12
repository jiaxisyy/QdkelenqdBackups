package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.AlarmRecordData;
import bean.HistoryData;
import utils.Constants;
import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/2.
 */
public class SqlManager extends DataBaseHelper {

    private SQLiteDatabase db;
    String[] datatime =ReadAndWrite.ReadJni(Constants.Define.OP_WORD_D,new int[]{610,611,612,613,614,615});
//    private String[] datatime=new String[]{"15","03","02","15","30","20"};

    public SqlManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = getWritableDatabase();
    }
    public List<AlarmRecordData> searchAlarmRecord(String[] columns,String selection, String[] selectionArgs,String groupBy, String having, String orderBy,String limit){
//        Cursor cursor =  db.query("alarm",null,null,null,null,null,"alarmID desc");
        Cursor cursor =   db.query("alarm",columns,selection,selectionArgs,groupBy,having,orderBy,limit);

        List<AlarmRecordData> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                AlarmRecordData alarmRecordData = new AlarmRecordData();
                //ID
                alarmRecordData.setKeyId(cursor.getInt(0));
                //日期
                alarmRecordData.setDate(cursor.getString(1));
                //时间
                alarmRecordData.setIncident(cursor.getString(2));
                //类型
                alarmRecordData.setType(cursor.getString(3));
                //报警值
                alarmRecordData.setData(cursor.getString(4));
                //报警说明
                alarmRecordData.setExplain(cursor.getString(5));
                list.add(alarmRecordData);
            }while (cursor.moveToNext());
        }
        return list;
    }
    /**
     * 存储报警记录
     */
    public int insertAlarmRecord(String type,String data,String explain,String limit ){

        ContentValues values = new ContentValues();


        values.put("date", datatime[0]+"-"+datatime[1]+"-"+datatime[2]+"  "+datatime[3]+":"+datatime[4]+":"+datatime[5]);
        values.put("incident",type);
        values.put("now",data);
        values.put("range",limit);
        values.put("explain",explain);
        db.insert("alarm",null,values);
        Cursor cursor =  db.query("alarm",new String[]{"alarmID"},null,null,null,null,"alarmID desc","1");
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        return -1;
    }
//    public void upDateAlarmRecord(int ID){
//        ContentValues values = new ContentValues();
//        String[] datatime =ReadAndWrite.ReadJni(Constants.Define.OP_WORD_D,new int[]{610,611,612,613,614,615});
//        values.put("cancel",datatime[0]+"-"+datatime[1]+"-"+datatime[2]+"\n"+datatime[3]+":"+datatime[4]+":"+datatime[5]);
//        db.update("alarm",values,"alarmID =?",new String[]{String.valueOf(ID)});
//    }
}
