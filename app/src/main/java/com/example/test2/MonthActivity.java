package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;

//월간 그래프
public class MonthActivity extends AppCompatActivity {
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        PieChart pieChart = findViewById(R.id.PieChart);

        sw=(Switch)findViewById(R.id.switch1);
        CheckState();

        sw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
             CheckState();
            }
        });


        //Spinner 화면 전환
        final String[] item = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1){//월간->메인
                    Intent I = new Intent(MonthActivity.this, MainActivity.class);
                    startActivity(I);
                    finish();
                }
                else if(position==2){//월간->일간
                    Intent I = new Intent(MonthActivity.this, DayActivity.class);
                    startActivity(I);
                    finish();
                }
                else if(position==3){//주간
                    Intent I = new Intent(MonthActivity.this, WeekActivity.class);
                    startActivity(I);
                    finish();
                }
                else if(position==4){//월간
                    Intent I = new Intent(MonthActivity.this, MonthActivity.class);
                    startActivity(I);
                    finish();
                }
                else if (position == 5) {//연간으로 이동
                    Intent I = new Intent(MonthActivity.this, YearActivity.class);
                    startActivity(I);
                    finish();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void CheckState() {
        PieChart pieChart = findViewById(R.id.PieChart);
        //mysql값을 받아오는 것으로 변경예정
        //친구와의 비교
        if (sw.isChecked()) {
            ArrayList <Entry>GoodBad = new ArrayList();

            GoodBad.add(new Entry(46f, 0));
            GoodBad.add(new Entry(51f, 1));
            GoodBad.add(new Entry(73f, 2));
            GoodBad.add(new Entry(41f, 3));
            GoodBad.add(new Entry(60f, 4));
            GoodBad.add(new Entry(69f, 5));
            GoodBad.add(new Entry(69f, 6));
            GoodBad.add(new Entry(69f, 7));
            GoodBad.add(new Entry(73f, 8));
            GoodBad.add(new Entry(41f, 9));

            PieDataSet dataSet = new PieDataSet(GoodBad, "Good Posture");
            ArrayList day = new ArrayList();
            //x축 기간
            day.add("4week");
            day.add("3week");
            day.add("2week");
            day.add("1week");
            day.add("Recent");
            day.add("4week.f");
            day.add("3week.f");
            day.add("2week.f");
            day.add("1week.f");
            day.add("Recent.f");

            PieData data = new PieData(day, dataSet);
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.BLACK);
            pieChart.setData(data);
            pieChart.animateXY(100, 100);
            //색 설정
            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            //범주
            pieChart.setDescription("Compare Graph");
        } else {
            //개인측정
            //mysql값을 받아오는 것으로 변경예정
            ArrayList GoodBad = new ArrayList();

            GoodBad.add(new Entry(46f, 0));
            GoodBad.add(new Entry(51f, 1));
            GoodBad.add(new Entry(73f, 2));
            GoodBad.add(new Entry(41f, 3));
            GoodBad.add(new Entry(60f, 4));
            GoodBad.add(new Entry(69f, 5));

            PieDataSet dataSet = new PieDataSet(GoodBad, "Good Posture");
            ArrayList day = new ArrayList();
            //x축 기간
            day.add("5week");
            day.add("4week");
            day.add("3week");
            day.add("2week");
            day.add("1week");
            day.add("Recent");


            PieData data = new PieData(day, dataSet);
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.BLACK);
            pieChart.setData(data);
            pieChart.animateXY(100, 100);
            //색 설정
            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            //범주
            pieChart.setDescription("Compare Graph");

        }
    }
}