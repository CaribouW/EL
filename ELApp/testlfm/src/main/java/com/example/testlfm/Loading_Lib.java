package com.example.testlfm;

import android.support.v4.app.FragmentActivity;

import com.roger.catloadinglibrary.CatLoadingView;

public class Loading_Lib {
    /**
     * 1.cat loading
     * compile 'com.roger.catloadinglibrary:catloadinglibrary:1.0.4'
     */
    public static CatLoadingView getCatLoading() {
        return new CatLoadingView();
    }

    public static CatLoadingView catLoadingShow(FragmentActivity context, CatLoadingView catLoadingView) {
        synchronized (Loading_Lib.class) {
            catLoadingView.show(context.getSupportFragmentManager(), "");
            return catLoadingView;
        }
    }

    public static CatLoadingView catLoadingPause(CatLoadingView catLoadingView) {
        synchronized (Loading_Lib.class){
            catLoadingView.onPause();
            return catLoadingView;
        }
    }

    public static CatLoadingView catLoadingResume(CatLoadingView catLoadingView) {
        synchronized (Loading_Lib.class){
            catLoadingView.onResume();
            return catLoadingView;
        }
    }
}
