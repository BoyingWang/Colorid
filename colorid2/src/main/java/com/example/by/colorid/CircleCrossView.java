package com.example.by.colorid;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.w3c.dom.ProcessingInstruction;

/**
 * @author: by
 * @time: 2016/1/23.8:22
 */
public class CircleCrossView extends SurfaceView implements SurfaceHolder.Callback{
    private  final static String TAG="CircleCrossView";
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private int color;

    public CircleCrossView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Log.i(TAG,"init");
        holder=getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);//设置显示格式，透明
        setKeepScreenOn(true);
        setZOrderOnTop(true);
        paint=new Paint();
        paint.setAntiAlias(true);
        color=Color.RED;

    }

    public CircleCrossView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleCrossView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");
        refresh();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     * 画图
     * @param canvas
     */
    private  void myDraw(Canvas canvas)
    {   //  绘制背景
/*        paint.setColor(Color.WHITE);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);*/
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);//设置坐标系为view中心
        paint.setStyle(Paint.Style.STROKE);//设置画笔为不填充
        paint.setColor(color);
        paint.setStrokeWidth(10);
        canvas.drawCircle(0, 0, getHeight() / 4, paint);
        paint.setStrokeWidth(5);
        float radius=getHeight()/4;
        canvas.drawLine(0,0,0,-radius,paint);//画上线
        canvas.drawLine(0,-paint.getStrokeWidth()/2,radius,0,paint);//画右线

        canvas.restore();
    }

    /**
     * 逻辑
     */
    private  void logic()
    {

    }

    /**
     * 刷新
     */
    public  void refresh()
    {
        canvas=holder.lockCanvas();
        if(null!=canvas)
        {
            myDraw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
        logic();
    }

    public void setColor(int color) {
        this.color = color;
    }
}
