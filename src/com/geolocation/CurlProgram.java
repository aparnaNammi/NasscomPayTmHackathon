package com.geolocation;

import java.awt.Image;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class CurlProgram {
	public String geolocaton(LocationCoordinates locationCoordinates) {
		 
	    try {
	 
	    String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyBJ0_Um1j8aTyJ3Qyy0y_45FgpNtmlzazg";
	 
	    /*URL obj = new URL(url);
	    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
	 
	    conn.setRequestProperty("Content-Type", "application/json");
	    conn.setDoOutput(true);
	 
	    conn.setRequestMethod("PUT");
	 
	    String userpass = "user" + ":" + "pass";
	    String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
	    conn.setRequestProperty ("Authorization", basicAuth);
	    conn.setRequestProperty ("format", "json");
	    conn.setRequestProperty ("pattern", "#");*/
	 
	    //String data =  "{\"format\":\"json\",\"pattern\":\"#\"}";
	    
	    String message;
	    JSONObject json = new JSONObject();

	    /*json.put("homeMobileCountryCode", 404);
	    json.put("homeMobileNetworkCode", 73);
	    json.put("radioType", "gsm");
	    json.put("carrier", "cellone");*/
	    json.put("considerIp", false);

	    JSONArray jsonArray1 = new JSONArray();
	    JSONObject jsonObj = new JSONObject();

	    /*jsonObj.put("cellId", 2224);
	    jsonObj.put("locationAreaCode", 16);
	    jsonObj.put("mobileCountryCode", 257 );
	    jsonObj.put("mobileNetworkCode", 02);*/
	    //jsonObj.put("age", 0);
	    //jsonObj.put("signalStrength", -60);
	    //jsonObj.put("timingAdvance", 15);
	    jsonObj.put("cellId", locationCoordinates.getCellId());
	    jsonObj.put("locationAreaCode", locationCoordinates.getLac());
	    jsonObj.put("mobileCountryCode", locationCoordinates.getMcc() );
	    jsonObj.put("mobileNetworkCode", locationCoordinates.getMnc());
	    jsonArray1.put(jsonObj);
	    json.put("cellTowers", jsonArray1);
	    
	    
	    JSONArray jsonArray2 = new JSONArray();
	    JSONObject jsonObj1 = new JSONObject();

	    jsonObj1.put("macAddress", "00:25:9c:cf:1c:ac");
	    //jsonObj1.put("signalStrength", -43);
	    //jsonObj1.put("age", 0);
	    //jsonObj1.put("channel", 11);
	    //jsonObj1.put("signalToNoiseRatio", 0);
	    jsonArray2.put(jsonObj1);
	    
	    JSONObject jsonObj2 = new JSONObject();

	    jsonObj2.put("macAddress", "00:25:9c:cf:1c:ad");
	    //jsonObj2.put("signalStrength", -107);
	    //jsonObj2.put("age", 0);
	    //jsonObj2.put("channel", 11);
	    //jsonObj2.put("signalToNoiseRatio", 0);
	    jsonArray2.put(jsonObj2);
	    
	   // json.put("wifiAccessPoints", jsonArray2);

	    message = json.toString();
	    System.out.println(message);

	    /*OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
	    out.write(message);
	    out.close();
	 
	    new InputStreamReader(conn.getInputStream()); */ 
	    
	    
	    
	    
	    
	    
	    HttpParams httpParams = new BasicHttpParams();
	   // ConnRouteParams.setDefaultProxy(httpParams, new HttpHost(PROXY_HOST, PROXY_PORT));
	    HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
	    HttpConnectionParams.setSoTimeout(httpParams, 1000);

	    HttpClient client = new DefaultHttpClient(httpParams);
	    HttpPost request = new HttpPost(url);
	    request.setHeader("Content-type", "application/json");

	    try {
	        StringEntity se = new StringEntity(json.toString());
	        request.setEntity(se);

	        HttpResponse response = client.execute(request);
	        if(response!=null){
	        	Gson gson = new Gson();
	            String jsonResult = EntityUtils.toString(response.getEntity());
	            System.out.println(jsonResult);
	            GeoLocationResponse geolocationResponse = gson.fromJson(jsonResult, GeoLocationResponse.class);
	            
	            /*logger.info(jsonResult);*/
	            System.out.println("Est. Location: " + geolocationResponse.getLocation().getLat() + ", "+ geolocationResponse.getLocation().getLng() );
	            
	            GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDg5JSH_LdbxP9GEnLRinKpPjwC6eIrEc8").build();
	            //GeocodingApiRequest geocodingApiRequest = new GeocodingApiRequest(context);
	            LatLng latlng = new LatLng(geolocationResponse.getLocation().getLat(), geolocationResponse.getLocation().getLng());
				//geocodingApiRequest.latlng(latlng );
				
	            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, latlng).await();
	            if (results != null && results.length > 0) {
	                for(GeocodingResult geocodingResult : results){
	                	System.out.println(geocodingResult.formattedAddress);
	                }
	            }
	            
	            
	            GeoApiContext geoApiContextForDir = new GeoApiContext.Builder().apiKey("AIzaSyAdjU3vQl4Cr-w0qOvAb27szGr7cqZhkOw").build();

	            LatLng originLatLng = new LatLng(geolocationResponse.getLocation().getLat(), geolocationResponse.getLocation().getLng());

	            LatLng destinationLatLng = new LatLng(geolocationResponse.getLocation().getLat(), geolocationResponse.getLocation().getLng());

	            //LatLng wayPoints = new LatLng(17.4477, 78.38264);


	            DirectionsResult result = DirectionsApi.newRequest(geoApiContextForDir)
	                    .origin(originLatLng)
	                    .destination(destinationLatLng)
	                    //.waypoints("17.44027,78.39431", "17.43149,78.38817")
	                    .await();
	            for(DirectionsRoute route : result.routes) {
	           // System.out.println(route.bounds.southwest);
	            }
	            
	            Image image = null;
	            String urlString = "https://maps.googleapis.com/maps/api/staticmap?"
	            		+ "center="+geolocationResponse.getLocation().getLat() + "," +geolocationResponse.getLocation().getLng()
	            		+"&size=800x600&sensor=true&zoom=15&maptype=roadmap&"
	            		+ "markers=color:red%7Clabel:S%7C"
	            		+geolocationResponse.getLocation().getLat() + "," +geolocationResponse.getLocation().getLng();
	            URL url1 = new URL(urlString);
	            System.out.println("urlString :" + urlString);
	            
	            String destinationFile = "image-"+geolocationResponse.getLocation().getLat()+","+geolocationResponse.getLocation().getLng()+".jpg";
	            
	            InputStream is = url1.openStream();
	            OutputStream os = new FileOutputStream(destinationFile);

	            byte[] b = new byte[2048];
	            int length;

	            while ((length = is.read(b)) != -1) {
	                os.write(b, 0, length);
	            }

	            is.close();
	            os.close();

	            //HttpPost request1 = new HttpPost(url);
	    	    //request.setHeader("Content-type", "application/json");
	            
	            //jXImageView1.setImage(image); 
	            
	            //result.
	            /*URLConnection connection = url1.openConnection();
	            connection.setRequestProperty("User-Agent", "xxxxxx");
	            image = ImageIO.read(url1);*/
	            return destinationFile;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
		return null;
	 
	  }
	
	public static void main(String args[]){
		Map<String, LocationCoordinates> locationCoorMap = new HashMap<>();
		LocationCoordinates locCoor = new LocationCoordinates();
		locCoor.setCellId(5803);
		locCoor.setLac(20040);
		locCoor.setMcc(404);
		locCoor.setMnc(13);
		locationCoorMap.put("9989932764", locCoor);
		CurlProgram curlProg = new CurlProgram();
		curlProg.geolocaton(locationCoorMap.get("9989932764"));
	}
}
