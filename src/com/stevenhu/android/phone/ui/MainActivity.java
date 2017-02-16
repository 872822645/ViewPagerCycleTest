package com.stevenhu.android.phone.ui;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.stevenhu.android.phone.bean.ADInfo;
import com.stevenhu.android.phone.utils.ViewFactory;
import com.umeng.analytics.MobclickAgent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;
/**
 * 描述：主页
 *
 * @author 胡笃勇
 * @version 2015年5月8日 上午10:47:37
 */
public class MainActivity extends Activity implements OnClickListener{

	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	private LinearLayout ll_circer;
	/*private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
			"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
			"http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
			"http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
			"http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"
			};*/
	private List<String>img_list=new ArrayList<>();
			
	private List<String>img_lists=new ArrayList<>();
	private Button bt_0;
	private Button bt_1;
	private Button bt_2;
	private Button bt_3;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_main);
		img_lists.add("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg");
		img_lists.add("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg");
		img_lists.add("http://pic18.nipic.com/20111215/577405_080531548148_2.jpg");
		img_lists.add("http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg");
		img_lists.add("http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg");
		cycleViewPager = (CycleViewPager) getFragmentManager()
				.findFragmentById(R.id.fragment_cycle_viewpager_content);
		ll_circer=(LinearLayout) findViewById(R.id.ll_circer);
		bt_0=(Button) findViewById(R.id.bt_0);
		bt_1=(Button) findViewById(R.id.bt_1);
		bt_2=(Button) findViewById(R.id.bt_2);
		bt_3=(Button) findViewById(R.id.bt_3);
		bt_0.setOnClickListener(this);
		bt_1.setOnClickListener(this);
		bt_2.setOnClickListener(this);
		bt_3.setOnClickListener(this);
		configImageLoader();
		initialize();
	}
	
	@SuppressLint("NewApi")
	private void initialize() {
		if(img_list.size()==0){
			ll_circer.setVisibility(View.GONE);
		}else{
			ll_circer.setVisibility(View.VISIBLE);
		//imageUrls[0]=R.drawable.ic_launcher+"";
		infos.clear();
		for(int i = 0; i < img_list.size(); i ++){
			ADInfo info = new ADInfo();
			info.setUrl(img_list.get(i));
			info.setContent("图片-->" + i );
			infos.add(info);
		}
		views.clear();
		if(img_list.size()==1){
			
		}else{
		// 将最后一个ImageView添加进来
		views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
		}
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
		}
if(img_list.size()==1){
			
		}else{
		// 将第一个ImageView添加进来
		views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));
		}
		if(img_list.size()==1){
			cycleViewPager.setCycle(false);
		}else{
		// 设置循环，在调用setData方法前调用
		cycleViewPager.setCycle(true);
		}
		// 在加载数据前设置是否循环
		cycleViewPager.setData(views, infos, mAdCycleViewListener);
		if(img_list.size()==1){
			cycleViewPager.setWheel(false);
		}else{
		//设置轮播
		cycleViewPager.setWheel(true);

	    // 设置轮播时间，默认5000ms
		cycleViewPager.setTime(4000);
		}
		//设置圆点指示图标组居中显示，默认靠右
		cycleViewPager.setIndicatorCenter();}
	}
	
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			/*if (cycleViewPager.isCycle()) {
				
				
				Toast.makeText(MainActivity.this,
						"position-->"+(position-1) + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}else{
				Toast.makeText(MainActivity.this,
						"position-->"+position+ info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}*/
			Toast.makeText(MainActivity.this,info.getContent(),1000).show();
			
		}

	};
	
	/**
	 * 配置ImageLoder
	 */
	private void configImageLoader() {
		// 初始化ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_0:
			img_list.clear();
			
			initialize();
			break;

        case R.id.bt_1:
        	img_list.clear();
        		img_list.add(img_lists.get(0));
        	
        	initialize();
			break;
        case R.id.bt_2:
        	img_list.clear();
        	img_list.add(img_lists.get(0));
        	img_list.add(img_lists.get(1));
        	initialize();
			break;
        case R.id.bt_3:
        	img_list.clear();
        	img_list.add(img_lists.get(0));
        	img_list.add(img_lists.get(1));
        	img_list.add(img_lists.get(2
        			));
        	initialize();
			break;
		 }
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		}
		public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		}
}
