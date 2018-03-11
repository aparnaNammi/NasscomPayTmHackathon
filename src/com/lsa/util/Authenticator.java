package com.lsa.util;

import java.util.HashMap;
import java.util.Map;

public class Authenticator {
	
	private static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("9989932764","Naga Jyothi");
		map.put("9059444485","Sharukh");
		map.put("9490167336","Uma");
		map.put("9059444485","Aparna");
	}
	
	

	
	public String authenticate(String username, String password) {
		if(map.containsKey(username)) {
			if(map.get(username).equalsIgnoreCase(password)) {
				return "success";
			} else {
				return "failure";
			}
		} else {
			return "signup";
		}
	}
	
}
