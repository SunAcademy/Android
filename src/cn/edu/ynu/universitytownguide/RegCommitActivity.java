package cn.edu.ynu.universitytownguide;

import java.util.HashMap;
import java.util.Map;

import us.wenqi.us.Util.DesUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

public class RegCommitActivity extends Activity implements OnClickListener {
	private TextView title;
	private Button reg_commit_btn;
	private EditText password;
	private EditText password_verity;
	private String url="http://192.168.1.103/test.do";
	private String reg_val;
	private String telphone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register3);
		
		reg_commit_btn= (Button) findViewById(R.id.reg3_commit_btn);
		password = (EditText) findViewById(R.id.userpassword);
		password_verity = (EditText) findViewById(R.id.userpasswordv);
		
		Intent intent= getIntent();
		final String phone= intent.getStringExtra("phone");
		telphone= phone;
		Toast.makeText(getApplication(), phone, Toast.LENGTH_SHORT).show();
		
		reg_commit_btn.setOnClickListener(this);
		












	}








	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String pass= password.getText().toString();
		String pass_v= password_verity.getText().toString();
		
		if(pass.equals(pass_v)){
			DesUtils des;
			try {
				des = new DesUtils("^d[owq]\\ue40");
				String security_phone= des.encrypt(telphone);
				String security_password= des.encrypt(pass);
				des = new DesUtils("dwq");
				
				reg_val= des.encrypt(security_password+"&wenqi"+security_phone+"*wqROslP");
				System.out.println(reg_val);
				Postdd();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
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
	                        Toast.makeText(RegCommitActivity.this, response, Toast.LENGTH_SHORT).show(); 
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
		            map.put("name1", reg_val);  
		            map.put("name2", "value2");  
				return map;
			}
		 };
		 
		 requestQueue.add(request);
	}
}






