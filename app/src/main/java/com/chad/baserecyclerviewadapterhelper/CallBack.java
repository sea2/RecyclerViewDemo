package com.chad.baserecyclerviewadapterhelper;

/**
 * Created by lhy on 2018/9/7.
 */
public class CallBack {

    Listener listener;

    CallBack() {

    }


    public void setListener(Listener listener) {
        this.listener = listener;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.listener.doSoming();
    }





    interface Listener {
        void doSoming();
    }


}
