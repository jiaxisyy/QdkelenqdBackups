package activity;

import android.content.Intent;
import android.util.Log;

import com.hitek.serialJNI.serialJNI;

/**
 * Created by shuang.xiang on 2016/4/27.
 */
public class MyApplication extends android.app.Application {
    private static final String TAG = "MyApplication";
    private static serialJNI serial = null;

    public static serialJNI getInstance() {
        if (serial == null) {
            synchronized (MyApplication.class) {
                if (serial == null) {
                    serial = new serialJNI();
                }
            }
        }
        return serial;
    }

    public MyApplication() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        boolean init = getInstance().init();
        Log.d(TAG, "init=" + init);
        new Thread(new Runnable() {

            @Override
            public void run() {
                getInstance().mainloop();
            }
        }).start();
//        instance = this;
//        Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程  以下用来捕获程序崩溃异常

    }

   protected static MyApplication instance;
//
//    // 创建服务用于捕获崩溃异常
//    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
//        public void uncaughtException(Thread thread, Throwable ex) {
//            restartApp();//发生崩溃异常时,重启应用
//        }
//    };

    public void restartApp() {
        Intent intent = new Intent(instance, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        instance.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
    }
}
