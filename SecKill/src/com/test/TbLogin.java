package com.test;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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

public class TbLogin {

	private String url = "https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F";
	private String logInurl = "https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F";
//	private String AuthImgSrc = "";
	private String forwardUrl = "";
	private String checkUrl = "";
	private Map<String, String> paramMap = null;
	private static String LOGIN_NAME = "";
	private static String LOGIN_PSW = "";
//	private static String AuthCodeImgPath = "E://waldos//pic//code.jpg";

	public List<Cookie> doLogin() throws Exception {

		HttpHost proxy = new HttpHost("10.6.30.135", 8080);
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore)
				.build();
		// String rs = "";
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
//				AuthImgSrc = getImgsrcFromDoc(doc);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response1.close();
			}
//			:authority:login.taobao.com
//			:method:POST
//			:path:/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F
//			:scheme:https
//			accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//			accept-encoding:gzip, deflate
//			accept-language:zh-CN,zh;q=0.8
//			cache-control:no-cache
//			content-length:3317
//			content-type:application/x-www-form-urlencoded
//			cookie:_uab_collina=151296970760809616646104; cna=p8IiETC60noCAdJIIcjSrRGQ; thw=cn; _tb_token_=fbb54b918bef8; cookie2=6bf3224c5e49a7aced9ac5b4b4c498ff; t=9640d05a5f88413f369e54053c7265a3; _cc_=V32FPkk%2Fhw%3D%3D; tg=0; lc=Vyh61ll2vEZKjjuU; l=AqKiG2ArZVl6HU9dl5Ukq-XRciIE8qYN; mt=ci=0_0; v=0; _umdata=85957DF9A4B3B3E871C3670673E38D2C82D80A17A9A4ABCD3D3E0159C5BB342B2DC11ECAFCB5557FCD43AD3E795C914C4362CB97AFC09B93E4EBAEDFA243D044; isg=Aq-vDawFKQia2y00pI-CrguLPsO4VQJ3mNSMVME-wZ4lEMsSwiScxxaG5jfV
//			origin:https://login.taobao.com
//			pragma:no-cache
//			referer:https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F
//			upgrade-insecure-requests:1
//			user-agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36

			// 4 执行登录
			URI uri = new URI(logInurl);
			RequestBuilder builder = RequestBuilder.post().setUri(uri);
	        builder.setHeader(":authority", "login.taobao.com");  
	        builder.setHeader(":method", "POST");  
	        builder.setHeader(":path", "/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F");  
	        builder.setHeader(":scheme", "https"); 
	        builder.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");  
	        builder.setHeader("accept-encoding", "gzip, deflate");  
	        builder.setHeader("accept-language", "zh-CN,zh;q=0.8");  
	        builder.setHeader("cache-control", "no-cache");
	        
	        builder.setHeader("content-type", "application/x-www-form-urlencoded");  
	    	StringBuffer sb = new StringBuffer();
			for (Cookie c : cookieStore.getCookies()) {
				sb.append(c.getName() + "=" + c.getValue() + ";");
			}
			sb.deleteCharAt(sb.length() - 1);
			builder.setHeader("cookie", sb.toString());

			builder.setHeader("origin", "https://login.taobao.com");
			builder.setHeader("pragma", "no-cache");
			builder.setHeader("referer", "https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F");
	        builder.setHeader("upgrade-insecure-requests", "1");  
	        builder.setHeader("User-agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");  

//			System.out.println("输入验证码");
//			Scanner in = new Scanner(System.in);
//			String code = in.next();
//			in.close();
//			paramMap.put("authcode", code);
			// POST 参数
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				builder.addParameter(entry.getKey(), entry.getValue());
			}
			HttpUriRequest login = builder.build();
			CloseableHttpResponse response4 = httpclient.execute(login);
			try {
				
				HttpEntity entity = response4.getEntity();  
                String content = EntityUtils.toString(entity);  
//                EntityUtils.consume(entity);  
                Document doc = Jsoup.parse(content); 
                Elements es = doc.getElementsByTag("script");
                for (Element e : es) {
                	String s = e.toString();
//                	System.out.println(s);
					if (s.contains("self.location.href")) {
						forwardUrl = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
//						System.out.println("url: " + url);
					}
				}
//				JdLoginTest3.showResponseInfo(response4);
				// HttpEntity entity = response4.getEntity();
				// rs = EntityUtils.toString(entity);
				// EntityUtils.consume(entity);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response4.close();
			}
			
			builder = RequestBuilder.get().setUri(new URI(forwardUrl));
			
//			:authority:login.taobao.com
//			:method:GET
//			:path:/member/login_unusual.htm?user_num_id=395415567&is_ignore=&from=tb&style=default&popid=&callback=&minipara=&css_style=&is_scure=true&c_is_secure=&tpl_redirect_url=https%3A%2F%2Fwww.taobao.com%2F&cr=https%3A%2F%2Fwww.taobao.com%2F&trust_alipay=&full_redirect=&need_sign=&not_duplite_str=&from_encoding=&sign=&timestamp=&sr=false&guf=&sub=false&wbp=&wfl=null&allp=&loginsite=0&login_type=3&lang=zh_CN&key_login=false&new_iv_check=true&iv_check_time=1512969950252&iv_check_sign=449fadcc77272a9321c240fef4d20c43&param=
//			:scheme:https
//			accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//			accept-encoding:gzip, deflate, sdch
//			accept-language:zh-CN,zh;q=0.8
//			cache-control:no-cache
//			cookie:_uab_collina=151296970760809616646104; cna=p8IiETC60noCAdJIIcjSrRGQ; thw=cn; _tb_token_=fbb54b918bef8; cookie2=6bf3224c5e49a7aced9ac5b4b4c498ff; t=9640d05a5f88413f369e54053c7265a3; _cc_=V32FPkk%2Fhw%3D%3D; tg=0; lc=Vyh61ll2vEZKjjuU; l=AqKiG2ArZVl6HU9dl5Ukq-XRciIE8qYN; mt=ci=0_0; v=0; _umdata=85957DF9A4B3B3E871C3670673E38D2C82D80A17A9A4ABCD3D3E0159C5BB342B2DC11ECAFCB5557FCD43AD3E795C914C4362CB97AFC09B93E4EBAEDFA243D044; isg=AgYG7WIVoD9bGHRbNXyb-SqoV_yJZksgGdf1u_Av8ikE86YNWPeaMeyBvxjE
//			pragma:no-cache
//			referer:https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F
//			upgrade-insecure-requests:1
//			user-agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36
			
			builder.setHeader(":authority", "login.taobao.com");
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", "/member/login_unusual.htm?user_num_id=395415567&is_ignore=&from=tb&style=default&popid=&callback=&minipara=&css_style=&is_scure=true&c_is_secure=&tpl_redirect_url=https%3A%2F%2Fwww.taobao.com%2F&cr=https%3A%2F%2Fwww.taobao.com%2F&trust_alipay=&full_redirect=&need_sign=&not_duplite_str=&from_encoding=&sign=&timestamp=&sr=false&guf=&sub=false&wbp=&wfl=null&allp=&loginsite=0&login_type=3&lang=zh_CN&key_login=false&new_iv_check=true&iv_check_time=1512969950252&iv_check_sign=449fadcc77272a9321c240fef4d20c43&param=");
			builder.setHeader(":scheme", "https");
			builder.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			builder.setHeader("accept-encoding", "gzip, deflate");
			builder.setHeader("accept-language", "zh-CN,zh;q=0.8");
			builder.setHeader("cache-control", "no-cache");

//			builder.setHeader("content-type", "application/x-www-form-urlencoded");
			sb = new StringBuffer();
			for (Cookie c : cookieStore.getCookies()) {
				sb.append(c.getName() + "=" + c.getValue() + ";");
			}
			sb.deleteCharAt(sb.length() - 1);
			builder.setHeader("cookie", sb.toString());

//			builder.setHeader("origin", "https://login.taobao.com");
			builder.setHeader("pragma", "no-cache");
			builder.setHeader("referer",
					"https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F");
			builder.setHeader("upgrade-insecure-requests", "1");
			builder.setHeader("User-agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");  


			
			CloseableHttpResponse response5 = httpclient.execute(builder.build());
			try {
//				JdLoginTest3.showResponseInfo(response5);
				HttpEntity entity = response5.getEntity();  
                String content = EntityUtils.toString(entity);  
//                EntityUtils.consume(entity);  
                Document doc = Jsoup.parse(content); 
                Elements es = doc.getElementsByTag("iframe");
                for (Element e : es) {
//                	System.out.println(s);
                	if (!"".equals(e.attr("src"))) {
                		this.checkUrl = e.attr("src");
                	}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response1.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// httpclient.close();
		}

		return cookieStore.getCookies();
	}
	
    public static Map<String, String> getMapfromDoc(Document doc) {  
        Map<String, String> map = new HashMap<>();  
  
        Elements e = doc.getElementsByTag("form");  
  
        Elements ele = e.select("input");  
        for (Element element : ele) {  
            map.put(element.attr("name"), element.attr("value"));  
        }  
        map.put("TPL_username", LOGIN_NAME);  
        map.put("TPL_password", LOGIN_PSW);  
//        map.put("nloginpwd", LOGIN_PSW);  
  
        return map;  
    }
    
    public static void main(String[] args) {
    	TbLogin login = new TbLogin();
    	try {
			login.doLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
