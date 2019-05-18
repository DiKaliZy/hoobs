package getInfo;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

public class client {
	public static void main(String[] args) throws
	ClientProtocolException, JSONException {
		// TODO Auto-generated method stub
		connect2Server("106.10.54.51", "18080");
	}
	
	public static JSONObject connect2Server(String ip, String port) {	
		
//		while(true) {
			handler client = new handler(ip, port);
			JSONObject got = client.getInfo();
			System.out.println(got);
			return got;
			/*
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
	//	}
		
	}
	
}
