package com.passcombine.accountbook.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.passcombine.accountbook.vo.AccountBookVo;
import com.passcombine.accountbook.BaseActivity;
import com.passcombine.accountbook.R;
import com.passcombine.accountbook.databinding.FragmentChartBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChartFragment extends Fragment {

    String TAG = ChartFragment.class.getSimpleName();

    FragmentChartBinding binding;

    ArrayList<AccountBookVo> bankData = new ArrayList<>();

    int position = 0;
    public ChartFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chart, container, false);

        View view = binding.getRoot();
        init();
        return view;
    }


    public void init(){

        bankData = BaseActivity.mAccountBookVo;

        try{
            position = getArguments().getInt("POSITION");
        }catch (Exception e){

        }
        List<Entry> entries = new ArrayList<>();


        Log.e(TAG, "CEHCK Chart : "+ new Gson().toJson(bankData));

        HashMap<Integer, Integer> chart_data = new HashMap<>();

        String inOut = "수입";

        if(position == 0){
            inOut = "수입";
        }else {
            inOut = "지출";
        }

        for(int i = 0; i < bankData.size(); i++){

            if(bankData.get(i).getInOut().equals(inOut)){


                int date =  Integer.parseInt(bankData.get(i).getDate().replaceAll("[^0-9]","").substring(4,6));

                Log.e(TAG , "CEHCK DATE : "+ date);

                int price = Integer.parseInt(bankData.get(i).getPrice().replaceAll("[^0-9]",""));

                int sumPrice = 0;
                if(chart_data.get(date) != null){
                    sumPrice = price + chart_data.get(date);
                }else{
                    sumPrice = price;
                }


                chart_data.put(date, sumPrice);



            }


        }

        for(int i = 1 ; i < 13 ; i++){

            if(chart_data.get(i) != null){
                entries.add(new Entry(i,  chart_data.get(i)));
            }else {
                entries.add(new Entry(i,  0));
            }

        }






        LineDataSet lineDataSet = new LineDataSet(entries, "월");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);

        binding.lineChart.setData(lineData);

        XAxis xAxis = binding.lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);
        YAxis yLAxis = binding.lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);
        YAxis yRAxis = binding.lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);
        Description description = new Description();
        description.setText("");
        binding.lineChart.setDoubleTapToZoomEnabled(false);
        binding.lineChart.setDrawGridBackground(false);
        binding.lineChart.setDescription(description);
        binding.lineChart.animateY(2000, Easing.EaseInCubic );


        binding.lineChart.invalidate();

        MyMarkerView marker = new MyMarkerView(getContext(),R.layout.markerviewtext);
        marker.setChartView(binding.lineChart);
        binding.lineChart.setMarker(marker);



    }

    public void setChart(String chart_flag ){



        List<Entry> entries = new ArrayList<>();

        String inOut = "";
        if(chart_flag.equals("I")){

            inOut = "수입";
        }else {

            inOut = "지출";
        }

        HashMap<Integer, Integer> chart_data = new HashMap<>();

        for(int i = 0; i < bankData.size(); i++){

            if(bankData.get(i).getInOut().equals(inOut)){


                int date =  Integer.parseInt(bankData.get(i).getDate().replaceAll("[^0-9]","").substring(4,6));

                Log.e(TAG , "CEHCK DATE : "+ date);

                int price = Integer.parseInt(bankData.get(i).getPrice().replaceAll("[^0-9]",""));

                int sumPrice = 0;
                if(chart_data.get(date) != null){
                    sumPrice = price + chart_data.get(date);
                }else{
                    sumPrice = price;
                }


                chart_data.put(date, sumPrice);




            }


        }

        for(int i = 1 ; i < 13 ; i++){

            if(chart_data.get(i) != null){
                entries.add(new Entry(i,  chart_data.get(i)));
            }else {
                entries.add(new Entry(i,  0));
            }

        }





        LineDataSet lineDataSet = new LineDataSet(entries, "월");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);

        binding.lineChart.setData(lineData);
        binding.lineChart.setVisibleXRangeMinimum(12);
        XAxis xAxis = binding.lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);
        YAxis yLAxis = binding.lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);
        YAxis yRAxis = binding.lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);
        Description description = new Description();
        description.setText("");
        binding.lineChart.setDoubleTapToZoomEnabled(false);
        binding.lineChart.setDrawGridBackground(false);
        binding.lineChart.setDescription(description);
        binding.lineChart.animateY(2000, Easing.EaseInCubic );


        binding.lineChart.invalidate();

        MyMarkerView marker = new MyMarkerView(getContext(),R.layout.markerviewtext);
        marker.setChartView(binding.lineChart);
        binding.lineChart.setMarker(marker);



    }

    public class MyMarkerView extends MarkerView {

        private TextView tvContent;

        public MyMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);

            tvContent = (TextView)findViewById(R.id.tvContent);
        }

        // callbacks everytime the MarkerView is redrawn, can be used to update the
        // content (user-interface)
        @Override
        public void refreshContent(Entry e, Highlight highlight) {

            if (e instanceof CandleEntry) {

                CandleEntry ce = (CandleEntry) e;

                tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
            } else {

                tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true));
            }

            super.refreshContent(e, highlight);
        }

        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }
    }


}
