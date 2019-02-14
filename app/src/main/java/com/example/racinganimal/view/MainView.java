package com.example.racinganimal.view;

public interface MainView {
    void showGetCoin(int lostCoin);

    void setProgressOne(int i);

    void setProgressTwo(int i);

    void setProgressThree(int i);

    void setImPlayVisibility(boolean show);

    void errorSetCoin(String message);

    void showTotalCoin(int totalCoin);
}
