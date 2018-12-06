package com.kotlin.limiao.textswither;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miao on 2018/12/6.
 */
public class SlideTextView extends TextSwitcher {

    private int index = 0;//textview上下滚动下标
    private Handler handler = new Handler();
    private boolean isFlipping = false; // 是否启用预警信息轮播
    private List<String> textList = new ArrayList<>();
    private int inAnimId = R.anim.slide_in_bottom;
    private int outAnimId = R.anim.slide_out_top;
    private TextUtils.TruncateAt ellipsize = TextUtils.TruncateAt.MIDDLE;
    private int textSize = 12;
    private int textColor = Color.BLACK;
    private int gravity = Gravity.CENTER;
    private int textPaddingLeft = 25;
    private int textPaddingTop = 0;
    private int textPaddingRight = 25;
    private int textPaddingBottom = 0;

    private int duration = 3000;

    private Context mContext;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFlipping) {
                return;
            }
            setText(textList.get(index % textList.size()));
            if (index == textList.size()) {
                index = 0;
            }
            index++;
            startFlipping();
        }
    };

    public SlideTextView(Context context) {
        super(context);
        mContext = context;
//        setTextSwitcher();
    }

    public SlideTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        setTextSwitcher();
    }




    //开启信息轮播
    public SlideTextView startFlipping() {
        if (textList.size() == 1) {
            setText(textList.get(0));
            index = 0;
        }
        if (textList.size() > 1) {
            handler.removeCallbacks(runnable);
            isFlipping = true;
            handler.postDelayed(runnable, duration);
        }

        return this;
    }

    //关闭信息轮播
    public SlideTextView stopFlipping() {
        if (textList.size() > 1) {
            isFlipping = false;
            handler.removeCallbacks(runnable);
        }
        return this;
    }


    /**
     * 先设置 text 的各种属性，然后再添加到 textswitcher
     * @return
     */
    public SlideTextView addTextSwitcher() {
       setInAnimation(AnimationUtils.loadAnimation(mContext, inAnimId));
       setOutAnimation(AnimationUtils.loadAnimation(mContext, outAnimId));
       setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(mContext);
                textView.setTextSize(textSize);//字号
                textView.setTextColor(textColor);
                textView.setEllipsize(ellipsize);
                textView.setSingleLine();
                textView.setGravity(gravity);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                params.gravity = Gravity.CENTER;
                textView.setLayoutParams(params);
                textView.setPadding(textPaddingLeft, textPaddingTop, textPaddingRight, textPaddingBottom);
                return textView;
            }
        });
       return this;
    }


    public List<String> getTextList() {
        return textList;
    }

    public SlideTextView setTextList(List<String> textList) {
        this.textList = textList;
        return this;
    }

    public int getInAnimId() {
        return inAnimId;
    }

    public SlideTextView setInAnimId(int inAnimId) {
        this.inAnimId = inAnimId;
        return this;
    }

    public int getOutAnimId() {
        return outAnimId;
    }

    public SlideTextView setOutAnimId(int outAnimId) {
        this.outAnimId = outAnimId;
        return this;
    }

    public TextUtils.TruncateAt getEllipsize() {
        return ellipsize;
    }

    public SlideTextView setEllipsize(TextUtils.TruncateAt ellipsize) {
        this.ellipsize = ellipsize;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public SlideTextView setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public SlideTextView setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getGravity() {
        return gravity;
    }

    public SlideTextView setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public int getTextPaddingLeft() {
        return textPaddingLeft;
    }

    public SlideTextView setTextPaddingLeft(int textPaddingLeft) {
        this.textPaddingLeft = textPaddingLeft;
        return this;
    }

    public int getTextPaddingTop() {
        return textPaddingTop;
    }

    public SlideTextView setTextPaddingTop(int textPaddingTop) {
        this.textPaddingTop = textPaddingTop;
        return this;
    }

    public int getTextPaddingRight() {
        return textPaddingRight;
    }

    public SlideTextView setTextPaddingRight(int textPaddingRight) {
        this.textPaddingRight = textPaddingRight;
        return this;
    }

    public int getTextPaddingBottom() {
        return textPaddingBottom;
    }

    public SlideTextView setTextPaddingBottom(int textPaddingBottom) {
        this.textPaddingBottom = textPaddingBottom;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public SlideTextView setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
