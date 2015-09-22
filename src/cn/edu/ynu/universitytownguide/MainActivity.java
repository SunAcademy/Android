package cn.edu.ynu.universitytownguide;

import cn.smssdk.SMSSDK;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnCheckedChangeListener {
	private Button regBtn;
	private EditText phone;
	private TextView back_btn;
	private CheckBox agreeRules;
	private boolean ture_number=false;
	private boolean isChecked=false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register2);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
		regBtn= (Button) findViewById(R.id.regBtn);
		
		phone=(EditText) findViewById(R.id.phone);
		agreeRules= (CheckBox) findViewById(R.id.agreeRule);
		back_btn= (TextView) findViewById(R.id.back_btn);
		back_btn.setTypeface(font);
		back_btn.setText("\ue806");
		back_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		
		
		
		agreeRules.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					regBtn.setTextColor(0xff000000);
					regBtn.setClickable(true);
					
				}
				else{
					regBtn.setTextColor(0xfff0f0f0);
					regBtn.setClickable(false);
					
				}
				
			}
		});
		
		regBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		//		SMSSDK.getVerificationCode("86", phone.toString());
				
				if(checkOnclick()){
					Intent intent= new Intent(MainActivity.this, RegConfirmActivity.class);
					intent.putExtra("phone", phone.getText().toString());
					
					startActivity(intent);
					
				}
				else {
					Toast.makeText(MainActivity.this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});
		
		regBtn.setClickable(false);
		
		
		
		phone.addTextChangedListener(new TextWatcher() {
			
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
				if(phone.getText().length()==11){
					if(phone.getText().toString().matches("^1[3-57-8]\\d{9}$")){
						ture_number=true;
					}
					else{
						ture_number=false;
					}
				}
			}
		});
		
		
		
		
		
	
		
	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	
	private boolean checkOnclick() {
		// TODO Auto-generated method stub
		
			if(phone.getText().toString().matches("^1[3-57-8]\\d{9}$")){
				return true;
			}
			else{
				return false;
			}
			
			
		

	}




}
