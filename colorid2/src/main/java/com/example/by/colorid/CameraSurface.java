package com.example.by.colorid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * @author: by
 * @time: 2016/1/23.9:27
 */
public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback{
    public final  static  String TAG="CameraSurface";
    private  SurfaceHolder holder;
    protected Camera  camera;
    private  boolean ispreview;
    public CameraSurface(Context context) {
        super(context);
        init();
    }
    public CameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
     camera=Camera.open();
        if(null!=camera)
        {
            try {
                camera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
                camera.release();
                camera=null;
            }
        }
        Camera.Parameters parameters=camera.getParameters();
        parameters.setPreviewSize(getWidth(),getHeight());
        camera.setParameters(parameters);
        camera.startPreview();
        ispreview=true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(null!=camera)
        {
            if(ispreview)
            {
                camera.stopPreview();
            }
            camera.release();
            camera=null;
        }

    }
    private  void  init()
    {
        holder=getHolder();
        holder.addCallback(this);//监听器
        setKeepScreenOn(true);
        ispreview=false;
    }

}
