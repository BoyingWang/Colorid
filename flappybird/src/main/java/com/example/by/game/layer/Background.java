package com.example.by.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.by.flappybird.R;
import com.example.by.game.GameSurface;
import com.example.by.utils.Assist;

import java.io.IOException;

/**
 * @author: by
 * @time: 2016/1/24.10:01
 */
public class Background extends Baselayer{

    public Background(GameSurface surface) {
        super(surface);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.C));
        // 画纯色背景
        canvas.drawRect(0, 0, screenW, screenH, paint);
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }
}
