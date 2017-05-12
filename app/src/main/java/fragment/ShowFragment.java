package fragment;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitek.serial.R;

import java.util.Random;

import utils.Constants;
import utils.ReadAndWrite;
import view.DrawChart;

/**
 * Created by shuang.xiang on 2016/8/11.
 */
public class ShowFragment extends Fragment {

    private static final String TAG = "ShowFragment";
    private View view;
    private boolean flag = true;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.show_fragment, container, false);
        //XXX初始化view的各控件
        init();

        return view;
    }

    private void init() {
        final DrawChart dc = (DrawChart) view.findViewById(R.id.dc_show);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    String[] data = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D, new int[]{16, 10});
                    if (data != null && data.length > 0) {
                        String sub = data[0].substring(0, data[0].indexOf("."));
                        dc.setY(Integer.parseInt(sub));
                        float i = Float.parseFloat(data[1]) / 3;

                        float v = i * 150;

                        String s = String.valueOf(v);

                        String substring = s.substring(0, s.indexOf("."));

                        dc.setOtherY(Integer.parseInt(substring));
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = false;
    }


}
