package com.example.hantun.myapplication.ui.base;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.hantun.myapplication.AppConstants;

public class BaseFragment extends Fragment implements AppConstants {

    protected Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}