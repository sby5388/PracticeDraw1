package com.by5388.demo.paint.canvas;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private static final String TAG = "MainActivity";

    private List<PieChartView.PieData> mDataList = new ArrayList<>();

    private PieChartView mPieChartView;
    private MyAdapter mMyAdapter;

    private AlertDialog mAlertDialog;

    private EditText mEditTextName;
    private EditText mEditTextValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyAdapter = new MyAdapter();
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        mPieChartView = findViewById(R.id.pie_chart_view);

        listView.setAdapter(mMyAdapter);

        final Button commit = findViewById(R.id.commit);
        final Button reset = findViewById(R.id.reset);
        final Button insert = findViewById(R.id.insert);
        final Button animation = findViewById(R.id.button_animation);

        commit.setOnClickListener(v -> submit());
        reset.setOnClickListener(v -> clear());
        insert.setOnClickListener(v -> showAddDialog());
        animation.setOnClickListener(v -> startCustomAnimation());
        initDefaultData();
    }

    private void initDefaultData() {
        // TODO: 2019/10/9 自动补充一些默认的数据
        mDataList.add(new SampleData("广州", 1400));
        mDataList.add(new SampleData("深圳", 1200));
        mDataList.add(new SampleData("东莞", 700));
        mDataList.add(new SampleData("佛山", 800));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPieChartView.setDataList(mDataList);
        mMyAdapter.setSampleData(mDataList);
    }

    private void submit() {
        mPieChartView.setDataList(mDataList);
    }

    private void clear() {
        mPieChartView.setDataList(new ArrayList<>());
    }


    float mAngle = 90f;

    private void startCustomAnimation() {
        // TODO: 2019/10/10 自定义的动画 参考 https://blog.csdn.net/qq_40881680/article/details/
        //旋转90度
        final ObjectAnimator animator = ObjectAnimator.ofFloat(mPieChartView, "rotation", mAngle);
        animator.setDuration(300);
        animator.start();
        mAngle += 90f;
        // TODO: 2019/10/10 如何实现 再次点击时 继续旋转度数呢

    }


    private void showAddDialog() {
        checkDialog();
        mAlertDialog.show();
    }

    private void checkDialog() {
        if (mAlertDialog == null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            final View view = inflater.inflate(R.layout.dialog_add_city, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("添加城市")
                    .setView(view)
                    .setPositiveButton(android.R.string.ok, this)
                    .setNegativeButton(android.R.string.cancel, this)
                    .create();
            // TODO: 2019/10/9 为何在AlertDialog.findViewById  : 会找不到组件呢
//            todo mEditTextValue = alertDialog.findViewById(R.id.input_value);
            mEditTextName = view.findViewById(R.id.input_name);
            mEditTextValue = view.findViewById(R.id.input_value);
            if (mEditTextName == null || mEditTextValue == null) {
                throw new NullPointerException();
            }
            mAlertDialog = alertDialog;
        }
        if (mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
        // TODO: 2019/10/9 出现了控指针
        Objects.requireNonNull(mEditTextName);
        Objects.requireNonNull(mEditTextValue);
        mEditTextName.setText("");
        mEditTextValue.setText("");

    }

    @Override
    public void onClick(@NonNull DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                final String name = mEditTextName.getText().toString().trim();
                final String value = mEditTextValue.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(value)) {
                    Toast.makeText(this, "输入为空", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                try {
                    final float f = Float.parseFloat(value);
                    final SampleData sampleData = new SampleData(name, f);
                    mDataList.add(sampleData);
                    mMyAdapter.setSampleData(mDataList);
                    dialog.dismiss();
                } catch (NumberFormatException e) {
                    // TODO: 2019/10/9 如何保持对话框一直显示，除了反射的方法之外？？？
                    Toast.makeText(this, "输入错误", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "onClick: ", e);
                }
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
