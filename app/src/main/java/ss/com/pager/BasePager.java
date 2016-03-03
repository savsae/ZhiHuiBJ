package ss.com.pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import ss.com.zhihuibj.R;

/**
 * 主页下五个页面基类
 */
public class BasePager {

    public Activity currentActi;

    private View rootView;

    protected ImageButton menuBtn;

    protected TextView titleTView;

    protected FrameLayout contentFlay;

    public View getRootView() {
        return rootView;
    }

    public BasePager(Activity currentActi){
        this.currentActi = currentActi;
        initViews();
//        initData();  //报错
    }

    /*
    初始化布局
     */
    public void initViews(){
        rootView = View.inflate(currentActi, R.layout.base_pager, null);
        menuBtn = (ImageButton) rootView.findViewById(R.id.menu_btn);
        titleTView = (TextView) rootView.findViewById(R.id.main_title);
        contentFlay = (FrameLayout) rootView.findViewById(R.id.contentFLay);
    }

    /*
    数据填充
     */
    public void initData(){

    }

}
