package com.passcombine.accountbook.act;

import android.graphics.Color;
import android.os.Bundle;

import com.passcombine.accountbook.BaseActivity;
import com.passcombine.accountbook.R;
import com.sarnava.textwriter.TextWriter;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


        ((TextWriter)findViewById(R.id.textWriter))
                .setWidth(12)
                .setDelay(30)
                .setColor(Color.RED)
                .setConfig(TextWriter.Configuration.SQUARE)
                .setSizeFactor(20f)
                .setLetterSpacing(25f)
                .setText("ACCOUNT BOOK")
                .setListener(new TextWriter.Listener() {
                    @Override
                    public void WritingFinished() {

                        //do stuff after animation is finished

                        try{
                            Thread.sleep(500);
                            moveToActivity_finish(mActivity, AccountBookActivity.class);
                        }catch (Exception e){

                        }



                    }
                })
                .startAnimation();

    }
}