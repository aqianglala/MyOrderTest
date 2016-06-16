package com.example.qiang_pc.myordertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qiang-pc on 2016/6/16.
 */
public class TestAdapter extends BaseAdapter{
    private final LayoutInflater inflater;
    private ArrayList<String>data;
    private ArrayList<String>tempData = new ArrayList<>();
    private Context context;

    private boolean isOpen;

    public TestAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        tempData.addAll(data);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(!isOpen){// 收缩
            data.clear();
            data.add(tempData.get(0));
        }else{// 展开
            data.clear();
            data.addAll(tempData);
        }
        return data.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 最后一项
        View view = null;
        if(position == data.size()){
            view = inflater.inflate(R.layout.more,parent,false);
            final Button btn_more = (Button) view.findViewById(R.id.btn_more);
            if(isOpen){
                btn_more.setText("收缩");
            }else{
                btn_more.setText("展开");
            }
            btn_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isOpen){// 收缩
                        data.clear();
                        data.add(tempData.get(0));
                        isOpen = !isOpen;
                        notifyDataSetChanged();
                    }else{// 展开
                        data.clear();
                        data.addAll(tempData);
                        isOpen = !isOpen;
                        notifyDataSetChanged();
                    }
                }
            });
        }
//        else if(position == data.size()+1){// 日历控件
//            view = inflater.inflate(R.layout.calendarview,parent,false);
//            MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);
//            widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
//            widget.setOnDateChangedListener(new OnDateSelectedListener() {
//                @Override
//                public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
//
//                }
//            });
//        }
        else{
            view = inflater.inflate(R.layout.test,parent,false);
            TextView textView = (TextView) view.findViewById(R.id.textview);
            textView.setText(data.get(position));
        }
        return view;
    }
}
