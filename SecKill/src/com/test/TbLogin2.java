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

public class TbLogin2 {
	private String url = "https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F";
	private String logInurl = "https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F";
//	private String AuthImgSrc = "";
	private String forwardUrl = "https://www.taobao.com/";
//	private String checkUrl = "";
	private Map<String, String> paramMap = null;
	private static String LOGIN_NAME = "";
	private static String LOGIN_PSW = "";
	private CloseableHttpClient httpclient = null;
//	private static String AuthCodeImgPath = "E://waldos//pic//code.jpg";
	
	private String cartUrl = null;

	public List<Cookie> doLogin() throws Exception {

		HttpHost proxy = new HttpHost("10.6.30.135", 8080);
		BasicCookieStore cookieStore = new BasicCookieStore();
		httpclient = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore)
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
			
//			:host:www.taobao.com
//			:method:GET
//			:path:/
//			:scheme:https
//			:version:HTTP/1.1
//			accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//			accept-encoding:gzip, deflate, sdch
//			accept-language:zh-CN,zh;q=0.8
//			cache-control:no-cache
//			cookie:cna=p8IiETC60noCAdJIIcjSrRGQ; thw=cn; l=AigohYN3j2/s35UzGXf-PQvEeBw6Coxb; _tb_token_=7073eef03e03e; v=0; uc1=cookie14=UoTdeA0ERUvotw%3D%3D&lng=zh_CN&cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&existShop=false&cookie21=URm48syIYn73&tag=8&cookie15=Vq8l%2BKCLz3%2F65A%3D%3D&pas=0; uc3=sg2=AnPF7jfMt5LA2rHrJalTDnnO2yV5km7yScaHMry%2Bg9Q%3D&nk2=FPKxNSJprw%3D%3D&id2=UNky7FsX3m6L&vt3=F8dBzLWm4cd3%2FuDZYCE%3D&lg2=UtASsssmOIJ0bQ%3D%3D; existShop=MTUxMzA0MjI0Ng%3D%3D; uss=BqtY98KgQeRTJzMqAPgFYODqujignkfdzXwAvcvehZbLjHVtfMSSWTSszg%3D%3D; lgc=wksunwk; tracknick=wksunwk; cookie2=231a84c676eec0638b6785af6c6b8eac; sg=k7b; mt=np=&ci=0_0; cookie1=Aiggl%2FPRf8cMnfVI3HsfxtFwwS2lZf%2FcnpitvdDc2fU%3D; unb=395415567; skt=d4f8f931fb9f9f57; t=9640d05a5f88413f369e54053c7265a3; _cc_=WqG3DMC9EA%3D%3D; tg=0; _l_g_=Ug%3D%3D; _nk_=wksunwk; cookie17=UNky7FsX3m6L; isg=ApqaMYuglLrxzxgXwWDP_TZM60C9yh-klTMZd6QSPy34FzlRgFswtNAlk9Rw
//			pragma:no-cache
//			referer:https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F
//			upgrade-insecure-requests:1
//			user-agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36			
			
			builder.setHeader(":host", "www.taobao.com");
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", "/");
			builder.setHeader(":scheme", "https");
			builder.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			builder.setHeader("accept-encoding", "gzip, deflate, sdch");
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
                Elements es = doc.getElementsByTag("a");
                for (Element e : es) {
//                	System.out.println(s);
                	String href = e.attr("href");
                	if (href.contains("cart.taobao.com")) {
                		this.cartUrl = href;
                	}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response5.close();
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
    
    public CloseableHttpClient getHttpclient() {
		return httpclient;
	}

	public String getCartUrl() {
		return cartUrl;
	}

	public static void main(String[] args) {
    	TbLogin2 login = new TbLogin2();
    	try {
			login.doLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
