package com.by5388.demo.paint.canvas;

/**
 * @author Administrator  on 2019/10/9.
 */
public class SampleData implements PieChartView.PieData {
    private final String mName;
    private final float mValue;

    public static SampleData newInstance(String name, float value) {
        return new SampleData(name, value);
    }

    SampleData(String name, float value) {
        mName = name;
        mValue = value;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public float getValue() {
        return mValue;
    }
}
