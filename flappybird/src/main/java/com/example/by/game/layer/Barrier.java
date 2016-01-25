package com.example.by.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.by.flappybird.R;
import com.example.by.game.GameSurface;
import com.example.by.utils.Assist;
import com.example.by.utils.Constants;

import java.util.Random;

/**
 * @author: by
 * @time: 2016/1/24.10:03
 */
public class Barrier extends Baselayer{

    private float spaceH;              //障碍间隙
    private float distance;          //障碍间距
    private float barrierY;
    private float barrierW;                        //障碍的宽

    private float speed;         //障碍速度

    private float barrier1X;             //第一个障碍的x，y
    private float barrier2X;              //第二个障碍的x，y
    private float barrier1H,barrier2H;
    private float playerx,playery;//猪脚的x,y
    private  float radius;//猪脚的半径；



    public Barrier(GameSurface surface) {
        super(surface);

        spaceH=screenH/4;
        barrierW=120;
        distance=screenW/2-barrierW/2;
        barrierY=0;
        speed=10;

        barrier1X=screenW+200;
        barrier1H=setBarrierH();


        barrier2X=barrier1X+distance+barrierW;
        barrier2H=setBarrierH();


    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.SpringGreen));
        //画第一个上
        canvas.drawRect(barrier1X, barrierY, barrier1X + barrierW, barrierY + barrier1H, paint);
        //画第一个下
        canvas.drawRect(barrier1X,barrier1H+spaceH,barrier1X+barrierW,screenH,paint);

        //第二个上
        canvas.drawRect(barrier2X,barrierY,barrier2X+barrierW,barrierY+barrier2H,paint);
        //画第2个下
        canvas.drawRect(barrier2X,barrier2H+spaceH,barrier2X+barrierW,screenH,paint);
    }

    @Override
    public void logic() {
        barrier1X-=speed;
        barrier2X-=speed;

        if(barrier1X + barrierW < 0){
            barrier1X = screenW;
            barrier1H=setBarrierH();
        }

        if(barrier2X + barrierW < 0){
            barrier2X =screenW;
            barrier2H=setBarrierH();
        }
        boolean iscoll1=circleAndRect(playerx,playery,radius,barrier1X,barrierY,barrierW,
                barrier1H);
        boolean iscoll2=circleAndRect(playerx,playery,radius,barrier1X,barrier1H+spaceH,
                barrierW,screenH-barrier1H-spaceH);
        boolean iscoll3=circleAndRect(playerx,playery,radius,barrier2X,barrierY,barrierW,
                barrier2H);
        boolean iscoll4=circleAndRect(playerx,playery,radius,barrier2X,barrier2H+spaceH,
                barrierW,screenH-barrier2H-spaceH);
        if(iscoll1||iscoll2||iscoll3||iscoll4){
            surface.setGameState(Constants.GAME_END);
        }

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }
    private float setBarrierH()
    {
        return  new Random().nextInt((int) (screenH - spaceH-50));
    }

    public void setPlayerx(float playerx) {
        this.playerx = playerx;
    }

    public void setPlayery(float playery) {
        this.playery = playery;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float setRadius() {
        return radius;
    }
}
