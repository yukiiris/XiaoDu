package com.moltres.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.b3log.latke.servlet.HTTPRequestMethod;
import org.b3log.latke.servlet.annotation.RequestProcessing;
import org.b3log.latke.servlet.annotation.RequestProcessor;
import org.eclipse.jetty.util.UrlEncoded;

import com.alibaba.fastjson.JSONObject;
@RequestProcessor
public class Property extends HttpServlet{
	
	@Override
	  public void doGet(HttpServletRequest request,
              HttpServletResponse response)
      throws ServletException, IOException
	 {
		String type = request.getParameter("type");
	    byte[] bcache = new byte[2048];  
	    int readSize = 0;//每次读取的字节长度  
	    int totalSize = 0;//总字节长度  
	    ByteArrayOutputStream infoStream = new ByteArrayOutputStream();  
	    try {  
	        //一次性读取2048字节  
	        while ((readSize = request.getInputStream().read(bcache)) > 0) {  
	            totalSize += readSize;  
	            infoStream.write(bcache,0,readSize);  
	        }  
	    } catch (IOException e1) {  
	    } finally {  
	        try {  
	            //输入流关闭  
	            request.getInputStream().close();  
	        } catch (IOException e) {  
	        }  
	    }  
	    if (type.contains("active"))
		{
			updateAcitivity(infoStream.toString());
			return;
		}
    	String[] jsonStr = infoStream.toString().split("#");
    	List<JSONObject> objects = new ArrayList<>();
    	for (String s : jsonStr)
    	{
    		JSONObject json = JSONObject.parseObject(s);
    		objects.add(json);
    	}

		if (type.contains("question"))
		{
			try {
			
				updateQuestion(objects);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (type.contains("keyword"))
		{
			try {
				
				updateKeyWords(objects);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (type.contains("robot"))
		{
			try {
				updateUser(objects);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 }
	 
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 {
		 doGet(request, response);
	 }
	 
	 public static boolean updateName(String name)
	 {
		 Properties properties = getProperties();

		 properties.setProperty("qq.bot.name", name);   
	        // 文件输出流   
	        try {  
	        	//FileOutputStream fos = new FileOutputStream("/root/apache-tomcat-7.0.75-windows-x64/apache-tomcat-7.0.75/webapps/xiaov-master/WEB-INF/classes/xiaov.properties");

	            FileOutputStream fos = new FileOutputStream("C:\\Users\\asus\\Downloads\\xiaov-master\\xiaov-master\\src\\main\\resources\\xiaov.properties");   
	            // 将Properties集合保存到流中   
	            properties.store(fos, "Copyright (c) Boxcode Studio");   
	            fos.close();// 关闭流   
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } 
	        return true;
	 }
	 
	 public static boolean updateKeyWords(List<JSONObject> objects)
	 {
		 Properties properties = getProperties();
		 String words = "";
		 for (JSONObject s : objects)
		 {
			 words += UrlEncoded.encodeString(s.getString("keyword"));
			 words += ",";
		 }
		 properties.setProperty("bot.follow.keywords", words);
	        // 文件输出流   
	        try {  
	        	//FileOutputStream fos = new FileOutputStream("/root/apache-tomcat-7.0.75-windows-x64/apache-tomcat-7.0.75/webapps/xiaov-master/WEB-INF/classes/xiaov.properties");

	            FileOutputStream fos = new FileOutputStream("C:\\Users\\asus\\Downloads\\xiaov-master\\xiaov-master\\src\\main\\resources\\xiaov.properties");   
	            // 将Properties集合保存到流中   
	            properties.store(fos, "Copyright (c) Boxcode Studio");   
	            fos.close();// 关闭流   
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } 
	        return true;
	 }
	 
	 public static boolean updateUser(List<JSONObject> robotEntities)
	 {
		 String name = null;
		 String Usercnt = null;
		 String character = null;
		 String isActive = null;
		 for (JSONObject robotEntity : robotEntities)
		 {
			 name += UrlEncoded.encodeString(robotEntity.getString("name"));
			 name += ",";
			 Usercnt +=  UrlEncoded.encodeString(robotEntity.getString("stopSpeakingNum"));
			 Usercnt += ",";
			 character += UrlEncoded.encodeString(robotEntity.getString("character"));
			 character += ",";
			 isActive += UrlEncoded.encodeString(robotEntity.getString(""));
			 isActive += ",";
		 }
		 updateAcitivity(isActive);
		 updateCharacter(character);
		 updateName(name);
		 updateUserCnt(Usercnt);
		 return true;
	 }
	 
	 public static boolean updateUserCnt(String count)
	 {
		 Properties properties = getProperties();
		 properties.setProperty("qq.bot.pushGroupUserCnt", count);   
	        // 文件输出流   
	        try {  
	        	//FileOutputStream fos = new FileOutputStream("/root/apache-tomcat-7.0.75-windows-x64/apache-tomcat-7.0.75/webapps/xiaov-master/WEB-INF/classes/xiaov.properties");

	            FileOutputStream fos = new FileOutputStream("C:\\Users\\asus\\Downloads\\xiaov-master\\xiaov-master\\src\\main\\resources\\xiaov.properties");   
	            // 将Properties集合保存到流中   
	            properties.store(fos, "Copyright (c) Boxcode Studio");   
	            fos.close();// 关闭流   
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } 
	        return true;
	 }
	 
	 public static boolean getActive(int i)
	 {
		 Properties properties = getProperties();
		 String active = properties.getProperty("qq.bot.active");
		 String[] strings = active.split(",");
		 if (strings[i].equals(1 + ""))
		 {
			 return true;
		 }
		 return false;
	 }
	 
	 public static boolean updateAcitivity(String count)
	 {
		 Properties properties = getProperties(); 
 
	     String active = properties.getProperty("qq.bot.active");
	     String[] strings = active.split(",");
	     String[] strs = count.split(",");
	     
	     for (int i = 0; i < 5; i++)
	     {
	    	 if (strings[i].equals("0") && strs[i].equals("1"))
	    	 {
	    		 MyThread thread = new MyThread(i);
	    		 thread.start();
	    	 }
	     }
	        
		 properties.setProperty("qq.bot.active", count);   
	        // 文件输出流   
	        try {  
	            FileOutputStream fos = new FileOutputStream("C:\\Users\\asus\\Downloads\\xiaov-master\\xiaov-master\\src\\main\\resources\\xiaov.properties");   
	            // 将Properties集合保存到流中   
	            properties.store(fos, "Copyright (c) Boxcode Studio");   
	            fos.close();// 关闭流   
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } 
	        return true;
	 }
	 
	 public static boolean updateCharacter(String character)
	 {
		 Properties properties = getProperties();

		 properties.setProperty("qq.bot.type", character);   
	        // 文件输出流   
	        try {  
	        	//FileOutputStream fos = new FileOutputStream("/root/apache-tomcat-7.0.75-windows-x64/apache-tomcat-7.0.75/webapps/xiaov-master/WEB-INF/classes/xiaov.properties");
	            FileOutputStream fos = new FileOutputStream("C:\\Users\\asus\\Downloads\\xiaov-master\\xiaov-master\\src\\main\\resources\\xiaov.properties");   
	            // 将Properties集合保存到流中   
	            properties.store(fos, "Copyright (c) Boxcode Studio");   
	            fos.close();// 关闭流   
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return false;  
	        } 
	        return true;
	 }
	 
	 public static boolean updateQuestion(List<JSONObject> questions)
	 {
		 Properties properties = getProperties();
		 String question = "";
		 String answer = "";
		 for (JSONObject questionEntity : questions)
		 {
			 question += UrlEncoded.encodeString(questionEntity.getString("question"));
			 question += ",";
			 answer += UrlEncoded.encodeString(questionEntity.getString("answer"));
			 answer += ",";
		 }
		 properties.setProperty("bot.follow.question", question);
		 properties.setProperty("bot.follow.answer", answer);
        try { 
        	//FileOutputStream fos = new FileOutputStream("/root/apache-tomcat-7.0.75-windows-x64/apache-tomcat-7.0.75/webapps/xiaov-master/WEB-INF/classes/xiaov.properties");

            FileOutputStream fos = new FileOutputStream("C:\\Users\\asus\\Downloads\\xiaov-master\\xiaov-master\\src\\main\\resources\\xiaov.properties");   
            // 将Properties集合保存到流中   
            properties.store(fos, "Copyright (c) Boxcode Studio");   
            fos.close();// 关闭流   
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return false;  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return false;  
        } 
        System.out.println(getProperties().getProperty("bot.follow.question"));
		 return true;
	 }
	 
	 public static String getNames()
	 {
		 Properties properties = getProperties();
		 return properties.getProperty("qq.bot.name");
	 }
	 
	 public static String getQuestion()
	 {
		 Properties properties = getProperties();
		 return properties.getProperty("bot.follow.question");
	 }
	 
	 public static String getAnswer()
	 {
		 Properties properties = getProperties();
		 return properties.getProperty("bot.follow.answer");
	 }
	 
	 public static String getKeyword()
	 {
		 Properties properties = getProperties();
		 return properties.getProperty("bot.follow.keywords");
	 }
	 
	 public static String getType()
	 {
		 Properties properties = getProperties();
		 return properties.getProperty("qq.bot.type");
	 }
	 
	 public static String getUserCnt()
	 {
		 Properties properties = getProperties();
		 return properties.getProperty("qq.bot.pushGroupUserCnt");
	 }
	 
	 public static Properties getProperties()
	 {
	        Properties prop = new Properties();// 属性集合对象   
	        FileInputStream fis;  
	        try {  
	        	//fis = new FileInputStream("/root/apache-tomcat-7.0.75-windows-x64/apache-tomcat-7.0.75/webapps/xiaov-master/WEB-INF/classes/xiaov.properties");  
	            fis = new FileInputStream("C:\\Users\\asus\\Downloads\\xiaov-master\\xiaov-master\\src\\main\\resources\\xiaov.properties");  
	            prop.load(fis);// 将属性文件流装载到Properties对象中   
	            fis.close();// 关闭流  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return null;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            return null;  
	        }  
	        return prop;
	 }

	 
	 public static void main(String[] agrs)
	 {
		 System.out.println(getAnswer());
		 System.out.println(getKeyword());
		 System.out.println(getNames());
		 System.out.println(getQuestion());
		 System.out.println(getType());
		 System.out.println(getUserCnt());
	 }

}
