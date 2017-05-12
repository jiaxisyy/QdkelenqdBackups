package bean;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import activity.MyApplication;
import utils.Constants;

/**
 * Created by zuheng.lv on 2016/6/20.
 */
public class AnimationSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private Paint paint;
    private Paint paintRect1;
    private Paint paintRect2;
    private final int UP=0;
    private final int RIGHT=1;
    private final int DOWN=2;
    private int flag = 1;
    private List<Circle> list;
    private Circle circle ;
    private Thread thread;
    private Boolean threadFlag = false;
    private SurfaceHolder holder;
    private MyThread myThread;


    public AnimationSurfaceView(Context context) {
        super(context);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder =this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paintRect1 = new Paint();
        paintRect1.setColor(Color.RED);
        paintRect2 = new Paint();
        paintRect2.setColor(Color.RED);
        list = new ArrayList<Circle>();
        list.clear();
        initData();
        myThread = new MyThread();
//        threadFlag = true;
    }

    public AnimationSurfaceView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder =this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paintRect1 = new Paint();
        paintRect1.setColor(Color.RED);
        paintRect2 = new Paint();
        paintRect2.setColor(Color.RED);
        list = new ArrayList<Circle>();
        list.clear();
        initData();
        myThread = new MyThread();
//        threadFlag = true;
    }
    public AnimationSurfaceView(Context context, AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder =this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paintRect1 = new Paint();
        paintRect1.setColor(Color.RED);
        paintRect2 = new Paint();
        paintRect2.setColor(Color.RED);
        list = new ArrayList<Circle>();
        list.clear();
        myThread = new MyThread();
//        threadFlag = true;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        list.clear();
        initData();
        myThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    public void initData(){
        circle = new Circle();
        circle.setX(100);
        circle.setY(246);
        circle.setFlag(RIGHT);
        circle.setLINE(true);
        list.add(circle);
    }
    public void loopDraw(){
        for (int i =0;i<list.size();i++) {
            switch (list.get(i).getX()) {
                case 121:
                    if (list.get(i).getY() >= 193) {
                        list.get(i).setFlag(UP);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
                case 158:
                    if (list.get(i).getY() <= 234) {
                        list.get(i).setFlag(DOWN);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
                case 223:
                    if (list.get(i).getY() >= 180) {
                        list.get(i).setFlag(UP);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
                case 266:
                    if (list.get(i).getY() <= 222) {
                        list.get(i).setFlag(DOWN);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
                case 340:
                    if (list.get(i).getY() >= 160) {
                        list.get(i).setFlag(UP);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
                case 393:
                    if (list.get(i).getY() <= 220) {
                        list.get(i).setFlag(DOWN);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
                case 502:
                    if (list.get(i).getY() <= 220) {
                        list.get(i).setFlag(DOWN);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
                case 575:
                    if (list.get(i).getY() >= 192) {
                        list.get(i).setFlag(UP);
                    } else {
                        list.get(i).setFlag(RIGHT);
                    }
                    break;
            }
        }
    }
    private void getData(){
        byte[]y0=  MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y,0,1);
        byte[]y1=  MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y,1,1);
        if(y0[0]==1){
            paintRect1.setColor(Color.GREEN);
        }else {
            paintRect1.setColor(Color.RED);
        }
        if(y1[0]==1){
            paintRect2.setColor(Color.GREEN);
            threadFlag = true;
        }else {
            paintRect2.setColor(Color.RED);
            threadFlag = false;
        }
    }

    public void runDraw(){
        for (int i =0;i<list.size();i++) {
            switch (list.get(i).getFlag()) {
                case UP:
                    list.get(i).setY(list.get(i).getY() - 1);
                    break;
                case DOWN:
                    list.get(i).setY(list.get(i).getY() + 1);
                    break;
                case RIGHT:
                    list.get(i).setX(list.get(i).getX() + 1);
                    break;
            }
        }
    }
        class MyThread extends Thread{
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    getData();
                    if(list.size()>1&&list.get(0).getX()>getWidth() ){
                        list.remove(0);
                    }
                    if(list.get(list.size()-1).getX()>=120 ) {
                        initData();
                    }
                        loopDraw();
                        runDraw();
                        Canvas canvas = holder.lockCanvas();
                    if(canvas!=null){
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        drawUI(canvas);
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }

        }

     private void drawUI(Canvas canvas){
        canvas.drawRect(70,232,92,250,paintRect1);
        canvas.drawRect(190,232,210,248,paintRect2);

        if(threadFlag){
            for(int i=0;i<list.size();i++){
               if((list.get(i).getX()<=162 || list.get(i).getX()>=220)&&(list.get(i).getX()<=294||list.get(i).getX()>=360)&&(list.get(i).getX()<=410||list.get(i).getX()>=492)&&(list.get(i).getX()<=502||list.get(i).getX()>=596)){
                    canvas.drawCircle(list.get(i).getX(),list.get(i).getY(),4,paint);
                }
          }
        }
    }
}
