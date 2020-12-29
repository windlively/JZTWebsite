package com.xkdjzt.web.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Temp {
	public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "0000";
        int hashIterations = 3;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println(obj);
	}
}
