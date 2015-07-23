package com.wl.mobail.day06_lollipopdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View card = findViewById(R.id.card);
        card.setOnTouchListener(this);//
        card.setOnClickListener(this);
    }
//设置按钮（图片）被点击时的阴影。
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        CardView card = (CardView) v;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                card.setCardElevation(getResources().getDimension(R.dimen.card_max_elevation));
                break;
            case MotionEvent.ACTION_UP:
                card.setCardElevation(getResources().getDimension(R.dimen.card_elevation));
                break;
        }
        return !v.isClickable();
        //放回false才能使点击事件onclick方法被执行，返回true不指向onclick方法，
        // 若无onclick监听，onTouch返回false，则不会执行onTouch中的ACTION_UP。
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(this, "CardView is clicked!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, RecyclerActivity.class));

    }
}
