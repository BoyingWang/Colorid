package com.example.by.colorid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.location.GpsStatus;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: by
 * @time: 2016/1/23.10:48
 */
public class PreviewSurface extends  CameraSurface implements Camera.PreviewCallback
{
    private OnColorListener listener;
    public PreviewSurface(Context context) {
        super(context);
    }

    public PreviewSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        super.surfaceDestroyed(holder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
        camera.setPreviewCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        //camera的尺寸
    Camera.Size size=camera.getParameters().getPreviewSize();
        //把data转换为YUVImage
        YuvImage image=new YuvImage(data, ImageFormat.NV21,size.width,size.height,null);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        if(null!=image)
        {
            image.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(outputStream
                .toByteArray()));
        int color=bitmap.getPixel(size.width/2,size.height/2);
        Log.i(TAG, "color:" + color);
        if(null!=listener)
        {
            listener.onColor(color);
        }
    }

    /**
     * 颜色监听器
     */
    public interface OnColorListener{
        void onColor(int color);//接口里默认是public
    }

    /**
     * 设置监听器
     * @param listener
     */
    public void setOnColorListener(OnColorListener listener) {
        this.listener=listener;
    }
}
