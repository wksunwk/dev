package com.login;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.util.ConfigUtil;
import com.util.JsonProcessor;

public class JdLogin {

	private String url = "https://passport.jd.com/uc/login?ltype=logout";  
    private String logInurl = "https://passport.jd.com/uc/loginService";  
//    private String showAuthCodeUrl = "https://passport.jd.com/uc/showAuthCode?r="  
//            + Math.random() + "&version=2015";  
    private String AuthImgSrc = "";  
    private Map<String, String> paramMap=null;  
    private static String LOGIN_NAME = "";  
    private static String LOGIN_PSW = "";  
    private static String AuthCodeImgPath = "E://waldos//pic//code.jpg";  
    static {  
        //读取配置文件  用户名 密码 验证码存储路径  
        LOGIN_NAME = ConfigUtil.getInstance().getJdUserName();  
        LOGIN_PSW = ConfigUtil.getInstance().getJdPassword();  
    }  
    
//    private CloseableHttpClient httpclient;
//    
//    private BasicCookieStore cookieStore;
    
//    public CloseableHttpClient getHttpclient() {
//		return httpclient;
//    }
    
    private void init() {
//    	HttpHost proxy = new HttpHost("10.6.30.135", 8080); 
//		cookieStore = new BasicCookieStore();
//		httpclient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build();
//		httpclient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
    }
    
    public JdLogin() {
    	init();
    }
    
    public List<Cookie> doLogin() throws Exception {  
  
    	HttpHost proxy = new HttpHost("10.6.30.135", 8080); 
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build();
//        String rs = "";  
        // 获取表单参数  
        try {  
            paramMap = new HashMap<>();  
            HttpGet httpget = new HttpGet(url);  
            CloseableHttpResponse response1 = httpclient.execute(httpget);  
            try {  
                HttpEntity entity = response1.getEntity();  
                String content = EntityUtils.toString(entity);  
                EntityUtils.consume(entity);  
                Document doc = Jsoup.parse(content);  
                paramMap = getMapfromDoc(doc);  
                AuthImgSrc = getImgsrcFromDoc(doc);  
            } catch(Exception e) {
            	e.printStackTrace();
            } finally {  
                response1.close();  
            }  
  
            // 2显示验证码     TODO 有没有好像都一样  
//          HttpUriRequest showAuthCode = RequestBuilder.post()  
//                  .setUri(new URI(showAuthCodeUrl))  
//                  .addParameter("loginName", LOGIN_NAME).build();  
//          CloseableHttpResponse response2 = httpclient.execute(showAuthCode);  
//          try {  
//              HttpEntity entity = response2.getEntity();  
//              EntityUtils.consume(entity);  
//          } finally {  
//              response2.close();  
//          }  
  
            // 3 获取验证码图片 并保存到指定路径  
			RequestBuilder builder = RequestBuilder.get().setUri(new URI("http:" + AuthImgSrc + "&yys=" + new Date().getTime()));
			SetHeradersForAuthImg(builder);
			HttpUriRequest getAuthCode = builder.build();
			CloseableHttpResponse response3 = httpclient.execute(getAuthCode);
			try {
				HttpEntity entity = response3.getEntity();
				FileUtils.copyInputStreamToFile(entity.getContent(), new File(AuthCodeImgPath));
				EntityUtils.consume(entity);
			} catch(Exception e) {
            	e.printStackTrace();
            } finally {
				response3.close();
			}  
            // 4 执行登录  
			URI uri = new URI(logInurl + "?uuid=" + paramMap.get("uuid") + "&type=logout" + "&r=" + Math.random()
					+ "&version=2015");
			RequestBuilder builder2 = RequestBuilder.post().setUri(uri);
			setHeadersForLogIn(builder2);
			System.out.println("输入验证码");
			Scanner in = new Scanner(System.in);
			String code = in.next();
			in.close();
			paramMap.put("authcode", code);
            //POST 参数  
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				builder2.addParameter(entry.getKey(), entry.getValue());
			}
			HttpUriRequest login = builder2.build();
			CloseableHttpResponse response4 = httpclient.execute(login);
			try {
				showResponseInfo(response4);
//				HttpEntity entity = response4.getEntity();
//				rs = EntityUtils.toString(entity);
//				EntityUtils.consume(entity);				
			} catch(Exception e) {
            	e.printStackTrace();
            } finally {
				response4.close();
			} 
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {  
            httpclient.close();  
        }  
        List<Cookie> cookies = cookieStore.getCookies();
        JsonProcessor.getInstance().writeJdCookies(cookies);
        return cookies;
    }  
  
    public static Map<String, String> getMapfromDoc(Document doc) {  
        Map<String, String> map = new HashMap<>();  
  
        Elements e = doc.getElementsByTag("form");  
  
        Elements ele = e.select("input");  
        for (Element element : ele) {  
            map.put(element.attr("name"), element.attr("value"));  
        }  
        map.put("loginname", LOGIN_NAME);  
        map.put("loginpwd", LOGIN_PSW);  
        map.put("nloginpwd", LOGIN_PSW);  
  
        return map;  
    }  
  
    public static String getImgsrcFromDoc(Document doc) {  
  
        Elements e = doc.getElementsByTag("img");  
        for (Element element : e) {  
            if (!"".equals(element.attr("src2")))  
                return element.attr("src2");  
        }  
  
        return "";  
    }  
      
    /**登录的请求头**/  
    public static void setHeadersForLogIn(RequestBuilder builder){  
        builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");  
        builder.setHeader("Accept-Encoding", "gzip, deflate, sdch");  
        builder.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");  
        builder.setHeader("Connection", "keep-alive");  
        builder.setHeader("Host", "passport.jd.com");  
        builder.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");  
    }  
      
    /**请求图片验证码的请求头**/  
    public static void SetHeradersForAuthImg(RequestBuilder builder){  
        builder.setHeader("Accept", "image/webp,*/*;q=0.8");  
        builder.setHeader("Accept-Encoding", "gzip, deflate, sdch");  
        builder.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");  
        builder.setHeader("Connection", "keep-alive");  
        builder.setHeader("Host", "authcode.jd.com");  
        builder.setHeader("Referer", "https://passport.jd.com/uc/login?ltype=logout");//必须。因为没弄这个浪费了很长时间  
        builder.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");  
    }  
    
	public static void showResponseInfo(HttpResponse hr) throws ParseException, IOException {

		System.out.println("响应状态行信息：" + hr.getStatusLine());
		System.out.println("---------------------------------------------------------------");

		System.out.println("响应头信息：");
		Header[] allHeaders = hr.getAllHeaders();
		for (int i = 0; i < allHeaders.length; i++) {
			System.out.println(allHeaders[i].getName() + ":" + allHeaders[i].getValue());
		}

		System.out.println("---------------------------------------------------------------");
		System.out.println("响应正文：");
		System.out.println(EntityUtils.toString(hr.getEntity()));

	}
	
	public static void showRequestInfo(HttpRequest hr) throws ParseException, IOException {

		System.out.println("请求状态行信息：" + hr.getRequestLine());
		System.out.println("---------------------------------------------------------------");

		System.out.println("请求头信息：");
		Header[] allHeaders = hr.getAllHeaders();
		for (int i = 0; i < allHeaders.length; i++) {
			System.out.println(allHeaders[i].getName() + ":" + allHeaders[i].getValue());
		}

		System.out.println("---------------------------------------------------------------");
//		System.out.println("请求参数：");
//		System.out.println(EntityUtils.toString(hr.getEntity()));

	}
	
	public static void main(String[] args) {
		JdLogin login = new JdLogin();
		try {
			login.doLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
