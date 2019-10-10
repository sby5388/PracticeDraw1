package com.by5388.demo.paint.canvas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author Administrator  on 2019/10/9.
 */
public class MyAdapter extends BaseAdapter {
    private List<PieChartView.PieData> mSampleData;

    MyAdapter() {
        mSampleData = new ArrayList<>();
    }

    void setSampleData(List<PieChartView.PieData> sampleData) {
        mSampleData = sampleData;
        mSampleData.sort(new Comparator<PieChartView.PieData>() {
            @Override
            public int compare(PieChartView.PieData o1, PieChartView.PieData o2) {
                if (o1.getValue() == o2.getValue()) {
                    return 0;
                }
                return o1.getValue() > o2.getValue() ? -1 : 1;
            }
        });
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSampleData.size();
    }

    @Override
    public PieChartView.PieData getItem(int position) {
        return mSampleData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            final Context context = parent.getContext();
            final LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_simple_data, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bind(getItem(position));
        return convertView;
    }


    private static class ViewHolder {
        private final TextView mTextViewName;
        private final TextView mTextViewValue;

        private ViewHolder(View view) {
            mTextViewName = view.findViewById(R.id.text_name);
            mTextViewValue = view.findViewById(R.id.text_value);
        }

        void bind(@NonNull PieChartView.PieData data) {
            mTextViewName.setText(data.getName());
            mTextViewValue.setText(String.valueOf(data.getValue()));
        }
    }
}
