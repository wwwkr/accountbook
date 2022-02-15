package com.passcombine.accountbook;

import android.app.Application;
import android.util.Log;

public class MyApplicaton extends Application {

    String TAG = MyApplicaton.class.getSimpleName();


    private Thread.UncaughtExceptionHandler androidDefaultUEH;
    private UncaughtExceptionHandler unCatchExceptionHandler;

    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return unCatchExceptionHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();



        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        unCatchExceptionHandler = new UncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(unCatchExceptionHandler);


    }


    public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            // 이곳에서 로그를 남기는 작업을 하면 된다.
            Log.e(TAG, "error MyApp---------> " + ex.getMessage());





            androidDefaultUEH.uncaughtException(thread, ex);


        }
    }

}
