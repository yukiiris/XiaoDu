package com.moltres.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SendUrl {

	public static String sendGet(String url, Map<String, String> parameters) {  
        String result = "";
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        String params = ""; 
        try { 
            
            java.net.URL connURL = new java.net.URL(url);  
           
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
                    .openConnection();  
            
            httpConn.setRequestProperty("Accept", "*/*");  
            httpConn.setRequestProperty("Connection", "Keep-Alive");  
            httpConn.setRequestProperty("User-Agent",  
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
            
            httpConn.connect();  
            
            Map<String, List<String>> headers = httpConn.getHeaderFields();  
            
            for (String key : headers.keySet()) {  
                System.out.println(key + "\t：\t" + headers.get(key));  
            }  
            
            in = new BufferedReader(new InputStreamReader(httpConn  
                    .getInputStream(), "UTF-8"));  
            String line;  
             
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
	
    protected static String doInBackground() {
    	OkHttpClient client = new OkHttpClient();

    	MediaType mediaType = MediaType.parse("application/octet-stream");
    	RequestBody body = RequestBody.create(mediaType, "{\"answer\":\"duenbo\",\"question\":\"whos is handsome\"}#{\"answer\":\"duenbo\",\"question\":\"who is the most handsome\"}#");
    	Request request = new Request.Builder()
    	  .url("http://119.23.233.101:8080/xiaov-master/update?type=question")
    	  .post(body)
    	  .addHeader("cache-control", "no-cache")
    	  .addHeader("postman-token", "1d894b75-b767-bb65-93c1-67ff05e7a9f7")
    	  .build();

    	try {
			Response response = client.newCall(request).execute();
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return request.toString();
    }
	
    public static String sendPost(String url) {  
        String result = "";// 返回的结果  
        BufferedReader in = null;// 读取响应输入流  
        PrintWriter out = null;  
        StringBuffer sb = new StringBuffer();// 处理请求参数  
        String params = "";// 编码之后的参数  
        String str = "{\"answer\":\"..\",\"question\":\"handsome duenbo\"}#{\"answer\":\"ss\",\"question\":\"duenbo is handsome\"}#";
            
            try {
				java.net.URL connURL = new java.net.URL(url);  
				
				java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
				        .openConnection();  
				
				httpConn.setRequestProperty("Accept", "*/*");  
				httpConn.setRequestProperty("Connection", "Keep-Alive");  
				httpConn.setRequestProperty("User-Agent",  
				        "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
				
				httpConn.setDoInput(true);  
				httpConn.setDoOutput(true);  
				
				out = new PrintWriter(httpConn.getOutputStream());  
				
				out.write(str);  
				
				out.flush();  
				
				in = new BufferedReader(new InputStreamReader(httpConn  
				        .getInputStream(), "UTF-8"));  
				String line;  
				
				while ((line = in.readLine()) != null) {  
				    result += line;  
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
           
        return result;  
    }  
    
    public static List<JSONObject> getJSONObject(String url)
    {
    	String string = sendGet(url, null);
    	if (string.contains("404 Not Found!"))
    	{
    		return null;
    	}
    	System.out.println(string);
    	String[] jsonStr = string.split("#");
    	List<JSONObject> objects = new ArrayList<>();
    	for (String s : jsonStr)
    	{
    		JSONObject json = JSONObject.parseObject(s);
    		objects.add(json);
    	}
    	return objects;
    }
    
    public static void main(String[] agrs)
    {
    	String content = "淘宝 女装 2017";
    	int index = content.indexOf(" ");
    	String url = null;
    	try {
    		url = "http://45.78.50.42:8080/" + URLEncoder.encode("今日头条", "UTF-8");
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//getJSONObject("http://45.78.50.42:8000/%E6%B7%98%E5%AE%9D%20%E7%AE%97%E6%B3%95%E5%AF%BC%E8%AE%BA");
    	//System.out.println(sendGet("http://45.78.50.42:8000/%E6%B7%98%E5%AE%9D%20%E7%AE%97%E6%B3%95%E5%AF%BC%E8%AE%BA", null));
    }
}
