package ss.com.pager.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import ss.com.pager.BasePager;

/**主页
 */
public class HomePager extends BasePager{

    public HomePager(Activity currentActi) {
        super(currentActi);
    }

    @Override
    public void initData() {
        menuBtn.setVisibility(View.VISIBLE);
        titleTView.setText("智慧北京");
        TextView textView = new TextView(currentActi);
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setText("智慧北京");
        contentFlay.addView(textView);
    }
}
