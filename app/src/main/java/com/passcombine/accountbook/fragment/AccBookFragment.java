package com.passcombine.accountbook.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.passcombine.accountbook.BaseActivity;
import com.passcombine.accountbook.util.DialogUtil;
import com.passcombine.accountbook.util.OnCustomClickListener;
import com.passcombine.accountbook.util.PreferenceManager;
import com.passcombine.accountbook.vo.AccountBookVo;
import com.passcombine.accountbook.R;
import com.passcombine.accountbook.adapter.RvAdapter_AccBook;
import com.passcombine.accountbook.databinding.FragmentAccbookBinding;

import java.util.ArrayList;
import java.util.Comparator;

public class AccBookFragment extends Fragment {

    String TAG = AccBookFragment.class.getSimpleName();

    FragmentAccbookBinding binding;

    RvAdapter_AccBook rvAdapter_accBook;

    ArrayList<AccountBookVo> bankData = new ArrayList<>();

    int position = 0;
    int flag = 0;

    public AccBookFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_accbook, container, false);

        View view = binding.getRoot();
        init(0);

        Log.e(TAG , "CHECK : onCreateView");
        return view;
    }


    public void init(int flag){

        try{
            bankData = getArguments().getParcelableArrayList("ACC_DATA");
            position = getArguments().getInt("POSITION");
//            flag = getArguments().getInt("FLAG");
        }catch (Exception e){
            Log.e(TAG , "CHECK e : "+ e.getMessage());
        }


        Log.e(TAG , "CHECK : "+ new Gson().toJson(bankData));



        rvAdapter_accBook = new RvAdapter_AccBook(getActivity() , bankData);
        binding.rvAccBook.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvAccBook.setAdapter(rvAdapter_accBook);


        Log.e(TAG , "CHECK FLAG = "+ flag);
        if(flag == 1){

            rvAdapter_accBook.setOnLongClickListener(new OnCustomClickListener() {
                @Override
                public void onLongClick(View view, final int position) {

                    DialogUtil.showDialog_twoBtn(getContext(), BaseActivity.mAccountBookVo.get(position).getDate() + "\n" + BaseActivity.mAccountBookVo.get(position).getContent() + " / " + BaseActivity.mAccountBookVo.get(position).getPrice() + "\n삭제하시겠습니까?",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    BaseActivity.mAccountBookVo.remove(position);

                                    PreferenceManager.clear(getContext());


                                    for(int i = 0; i < BaseActivity.mAccountBookVo.size(); i++){

                                        PreferenceManager.setString(getContext(), (i+1)+"", new Gson().toJson(BaseActivity.mAccountBookVo.get(i)));
                                    }



                                    bankData.clear();

                                    bankData = BaseActivity.mAccountBookVo;


                                    rvAdapter_accBook.setData(bankData);
                                    rvAdapter_accBook.notifyDataSetChanged();

                                    DialogUtil.dialog.dismiss();

                                }
                            });
                }
            });

        }

    }


    @Override
    public void onStart() {
        super.onStart();

        Log.e(TAG , "CHECK : onStart");


        try{
            rvAdapter_accBook.notifyDataSetChanged();
        }catch (Exception e){

        }
    }
}
