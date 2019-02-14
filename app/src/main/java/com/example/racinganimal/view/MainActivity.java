package com.example.racinganimal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.racinganimal.R;
import com.example.racinganimal.common.Constants;
import com.example.racinganimal.common.Utils;
import com.example.racinganimal.presenter.LogicPresenter;
import com.example.racinganimal.presenter.LogicPresenterImpl;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {

    private SeekBar mSeekBarOne;
    private SeekBar mSeekBarTwo;
    private SeekBar mSeekBarThree;
    private TextView mTvMyCoin;
    private TextView mTvGetCoin;
    private Spinner mSpinnerOne;
    private Spinner mSpinnerTwo;
    private Spinner mSpinnerThree;
    private ImageView mImageViewPlay;
    private LogicPresenter mPresenter;
    private ImageView mIvIconCoin;

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
        mIvIconCoin.setOnClickListener(this);
    }

    private void initData() {
        showGetCoin(0);
        showTotalCoin(Utils.getCoin(this));
        mPresenter = new LogicPresenterImpl(this, this);
        mSeekBarOne.setMax(Constants.MAX_SEEKBAR);
        mSeekBarTwo.setMax(Constants.MAX_SEEKBAR);
        mSeekBarThree.setMax(Constants.MAX_SEEKBAR);
        mSeekBarTwo.setEnabled(false);
        mSeekBarOne.setEnabled(false);
        mSeekBarThree.setEnabled(false);
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
        mIvIconCoin = findViewById(R.id.icon_coin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_coin: {
                mPresenter.addCoin();
            }
            break;
            case R.id.iv_play: {
                int coinOne = Integer.valueOf((String) mSpinnerOne.getSelectedItem());
                int coinTwo = Integer.valueOf((String) mSpinnerTwo.getSelectedItem());
                int coinThree = Integer.valueOf((String) mSpinnerThree.getSelectedItem());
                mPresenter.onClickPlay(coinOne, coinTwo, coinThree);
            }
            break;
        }
    }

    @Override
    public void setProgressOne(int i) {
        mSeekBarOne.setProgress(i);
    }

    @Override
    public void setProgressTwo(int i) {
        mSeekBarTwo.setProgress(i);
    }

    @Override
    public void setProgressThree(int i) {
        mSeekBarThree.setProgress(i);
    }

    @Override
    public void setImPlayVisibility(boolean show) {
        if (show) {
            mImageViewPlay.setVisibility(View.VISIBLE);
        } else {
            mImageViewPlay.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showGetCoin(int lostCoin) {
        mTvGetCoin.setText(Constants.LOST_COIN + lostCoin + Constants.MY_COIN);
    }

    @Override
    public void errorSetCoin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTotalCoin(int totalCoin) {
        if (totalCoin>1000){
            mTvMyCoin.setText((float)totalCoin/1000 +"k" + Constants.MY_COIN);
            return;
        }
        mTvMyCoin.setText(totalCoin + Constants.MY_COIN);
    }
}
