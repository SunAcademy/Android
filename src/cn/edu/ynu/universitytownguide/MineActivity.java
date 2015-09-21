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

public class MineActivity extends Activity  {
	private TextView login_btn;
	private TextView home;
	private TextView purches;
	private TextView photo;
	private TextView guide;
	private TextView myself;



	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_main);
		login_btn= (TextView) findViewById(R.id.login_btn);
		
		
		myself = (TextView) findViewById(R.id.myself);
		home = (TextView) findViewById(R.id.home);
		purches = (TextView) findViewById(R.id.purches)
				;
		photo = (TextView) findViewById(R.id.photo);
		guide = (TextView) findViewById(R.id.guide);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
		myself.setTypeface(font);
		myself.setTextColor(0xff007aff);
		myself.setText("\ue800");
		
		home.setTypeface(font);
		purches.setTypeface(font);
		photo.setTypeface(font);
		guide.setTypeface(font);
		
		home.setText("\ue802");
		purches.setText("\ue805");
		photo.setText("\ue809");
		guide.setText("\ue807");



		login_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplication(),LoginActivity.class);
				startActivity(intent);
				

			}
		});













	}
}






