package cn.edu.ynu.universitytownguide;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import us.wenqi.us.Util.FINAL;
import cn.smssdk.SMSSDK;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class WebViewer extends Activity implements OnClickListener{
	
	
	private FINAL finalt=FINAL.getInstance();
	private String url = FINAL.FILES_URL+"/index.jsp";
	private WebView webView;
	private TextView textView;
	private TextView home;
	private TextView purches;
	private TextView photo;
	private TextView guide;
	private TextView myself;
	private TextView preview;
	private TextView loading;
    private String tempcoor="gcj02";
    //                        tempcoor="gcj02";//国家测绘局标准
    //tempcoor="bd09ll";//百度经纬度标准
//tempcoor="bd09";//百度墨卡托标准
	private LocationClient locationClient;
	private LocationMode locationMode=LocationMode.Hight_Accuracy;
	
	
	
    private MyProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SMSSDK.initSDK(this, "a79492a1b2b0", "3be389512d02a4b2370abb8c9fb9c5ae");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		
		locationClient=((LocationApplication)getApplication()).mLocationClient;
		// Uri uri = Uri.parse(url); //url为你要链接的地址
		// Intent intent =new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		myself = (TextView) findViewById(R.id.myself);
		home = (TextView) findViewById(R.id.home);
		purches = (TextView) findViewById(R.id.purches)
				;
		photo = (TextView) findViewById(R.id.photo);
		guide = (TextView) findViewById(R.id.guide);
		preview= home;
		
		Typeface font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
		myself.setTypeface(font);
		home.setTextColor(0xff007aff);
		myself.setText("\ue800");
		
		home.setTypeface(font);
		purches.setTypeface(font);
		photo.setTypeface(font);
		guide.setTypeface(font);
		
		home.setText("\ue802");
		purches.setText("\ue805");
		photo.setText("\ue809");
		guide.setText("\ue807");
		
		
		home.setOnClickListener(this);
		purches.setOnClickListener(this);
		guide.setOnClickListener(this);
		myself.setOnClickListener(this);
		photo.setOnClickListener(this);
		init();
		
		initLocation();
		locationClient.start();
	}
	
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationClient.stop();
        super.onStop();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(locationMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
        int span=50000;   

        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationClient.setLocOption(option);
        
        
    }
    
    

	private void init() {
		// TODO Auto-generated method stub
		webView = (WebView) findViewById(R.id.webView);
		// WebView加载本地资源
		// webView.loadUrl("file:///android_asset/example.html");
		// WebView加载web资源
		webView.loadUrl(url);
		// 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开
		webView.setWebViewClient(new WebViewClient(){
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				//返回值是true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器去打开
				
				if(url.equals("a://guide")){
					onClick(guide);
					url=FINAL.FILES_URL+"/guide.html";
					
				}
				
				if(url.contains("store.html")){
					onClick(purches);
				}
				
				if(url.equals("a://login")){
					Intent intent = new Intent(getApplication(),LoginActivity.class);
					startActivity(intent);
					
				}
				view.loadUrl(url);
				return true;
			}
			//WebViewClient帮助WebView去处理一些页面控制和请求通知
			
		});
		//启用支持JavaScript
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		//WebView加载页面优先使用缓存加载
	//	settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
                //newProgress 1-100之间的整数
				if(newProgress==100)
				{
					//网页加载完毕，关闭ProgressDialog
					closeDialog();
				}
				else
				{
					//网页正在加载,打开ProgressDialog
					openDialog(newProgress);
				}
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
                  if(dialog!=null&&dialog.isShowing())
                  {
                	     dialog.dismiss();
                	     dialog=null;
                  }
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if(dialog==null)
				{
					dialog=MyProgressDialog.createDialog(WebViewer.this);
					
					Typeface font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
					
					loading= (TextView) findViewById(R.id.id_tv_loadingmsg);
				//	loading.setTypeface(font);
				//	loading.setText("\ue809");
					dialog.setTitle("正在加载");
					
					dialog.setMessage(font);
					dialog.show();
					
				}
				else
				{
					//dialog.setProgress(newProgress);
				}
			
				
			}
		});
		
		
		
	}
	
	//改写物理按键——返回的逻辑
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			//Toast.makeText(this, webView.getUrl(), Toast.LENGTH_SHORT).show();
			if(webView.canGoBack())
			{
				webView.goBack();//返回上一页面
				return true;
			}
			else
			{
				System.exit(0);//退出程序
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		
		if(preview!=null){
			preview.setTextColor(0xff929292);
			((TextView)v).setTextColor(0xff007aff);
			preview= (TextView) v;
			
		}
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.home:
		{

			webView.loadUrl(url);
			break;
		}
			
		case R.id.purches:
			
		{
			webView.loadUrl(FINAL.FILES_URL+"/store.html");
			break;
		}
		
		case R.id.photo:{
			
			
			if(finalt.getLocationscribe()==null||finalt.getLocationscribe().equals("")){
				Toast.makeText(WebViewer.this, "正在获取位置信息请稍后", Toast.LENGTH_SHORT).show();
			}
			else{
				webView.loadUrl(FINAL.FILES_URL+"/locatation.html?longitude="+finalt.getLatitude()+"&latitude="+finalt.getLontitude()+"");
			}
			


				
			
			break;
			
		}
		
		case R.id.myself:{
			
			
			
			if(FINAL.getInstance().getToken()!=null){
				webView.loadUrl(FINAL.FILES_URL+"/user.jsp?Token="+FINAL.getInstance().getToken());
				
			}
			else{
				
				webView.loadUrl(FINAL.FILES_URL+"/user.jsp");
			}
//			Intent intent = new Intent(getApplication(),
//					MineActivity.class);
//			startActivity(intent);
			
			
			break;
			
		}
		
		case R.id.guide:{
			
			webView.loadUrl(FINAL.FILES_URL+"/guide.html");
			
			
			break;
		}

		default:
			break;
		}
		
	}
	
	
	
}


