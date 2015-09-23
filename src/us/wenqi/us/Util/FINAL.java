package us.wenqi.us.Util;

import java.util.List;

public class FINAL {
	
	private static final FINAL instance= new FINAL();
	
	
	
	public static FINAL getInstance(){
		return instance;
		
	}
	
	private FINAL() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public static final String URL="http://113.55.32.14";
	public static final String FILES_URL="http://113.55.32.14";
	//file:///android_asset/University
	public  String addr;
	public  String latitude;
	public  String lontitude;
	public  String locationscribe;
	public List<String> poilist;
	public String Token="123";
	
	
	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public  String getAddr() {
		return addr;
	}
	public  void setAddr(String addr) {
		this.addr = addr;
	}
	public  String getLocationscribe() {
		return locationscribe;
	}
	public  void setLocationscribe(String locationscribe) {
		this.locationscribe = locationscribe;
	}
	public List<String> getPoilist() {
		return poilist;
	}
	public void setPoilist(List<String> poilist) {
		this.poilist = poilist;
	}
	
	public  String getLatitude() {
		return latitude;
	}
	public  void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public  String getLontitude() {
		return lontitude;
	}
	public  void setLontitude(String lontitude) {
		this.lontitude = lontitude;
	}
	

}
