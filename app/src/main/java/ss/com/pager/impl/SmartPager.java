package ss.com.pager.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import ss.com.pager.BasePager;

/**
 * 智慧中心
 */
public class SmartPager extends BasePager {
    public SmartPager(Activity currentActi) {
        super(currentActi);
    }

    @Override
    public void initData() {
        super.menuBtn.setVisibility(View.INVISIBLE);
        super.titleTView.setText("智慧服务");
        TextView textView = new TextView(currentActi);
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setText("智慧服务");
        super.contentFlay.addView(textView);
    }
}
