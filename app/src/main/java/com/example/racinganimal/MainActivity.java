package com.example.racinganimal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar mSeekBarOne;
    private SeekBar mSeekBarTwo;
    private SeekBar mSeekBarThree;
    private TextView mTvMyCoin;
    private TextView mTvGetCoin;
    private Spinner mSpinnerOne;
    private Spinner mSpinnerTwo;
    private Spinner mSpinnerThree;
    private ImageView mImageViewPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setEvent();
    }

    private void setEvent() {
        mImageViewPlay.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        mSeekBarOne = findViewById(R.id.sb_one);
        mSeekBarTwo = findViewById(R.id.sb_two);
        mSeekBarThree = findViewById(R.id.sb_three);
        mSpinnerOne = findViewById(R.id.sp_one);
        mSpinnerTwo = findViewById(R.id.sp_two);
        mSpinnerThree = findViewById(R.id.sp_three);
        mImageViewPlay = findViewById(R.id.iv_play);
        mTvMyCoin = findViewById(R.id.tv_my_coin);
        mTvGetCoin = findViewById(R.id.tv_get_coin);
    }

    @Override
    public void onClick(View v) {
        String s = (String) mSpinnerTwo.getSelectedItem();
    }
}
