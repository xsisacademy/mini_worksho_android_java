package com.workshop.android.java.utilities;

import android.app.ProgressDialog;
import android.content.Context;

import com.workshop.android.java.R;


public class LoadingClass {
    public static ProgressDialog loadingAnimationAndText(Context context, String teks){
        ProgressDialog loading = new ProgressDialog(context, R.style.CustomLoadingStyle);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        loading.setMessage(teks);

        return loading;
    }

    public static ProgressDialog loadingAnimation(Context context){
        ProgressDialog loading = new ProgressDialog(context, R.style.CustomLoadingStyle);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);

        return loading;
    }
}
