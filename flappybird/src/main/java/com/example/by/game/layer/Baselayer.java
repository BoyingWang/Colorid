package com.example.by.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.by.game.GameSurface;

import java.util.HashMap;

/**
 * @author: by
 * @time: 2016/1/24.10:12
 */
public abstract  class Baselayer {

    protected GameSurface surface;

    protected Resources res;

    /**
     * 当前surface视图的宽
     */
    protected int screenW;
    /**
     * 当前surface视图的高
     */
    protected int screenH;
    public Baselayer(GameSurface surface) {
        this.surface = surface;

        this.screenW = surface.getWidth();
        this.screenH = surface.getHeight();

        res = surface.getResources();
    }

    /**
     * 画图
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    public abstract void draw(Canvas canvas, Paint paint);

    /**
     * 逻辑
     */
    public abstract void logic();

    /**
     * 触摸事件
     *
     * @param event
     */
    public abstract void onTouchEvent(MotionEvent event);

    /**
     * 按键点击事件
     *
     * @param keyCode
     * @param event
     */
    public abstract void onKeyDown(int keyCode, KeyEvent event);
    /**
     * 圆与矩形间的碰撞检测
     *
     * @param circleX
     * @param circleY
     * @param circleR
     * @param rectX
     * @param rectY
     * @param rectW
     * @param rectH
     * @return
     */
    boolean circleAndRect(float circleX, float circleY, float circleR, float rectX, float
            rectY, float rectW, float rectH) {
        if (circleX + circleR < rectX) {
            return false;
        } else if (circleX - circleR > rectX + rectW) {
            return false;
        } else if (circleY + circleR < rectY) {
            return false;
        } else if (circleY - circleR > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY < rectX) {
                return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX > rectX + rectW && circleY < rectY) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY > rectX + rectW) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) >
                circleR * circleR && circleX > rectX + rectW && circleY > rectY + rectH) {
            return false;
        }
        return true;
    }
}
