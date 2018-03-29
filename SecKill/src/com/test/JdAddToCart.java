package com.test;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JdAddToCart {

	public static void main(String[] args) {
		try {
//			String itemUrl = "https://cart.jd.com/gate.action?pid=3142409&pcount=1&ptype=1";
			String itemUrl = "http://item.jd.com/3142409.html";
			
			JdLoginTest3 login = new JdLoginTest3();
			CloseableHttpClient hc = login.getHttpclient();
			List<Cookie> cs = login.doLogin();
			
			RequestBuilder b = RequestBuilder.get().setUri(new URI(itemUrl));
			
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
			b.setHeader("Host", "item.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", "http://search.jd.com/Search?keyword=%E8%93%9D%E6%BC%82&enc=utf-8&suggest=1.def.0.V12&wq=lanpiao&pvid=efe0d4cd45074361a5756144badfed85");
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			HttpUriRequest req = b.build();
//			JdLoginTest3.showRequestInfo(req);
			CloseableHttpResponse response = hc.execute(req);
//			JdLoginTest3.showResponseInfo(response);

			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			Document doc = Jsoup.parse(content);
			Element e = doc.getElementById("InitCartUrl");
			String href = "http:" + e.attr("href");            
			b = RequestBuilder.get().setUri(new URI(href));			
			b.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			b.setHeader("Accept-Encoding", "gzip, deflate, sdch");
			b.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
			b.setHeader("Cache-Control", "no-cache");
			sb = new StringBuffer();
			for (Cookie c : cs) {
				sb.append(c.getName() + "=" + c.getValue() + ";");
			}
			sb.deleteCharAt(sb.length() - 1);
			b.setHeader("Cookie", sb.toString());
			b.setHeader("Host", "cart.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", itemUrl);
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			req = b.build();
			response = hc.execute(req);
//			JdLoginTest3.showResponseInfo(response);

			String url = "https://cart.jd.com/gotoOrder.action?rd=" + Math.random();
			
//			Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//					Accept-Encoding:gzip, deflate, sdch
//					Accept-Language:zh-CN,zh;q=0.8
//					Cache-Control:no-cache
//					Cookie:__jdv=122270672|direct|-|none|-|1511917320790; TrackID=1EgSwEYcCVmc41a1zkRqpHuQTGYZY9iicVQ1cImQAApJF0sZuee1hztq3D8gst-PlYra0NAOWWBOBrIyXHNTazQ; pinId=lWBbMJe-kGw; pin=wksunwk; unick=wksunwk; _tp=M9kxLdSVBVfHXXrB0eq%2FpQ%3D%3D; _pst=wksunwk; ceshi3.com=103; user-key=a92f0ba0-e04d-46ae-a3c8-f897f3934772; ipLocation=%u5317%u4eac; thor=55F1F33EB548461BF813114C1631A3B1EDA5BEA8B1D194C23EBA9D3FC3B8A9C643A80FF5974E027647F0749D3CA6FAF10F4030BC683F1843C067F699FD18F3119F60BCC5C56FADDC5D818098767BCD64AE85576F24B1E622765768352A803CC2794E9511F9578D40D7B6A1D620D71198BA9E0E6CED9A114A272065EE5BCFEB46FA4A910E112473D9942386B00BE89359; cart-main=xx; cd=0; cn=21; __jda=122270672.1512961055394501821215.1512961055.1513057801.1513064434.4; __jdb=122270672.13.1512961055394501821215|4.1513064434; __jdc=122270672; ipLoc-djd=1-2808-51531-0.137716869; 3AB9D23F7A4B3C9B=SEYMXLVJVF5BCJHQXZBG2QI3ZCYXENDGTUNM66FACAGHDI3JIYAFPDSZG553V5YQK4LOZC7WP76VN3FH22JU6GU7LE; __jdu=1512961055394501821215
//					Host:cart.jd.com
//					Pragma:no-cache
//					Proxy-Connection:keep-alive
//					Referer:http://cart.jd.com/cart.action
//					Upgrade-Insecure-Requests:1
//					User-Agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36
			
			b = RequestBuilder.get().setUri(new URI(url));
			b.setConfig(RequestConfig.custom().setCircularRedirectsAllowed(true).build());
			b.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			b.setHeader("Accept-Encoding", "gzip, deflate, sdch");
			b.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
			b.setHeader("Cache-Control", "no-cache");
			sb = new StringBuffer();
			for (Cookie c : cs) {
				sb.append(c.getName() + "=" + c.getValue() + ";");
			}
			sb.deleteCharAt(sb.length() - 1);
			b.setHeader("Cookie", sb.toString());
//			b.setHeader("Host", "cart.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", "https://cart.jd.com/cart.action");
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			req = b.build();
			response = hc.execute(req);
//			JdLoginTest3.showResponseInfo(response);
			
			b = RequestBuilder.post().setUri(new URI("https://trade.jd.com/shopping/order/submitOrder.action"));
//			b.setConfig(RequestConfig.custom().setCircularRedirectsAllowed(true).build());
			b.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			b.setHeader("Accept-Encoding", "gzip, deflate, sdch");
			b.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
			b.setHeader("Cache-Control", "no-cache");
			sb = new StringBuffer();
			for (Cookie c : cs) {
				sb.append(c.getName() + "=" + c.getValue() + ";");
			}
			sb.deleteCharAt(sb.length() - 1);
			b.setHeader("Cookie", sb.toString());
			b.setHeader("Host", "trade.jd.com");
			b.setHeader("Origin", "https://trade.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", url);
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
	        Map<String, String> map = new HashMap<>();  
	        
	        Elements es = doc.getElementsByTag("form");  
	  
	        Elements ele = es.select("input");  
	        for (Element element : ele) {  
	            map.put(element.attr("name"), element.attr("value"));  
	        } 
	        //POST ²ÎÊý  
			for (Map.Entry<String, String> entry : map.entrySet()) {
				b.addParameter(entry.getKey(), entry.getValue());
			}
	        
			req = b.build();
			response = hc.execute(req);
			JdLoginTest3.showResponseInfo(response);
			
			
			hc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
