package ss.com.zhihuibj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import ss.com.utils.SharedPreferencesUtils;

public class GuideActivity extends Activity {

    private ViewPager vp_Guide;
    private LinearLayout shapeGrayLi;
    private RelativeLayout shapeRelativeLy;
    private View redShapView;
    private Button startMainBtn;
    private ArrayList<ImageView> imgArrayList;
    private int pointWidth;
    private static final int pages[] = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView(){
        vp_Guide = (ViewPager) findViewById(R.id.vp_guide);
        vp_Guide.setOnPageChangeListener(pageChangeListener);
        shapeGrayLi = (LinearLayout) findViewById(R.id.shapeGray);
        shapeRelativeLy = (RelativeLayout) findViewById(R.id.shapeRela);
        shapeRelativeLy = (RelativeLayout) findViewById(R.id.shapeRela);
        startMainBtn = (Button) findViewById(R.id.startMain);
        startMainBtn.setOnClickListener(onClickListener);
        imgArrayList = new ArrayList<ImageView>();
        int count = pages.length;
        for (int i = 0 ; i < count ; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(pages[i]);
            imgArrayList.add(imageView);
            //初始化小圆点
            View shapView = new View(this);
            shapView.setBackgroundResource(R.drawable.guide_shape_point_gray);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10,10);
            if (i > 0){
                layoutParams.setMargins(10,0,0,0);
//                layoutParams.leftMargin = 10;
            }
            shapView.setLayoutParams(layoutParams);
            shapeGrayLi.addView(shapView);
        }
        vp_Guide.setAdapter(new GuideAdapter());
        //创建红色点
        redShapView = new View(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10,10);
        redShapView.setLayoutParams(layoutParams);
        redShapView.setBackgroundResource(R.drawable.guide_shape_red);
        shapeRelativeLy.addView(redShapView);

        int width = shapeGrayLi.getChildAt(1).getLeft() - shapeGrayLi.getChildAt(0).getLeft(); //俩小圆点之间的距离
        System.out.println(String.valueOf(width));
        //获取视图树
        shapeGrayLi.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //布局绘制结束后调用
                pointWidth = shapeGrayLi.getChildAt(1).getLeft() - shapeGrayLi.getChildAt(0).getLeft(); //俩小圆点之间的距离
                shapeGrayLi.getViewTreeObserver().removeGlobalOnLayoutListener(this);//有可能会有多次回调  所以需要用掉一次移除掉
            }
        });
    }

    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgArrayList.get(position));
            return imgArrayList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            int width = shapeGrayLi.getChildAt(1).getLeft() - shapeGrayLi.getChildAt(0).getLeft(); //俩小圆点之间的距离
//            System.out.println(String.valueOf(width));
            int redPointYD = (position * pointWidth) + ((int) (pointWidth * positionOffset)); ///红点移动距离
            RelativeLayout.LayoutParams redSLp = (RelativeLayout.LayoutParams) redShapView.getLayoutParams();
            redSLp.leftMargin = redPointYD;
            redShapView.setLayoutParams(redSLp);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == pages.length - 1){
                startMainBtn.setVisibility(View.VISIBLE);
            }else{
                startMainBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.startMain:
                    SharedPreferencesUtils.setBoolean(GuideActivity.this, "is_user_guide_showed", true);
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

}
