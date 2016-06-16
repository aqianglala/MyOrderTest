package com.example.qiang_pc.myordertest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mData;
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        View calenderLayout = LayoutInflater.from(this).inflate(R.layout.calendarview, listView,
                false);
        MaterialCalendarView widget = (MaterialCalendarView) calenderLayout.findViewById(R.id.calendarView);
        // 模拟历史订单日期
        ArrayList<CalendarDay> oldDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 3; i++) {
            CalendarDay day = CalendarDay.from(calendar);
            oldDates.add(day);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        Drawable drawable = getResources().getDrawable(R.drawable.layer_green);
        widget.addDecorator(new EventDecorator(drawable, oldDates));

        listView.addFooterView(calenderLayout);
        initData();
        testAdapter = new TestAdapter(mData, this);
        listView.setAdapter(testAdapter);

    }

    private void initData() {
        mData = new ArrayList<>();
        for(int i=0;i<20;i++){
            mData.add("第"+i+"条数据");
        }
    }
}
