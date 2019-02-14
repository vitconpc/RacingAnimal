package com.example.racinganimal.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.racinganimal.R;
import com.example.racinganimal.common.Constants;
import com.example.racinganimal.common.Utils;
import com.example.racinganimal.view.MainView;

import java.util.Random;

public class LogicPresenterImpl implements LogicPresenter {

    private Context mContext;
    private MainView mMainView;
    private CountDownTimer mCountDownTimer;
    private int mProgressOne;
    private int mProgressTwo;
    private int mProgressThree;
    private int mCoinOne;
    private int mCoinTwo;
    private int mCoinThree;
    private int mTotalCoin;
    private int mLostCoin;

    public LogicPresenterImpl(Context context, final MainView mainView) {
        mContext = context;
        mMainView = mainView;
        mTotalCoin = Utils.getCoin(mContext);
        mLostCoin = 0;
        mCoinOne = 0;
        mCoinTwo = 0;
        mCoinThree = 0;
        mCountDownTimer = new CountDownTimer(100000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random random = new Random();
                mProgressOne += random.nextInt(5) + 1;
                mProgressTwo += random.nextInt(5) + 1;
                mProgressThree += random.nextInt(5) + 1;
                mainView.setProgressOne(mProgressOne);
                mainView.setProgressTwo(mProgressTwo);
                mainView.setProgressThree(mProgressThree);

                if (mProgressOne >= Constants.MAX_SEEKBAR) {
                    this.cancel();
                    mLostCoin += mCoinOne;
                    mLostCoin -= mCoinTwo;
                    mLostCoin -= mCoinThree;
                    done();
                } else if (mProgressTwo >= Constants.MAX_SEEKBAR) {
                    this.cancel();
                    mLostCoin -= mCoinOne;
                    mLostCoin += mCoinTwo;
                    mLostCoin -= mCoinThree;
                    done();
                } else if (mProgressThree >= Constants.MAX_SEEKBAR) {
                    this.cancel();
                    mLostCoin -= mCoinOne;
                    mLostCoin -= mCoinTwo;
                    mLostCoin += mCoinThree;
                    done();
                }
            }

            @Override
            public void onFinish() {

            }
        };
    }

    private void done() {
        //save data
        mTotalCoin += mLostCoin;
        Utils.saveCoin(mContext, mTotalCoin);
        //show button
        mMainView.setImPlayVisibility(true);
        //show data
        mMainView.showGetCoin(mLostCoin);
        mMainView.showTotalCoin(mTotalCoin);
        mLostCoin = 0;
    }

    private void resetProgess() {
        mProgressOne = 0;
        mProgressTwo = 0;
        mProgressThree = 0;
    }

    @Override
    public void onClickPlay(int coinOne, int coinTwo, int coinThree) {
        if (coinOne + coinTwo + coinThree > mTotalCoin) {
            mMainView.errorSetCoin(mContext.getString(R.string.set_coin_again));
            return;
        }
        mCoinOne = coinOne;
        mCoinTwo = coinTwo;
        mCoinThree = coinThree;
        resetProgess();
        mMainView.setImPlayVisibility(false);
        mCountDownTimer.start();
    }

    @Override
    public void addCoin() {
        if (mTotalCoin < 1000) {
            mTotalCoin += 1000;
            Utils.saveCoin(mContext, mTotalCoin);
            mMainView.showTotalCoin(mTotalCoin);
            mMainView.errorSetCoin(mContext.getString(R.string.add_1000_coin_success));
            return;
        }
        mMainView.errorSetCoin(mContext.getString(R.string.add_coin_fails));
    }
}
