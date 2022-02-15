package com.passcombine.accountbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.passcombine.accountbook.vo.AccountBookVo;
import com.passcombine.accountbook.BaseActivity;
import com.passcombine.accountbook.R;
import com.passcombine.accountbook.databinding.FragmentWalletBinding;
import com.passcombine.accountbook.util.StringUtil;

import java.util.ArrayList;

public class WalletFragment extends Fragment {

    String TAG = WalletFragment.class.getSimpleName();

    FragmentWalletBinding binding;


    ArrayList<AccountBookVo> bankData = new ArrayList<>();

    int position = 0;

    public WalletFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);

        View view = binding.getRoot();
        init();


        return view;
    }


    public void init(){


        int in_price = 0;
        int out_price = 0;
        int total_price = 0;


        try{
            bankData = BaseActivity.mAccountBookVo;
            position = getArguments().getInt("POSITION");
        }catch (Exception e){

        }

        for(int i = 0 ; i< bankData.size(); i ++){

            if(bankData.get(i).getInOut().equals("수입")){

                in_price += Integer.parseInt(bankData.get(i).getPrice().replaceAll("[^0-9]",""));

            }else {
                out_price += Integer.parseInt(bankData.get(i).getPrice().replaceAll("[^0-9]",""));
            }

        }

        binding.tvIn.setText(StringUtil.formatNumberCommaWon(in_price));
        binding.tvOut.setText(StringUtil.formatNumberCommaWon(out_price));

        total_price = in_price - out_price;

        binding.tvTotal.setText(StringUtil.formatNumberCommaWon(total_price));

    }


}
