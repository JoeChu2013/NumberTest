package com.joe.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



public class JsonUtil {
	
	public static  MapBean parseJson(String mapData) {
		MapBean mapJson = JSON.parseObject(mapData,MapBean.class);
		return mapJson;
	}

}   
