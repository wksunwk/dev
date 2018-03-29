package com.test;

import java.net.URI;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

public class JdOrderList {

	public static void main(String[] args) {
		// http://order.jd.com/center/list.action
		try {
			JdLoginTest3 login = new JdLoginTest3();
			CloseableHttpClient hc = login.getHttpclient();
			List<Cookie> cs = login.doLogin();
			
			RequestBuilder b = RequestBuilder.get().setUri(new URI("http://order.jd.com/center/list.action"));
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
			b.setHeader("Host", "order.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", "http://www.jd.com/");
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			HttpUriRequest req = b.build();
//			JdLoginTest3.showRequestInfo(req);
			CloseableHttpResponse response = hc.execute(req);
			JdLoginTest3.showResponseInfo(response);
			hc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
