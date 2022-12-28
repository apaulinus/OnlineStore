package com.sss.onlinestore.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.util.Assert;

public class OnlineStoreUtil {

	public static String passwordDigest(String password) throws Exception{
		Assert.hasText(password, "Password must have a value");
		
		StringBuilder sb = new StringBuilder();
        try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

	        for (byte b : hashInBytes) {
	            sb.append(String.format("%02x", b));
	        }
	    } catch (Exception e) {
	    	throw new Exception("Password Error; Please check your password and try again.");
	    }
		return sb.toString();
	}
}
