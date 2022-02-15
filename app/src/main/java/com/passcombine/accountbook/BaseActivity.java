package com.passcombine.accountbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.passcombine.accountbook.vo.AccountBookVo;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    public Activity mActivity;

    public String TAG = "";


    public static ArrayList<AccountBookVo> mAccountBookVo = new ArrayList<>();

    public FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        setDefault(this);
        fragmentManager = getSupportFragmentManager();

    }




    public void moveToActivity(Activity fromActivity, Class toActivity){

        startActivity(new Intent(fromActivity, toActivity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

        overridePendingTransition(0,0);

    }


    public void moveToActivity_finish(Activity fromActivity, Class toActivity){

        startActivity(new Intent(fromActivity, toActivity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

        overridePendingTransition(0,0);

        finish();

    }

    public void setDefault(Activity mActivity){

        this.mActivity = mActivity;

        TAG = mActivity.getClass().getSimpleName();

    }


    public void addFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, int containerId) {


        if (fragment.isAdded()) {
            return;
        }
        String backStackName = fragment.getClass().getName();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment, backStackName);
        if (addToBackStack)
            transaction.addToBackStack(backStackName);
        transaction.commitAllowingStateLoss();
    }

    public void addFragment_Finish(FragmentManager fragmentManager, Fragment oldfragment, Fragment fragment, boolean addToBackStack, int containerId) {
        if (fragment.isAdded()) {
            return;
        }
        fragmentManager.popBackStack(oldfragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        String backStackName = fragment.getClass().getName();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment, backStackName);
        if (addToBackStack)
            transaction.addToBackStack(backStackName);
        transaction.commit();
    }

    public void addFragment_Bundle(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, int containerId, Bundle bundle) {
        if (fragment.isAdded()) {
            return;
        }
        String backStackName = fragment.getClass().getName();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment, backStackName);
        if (addToBackStack)
            transaction.addToBackStack(backStackName);
        transaction.commit();
//        transaction.commitAllowingStateLoss();
    }

    public void addFragment_BundleAndFinish(FragmentManager fragmentManager, Fragment oldfragment, Fragment fragment, boolean addToBackStack, int containerId, Bundle bundle) {
        if (fragment.isAdded()) {
            return;
        }
        fragmentManager.popBackStack(oldfragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        String backStackName = fragment.getClass().getName();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment, backStackName);
        if (addToBackStack)
            transaction.addToBackStack(backStackName);
        transaction.commit();
    }

    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, int containerId) {
        String backStackName = fragment.getClass().getName();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, backStackName);
        transaction.commitAllowingStateLoss();
    }

    public void replaceFragment_Bundle(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, int containerId, Bundle bundle) {
        String backStackName = fragment.getClass().getName();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, backStackName);
        transaction.commitAllowingStateLoss();
    }

}