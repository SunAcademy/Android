package us.wenqi.us.Util;

import android.os.CountDownTimer;
import android.widget.Button;

public class TimeConut extends CountDownTimer{
	
	private Button button;
	
	

	public Button getButton() {
		return button;
	}


	public void setButton(Button button) {
		this.button = button;
	}


	public TimeConut(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		button.setClickable(true);
		button.setTextColor(0xff000000);
		button.setText("重新发送");
		
		
	}


	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		button.setText(millisUntilFinished /1000 +"秒后重发");
		
		
	}
	

}
