package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
//일간 그래프(꺽은선 그래프)
public class StatisticsDayActivity extends AppCompatActivity {
    //Button btnBarChart,btnPieChart;
    Switch sw;
    Spinner spinner;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        //BarChart barChart=(BarChart) findViewById(R.id.barChart);
        LineChart chart = findViewById(R.id.lineChart);

        sw=(Switch)findViewById(R.id.switch1);
        CheckState();

        sw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CheckState();
            }
        });



        //Spinner_선택시 화면 전환
        final String[] item = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(2,false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1){//Main화면에서 Main화면으로 =>변동없음
                    finish();
                }
                else if(position==2){//일간으로 이동
                    Intent I = new Intent(StatisticsDayActivity.this, StatisticsDayActivity.class);
                    startActivity(I);
                    finish();
                }
                else if(position==3){//주간으로 이동
                    Intent I = new Intent(StatisticsDayActivity.this, StatisticsWeekActivity.class);
                    startActivity(I);
                    finish();
                }
                else if(position==4){//월간으로 이동
                    Intent I = new Intent(StatisticsDayActivity.this, StatisticsMonthActivity.class);
                    startActivity(I);
                    finish();
                }
                else if (position == 5) {//연간으로 이동
                    Intent I = new Intent(StatisticsDayActivity.this, StatisticsYearActivity.class);
                    startActivity(I);
                    finish();
                }
            }

            public void processDatePickerResult(int year,int month,int day){

                String format =  new String("Current Date: yyyy-MM-dd");

                // [서버 통신]

                //StatisticsFragment.JSONTaskGET task = new StatisticsFragment.JSONTaskGET();

                //String parameter = "?id="+user_id+"&year="+year+"&month="+"&day="+"&hour="; // 특정 year의 기록을 month 단위로 받아옴
                //String parameter = "?id="+user_id+"&year="+year+"&month="+month+"&day="+"&hour="; // 특정 month의 기록을 day 단위로 받아옴
                String parameter = "?id="+user_id+"&year="+year+"&month="+month+"&day="+day+"&hour="; // 특정 day의 기록을 hour 단위로 받아옴
                //String parameter = "?id="+user_id+"&year="+year+"&month="+month+"&day="+day+"&hour="+hour; // 특정 hour의 기록을 minute 단위로 받아옴
                //task.execute("http://3.92.215.113:4001/posture"+parameter);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void CheckState() {
        LineChart chart = findViewById(R.id.lineChart);
        TextView textView = findViewById(R.id.text1);
        TextView btn = findViewById(R.id.btn3);
        double sum = 0,sumF=0;
        YAxis leftYAxis = chart.getAxisLeft();
        leftYAxis.setAxisMaxValue(100f);
        if (sw.isChecked()){
            //현재는 임의로 값을 배정, 이후 mysql값을 받아오는 것으로 수정예정
            btn.setText("COMPARE REPORT");
            ArrayList<Entry> GoodBad = new ArrayList<>();
            ArrayList<Entry> GoodBad_Friend = new ArrayList<>();

            GoodBad.add(new Entry(46f,0));
            GoodBad.add(new Entry(51f,1));
            GoodBad.add(new Entry(73f,2));
            GoodBad.add(new Entry(41f,3));
            GoodBad.add(new Entry(60f,4));
            GoodBad.add(new Entry(69f,5));

            GoodBad_Friend.add(new Entry(51f,0));
            GoodBad_Friend.add(new Entry(56f,1));
            GoodBad_Friend.add(new Entry(78f,2));
            GoodBad_Friend.add(new Entry(46f,3));
            GoodBad_Friend.add(new Entry(65f,4));
            GoodBad_Friend.add(new Entry(74f,5));

            //x축, 시간
            ArrayList<String> day = new ArrayList<>();

            day.add("1pm");
            day.add("2pm");
            day.add("3pm");
            day.add("4pm");
            day.add("5pm");
            day.add("6pm");

            chart.animateY(100);


            //그래프 구현


            LineData data = new LineData(day);
            LineDataSet set = new LineDataSet(GoodBad,"User");
            set.setColor(Color.BLUE);
            set.setDrawValues(true);
            set.setValueTextSize(10);
            set.setValueTextColor(Color.BLACK);
            data.addDataSet(set);

            LineDataSet set_Friend = new LineDataSet(GoodBad_Friend,"practice");
            data.addDataSet(set_Friend);
            set_Friend.setColor(Color.RED);
            set_Friend.setDrawValues(true);
            set_Friend.setValueTextSize(10);
            set_Friend.setValueTextColor(Color.BLACK);
            chart.setData(data);

            YAxis yAxisRight = chart.getAxisRight(); //Y축의 오른쪽면 설정
            yAxisRight.setDrawLabels(false);
            yAxisRight.setDrawAxisLine(false);
            yAxisRight.setDrawGridLines(false);

            chart.invalidate();
            //Daily Report 구현_친구와 비교
            for(int i=0;i<GoodBad.size();i++){
                sum +=GoodBad.get(i).getVal();
            }
            sum=sum/GoodBad.size();
            for(int i=0;i<GoodBad.size();i++){
                sumF +=GoodBad_Friend.get(i).getVal();
            }
            sumF=sumF/GoodBad_Friend.size();

            //평균값을 친구의 데이터와 비교한다.
            if(sum<sumF)
                textView.setText("Ooooops! Your posture is worse than your friend's. You look like a Luigi!");
            else if(sum==sumF)
                textView.setText("You are neck and neck with your friend!");
            else
                textView.setText("You are better than your friend! Be Proud of that!");
        }
        else{
            //현재는 임의로 값을 배정, 이후 mysql값을 받아오는 것으로 수정예정
            btn.setText("DAILY REPORT");
            ArrayList<Entry> GoodBad = new ArrayList<>();

            GoodBad.add(new BarEntry(46f,0));
            GoodBad.add(new BarEntry(51f,1));
            GoodBad.add(new BarEntry(73f,2));
            GoodBad.add(new BarEntry(41f,3));
            GoodBad.add(new BarEntry(60f,4));
            GoodBad.add(new BarEntry(69f,5));

            //x축, 시간
            ArrayList<String> day = new ArrayList<>();

            day.add("1pm");
            day.add("2pm");
            day.add("3pm");
            day.add("4pm");
            day.add("5pm");
            day.add("6pm");

            //그래프 구현

            chart.animateY(100);
            LineData data = new LineData(day);
            LineDataSet set = new LineDataSet(GoodBad,"Friend");
            set.setColor(Color.BLUE);
            set.setDrawValues(true);
            set.setValueTextSize(10);
            set.setValueTextColor(Color.BLACK);
            data.addDataSet(set);
            chart.setData(data);

            YAxis yAxisRight = chart.getAxisRight(); //Y축의 오른쪽면 설정
            yAxisRight.setDrawLabels(false);
            yAxisRight.setDrawAxisLine(false);
            yAxisRight.setDrawGridLines(false);


            chart.invalidate();
            //Daily Report 사용자
            for(int i=0;i<GoodBad.size();i++){
                sum +=GoodBad.get(i).getVal();
            }
            sum=sum/GoodBad.size();

            //기준값과 사용자의 데이터 비교
            if(sum<30f)
                textView.setText("Ooops! Your Posture is quite bad!!");
            else if(sum<=65f)
                textView.setText("Well done!! You are like a tree!");
            else
                textView.setText("Master of Posture! Your will is like steel!!");
        }
    }
}