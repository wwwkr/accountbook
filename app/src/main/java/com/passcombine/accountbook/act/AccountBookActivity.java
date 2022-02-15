package com.passcombine.accountbook.act;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.iammert.library.readablebottombar.ReadableBottomBar;
import com.passcombine.accountbook.vo.AccountBookVo;
import com.passcombine.accountbook.BaseActivity;
import com.passcombine.accountbook.R;
import com.passcombine.accountbook.databinding.ActivityAccountBookBinding;
import com.passcombine.accountbook.fragment.AccBookFragment;
import com.passcombine.accountbook.fragment.ChartFragment;
import com.passcombine.accountbook.fragment.WalletFragment;
import com.passcombine.accountbook.util.PreferenceManager;
import com.passcombine.accountbook.util.StringUtil;

import java.util.ArrayList;
import java.util.Comparator;

public class AccountBookActivity extends BaseActivity {


    ActivityAccountBookBinding binding;



    ArrayList<AccountBookVo> bankData = new ArrayList<>();


    Fragment fragment = new AccBookFragment();


    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefault(this);
        binding =  DataBindingUtil.setContentView(mActivity, R.layout.activity_account_book);


        init();
        setListener();


    }


    public void init(){

       int index = 1;

        mAccountBookVo.clear();

       while (true){

           AccountBookVo data = new Gson().fromJson(PreferenceManager.getString(mActivity, index+""), AccountBookVo.class);

           if(data != null){
               mAccountBookVo.add(data);
               index++;
           }else {
               break;
           }



       }

        mAccountBookVo.sort(new Comparator<AccountBookVo>() {
            @Override
            public int compare(AccountBookVo t1, AccountBookVo t2) {

                int date1 =  Integer.parseInt(t1.getDate().replaceAll("[^0-9]",""));
                int date2=  Integer.parseInt(t2.getDate().replaceAll("[^0-9]",""));

                if (date1 == date2)
                    return 0;
                else if (date1 > date2)
                    return 1;
                else
                    return -1;

            }
        });




        binding.linToday.setTag(true);
        binding.linTotal.setTag(false);





        setListData(position);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ACC_DATA", bankData);


        addFragment_Bundle(fragmentManager, new AccBookFragment(), true, R.id.lin_container, bundle);




    }

    public void setListData(int date){

        switch (date){

            case 0:

                String today = StringUtil.getDate();

                bankData.clear();


                for(int i = 0; i < mAccountBookVo.size(); i++){

                    Log.e(TAG ,"CHECK 2 : "+mAccountBookVo.get(i).getDate().replaceAll("[^0-9]","") + " , "+ today );

                    if(mAccountBookVo.get(i).getDate().replaceAll("[^0-9]","").equals(today)){


                        bankData.add(mAccountBookVo.get(i));
                    }
                }





                break;

            case 1:

                bankData.clear();

                bankData.addAll(mAccountBookVo);

                Log.e(TAG , "CHECK 3 : "+ new Gson().toJson(bankData));





                break;
        }

    }

    public void setListener(){


        binding.bottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onItemSelected(int i) {


                Fragment currentFragment = fragmentManager.findFragmentById(R.id.lin_container);

                switch (i){
                    case 0:


                        binding.tvTitle.setText("가계부");


                        binding.tvToday.setText("일별");
                        binding.tvTotal.setText("전체");
                        binding.fbWrite.setVisibility(View.VISIBLE);
                        binding.linDate.setVisibility(View.VISIBLE);

                        fragment = new AccBookFragment();



                        if (currentFragment != null) {


                            if (!(currentFragment.getClass().getSimpleName().equals(new AccBookFragment().getClass().getSimpleName()))) {

                                Log.e(TAG , "CHECK POSITION : "+ position);

                                setListData(position);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("ACC_DATA", bankData);
                                bundle.putInt("POSITION", position);


                                replaceFragment_Bundle(fragmentManager, new AccBookFragment(), true, R.id.lin_container, bundle);

                            }

                        }

                        break;

                    case 1:


                        binding.tvTitle.setText("통계");

                        binding.tvToday.setText("수입");
                        binding.tvTotal.setText("지출");

                        binding.fbWrite.setVisibility(View.GONE);
                        binding.linDate.setVisibility(View.VISIBLE);

                        fragment = new ChartFragment();

                        if (currentFragment != null) {

                            if (!currentFragment.getClass().getSimpleName().equals(new ChartFragment().getClass().getSimpleName())) {

                                Bundle bundle = new Bundle();
                                bundle.putInt("POSITION", position);

                                replaceFragment_Bundle(fragmentManager, fragment, true, R.id.lin_container, bundle);

                            }

                        }


                        break;

                    case 2:
                        binding.tvTitle.setText("자산");
                        binding.linDate.setVisibility(View.GONE);
                        binding.fbWrite.setVisibility(View.GONE);

                        if (currentFragment != null) {


                            if (!(currentFragment.getClass().getSimpleName().equals(new WalletFragment().getClass().getSimpleName()))) {

                                Log.e(TAG , "CHECK POSITION : "+ position);

                                setListData(position);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("ACC_DATA", bankData);
                                bundle.putInt("POSITION", position);


                                replaceFragment_Bundle(fragmentManager, new WalletFragment(), true, R.id.lin_container, bundle);

                            }

                        }

                        break;

                    case 3:
                        binding.tvTitle.setText("설정");

                        binding.fbWrite.setVisibility(View.GONE);
                        break;

                }

            }
        });


        binding.fbWrite.setOnClickListener(onClickListener);
        binding.linToday.setOnClickListener(onClickListener);
        binding.linTotal.setOnClickListener(onClickListener);
    }




    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Fragment currentFragment = fragmentManager.findFragmentById(R.id.lin_container);

            switch (view.getId()){

                    //작성하기
                case R.id.fb_write:

                    Intent intent = new Intent(mActivity, WriteActivity.class);

                    intent.putExtra("INDEX", mAccountBookVo.size()+1);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    overridePendingTransition(0,0);


                    startActivity(intent);

                    break;


                    //일별
                case R.id.lin_today:

                    if(!(boolean)binding.linToday.getTag()){

                        binding.linTotal.setTag(false);
                        binding.linToday.setTag(true);

                        binding.linToday.setBackgroundColor(Color.BLACK);
                        binding.tvToday.setTextColor(Color.WHITE);

                        binding.linTotal.setBackgroundColor(Color.WHITE);
                        binding.tvTotal.setTextColor(Color.BLACK);

                        position = 0;





                        if (currentFragment != null) {

                            String fragmentName = currentFragment.getClass().getSimpleName();

                            if (fragmentName.equals(new AccBookFragment().getClass().getSimpleName())) {

                                setListData(position);

                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("ACC_DATA", bankData);

                                fragment.setArguments(bundle);

                                ((AccBookFragment)currentFragment).init(position);
                            }else if(fragmentName.equals(new ChartFragment().getClass().getSimpleName())){



                                ((ChartFragment)currentFragment).setChart("I");

                            }

                        }



                    }


                    break;

                    //전체
                case R.id.lin_total:

                    if(!(boolean)binding.linTotal.getTag()){

                        binding.linTotal.setTag(true);
                        binding.linToday.setTag(false);

                        binding.linToday.setBackgroundColor(Color.WHITE);
                        binding.tvToday.setTextColor(Color.BLACK);

                        binding.linTotal.setBackgroundColor(Color.BLACK);
                        binding.tvTotal.setTextColor(Color.WHITE);

                        position = 1;


                        if (currentFragment != null) {

                            String fragmentName = currentFragment.getClass().getSimpleName();

                            if (currentFragment.getClass().getSimpleName().equals(new AccBookFragment().getClass().getSimpleName())) {

                                setListData(position);

                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("ACC_DATA", bankData);


                                fragment.setArguments(bundle);

                                ((AccBookFragment)currentFragment).init(position);
                            }
                            else if(fragmentName.equals(new ChartFragment().getClass().getSimpleName())){


                                ((ChartFragment)currentFragment).setChart("O");

                            }

                        }


                    }


                    break;

            }
        }
    };
}