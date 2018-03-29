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

		// ��ȡ�ʲ�Ŀ¼������
		InputStream is = null;
		try {
			// ��ȡjson�ļ�
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
//		// ��ȡ�ʲ�Ŀ¼������
//		InputStream is = null;
//		try {
//			// ��ȡjson�ļ�
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
	
//	System.getProperty("java.version")                  1.6.0_10                                                        // Java ����ʱ������Ӧ�� 
//	System.getProperty("java.vendor")                   Sun Microsystems Inc.                                           //  Java ����ʱ������Ӧ��
//	System.getProperty("java.vendor.url")               http://java.sun.com/                                            // Java ��Ӧ�̵� URL 
//	System.getProperty("java.home")                     D:/Java/jdk1.6.0_10/jre                                         // Java ��װĿ¼ 
//	System.getProperty("java.vm.specification.version") 1.0                                                             // Java ������淶�汾 
//	System.getProperty("java.vm.specification.vendor")  Sun Microsystems Inc.                                           //  Java ������淶��Ӧ�� 
//	System.getProperty("java.vm.specification.name")    Java Virtual Machine Specification                              //  Java ������淶���� 
//	System.getProperty("java.vm.version")               11.0-b15                                                        //  Java �����ʵ�ְ汾 
//	System.getProperty("java.vm.vendor")                Sun Microsystems Inc.                                           //  Java �����ʵ�ֹ�Ӧ�� 
//	System.getProperty("java.vm.name")                  Java HotSpot(TM) Client VM                                      //  Java �����ʵ������ 
//	System.getProperty("java.specification.version")    1.6                                                             //  Java ����ʱ�����淶�汾 
//	System.getProperty("java.specification.vendor")     Sun Microsystems Inc.                                           //  Java ����ʱ�����淶��Ӧ�� 
//	System.getProperty("java.specification.name")       Java Platform API Specification                                 //  Java ����ʱ�����淶���� 
//	System.getProperty("java.class.version")            50.0                                                            //  Java ���ʽ�汾�� 
//	System.getProperty("java.class.path")               .;D:/Java/apache-tomcat-6.0.18/lib/servlet-api.jar;.........    //  Java ��·�� 
//	System.getProperty("java.library.path")             D:/Java/jdk1.6.0_10/bin;.;C:/WINDOWS/Sun/...................    //  ���ؿ�ʱ������·���б� 
//	System.getProperty("java.io.tmpdir")                C:/DOCUME~1/HUANG~1.XIA/LOCALS~1/Temp/                          //  Ĭ�ϵ���ʱ�ļ�·�� 
//	System.getProperty("java.compiler")                 null                                                            //  Ҫʹ�õ� JIT ������������ 
//	System.getProperty("java.ext.dirs")                 D:/Java/jdk1.6.0_10/jre/lib/ext;C:/WINDOWS/Sun/Java/lib/ext     //  һ��������չĿ¼��·�� 
//	System.getProperty("os.name")                       Windows XP                                                      //  ����ϵͳ������ 
//	System.getProperty("os.arch")                       x86                                                             //  ����ϵͳ�ļܹ� 
//	System.getProperty("os.version")                    5.1                                                             //  ����ϵͳ�İ汾 
//	System.getProperty("file.separator")                /                                                               //  �ļ��ָ������� UNIX ϵͳ���ǡ�/���� 
//	System.getProperty("path.separator")                ;                                                               //  ·���ָ������� UNIX ϵͳ���ǡ�:���� 
//	System.getProperty("line.separator")                                                                                //  �зָ������� UNIX ϵͳ���ǡ�/n���� 
//	System.getProperty("user.name")                     Huang.XiaoDong                                                  //  �û����˻����� 
//	System.getProperty("user.home")                     C:/Documents and Settings/huang.xiaodong                        //  �û�����Ŀ¼ 
//	System.getProperty("user.dir")                      D:/Java/testcode                                                //  �û��ĵ�ǰ����Ŀ¼
}
