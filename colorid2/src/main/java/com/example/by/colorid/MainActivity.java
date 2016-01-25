package com.example.by.colorid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements PreviewSurface.OnColorListener {
    private static final String TAG="MainActivity";
    private CircleCrossView crossView;
    private PreviewSurface previewSurface;//camera预览视图
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//加载布局的时候自动new
/*        crossView=(CircleCrossView)findViewById(R.id.cross_view);
        Log.i(TAG, "MAIN");
        crossView.setColor(Color.GREEN);
        crossView.refresh();*/
        crossView=(CircleCrossView)findViewById(R.id.cross_view);//和new不一样
        previewSurface=(PreviewSurface)findViewById(R.id.preview_surface);
        //设置颜色识别的监听器
        previewSurface.setOnColorListener(this);
    }


    @Override
    public void onColor(int color) {
        crossView.setColor(color);
        crossView.refresh();
    }
}
