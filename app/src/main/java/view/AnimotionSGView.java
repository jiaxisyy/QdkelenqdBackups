package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hitek.serial.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fragment.AnimationFragment;

/**
 * Created by zuheng.lv on 2016/8/12.
 */
public class AnimotionSGView extends SurfaceView implements SurfaceHolder.Callback {

    private double mWeight;
    private double mHeight;
    private SurfaceHolder holder;

    private Circle circle1;
    private Circle circle2;
    private Circle circle3;
    private Circle circle4;
    private Circle circle5;
    private Circle circle6;
    private Circle circle8;
    private Circle circle9;

    private List<Circle> circleList1;
    private List<Circle> circleList2;
    private List<Circle> circleList3;
    private List<Circle> circleList4;
    private List<Circle> circleList5;
    private List<Circle> circleList6;
    private List<Circle> circleList8;
    public List<Circle> circleList9;

    private boolean STATUS_CIRCLE1 = false;
    private boolean STATUS_CIRCLE2 = false;
    private boolean STATUS_CIRCLE3 = false;
    private boolean STATUS_CIRCLE4 = false;
    private boolean STATUS_CIRCLE5 = false;
    private boolean STATUS_CIRCLE6 = false;
    private boolean STATUS_CIRCLE7 = false;
    private boolean STATUS_CIRCLE8 = false;
    private boolean STATUS_CIRCLE9 = false;

    private DrawThread drawThread;
    private CircleThread1 thread1;
    private CircleThread2 thread2;
    private CircleThread3 thread3;
    private CircleThread4 thread4;
    private CircleThread5 thread5;
    private CircleThread6 thread6;
    private CircleThread8 thread8;
    private CircleThread9 thread9;

    private int bornFlag = 0;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private int COLOR_RED = 0;
    private int COLOR_BLUE = 1;
    private int COLOR_ORANGE = 2;


    private boolean threadFlag = true;
    private int timesleep = 90;
    private AnimationDrawable animationDrawable;
    private boolean isArrive = false;
    private Bitmap bitmap;
    private Context context;
    private Rect rect;
    public boolean frag_drawer = false;


    public AnimotionSGView(Context context) {

        super(context);
        this.context = context;
        initData();
    }

    public AnimotionSGView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        initData();
    }

    public AnimotionSGView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        this.context = context;
        initData();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //在创建时激发，一般在这里调用画图的线程。

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //在surface的大小发生改变时激发
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //销毁时激发，一般在这里将画图的线程停止、释放。
    }

    public void initData() {
//

//        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.animotion_bg)).getBitmap();
//        rect = new Rect(0, 0,670,520);
//        Canvas canvas = new Canvas();
//        canvas.drawBitmap(bitmap, null, rect, null);


        drawThread = new DrawThread();
        thread1 = new CircleThread1();
        thread2 = new CircleThread2();
        thread3 = new CircleThread3();
        thread4 = new CircleThread4();
        thread5 = new CircleThread5();
        thread6 = new CircleThread6();
        thread8 = new CircleThread8();
        thread9 = new CircleThread9();

        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        mHeight = dm2.heightPixels;
        mWeight = dm2.widthPixels;
//        setZOrderOnTop(true);
//        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder = this.getHolder();
        //surface回调
        holder.addCallback(this);
        setZOrderOnTop(true);


        circleList1 = new Vector<>();
        circleList2 = new Vector<>();
        circleList3 = new Vector<>();
        circleList4 = new Vector<>();
        circleList5 = new Vector<>();
        circleList6 = new Vector<>();
        circleList8 = new Vector<>();
        circleList9 = new Vector<>();

        mPaint1 = new Paint();
        mPaint1.setColor(Color.WHITE);
        mPaint1.setAlpha(90);
        mPaint1.setAntiAlias(true);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.BLUE);
        mPaint2.setARGB(90, 26, 20, 249);
        mPaint2.setAlpha(90);
        mPaint2.setAntiAlias(true);

        mPaint3 = new Paint();
        mPaint3.setARGB(90, 232, 101, 31);
        mPaint3.setAlpha(90);
        mPaint3.setAntiAlias(true);

    }

    public void born(int i) {
        switch (i) {
            case 0:
                circle1 = new Circle();
                circle1.setX(mWeight * 0.06);
                circle1.setY(mHeight * 0.145);
                circle1.setFlag(0);
                circle1.setLINE(true);
                circle1.setColor(0);
                circleList1.add(circle1);
                break;
            case 1:
                circle2 = new Circle();
                circle2.setX(mWeight * 0.08);
                circle2.setY(mHeight * 0.24);
                circle2.setFlag(0);
                circle2.setLINE(true);
                circle2.setColor(1);
                circleList2.add(circle2);
                break;
            case 2:
                circle3 = new Circle();
                circle3.setX(mWeight * 0.1);
                circle3.setY(mHeight * 0.335);
                circle3.setFlag(0);
                circle3.setLINE(true);
                circle3.setColor(1);
                circleList3.add(circle3);
                break;
            case 3:
                circle4 = new Circle();
                circle4.setX(mWeight * 0.285);
                circle4.setY(mHeight * 0.42);
                circle4.setFlag(0);
                circle4.setLINE(true);
                circle4.setColor(1);
                circleList4.add(circle4);
                break;
            case 4:
                circle5 = new Circle();
                circle5.setX(mWeight * 0.39);
                circle5.setY(mHeight * 0.375);
                circle5.setFlag(0);
                circle5.setLINE(true);
                circle5.setColor(1);
                circleList5.add(circle5);
                break;
            case 5:
                circle6 = new Circle();
                circle6.setX(mWeight * 0.775);
                circle6.setY(mHeight * 0.42);
                circle6.setFlag(0);
                circle6.setLINE(true);
                circle6.setColor(1);
                circleList6.add(circle6);
                break;
            case 7:
                circle8 = new Circle();
                circle8.setX(mWeight * 0.58);
                circle8.setY(mHeight * 0.475);
                circle8.setFlag(0);
                circle8.setLINE(true);
                circle8.setColor(1);
                circleList8.add(circle8);
                break;
            case 8:
                circle9 = new Circle();
                circle9.setX(mWeight * 0.59);
                circle9.setY(mHeight * 0.595);
                circle9.setFlag(0);
                circle9.setLINE(true);
                circle9.setColor(1);
                circleList9.add(circle9);
                break;
        }
    }

    private void drawUiCircle1(Canvas canvas) {
        for (int i = 0; i < circleList1.size(); i++) {
            if (!((circleList1.get(i).getX() > mWeight * 0.16 && circleList1.get(i).getX() < mWeight * 0.20) && ((circleList1.get(i).getY() > mHeight * 0.1 && circleList1.get(i).getY() < mHeight * 0.16) || (circleList1.get(i).getY() > mHeight * 0.35 && circleList1.get(i).getY() < mHeight * 0.39)))) {
                canvas.drawCircle((float) circleList1.get(i).getX(), (float) circleList1.get(i).getY(), (float) (mWeight * 0.004), mPaint3);
            }
        }
    }

    private void drawUiCircle2(Canvas canvas) {
        for (int i = 0; i < circleList2.size(); i++) {
            if (!((circleList2.get(i).getX() > mWeight * 0.16 && circleList2.get(i).getX() < mWeight * 0.20) && (circleList2.get(i).getY() > mHeight * 0.35 && circleList2.get(i).getY() < mHeight * 0.39))) {
                canvas.drawCircle((float) circleList2.get(i).getX(), (float) circleList2.get(i).getY(), (float) (mWeight * 0.004), mPaint2);
            }
        }
    }

    private void drawUiCircle3(Canvas canvas) {
        for (int i = 0; i < circleList3.size(); i++) {
            if (!((circleList3.get(i).getX() > mWeight * 0.16 && circleList3.get(i).getX() < mWeight * 0.20) && (circleList3.get(i).getY() > mHeight * 0.35 && circleList3.get(i).getY() < mHeight * 0.39))) {
                canvas.drawCircle((float) circleList3.get(i).getX(), (float) circleList3.get(i).getY(), (float) (mWeight * 0.004), mPaint2);
            }
        }
    }

    private void drawUiCircle4(Canvas canvas) {
        for (int i = 0; i < circleList4.size(); i++) {
            if (!((circleList4.get(i).getX() > mWeight * 0.265 && circleList4.get(i).getX() < mWeight * 0.3) && (circleList4.get(i).getY() > mHeight * 0.575 && circleList4.get(i).getY() < mHeight * 0.61))) {
                canvas.drawCircle((float) circleList4.get(i).getX(), (float) circleList4.get(i).getY(), (float) (mWeight * 0.004), mPaint2);
            }

        }
    }

    private void drawUiCircle5(Canvas canvas) {
        for (int i = 0; i < circleList5.size(); i++) {
            if (!(((circleList5.get(i).getX() > mWeight * 0.48 && circleList5.get(i).getX() < mWeight * 0.52)
                    || (circleList5.get(i).getX() > mWeight * 0.42 && circleList5.get(i).getX() < mWeight * 0.43)
                    || (circleList5.get(i).getX() > mWeight * 0.32 && circleList5.get(i).getX() < mWeight * 0.36))
                    && ((circleList5.get(i).getY() > mHeight * 0.3 && circleList5.get(i).getY() < mHeight * 0.46)
                    || (circleList5.get(i).getY() > mHeight * 0.57 && circleList5.get(i).getY() < mHeight * 0.62)))) {
                canvas.drawCircle((float) circleList5.get(i).getX(), (float) circleList5.get(i).getY(), (float) (mWeight * 0.004), mPaint2);
            }

        }
    }

    private void drawUiCircle6(Canvas canvas) {
        for (int i = 0; i < circleList6.size(); i++) {


            if (!((((circleList6.get(i).getX() > mWeight * 0.755 && circleList6.get(i).getX() < mWeight * 0.79) || (circleList6.get(i).getX() > mWeight * 0.65 && circleList6.get(i).getX() < mWeight * 0.68)) && (circleList6.get(i).getY() > mHeight * 0.26 && circleList6.get(i).getY() < mHeight * 0.29))
                    || ((circleList6.get(i).getX() > mWeight * 0.63 && circleList6.get(i).getX() < mWeight * 0.68) && (circleList6.get(i).getY() > mHeight * 0.34 && circleList6.get(i).getY() < mHeight * 0.4))
                    || ((circleList6.get(i).getX() > mWeight * 0.48 && circleList6.get(i).getX() < mWeight * 0.53) && (circleList6.get(i).getY() > mHeight * 0.3 && circleList6.get(i).getY() < mHeight * 0.46))
                    || ((circleList6.get(i).getX() > mWeight * 0.55 && circleList6.get(i).getX() < mWeight * 0.58) && (circleList6.get(i).getY() > mHeight * 0.39 && circleList6.get(i).getY() < mHeight * 0.43))
            )) {
                canvas.drawCircle((float) circleList6.get(i).getX(), (float) circleList6.get(i).getY(), (float) (mWeight * 0.004), mPaint2);
            }

        }
    }

    private void drawUiCircle8(Canvas canvas) {
        for (int i = 0; i < circleList8.size(); i++) {
            canvas.drawCircle((float) circleList8.get(i).getX(), (float) circleList8.get(i).getY(), (float) (mWeight * 0.004), mPaint2);

        }
    }

    private void drawUiCircle9(Canvas canvas) {
        for (int i = 0; i < circleList9.size(); i++) {
            canvas.drawCircle((float) circleList9.get(i).getX(), (float) circleList9.get(i).getY(), (float) (mWeight * 0.004), mPaint2);

        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * */
    class DrawThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    Canvas canvas = holder.lockCanvas();
                    if (canvas != null) {
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//                        canvas.drawBitmap(bitmap, null, rect, null);
                        drawUiCircle1(canvas);
                        drawUiCircle2(canvas);
                        drawUiCircle3(canvas);
                        drawUiCircle4(canvas);
                        drawUiCircle5(canvas);
                        drawUiCircle6(canvas);
                        drawUiCircle8(canvas);
                        drawUiCircle9(canvas);
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
                SystemClock.sleep(90);
            }

        }
    }


    class CircleThread1 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE1) {
                        if (circleList1.get(circleList1.size() - 1).getX() >= mWeight * 0.12) {
                            born(0);
                        }
                    }
                    loopCircle1();
                }
                SystemClock.sleep(timesleep);
            }

        }
    }

    class CircleThread2 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE1 && STATUS_CIRCLE2) {
                        if (circleList1.get(circleList1.size() - 1).getX() >= mWeight * 0.12) {
                            born(1);
                        }
                    } else if (!STATUS_CIRCLE1 && STATUS_CIRCLE2) {
                        if (circleList2.get(circleList2.size() - 1).getX() >= mWeight * 0.12) {
                            born(1);
                        }
                    }
                    loopCircle2();
                }
                SystemClock.sleep(timesleep);
            }
        }
    }

    class CircleThread3 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE3) {
                        if (circleList3.get(circleList3.size() - 1).getX() >= mWeight * 0.14) {
                            born(2);
                        }
                    }
                    loopCircle3();
                }
                SystemClock.sleep(timesleep);
            }
        }
    }

    class CircleThread4 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE4) {
                        if (circleList4.get(circleList4.size() - 1).getY() >= mHeight * 0.48) {
                            born(3);
                        }
                    }
                    loopCircle4();
                }
                SystemClock.sleep(timesleep);
            }
        }
    }

    class CircleThread5 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE5) {
                        if (circleList5.get(circleList5.size() - 1).getX() >= mWeight * 0.42) {
                            born(4);
                        }
                    }
                    loopCircle5();
                }
                SystemClock.sleep(timesleep);
            }
        }
    }

    class CircleThread6 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE6) {
                        if (circleList6.get(circleList6.size() - 1).getY() <= mHeight * 0.4) {
                            born(5);
                        }
                    }
                    loopCircle6();
                }
                SystemClock.sleep(timesleep);
            }
        }
    }

    class CircleThread8 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE8) {
                        if (circleList8.get(circleList8.size() - 1).getX() >= mWeight * 0.62) {
                            born(7);
                        }
                    }
                    loopCircle8();
                }
                SystemClock.sleep(timesleep);
            }
        }
    }

    class CircleThread9 extends Thread {
        @Override
        public void run() {
            super.run();
            while (threadFlag) {
                synchronized (holder) {
                    if (STATUS_CIRCLE9) {
                        if (circleList9.get(circleList9.size() - 1).getX() >= mWeight * 0.62) {
                            born(8);
                        }
                    }
                    loopCircle9();
                }
                SystemClock.sleep(timesleep);
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     *
     *
     * */
    private void loopCircle1() {
        for (int i = 0; i < circleList1.size(); i++) {
            switch (circleList1.get(i).getFlag()) {
                case 0:
                    if (circleList1.get(i).getX() >= 0.178 * mWeight) {
                        circleList1.get(i).setFlag(1);
                    } else {
                        if (circleList1.get(i).getDric() == 0) {
                            circleList1.get(i).setY(circleList1.get(i).getY() + mWeight * 0.002);
                            circleList1.get(i).setDric(1);
                        } else {
                            circleList1.get(i).setY(circleList1.get(i).getY() - mWeight * 0.002);
                            circleList1.get(i).setDric(0);
                        }
                        circleList1.get(i).setX(circleList1.get(i).getX() + mWeight * 0.005);
                    }
                    break;
                case 1:
                    if (circleList1.get(i).getY() >= 0.37 * mHeight) {
                        circleList1.get(i).setFlag(2);
                        circleList1.get(i).setColor(1);
                    } else {
                        if (circleList1.get(i).getDric() == 0) {
                            circleList1.get(i).setX(circleList1.get(i).getX() + mWeight * 0.002);
                            circleList1.get(i).setDric(1);
                        } else {
                            circleList1.get(i).setX(circleList1.get(i).getX() - mWeight * 0.002);
                            circleList1.get(i).setDric(0);
                        }
                        circleList1.get(i).setY(circleList1.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 2:
                    if (circleList1.get(i).getX() >= mWeight * 0.23) {

                        circleList1.remove(i);
                    } else {
                        if (circleList1.get(i).getDric() == 0) {
                            circleList1.get(i).setY(circleList1.get(i).getY() + mWeight * 0.002);
                            circleList1.get(i).setDric(1);
                        } else {
                            circleList1.get(i).setY(circleList1.get(i).getY() - mWeight * 0.002);
                            circleList1.get(i).setDric(0);
                        }
                        circleList1.get(i).setX(circleList1.get(i).getX() + mWeight * 0.005);
                    }
                    break;
            }
        }
    }


    private void loopCircle2() {
        for (int i = 0; i < circleList2.size(); i++) {
            switch (circleList2.get(i).getFlag()) {
                case 0:
                    if (circleList2.get(i).getX() >= 0.178 * mWeight) {
                        circleList2.get(i).setFlag(1);
                    } else {
                        if (circleList2.get(i).getDric() == 0) {
                            circleList2.get(i).setY(circleList2.get(i).getY() - mWeight * 0.002);
                            circleList2.get(i).setDric(1);
                        } else {

                            circleList2.get(i).setY(circleList2.get(i).getY() + mWeight * 0.002);
                            circleList2.get(i).setDric(0);
                        }
                        circleList2.get(i).setX(circleList2.get(i).getX() + mWeight * 0.005);
                    }
                    break;
                case 1:
                    if (circleList2.get(i).getY() >= 0.37 * mHeight) {
                        circleList2.get(i).setFlag(2);
                        circleList2.get(i).setColor(2);
                    } else {
                        if (circleList2.get(i).getDric() == 0) {
                            circleList2.get(i).setX(circleList2.get(i).getX() + mWeight * 0.002);
                            circleList2.get(i).setDric(1);
                        } else {
                            circleList2.get(i).setX(circleList2.get(i).getX() - mWeight * 0.002);
                            circleList2.get(i).setDric(0);
                        }
                        circleList2.get(i).setY(circleList2.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 2:
                    if (circleList2.get(i).getX() >= 0.23 * mWeight) {
                        circleList2.remove(i);
                    } else {
                        if (circleList2.get(i).getDric() == 0) {
                            circleList2.get(i).setY(circleList2.get(i).getY() + mWeight * 0.002);
                            circleList2.get(i).setDric(1);
                        } else {
                            circleList2.get(i).setY(circleList2.get(i).getY() - mWeight * 0.002);
                            circleList2.get(i).setDric(0);
                        }
                        circleList2.get(i).setX(circleList2.get(i).getX() + mWeight * 0.005);
                    }
                    break;
            }
        }
    }

    private void loopCircle3() {
        for (int i = 0; i < circleList3.size(); i++) {
            switch (circleList3.get(i).getFlag()) {
                case 0:
                    if (circleList3.get(i).getX() >= 0.178 * mWeight) {
                        circleList3.get(i).setFlag(1);
                    } else {
                        if (circleList3.get(i).getDric() == 0) {
                            circleList3.get(i).setY(circleList3.get(i).getY() + mWeight * 0.002);
                            circleList3.get(i).setDric(1);
                        } else {
                            circleList3.get(i).setY(circleList3.get(i).getY() - mWeight * 0.002);
                            circleList3.get(i).setDric(0);
                        }
                        circleList3.get(i).setX(circleList3.get(i).getX() + mWeight * 0.005);
                    }
                    break;
                case 1:
                    if (circleList3.get(i).getY() >= 0.37 * mHeight) {
                        circleList3.get(i).setFlag(2);
                        circleList3.get(i).setColor(1);
                    } else {
                        if (circleList3.get(i).getDric() == 0) {
                            circleList3.get(i).setX(circleList3.get(i).getX() + mWeight * 0.002);
                            circleList3.get(i).setDric(1);
                        } else {
                            circleList3.get(i).setX(circleList3.get(i).getX() - mWeight * 0.002);
                            circleList3.get(i).setDric(0);
                        }
                        circleList3.get(i).setY(circleList3.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 2:
                    if (circleList3.get(i).getX() >= 0.23 * mWeight) {
                        circleList3.remove(i);
                        isArrive = true;
                    } else {
                        if (circleList3.get(i).getDric() == 0) {
                            circleList3.get(i).setY(circleList3.get(i).getY() + mWeight * 0.002);
                            circleList3.get(i).setDric(1);
                        } else {
                            circleList3.get(i).setY(circleList3.get(i).getY() - mWeight * 0.002);
                            circleList3.get(i).setDric(0);
                        }
                        circleList3.get(i).setX(circleList3.get(i).getX() + mWeight * 0.005);
                    }
                    break;
            }
        }
    }

    private void loopCircle4() {
        for (int i = 0; i < circleList4.size(); i++) {
            switch (circleList4.get(i).getFlag()) {
                case 0:
                    if (circleList4.get(i).getY() >= 0.59 * mHeight) {
                        circleList4.get(i).setFlag(1);
//                        circleList4.get(i).setColor(1);
                    } else {
                        if (circleList4.get(i).getDric() == 0) {
                            circleList4.get(i).setX(circleList4.get(i).getX() + mWeight * 0.002);
                            circleList4.get(i).setDric(1);
                        } else {
                            circleList4.get(i).setX(circleList4.get(i).getX() - mWeight * 0.002);
                            circleList4.get(i).setDric(0);
                        }
                        circleList4.get(i).setY(circleList4.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 1:
                    if (circleList4.get(i).getX() <= 0.08 * mWeight) {
                        circleList4.remove(i);
                    } else {
                        if (circleList4.get(i).getDric() == 0) {
                            circleList4.get(i).setY(circleList4.get(i).getY() + mWeight * 0.002);
                            circleList4.get(i).setDric(1);
                        } else {
                            circleList4.get(i).setY(circleList4.get(i).getY() - mWeight * 0.002);
                            circleList4.get(i).setDric(0);
                        }
                        circleList4.get(i).setX(circleList4.get(i).getX() - mWeight * 0.005);
                    }
                    break;
            }
        }
    }

    private void loopCircle5() {
        for (int i = 0; i < circleList5.size(); i++) {
            switch (circleList5.get(i).getFlag()) {
                case 0:
                    if (circleList5.get(i).getX() >= 0.505 * mWeight) {
                        circleList5.get(i).setFlag(1);
                    } else {
                        if (circleList5.get(i).getDric() == 0) {
                            circleList5.get(i).setY(circleList5.get(i).getY() + mWeight * 0.002);
                            circleList5.get(i).setDric(1);
                        } else {
                            circleList5.get(i).setY(circleList5.get(i).getY() - mWeight * 0.002);
                            circleList5.get(i).setDric(0);
                        }
                        circleList5.get(i).setX(circleList5.get(i).getX() + mWeight * 0.005);
                    }
                    break;
                case 1:
                    if (circleList5.get(i).getY() >= 0.595 * mHeight) {
                        circleList5.get(i).setFlag(2);
                    } else {
                        if (circleList5.get(i).getDric() == 0) {
                            circleList5.get(i).setX(circleList5.get(i).getX() + mWeight * 0.002);
                            circleList5.get(i).setDric(1);
                        } else {
                            circleList5.get(i).setX(circleList5.get(i).getX() - mWeight * 0.002);
                            circleList5.get(i).setDric(0);
                        }
                        circleList5.get(i).setY(circleList5.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 2:
                    if (circleList5.get(i).getX() <= 0.335 * mWeight) {
                        circleList5.get(i).setFlag(3);
                    } else {
                        if (circleList5.get(i).getDric() == 0) {
                            circleList5.get(i).setY(circleList5.get(i).getY() + mWeight * 0.002);
                            circleList5.get(i).setDric(1);
                        } else {
                            circleList5.get(i).setY(circleList5.get(i).getY() - mWeight * 0.002);
                            circleList5.get(i).setDric(0);
                        }
                        circleList5.get(i).setX(circleList5.get(i).getX() - mWeight * 0.005);
                    }
                    break;
                case 3:
                    if (circleList5.get(i).getY() <= 0.39 * mHeight) {
                        circleList5.remove(i);
                    } else {
                        if (circleList5.get(i).getDric() == 0) {
                            circleList5.get(i).setX(circleList5.get(i).getX() + mWeight * 0.002);
                            circleList5.get(i).setDric(1);
                        } else {
                            circleList5.get(i).setX(circleList5.get(i).getX() - mWeight * 0.002);
                            circleList5.get(i).setDric(0);
                        }
                        circleList5.get(i).setY(circleList5.get(i).getY() - mWeight * 0.005);
                    }
                    break;
            }
        }
    }

    private void loopCircle6() {
//        if (frag_drawer) {
//            for (int i = 0; i < circleList6.size(); i++) {
//                circleList6.remove(i);
//            }
//        }
        for (int i = 0; i < circleList6.size(); i++) {
            switch (circleList6.get(i).getFlag()) {
                case 0:
                    if (circleList6.get(i).getY() <= 0.27 * mHeight) {
                        circleList6.get(i).setFlag(1);
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setX(circleList6.get(i).getX() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setY(circleList6.get(i).getY() - mWeight * 0.005);
                    }
                    break;
                case 1:
                    if (circleList6.get(i).getX() <= 0.665 * mWeight) {
                        circleList6.get(i).setFlag(2);
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setY(circleList6.get(i).getY() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.005);
                    }


                    break;
                case 2:
                    if (circleList6.get(i).getY() >= 0.371 * mHeight) {
                        circleList6.get(i).setFlag(3);
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setX(circleList6.get(i).getX() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 3:
                    if (circleList6.get(i).getX() <= 0.6 * mWeight) {
                        if (STATUS_CIRCLE7) {
                            circleList6.get(i).setFlag(4);
                        } else {
                            circleList6.remove(i);
                        }

                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setY(circleList6.get(i).getY() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.005);
                    }
                    break;
                case 4:
                    if (circleList6.get(i).getX() <= 0.52 * mWeight) {
                        circleList6.get(i).setFlag(5);
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setY(circleList6.get(i).getY() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.005);
                    }
                    break;
                case 5:
                    if (circleList6.get(i).getY() >= 0.405 * mHeight) {
                        circleList6.get(i).setFlag(6);
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setX(circleList6.get(i).getX() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 6:
                    if (circleList6.get(i).getX() >= 0.575 * mWeight) {
                        if (STATUS_CIRCLE8 && !STATUS_CIRCLE9) {
                            circleList6.get(i).setFlag(7);
                        } else if (STATUS_CIRCLE9) {
                            circleList6.get(i).setFlag(8);
                        } else {
                            circleList6.remove(i);
                        }
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setY(circleList6.get(i).getY() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setX(circleList6.get(i).getX() + mWeight * 0.005);
                    }
                    break;
                case 7:
                    if (circleList6.get(i).getY() >= 0.48 * mHeight) {
                        circleList6.remove(i);
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setX(circleList6.get(i).getX() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.005);
                    }
                    break;
                case 8:
                    if (circleList6.get(i).getY() >= 0.565 * mHeight) {
                        circleList6.remove(i);
                    } else {
                        if (circleList6.get(i).getDric() == 0) {
                            circleList6.get(i).setX(circleList6.get(i).getX() + mWeight * 0.002);
                            circleList6.get(i).setDric(1);
                        } else {
                            circleList6.get(i).setX(circleList6.get(i).getX() - mWeight * 0.002);
                            circleList6.get(i).setDric(0);
                        }
                        circleList6.get(i).setY(circleList6.get(i).getY() + mWeight * 0.005);
                    }
                    break;
            }
        }
    }

    private void loopCircle8() {

        for (int i = 0; i < circleList8.size(); i++) {
            switch (circleList8.get(i).getFlag()) {
                case 0:
                    if (circleList8.get(i).getX() >= 0.7 * mWeight) {
                        circleList8.remove(i);
                    } else {

                        if (circleList8.get(i).getDric() == 0) {
                            circleList8.get(i).setY(circleList8.get(i).getY() + mWeight * 0.002);
                            circleList8.get(i).setDric(1);
                        } else {
                            circleList8.get(i).setY(circleList8.get(i).getY() - mWeight * 0.002);
                            circleList8.get(i).setDric(0);
                        }
                        circleList8.get(i).setX(circleList8.get(i).getX() + mWeight * 0.005);
                    }
                    break;
            }
        }
    }

    private void loopCircle9() {


        for (int i = 0; i < circleList9.size(); i++) {
            switch (circleList9.get(i).getFlag()) {
                case 0:
                    if (circleList9.get(i).getX() >= 0.7 * mWeight) {
                        circleList9.remove(i);
                    } else {

                        if (circleList9.get(i).getDric() == 0) {
                            circleList9.get(i).setY(circleList9.get(i).getY() + mWeight * 0.002);
                            circleList9.get(i).setDric(1);
                        } else {
                            circleList9.get(i).setY(circleList9.get(i).getY() - mWeight * 0.002);
                            circleList9.get(i).setDric(0);
                        }
                        circleList9.get(i).setX(circleList9.get(i).getX() + mWeight * 0.005);
                    }
                    break;
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * */

    public boolean isOpne(int circleNumber) {
        switch (circleNumber) {
            case 1:
                return STATUS_CIRCLE1;
            case 2:
                return STATUS_CIRCLE2;
            case 3:
                return STATUS_CIRCLE3;
            case 4:
                return STATUS_CIRCLE4;
            case 5:
                return STATUS_CIRCLE5;
            case 6:
                return STATUS_CIRCLE6;
            case 7:
                return STATUS_CIRCLE6;
            case 8:
                return STATUS_CIRCLE6;
            case 9:
                return STATUS_CIRCLE6;
            default:
                return false;
        }
    }

    public void openCircle(int circleNumber) {
        switch (circleNumber) {
            case 1:
                System.out.println(thread1.isAlive() + "");
                if (!thread1.isAlive()) {

                    thread1.start();
                }
                if (!STATUS_CIRCLE1) {
                    born(0);
                    STATUS_CIRCLE1 = true;
                }

                break;
            case 2:
                if (!thread2.isAlive()) {
                    thread2.start();
                }
                if (!STATUS_CIRCLE2) {
                    born(1);
                    STATUS_CIRCLE2 = true;
                }
                break;
            case 3:
                if (!thread3.isAlive()) {
                    thread3.start();
                }
                if (!STATUS_CIRCLE3) {
                    born(2);
                    STATUS_CIRCLE3 = true;
                }
                break;
            case 4:
                if (!thread4.isAlive()) {
                    thread4.start();
                }
                if (!STATUS_CIRCLE4) {
                    born(3);
                    STATUS_CIRCLE4 = true;
                }
                break;
            case 5:
                if (!thread5.isAlive()) {
                    thread5.start();
                }
                if (!STATUS_CIRCLE5) {
                    born(4);
                    STATUS_CIRCLE5 = true;
                }
                break;
            case 6:
                if (!thread6.isAlive()) {
                    thread6.start();
                }
                if (!STATUS_CIRCLE6) {
                    born(5);
                    STATUS_CIRCLE6 = true;
                }
                break;
            case 7:
                STATUS_CIRCLE7 = true;
                break;
            case 8:
                if (!thread8.isAlive()) {
                    thread8.start();
                }
                if (!STATUS_CIRCLE8) {
                    born(7);
                    STATUS_CIRCLE8 = true;
                }
                break;
            case 9:
                if (!thread9.isAlive()) {

                    thread9.start();
                }
                if (!STATUS_CIRCLE9) {
                    STATUS_CIRCLE9 = true;
                    born(8);
                }

                break;
            case 10:
                break;
            case 11:
                break;
        }

    }

    public void stopCircle(int circleNumber) {
        switch (circleNumber) {
            case 1:
                STATUS_CIRCLE1 = false;
                break;
            case 2:
                STATUS_CIRCLE2 = false;
                break;
            case 3:
                STATUS_CIRCLE3 = false;
                isArrive = false;
                break;
            case 4:
                STATUS_CIRCLE4 = false;
                break;
            case 5:
                STATUS_CIRCLE5 = false;
                break;
            case 6:
                STATUS_CIRCLE6 = false;
                STATUS_CIRCLE8 = false;
                STATUS_CIRCLE9 = false;
                break;
            case 7:
                STATUS_CIRCLE7 = false;
                break;
            case 8:
                STATUS_CIRCLE8 = false;
                break;
            case 9:
                STATUS_CIRCLE9 = false;
                break;
        }
    }

    public void start() {
        if (!drawThread.isAlive()) {
            drawThread.start();
            threadFlag = true;
        }

    }

    public void end() {
        threadFlag = false;
    }

    public boolean isArrive() {

        return isArrive;
    }
}
