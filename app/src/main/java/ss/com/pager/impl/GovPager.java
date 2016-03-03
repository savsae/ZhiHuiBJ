package ss.com.pager.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import ss.com.pager.BasePager;

/**
 * 政务
 */
public class GovPager extends BasePager {
    public GovPager(Activity currentActi) {
        super(currentActi);
    }

    @Override
    public void initData() {
        super.menuBtn.setVisibility(View.INVISIBLE);
        super.titleTView.setText("政务");
        TextView textView = new TextView(currentActi);
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setText("政务");
        super.contentFlay.addView(textView);
    }
}
