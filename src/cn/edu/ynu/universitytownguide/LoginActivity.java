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

public class LoginActivity extends Activity  implements OnClickListener{
	private TextView title;
	private TextView back_btn;
	private EditText phone;
	private EditText password;
	private Button login_btn;
	private TextView forget_password;
	private TextView reg;
	
	
	



	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		title = (TextView) findViewById(R.id.title);
		back_btn = (TextView) findViewById(R.id.back_btn);
		phone= (EditText) findViewById(R.id.userphone);
		password = (EditText) findViewById(R.id.userpassword);
		forget_password = (TextView) findViewById(R.id.forget);
		reg= (TextView) findViewById(R.id.reg_Btn);
		login_btn = (Button) findViewById(R.id.login_btn);
		
		
		title.setText("登录");
		
		back_btn.setOnClickListener(this);
		forget_password.setOnClickListener(this);
		reg.setOnClickListener(this);
		login_btn.setOnClickListener(this);
		
		
		
		













	}






	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.forget:
			
			
			
			break;
			
		case R.id.back_btn:
			
			this.finish();
			
			break;
			
		case R.id.login_btn:
			
			String phonenum= phone.getText().toString();
			String userpass= password.getText().toString();
			if(phonenum.matches("^1[3-57-8]\\d{9}$")){
				if(userpass!=null&& !userpass.equals("")){
					Toast.makeText(LoginActivity.this, "正咋登录", Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
				}
			}
			else {
				Toast.makeText(LoginActivity.this, "手机号码错误", Toast.LENGTH_SHORT).show();
			}
			
			
			break;
			
		case R.id.reg_Btn:
			
			Intent intent = new Intent(getApplication(), MainActivity.class);
			startActivity(intent);
			
			
			break;

		default:
			break;
		}
		
	}
}






