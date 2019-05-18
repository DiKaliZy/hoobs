package getInfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class handler {
	private static String ip;
	private static String port;
	//private static String where;
    
	public handler(String ip, String port) {
		this.ip = ip;
		this.port = port;
		//this.where = where;
	}
    	
    public static void getInfo(){
        
        try {
            String uri = "http://" + ip + ":" + port + "/info";
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {   // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            String output = response.toString();
            
            if(responseCode==200) { // 정상 호출
            	
            	JSONObject json = new JSONObject(output);
                JSONObject tempHumid = (JSONObject)json.get("tempHumid");
                Float temper = tempHumid.getFloat("celsius");
                Float humid = tempHumid.getFloat("humid");
                JSONArray macs = (JSONArray)json.get("macAddresses");
                //message = (JSONObject)message.get("result");
                //String transle = message.get("translatedText").toString();
                //System.out.println("번역 결과 : "+transle+"\n");
				
            	System.out.println(temper +"//" + humid + "//" + macs );
            } else if(responseCode==404) {   // 에러 발생
               
            	//JSONObject json = new JSONObject(output);
            	//String errcode = json.get("errorCode").toString();
            	//String errmsg = json.get("errorMessage").toString();
            	//String errcoment = null;
            	
            	
            	System.out.println("오류 발생");// + errmsg);
            	//System.out.println("\t"+errcoment+"\n");
            } else {
            	//TODO Interner server error
            	System.out.println("오류 발생: \tInternal server errors\n");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
