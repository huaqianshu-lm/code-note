package com.kotlin.limiao.textswither;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextSwitcher mTextSwitcher;

    private List<String> mWarningTextList = new ArrayList<>();

    private SlideTextView mSlideTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSwitcher = findViewById(R.id.tv);
        mSlideTextView = findViewById(R.id.verticalTextView);
        mWarningTextList.add("11111111111111");
        mWarningTextList.add("2222222222222");
        mWarningTextList.add("333333333333333333");
        mWarningTextList.add("44444444444444444444444444");
        mWarningTextList.add("555555555555555555555555555555555555555555555555555555555555555555555");
//

        mSlideTextView.setTextList(mWarningTextList)
                .setTextColor(Color.RED)
                .setTextSize(20)
                .setTextPaddingLeft(30)
                .setTextPaddingRight(30)
                .setTextPaddingTop(30)
                .setTextPaddingBottom(30)
                .setEllipsize(TextUtils.TruncateAt.START)
                .setInAnimId(R.anim.slide_in_right)
                .setOutAnimId(R.anim.slide_out_left)
                .addTextSwitcher()
                .startFlipping();
    }







    @Override
    public void onResume() {
        super.onResume();
        mSlideTextView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        mSlideTextView.stopFlipping();
    }



}
