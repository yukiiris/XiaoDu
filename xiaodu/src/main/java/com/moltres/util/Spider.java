package com.moltres.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.b3log.xiaov.service.QQService;

import com.alibaba.fastjson.JSONObject;

public class Spider extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject jsonObject = null;
	boolean isGet = false;
	public JSONObject getJSONObject()
	{
		while (true)
		{
			if (isGet)
			{
				break;
			}
		}
		isGet = false;
		return jsonObject;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		jsonObject = JSONObject.parseObject(request.getParameter(""));
	}
}
