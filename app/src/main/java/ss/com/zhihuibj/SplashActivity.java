package ss.com.zhihuibj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import ss.com.utils.SharedPreferencesUtils;

/**
 * 闪屏页面
 */
public class SplashActivity extends Activity {

    private RelativeLayout SplashRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        startAnimation();
    }

    private void initView(){
        SplashRelative = (RelativeLayout) findViewById(R.id.splash_relative);
    }

    /*
    开启动画
     */
    private  void startAnimation(){
        AnimationSet animationSet = new AnimationSet(false);

        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true); //结束后保持动画不变

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        //给动画设置监听事件  监听动画结束
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束的时候 跳转到引导页  引导页只能跳一次
                boolean isShow = SharedPreferencesUtils.getBoolean(SplashActivity.this,"is_user_guide_showed",false);
                if (!isShow){
                    Intent guideIntent = new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(guideIntent);
                }else{
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        SplashRelative.startAnimation(animationSet);
    }

}

