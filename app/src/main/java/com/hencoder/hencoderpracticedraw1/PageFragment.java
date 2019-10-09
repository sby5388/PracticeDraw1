package com.hencoder.hencoderpracticedraw1;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

public class PageFragment extends Fragment {

    private static final String SAMPLE_LAYOUT_RES = "sampleLayoutRes";
    private static final String PRACTICE_LAYOUT_RES = "practiceLayoutRes";
    @LayoutRes
    int sampleLayoutRes;
    @LayoutRes
    int practiceLayoutRes;

    public static PageFragment newInstance(@LayoutRes int sampleLayoutRes, @LayoutRes int practiceLayoutRes) {
        final PageFragment fragment = new PageFragment();
        final Bundle args = new Bundle();
        args.putInt(SAMPLE_LAYOUT_RES, sampleLayoutRes);
        args.putInt(PRACTICE_LAYOUT_RES, practiceLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(sampleLayoutRes);
        sampleStub.inflate();

        ViewStub practiceStub = (ViewStub) view.findViewById(R.id.practiceStub);
        practiceStub.setLayoutResource(practiceLayoutRes);
        practiceStub.inflate();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt(SAMPLE_LAYOUT_RES);
            practiceLayoutRes = args.getInt(PRACTICE_LAYOUT_RES);
        }
    }
}
