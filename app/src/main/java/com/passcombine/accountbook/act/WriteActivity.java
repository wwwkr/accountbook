package com.passcombine.accountbook.act;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.passcombine.accountbook.vo.AccountBookVo;
import com.passcombine.accountbook.BaseActivity;
import com.passcombine.accountbook.R;
import com.passcombine.accountbook.databinding.ActivityWriteBinding;
import com.passcombine.accountbook.util.DialogUtil;
import com.passcombine.accountbook.util.PreferenceManager;
import com.passcombine.accountbook.util.StringUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends BaseActivity {


    ActivityWriteBinding binding;

    String select_date = "";


    String inOut = "수입";

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefault(this);
        binding = DataBindingUtil.setContentView(mActivity, R.layout.activity_write);


        init();

        setListener();
    }

    public void init(){

        index = getIntent().getIntExtra("INDEX", 0);

        binding.etDate.setText(new SimpleDateFormat("yyyy월 MM월 dd일").format(new Date()));



    }

    public void setListener(){

        binding.linIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.linIn.setBackgroundColor(Color.BLACK);
                binding.tvIn.setTextColor(Color.WHITE);

                binding.linOut.setBackgroundColor(Color.WHITE);
                binding.tvOut.setTextColor(Color.BLACK);

                inOut = "수입";
            }
        });

        binding.linOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.linIn.setBackgroundColor(Color.WHITE);
                binding.tvIn.setTextColor(Color.BLACK);

                binding.linOut.setBackgroundColor(Color.BLACK);
                binding.tvOut.setTextColor(Color.WHITE);


                inOut = "지출";
            }
        });


        binding.etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUtil.showDialogCalendar(mActivity, "날짜 선택", new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                        select_date = new SimpleDateFormat("yyyy년 MM월 dd일").format(date.getDate());



                        binding.etDate.setText(select_date);


                        DialogUtil.dialog.dismiss();
                    }
                });
            }
        });


        binding.etPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                try{

                    if(binding.etPrice.getText().length() > 14){

                        binding.etPrice.setText("1,000,000,000원");

                    }else{
                        int price = Integer.parseInt(binding.etPrice.getText().toString().replace(",","").replace("원", ""));


                        if(!b){
                            binding.etPrice.setText(StringUtil.formatNumberCommaWon(price));
                        }

                    }


                }catch (Exception e){

                    Log.e(TAG, "Exception : "+ e.getMessage());
                }


            }
        });


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(binding.etPrice.getText().toString().trim())){
                    Toast.makeText(mActivity, "금액을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }



                String date = binding.etDate.getText().toString();
                String price = binding.etPrice.getText().toString();
                String contant = binding.etContant.getText().toString();

                AccountBookVo accountBookVo = new AccountBookVo(index, date, price, contant, inOut);


                PreferenceManager.setString(mActivity, index+"", new Gson().toJson(accountBookVo));



                DialogUtil.showDialogOK_Btn(mActivity, "저장이 완료되었습니다.", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DialogUtil.dialog.dismiss();

                        moveToActivity(mActivity, AccountBookActivity.class);

                    }
                });

            }
        });
    }
}