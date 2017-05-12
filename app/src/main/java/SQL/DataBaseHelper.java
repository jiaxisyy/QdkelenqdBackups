package SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zuheng.lv on 2016/6/1.
 */
public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //报警记录的数据库
        db.execSQL("Create TABLE alarm (alarmID integer primary key autoincrement ," +
                "date  DATETIME default null," +
                "incident  varchar(20)," +
                "now varchar(20)," +
                "range varchar(20)," +
                "explain varchar(20)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
