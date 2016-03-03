package ss.com.pager.impl;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ss.com.pager.BasePager;
import ss.com.zhihuibj.R;

/**
 * 新闻中心
 */
public class NewsCenterPager extends BasePager {
    public NewsCenterPager(Activity currentActi) {
        super(currentActi);
    }

    @Override
    public void initData() {
        super.menuBtn.setVisibility(View.INVISIBLE);
//        super.titleTView.setText("新闻中心");
//        TextView textView = new TextView(currentActi);
//        textView.setTextColor(Color.GREEN);
//        textView.setTextSize(25);
//        textView.setGravity(Gravity.CENTER);
//        textView.setText("新闻中心");
//        super.contentFlay.addView(textView);
        //向新闻中心添加布局
        getDataFroServer();
        setNewsBJ();
    }

    /*设置新闻中心布局*/
    private void setNewsBJ(){
        View v = View.inflate(currentActi, R.layout.news_menu_details,null);
        TabPageIndicator tabPageIndicator = (TabPageIndicator) v.findViewById(R.id.tabsIndicator);
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.contentVP);
        tabPageIndicator.setViewPager(viewPager);
        contentFlay.addView(v);
    }

    public String getDataFroServer() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("www.baidu.com").build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();

            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String okHttpPostTest() {
        try{
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, "name:\"jack\",age:19");
            Request request = new Request.Builder()
                    .url("www.baidu.com")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }catch (Exception e){
            return "";
        }
    }
}
