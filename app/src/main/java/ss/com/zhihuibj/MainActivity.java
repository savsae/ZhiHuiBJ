package ss.com.zhihuibj;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import ss.com.pager.BasePager;
import ss.com.pager.impl.GovPager;
import ss.com.pager.impl.HomePager;
import ss.com.pager.impl.NewsCenterPager;
import ss.com.pager.impl.SettingPager;
import ss.com.pager.impl.SmartPager;

public class MainActivity extends Activity {

    private DrawerLayout drawerLayout;
    private RelativeLayout leftLayout;
    private RelativeLayout rightLayout;
    private RadioGroup bottomTabRadioGroup;

    @ViewInject(R.id.tab_home)
    private RadioButton tabHomeRadio;
    @ViewInject(R.id.vp_main_tab)
    private ViewPager mainVP;

    private List<ContentModel> contentList;
    private ContentAdapter adapter;
    private ListView listView;
    private List<BasePager> contentViews;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        adapter = new ContentAdapter();
        listView.setAdapter(adapter);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initData() {
        contentList = new ArrayList<ContentModel>();
        contentList.add(new ContentModel(R.drawable.newscenter_press, "新闻"));
        contentList.add(new ContentModel(R.drawable.newscenter_press, "笑话"));
        bottomTabRadioGroup.check(R.id.tab_home);
        System.out.println(tabHomeRadio.getText());

        //viewpager
        contentViews = new ArrayList<BasePager>();
        contentViews.add(new HomePager(this));
        contentViews.add(new NewsCenterPager(this));
        contentViews.add(new SmartPager(this));
        contentViews.add(new GovPager(this));
        contentViews.add(new SettingPager(this));
        mainVP.setAdapter(new contentPageAdapter());
    }

    private void initView() {
        x.view().inject(this); // xutils3 view初始化  代替xutils的viewutils.inject
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        leftLayout = (RelativeLayout) findViewById(R.id.left);
        rightLayout = (RelativeLayout) findViewById(R.id.right);
        listView = (ListView) leftLayout.findViewById(R.id.left_listview);
        bottomTabRadioGroup = (RadioGroup) findViewById(R.id.bottom_tab);
        bottomTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tab_home:
                        mainVP.setCurrentItem(0,false);
                        break;
                    case R.id.tab_news:
                        mainVP.setCurrentItem(1,false);
                        break;
                    case R.id.tab_smart:
                        mainVP.setCurrentItem(2,false);
                        break;
                    case R.id.tab_gov:
                        mainVP.setCurrentItem(3,false);
                        break;
                    case R.id.tab_setting:
                        mainVP.setCurrentItem(4,false);
                        break;
                }
            }
        });

        mainVP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                contentViews.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        contentViews.get(0).initData();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ss.com.zhihuibj/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ss.com.zhihuibj/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class ContentAdapter extends BaseAdapter {

        class ViewHold {
            public ImageView imageView;
            public TextView textView;
        }

        @Override
        public int getCount() {
            if (contentList.size() != 0) {
                return contentList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (contentList != null) {
                return contentList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold hold;
            if (convertView == null) {
                hold = new ViewHold();
                convertView = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.drawer_layout, null);
                convertView.setTag(hold);
            } else {
                hold = (ViewHold) convertView.getTag();
            }
            hold.imageView = (ImageView) convertView.findViewById(R.id.item_imageview);
            hold.textView = (TextView) convertView.findViewById(R.id.item_textview);
            hold.imageView.setImageResource(contentList.get(position).getImageView());
            hold.textView.setText(contentList.get(position).getText());
            return convertView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        System.out.println(item.getTitle());
        return super.onOptionsItemSelected(item);
    }

    class contentPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return contentViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = contentViews.get(position);
            View v = basePager.getRootView();
            container.addView(v);
//            basePager.initData();
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
