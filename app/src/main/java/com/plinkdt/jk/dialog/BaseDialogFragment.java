package com.plinkdt.jk.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.plinkdt.jk.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


public abstract class BaseDialogFragment extends DialogFragment {

    private boolean bottomAnim = false;
    private boolean isFullScreen = false;

    public BaseDialogFragment(boolean bottomAnim, boolean isFullScreen) {
        this.bottomAnim = bottomAnim;
        this.isFullScreen = isFullScreen;
    }


    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.FullDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutId(), container, true);
//        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);
        bindSource(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isFullScreen) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(80000000));
            getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        }
        if (bottomAnim) {
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogWindowStyle_Animation;
        }

        if (getShowsDialog()) {
            setShowsDialog(false);
        }
        super.onActivityCreated(savedInstanceState);
        setShowsDialog(true);

        final Activity activity = getActivity();
        if (activity != null) {
            getDialog().setOwnerActivity(activity);
        }
        if (savedInstanceState != null) {
            Bundle dialogState = savedInstanceState.getBundle("android:savedDialogState");
            if (dialogState != null) {
                getDialog().onRestoreInstanceState(dialogState);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    protected abstract int setLayoutId();

    protected abstract void bindSource(View view);
}
