package cn.edu.ynu.universitytownguide;

import java.util.HashMap;

import us.wenqi.us.Util.TimeConut;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegConfirmActivity extends Activity implements OnCheckedChangeListener {
	private TextView tView;

	private Button reSend;
	private Button regBtn;

	
	private EditText code;




	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		tView =(TextView) findViewById(R.id.haveSend);
		reSend =(Button) findViewById(R.id.reSend);
		regBtn= (Button) findViewById(R.id.regBtn);
		
		code= (EditText) findViewById(R.id.code);
		
		
		code.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(code.getText().length()==4){
					
					regBtn.setClickable(true);
					regBtn.setTextColor(0xff000000);
					
				}
				
			}
		});
		
		EventHandler eh=new EventHandler(){

			@Override
			public void afterEvent(int event, int result, Object data) {
				
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
			
		};
		SMSSDK.registerEventHandler(eh);

		TimeConut timeConut= new TimeConut(61000, 1000);
		timeConut.setButton(reSend);
		timeConut.start();

		Intent intent= getIntent();
		final String phone= intent.getStringExtra("phone");

		SMSSDK.getVerificationCode("86", phone);

		tView.setText("已经向"+phone+"发送验证码");
		
		regBtn.setOnClickListener(new OnClickListener() {
			

			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(RegConfirmActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				SMSSDK.submitVerificationCode("86",phone, code.getText().toString());
				

				
				
			}
		});
		
		
	



		Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub

	}


	public void sendSMS(EventHandler eh){
		//	SMSSDK.initSDK(this,APPKEY,APPSECRET);
		
		
		
		
		
		
		SMSSDK.registerEventHandler(eh);
		SMSSDK.unregisterEventHandler(eh);
	}
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.e("event", "event="+event);
			if (result == SMSSDK.RESULT_COMPLETE) {
				//短信注册成功后，返回MainActivity,然后提示新好友
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
					Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
				
					Toast.makeText(RegConfirmActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
					Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
					
				}else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
					Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
					
					
				}
			} else {
				((Throwable) data).printStackTrace();
				//int resId = getStringRes(RegConfirmActivity.this, "smssdk_network_error");
				
				Toast.makeText(RegConfirmActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
				//if (resId > 0) {
					Toast.makeText(RegConfirmActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
			//	}
			}
			
		}
		
	};
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	}


}
