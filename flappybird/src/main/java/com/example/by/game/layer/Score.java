package com.example.by.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.by.flappybird.R;
import com.example.by.game.GameSurface;
import com.example.by.utils.Assist;
import com.example.by.utils.Constants;

/**
 * @author: by
 * @time: 2016/1/24.10:04
 */
public class Score extends  Baselayer{
    private  int scoreX,scoreY;
    private  int score;
    private  int scoreMax;
    private long starttime;//游戏开始的时间
    private boolean isstart;
    public Score(GameSurface surface) {
        super(surface);score=0;
        scoreX=screenW/2;
        scoreY=screenH/3;
        isstart=true;
    }


    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        paint.setTextSize(100);

        switch (surface.getGameState())
        {
            case Constants.GAME_START:
                canvas.drawText(score+"s",scoreX,scoreY,paint);
                break;
            case Constants.GAMEING:
                canvas.drawText(score+"s",scoreX,scoreY,paint);
                break;
            default: break;
        }
    }

    @Override
    public void logic() {

    if(isstart)
    {
        starttime=System.currentTimeMillis();
        isstart=false;
    }
        long endtime=System.currentTimeMillis();
        score= (int) ((endtime - starttime)/1000);
        if(score>scoreMax) {
            surface.setScoreMax(score);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public int getScoreMax() {
        return scoreMax;
    }
}
