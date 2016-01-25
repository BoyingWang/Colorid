package com.example.by.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.by.flappybird.R;
import com.example.by.game.GameSurface;
import com.example.by.utils.Assist;
import com.example.by.utils.Constants;

/**
 * @author: by
 * @time: 2016/1/24.10:01
 */
public class Player extends Baselayer{

    private float playerX, playerY;
    private float radius;
    private float speed;
    private float acc;

    public Player(GameSurface surface) {
        super(surface);

        playerX = screenW / 2;
        playerY = screenH / 2;

        radius = 60;
        speed = 15;
        acc = 2;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.Black));
        switch (surface.getGameState()) {
            case Constants.GAME_START:
                canvas.drawCircle(screenW / 2, screenH / 2, radius, paint);
                break;
            case Constants.GAMEING:
                canvas.drawCircle(playerX, playerY, radius, paint);
                break;
            case Constants.GAME_END:
                canvas.drawCircle(screenW / 2, screenH / 2, radius, paint);
                break;
            default:
                break;
        }
    }

    @Override
    public void logic() {
        playerY += speed;
        speed += acc;
        if(speed >= 50){
            speed = 20;
        }
        if(speed <= -50){
            speed = -20;
        }
        if(acc >= 10){
            acc = 2;
        }
        if(acc <= -10){
            acc = -2;
        }

        if(playerY-radius<0){
            surface.setGameState(Constants.GAME_END);
        }
        if(playerY+radius>screenH){
            surface.setGameState(Constants.GAME_END);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        speed -= 20;

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    public float getplayerx() {
        return playerX;
    }

    public float getplayery() {
        return playerY;
    }

    public float getradius() {
        return radius;
    }
}

