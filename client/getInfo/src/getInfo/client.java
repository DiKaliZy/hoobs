package getInfo;

import java.util.Timer;
import java.util.TimerTask;

import java.io.IOException;
import java.util.Scanner;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public class client {
	public static void main(String[] args) throws
	ClientProtocolException, IOException, JSONException {
		// TODO Auto-generated method stub
		startClient();
	}
	
	public static void startClient() {
		String ip;	
		String port;		
		
		ip = "106.10.54.51";
		port = "18080";
		
//		while(true) {
			handler client = new handler(ip, port);
			client.getInfo();
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
