package cn.edu.ynu.universitytownguide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;






import java.util.Map;

import us.wenqi.us.Util.DesUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
	private String security_phone;
	private String security_password;
	private String login_value;
	private String name2;
	private String url="http://192.168.253.1/test.do";
	
	
	



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
	
	
	private void Postdd() {
		// TODO Auto-generated method stub
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		System.out.println("postdd");
		 StringRequest request = new StringRequest(
	                Request.Method.POST,
	                url,
	                new Response.Listener<String>() {
	                    @Override
	                    public void onResponse(String response) {
	                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show(); 
	                    }
	                },
	                new Response.ErrorListener() {
	                    

						@Override
						public void onErrorResponse(VolleyError arg0) {
							// TODO Auto-generated method stub
						//	Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show(); 
							url="http://172.27.35.1/test.do";
							Postdd();
						}
	                }){
			 @Override
			protected Map<String, String> getParams()
					throws AuthFailureError {
				// TODO Auto-generated method stub
				 Map<String, String> map = new HashMap<String, String>();  
		            map.put("name1", login_value);  
		            map.put("name2", "value2");  
				return map;
			}
		 };
		 
		 requestQueue.add(request);
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
					DesUtils des;
					try {
						des = new DesUtils("^d[owq]\\ue40");
						security_phone=des.encrypt(phonenum);
						security_password=des.encrypt(userpass);
						des = new DesUtils("dwq");
						login_value= des.encrypt(security_password+"&wenqi"+security_phone+"*wqRlEg");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Toast.makeText(LoginActivity.this, "正在登录", Toast.LENGTH_SHORT).show();
					Postdd();
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






