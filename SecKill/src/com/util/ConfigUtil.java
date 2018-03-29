package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	private static ConfigUtil instance = null;
	
	private Properties props = new Properties();
	
	private String fileName = JsonProcessor.ENV_PARAMS_PATH + "config.properties";
	
	private ConfigUtil() {
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            props.load(input);            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
	}
	
	public static synchronized ConfigUtil getInstance() {
		if (instance == null) {
			instance = new ConfigUtil();;
		}
		return instance;
	}
	
	public String getJdUserName() {
		String p = this.props.getProperty("jd.user.name");
		if (p == null || "".equals(p)) {
			p = "default";
		}
		return p;
	}
	
	public String getJdPassword() {
		String p = this.props.getProperty("jd.user.password");
		if (p == null || "".equals(p)) {
			p = "default";
		}
		return p;
	}
}
