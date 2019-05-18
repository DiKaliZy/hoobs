package getInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class handler {
	private static String ip;
	private static String port;
	//private static String where;
    
	public handler(String ip, String port) {
		this.ip = ip;
		this.port = port;
		//this.where = where;
	}
    	
    public static JSONObject getInfo(){
        
        try {
            String uri = "http://" + ip + ":" + port + "/info";
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {   // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            String output = response.toString();
            
            if(responseCode==200) { // ���� ȣ��
            	
            	JSONObject json = new JSONObject(output);
                JSONObject tempHumid = (JSONObject)json.get("tempHumid");
                Float temper = tempHumid.getFloat("celsius");
                Float humid = tempHumid.getFloat("humid");
                JSONArray macs = (JSONArray)json.get("macAddresses");
                
            	System.out.println(temper +"//" + humid + "//" + macs );
            	return json;
            } else if(responseCode==404) {   // ���� �߻�
               
            	//JSONObject json = new JSONObject(output);
            	//String errcode = json.get("errorCode").toString();
            	//String errmsg = json.get("errorMessage").toString();
            	//String errcoment = null;
            	
            	
            	System.out.println("���� �߻�");// + errmsg);
            	//System.out.println("\t"+errcoment+"\n");
            	return null;
            } else {
            	//TODO Interner server error
            	System.out.println("���� �߻�: \tInternal server errors\n");
            	return null;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
		
        
    }
}
