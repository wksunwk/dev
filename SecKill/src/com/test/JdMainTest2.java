package com.test;

import java.net.URI;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

public class JdMainTest2 {

	public static void main(String[] args) {
		try {
			JdLoginTest3 login = new JdLoginTest3();
			CloseableHttpClient hc = login.getHttpclient();
			List<Cookie> cs = login.doLogin();
			
			RequestBuilder b = RequestBuilder.get().setUri(new URI("https://www.jd.com"));
//			Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//					Accept-Encoding:gzip, deflate, sdch
//					Accept-Language:zh-CN,zh;q=0.8
//					Cache-Control:no-cache
//					Cookie:__jdv=122270672|direct|-|none|-|1511917320790; o2-webp=true; TrackID=17iJaBQde8243T95HjX-whMUyKOs96PUrdUjpKGwTDtFbW2hgDi-GY4Y9i35Wba7W2Ynno2Q-Y3LbgqMOo8qHgA; pinId=lWBbMJe-kGw; pin=wksunwk; unick=wksunwk; _tp=M9kxLdSVBVfHXXrB0eq%2FpQ%3D%3D; _pst=wksunwk; userInfo2016=1; _jrda=2; 3AB9D23F7A4B3C9B=AMZRBH65CCPEWLGZM6WD6MV7BOIMJ4KE6PRNOM3FYPRZJ5HOTV4NQ7JXAY3YFXQCKVHLVXOOODZANHSQ2L5BMB7XA4; __jda=122270672.72845304.1512443015.1512527214.1512545925.2; __jdb=122270672.1.72845304|2.1512545925; __jdc=122270672; __jdu=72845304
//					Host:www.jd.com
//					Pragma:no-cache
//					Proxy-Connection:keep-alive
//					Upgrade-Insecure-Requests:1
//					User-Agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36
			b.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			b.setHeader("Accept-Encoding", "gzip, deflate, sdch");
			b.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
			b.setHeader("Cache-Control", "no-cache");
			StringBuffer sb = new StringBuffer();
			for (Cookie c : cs) {
				sb.append(c.getName() + "=" + c.getValue() + ";");
			}
			sb.deleteCharAt(sb.length() - 1);
			b.setHeader("Cookie", sb.toString());
			b.setHeader("Host", "www.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			HttpUriRequest req = b.build();
//			HttpGet req = new HttpGet(new URI("https://www.jd.com")); 
			JdLoginTest3.showRequestInfo(req);
			CloseableHttpResponse response = hc.execute(req);
			JdLoginTest3.showResponseInfo(response);
			hc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
