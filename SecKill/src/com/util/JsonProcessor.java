package com.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.cookie.Cookie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class JsonProcessor {
	
	public static final String USER_DIR = System.getProperty("user.dir") + File.separator;
	
	public static final String ENV_PARAMS_PATH = USER_DIR + "params" + File.separator;
	
	private static final String GLOBAL_PARAMS_FILENAME = ENV_PARAMS_PATH + "JdCookies.json";
	
	private static JsonProcessor instance = null;
	
	private JsonProcessor(){
	}
	
	public static synchronized JsonProcessor getInstance() {
		if (instance == null) {
			instance = new JsonProcessor();;
		}
		return instance;
	}
	
	public List<Cookie>  readJdCookies() {
		File f = new File(GLOBAL_PARAMS_FILENAME);
		if (!f.exists()) {
			return new ArrayList<Cookie>();
		}
		List<MyCookie> ms = this.parse(GLOBAL_PARAMS_FILENAME, MyCookie.class);
		List<Cookie> cookies = new ArrayList<>();
		for (MyCookie c : ms) {
			cookies.add(c);
		}
		return cookies;
	}
	
	public void writeJdCookies(List<Cookie> params) {
		this.persist(params, GLOBAL_PARAMS_FILENAME);
	}
	
	private <T> List<T> parse(String fileName, Class<T> clazz) throws JSONException {

		// 获取资产目录管理者
		InputStream is = null;
		try {
			// 读取json文件
			is = new FileInputStream(fileName);

			int len = -1;
			byte[] buf = new byte[1024];
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			while ((len = is.read(buf)) != -1) {
				os.write(buf, 0, len);
			}
			os.flush();
			return JSON.parseArray(os.toString("GB2312"), clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
//	private <T> T parse(String fileName, Class<T> clazz) throws JSONException {
//
//		// 获取资产目录管理者
//		InputStream is = null;
//		try {
//			// 读取json文件
//			is = new FileInputStream(fileName);
//
//			int len = -1;
//			byte[] buf = new byte[1024];
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			while ((len = is.read(buf)) != -1) {
//				os.write(buf, 0, len);
//			}
//			os.flush();
//			return JSON.parseObject(os.toString("GB2312"), clazz);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	private void persist(Object object, String fileName) {
		FileOutputStream output = null;
		OutputStreamWriter writer = null;
		try {
			String s = JSON.toJSONString(object);

			File f = new File(fileName);
			if (f.exists()) {
				f.delete();
			}
			f.createNewFile();
			output = new FileOutputStream(f);
			writer = new OutputStreamWriter(output, "GB2312");
			writer.write(s);
			writer.flush();
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
//	System.getProperty("java.version")                  1.6.0_10                                                        // Java 运行时环境供应商 
//	System.getProperty("java.vendor")                   Sun Microsystems Inc.                                           //  Java 运行时环境供应商
//	System.getProperty("java.vendor.url")               http://java.sun.com/                                            // Java 供应商的 URL 
//	System.getProperty("java.home")                     D:/Java/jdk1.6.0_10/jre                                         // Java 安装目录 
//	System.getProperty("java.vm.specification.version") 1.0                                                             // Java 虚拟机规范版本 
//	System.getProperty("java.vm.specification.vendor")  Sun Microsystems Inc.                                           //  Java 虚拟机规范供应商 
//	System.getProperty("java.vm.specification.name")    Java Virtual Machine Specification                              //  Java 虚拟机规范名称 
//	System.getProperty("java.vm.version")               11.0-b15                                                        //  Java 虚拟机实现版本 
//	System.getProperty("java.vm.vendor")                Sun Microsystems Inc.                                           //  Java 虚拟机实现供应商 
//	System.getProperty("java.vm.name")                  Java HotSpot(TM) Client VM                                      //  Java 虚拟机实现名称 
//	System.getProperty("java.specification.version")    1.6                                                             //  Java 运行时环境规范版本 
//	System.getProperty("java.specification.vendor")     Sun Microsystems Inc.                                           //  Java 运行时环境规范供应商 
//	System.getProperty("java.specification.name")       Java Platform API Specification                                 //  Java 运行时环境规范名称 
//	System.getProperty("java.class.version")            50.0                                                            //  Java 类格式版本号 
//	System.getProperty("java.class.path")               .;D:/Java/apache-tomcat-6.0.18/lib/servlet-api.jar;.........    //  Java 类路径 
//	System.getProperty("java.library.path")             D:/Java/jdk1.6.0_10/bin;.;C:/WINDOWS/Sun/...................    //  加载库时搜索的路径列表 
//	System.getProperty("java.io.tmpdir")                C:/DOCUME~1/HUANG~1.XIA/LOCALS~1/Temp/                          //  默认的临时文件路径 
//	System.getProperty("java.compiler")                 null                                                            //  要使用的 JIT 编译器的名称 
//	System.getProperty("java.ext.dirs")                 D:/Java/jdk1.6.0_10/jre/lib/ext;C:/WINDOWS/Sun/Java/lib/ext     //  一个或多个扩展目录的路径 
//	System.getProperty("os.name")                       Windows XP                                                      //  操作系统的名称 
//	System.getProperty("os.arch")                       x86                                                             //  操作系统的架构 
//	System.getProperty("os.version")                    5.1                                                             //  操作系统的版本 
//	System.getProperty("file.separator")                /                                                               //  文件分隔符（在 UNIX 系统中是“/”） 
//	System.getProperty("path.separator")                ;                                                               //  路径分隔符（在 UNIX 系统中是“:”） 
//	System.getProperty("line.separator")                                                                                //  行分隔符（在 UNIX 系统中是“/n”） 
//	System.getProperty("user.name")                     Huang.XiaoDong                                                  //  用户的账户名称 
//	System.getProperty("user.home")                     C:/Documents and Settings/huang.xiaodong                        //  用户的主目录 
//	System.getProperty("user.dir")                      D:/Java/testcode                                                //  用户的当前工作目录
}
