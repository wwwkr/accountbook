package com.passcombine.accountbook.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.passcombine.accountbook.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;


public class DialogUtil {
    private static View decorView;
    private static int uiOption;

    public DialogUtil() {
    }

    private static OnOkBtClickListener okBtClickListener = null;

    public interface OnOkBtClickListener {
        void onOkBtClick();
    }

    public static void setOkBtClickListener(OnOkBtClickListener listener) {
        okBtClickListener = listener;
    }

    private static OnDismissListener dismissListener = null;

    public interface OnDismissListener {
        void onDissmiss();
    }

    public static void addDismissCallBackListener(OnDismissListener listener) {
        dismissListener = listener;
    }

    public static void showDialogOK(final Context context, String msg) {


        if (dialog != null) {

            dialog.dismiss();
        }


        if ((context != null) && !((Activity) context).isFinishing()) {


            final Dialog dialog = new Dialog(context);


            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.setContentView(R.layout.dialog);

            decorView = dialog.getWindow().getDecorView();
            uiOption = dialog.getWindow().getDecorView().getSystemUiVisibility();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(uiOption);
            //
            ((TextView) dialog.findViewById(R.id.tv_content)).setText(msg);

            dialog.findViewById(R.id.bt_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dialog != null) {

                        dialog.cancel();

                        if (okBtClickListener != null) {
                            okBtClickListener.onOkBtClick();
                        }

                    }

                }
            });

            dialog.findViewById(R.id.bt_cancel).setVisibility(View.GONE);
            dialog.show();

        }
    }

    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static Dialog dialog;

    public static void showDialogOK_Btn(final Context context, String msg, View.OnClickListener listener) {
        if (dialog != null) {


            dialog.dismiss();
        }

        if ((context != null) && !((Activity) context).isFinishing()) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.setContentView(R.layout.dialog);
            //
            decorView = dialog.getWindow().getDecorView();
            uiOption = dialog.getWindow().getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(uiOption);

            ((TextView) dialog.findViewById(R.id.tv_content)).setText(msg);
            dialog.findViewById(R.id.bt_ok).setOnClickListener(listener);
            dialog.findViewById(R.id.bt_cancel).setVisibility(View.GONE);

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                    if (dismissListener != null) {
                        dismissListener.onDissmiss();
                        dismissListener = null;
                    }
                }
            });

            try {

                dialog.show();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }


         static String select_date = "";
    public static void showDialogCalendar(final Context context, String title, OnDateSelectedListener listener) {
        MaterialCalendarView calendarView;

        if (dialog != null) {


            dialog.dismiss();

        }

        if ((context != null) && !((Activity) context).isFinishing()) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.layout_calendar);
            dialog.setCancelable(false);
            //
            decorView = dialog.getWindow().getDecorView();
            uiOption = dialog.getWindow().getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            //전체화면
//            decorView.setSystemUiVisibility(uiOption);

            ((TextView) dialog.findViewById(R.id.tv_title)).setText(title
            );

//            ((TextView)dialog.findViewById(R.id.tv_code)).setText(code);
//
            calendarView =  ( (MaterialCalendarView)dialog.findViewById(R.id.calendarView));

            calendarView.setSelectedDate(new Date());
            calendarView.setOnDateChangedListener(listener);
            dialog.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                }
            });




            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                    if (dismissListener != null) {
                        dismissListener.onDissmiss();
                        dismissListener = null;
                    }
                }
            });

            try {

                dialog.show();
            } catch (Exception e) {
                e.getStackTrace();
            }


        }

    }


    public static void showDialog_twoBtn(final Context context, String msg, View.OnClickListener listener) {
        if (dialog != null) {


            dialog.dismiss();
        }

        if ((context != null) && !((Activity) context).isFinishing()) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.setContentView(R.layout.dialog);
            //
//            decorView = dialog.getWindow().getDecorView();
//            uiOption = dialog.getWindow().getDecorView().getSystemUiVisibility();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//                uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            }
//            decorView.setSystemUiVisibility(uiOption);

            ((TextView) dialog.findViewById(R.id.tv_content)).setText(msg);


            dialog.findViewById(R.id.bt_ok).setOnClickListener(listener);
//            dialog.findViewById(R.id.bt_cancel).setVisibility(View.GONE);

            dialog.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                    if (dismissListener != null) {
                        dismissListener.onDissmiss();
                        dismissListener = null;
                    }
                }
            });

            try {

                dialog.show();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }




}