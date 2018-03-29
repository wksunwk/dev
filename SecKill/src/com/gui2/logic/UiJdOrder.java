package com.gui2.logic;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
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

import com.alibaba.fastjson.JSON;
import com.test.JdLoginTest3;
import com.util.JsonProcessor;

public class UiJdOrder {
	
	private CloseableHttpClient hc;
	
	private List<Cookie> cs;
	
	private String itemCode = null;
	
	private String refererUrl = null;
	
	public UiJdOrder() {
		init();
	}

	private void init() {
		hc = HttpClients.custom().setProxy(new HttpHost("10.6.30.135", 8080))
				.setDefaultCookieStore(new BasicCookieStore()).build();
		cs = JsonProcessor.getInstance().readJdCookies();
	}
	
	private void clientClose() {
		try {
			hc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addCart(String itemUrl) {
		try {
			itemCode = itemUrl.substring(itemUrl.lastIndexOf("/") + 1, itemUrl.lastIndexOf("."));
			// http://itemko.jd.com/itemShowBtn?callback=jQuery743999&skuId=251837&from=pc&_=1514186613468
			String url = "http://itemko.jd.com/itemShowBtn?callback=jQuery999999&skuId=" + itemCode + "&from=pc&_=" + System.currentTimeMillis();
			RequestBuilder b = RequestBuilder.get().setUri(new URI(url));
			
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
			b.setHeader("Host", "itemko.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", itemUrl);
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			HttpUriRequest req = b.build();
			CloseableHttpResponse response = hc.execute(req);

			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			content = content.substring(content.indexOf("(") + 1, content.lastIndexOf(")"));
			Map<String, String> m = JSON.parseObject(content, Map.class);
			url = "https:" + m.get("url");
			refererUrl = url;
//			Document doc = Jsoup.parse(content);
//			Element e = doc.getElementById("InitCartUrl");
//			String href = "http:" + e.attr("href"); 
//			String href = "https://divide.jd.com/user_routing?skuId=251837&sn=a01a4cdfb4dd888381b0920a178f0a12&from=pc";
//			if (count != 1) {
//				href = href.replace("pcount=1", "pcount=" + count);
//			}
			// https://item.jd.com/251837.html
			b = RequestBuilder.get().setUri(new URI(url));	
//			b.setConfig(RequestConfig.custom().setRedirectsEnabled(false).build());
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
			b.setHeader("Host", "divide.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", itemUrl);
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			req = b.build();
			response = hc.execute(req);
//			JdLoginTest3.showResponseInfo(response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	private void createOrder(int count) {
		try {
			/*
			 * 确认地址:
			 * https://marathon.jd.com/async/isSupportCodPayment.action?skuId=251837
			 * 支付方式：
			 * https://marathon.jd.com/async/calcuOrderPrice.action?skuId=251837&num=1
			 * 提交订单
			 * https://marathon.jd.com/seckill/submitOrder.action?skuId=251837&vid=			 * 
			 */
			
			
			
			// 确认地址
			String url = "https://marathon.jd.com/async/isSupportCodPayment.action?skuId=" + itemCode;
			
			RequestBuilder b = RequestBuilder.post().setUri(new URI(url));
			b.setConfig(RequestConfig.custom().setCircularRedirectsAllowed(true).build());
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
			b.setHeader("Host", "marathon.jd.com");
			b.setHeader("Origin", "https://marathon.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", refererUrl);
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			// orderParam.provinceId:1
//			orderParam.cityId:2808
//			orderParam.countyId:51531
//			orderParam.townId:			
			b.addParameter("orderParam.provinceId", "1");
			b.addParameter("orderParam.cityId", "2808");
			b.addParameter("orderParam.countyId", "51531");
			b.addParameter("orderParam.townId", "0");
			
			HttpUriRequest req = b.build();
			CloseableHttpResponse response = hc.execute(req);
//			JdLoginTest3.showResponseInfo(response);
			
			// 支付方式
			url = "https://marathon.jd.com/async/calcuOrderPrice.action?skuId=" + itemCode + "&num=" + count;
			
			b = RequestBuilder.post().setUri(new URI(url));
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
			b.setHeader("Host", "marathon.jd.com");
			b.setHeader("Origin", "https://marathon.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", refererUrl);
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
//			provinceId:1
//			cityId:2808
//			countyId:51531
//			townId:0
//			paymentType:4
//			codTimeType:3
		
			b.addParameter("provinceId", "1");
			b.addParameter("cityId", "2808");
			b.addParameter("countyId", "51531");
			b.addParameter("townId", "0");
			b.addParameter("paymentType", "4");
			b.addParameter("codTimeType", "3");
			
			req = b.build();
			response = hc.execute(req);
			JdLoginTest3.showResponseInfo(response);
//			
			
			// 提交订单
			url = "https://marathon.jd.com/seckill/submitOrder.action?skuId=" + itemCode + "&vid=";
			
			b = RequestBuilder.post().setUri(new URI(url));
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
			b.setHeader("Host", "marathon.jd.com");
			b.setHeader("Origin", "https://marathon.jd.com");
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", refererUrl);
			b.setHeader("Proxy-Connection", "keep-alive");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
//			orderParam.name:江夜
//			orderParam.addressDetail:腾龙家园三区15-4-202
//			orderParam.mobile:181****9371
//			orderParam.email:
//			orderParam.provinceId:1
//			orderParam.cityId:2808
//			orderParam.countyId:51531
//			orderParam.townId:
//			orderParam.paymentType:4
//			orderParam.password:
//			orderParam.invoiceTitle:4
//			orderParam.invoiceContent:1
//			orderParam.invoiceCompanyName:
//			orderParam.invoiceTaxpayerNO:
//			orderParam.usualAddressId:360143851
//			skuId:251837
//			num:1
//			orderParam.provinceName:北京
//			orderParam.cityName:房山区
//			orderParam.countyName:窦店镇
//			orderParam.townName:
//			orderParam.codTimeType:3
//			orderParam.mobileKey:da6f0a1c8a2a7d02331c0ccb4cef5abf
//			eid:SEYMXLVJVF5BCJHQXZBG2QI3ZCYXENDGTUNM66FACAGHDI3JIYAFPDSZG553V5YQK4LOZC7WP76VN3FH22JU6GU7LE
//			fp:496cc3e78731b404ad8567757d9bfbd5
		
			b.addParameter("provinceId", "1");
			b.addParameter("cityId", "2808");
			b.addParameter("countyId", "51531");
			b.addParameter("townId", "0");
			b.addParameter("paymentType", "4");
			b.addParameter("codTimeType", "3");
			
			req = b.build();
			response = hc.execute(req);
			JdLoginTest3.showResponseInfo(response);
			
//			b = RequestBuilder.post().setUri(new URI("https://trade.jd.com/shopping/order/submitOrder.action"));
////			b.setConfig(RequestConfig.custom().setCircularRedirectsAllowed(true).build());
//			b.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//			b.setHeader("Accept-Encoding", "gzip, deflate, sdch");
//			b.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//			b.setHeader("Cache-Control", "no-cache");
//			sb = new StringBuffer();
//			for (Cookie c : cs) {
//				sb.append(c.getName() + "=" + c.getValue() + ";");
//			}
//			sb.deleteCharAt(sb.length() - 1);
//			b.setHeader("Cookie", sb.toString());
//			b.setHeader("Host", "trade.jd.com");
//			b.setHeader("Origin", "https://trade.jd.com");
//			b.setHeader("Pragma", "no-cache");
//			b.setHeader("Referer", url);
//			b.setHeader("Proxy-Connection", "keep-alive");
//			b.setHeader("Upgrade-Insecure-Requests", "1");
//			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
//			
//	        Map<String, String> map = new HashMap<>();  
//	        HttpEntity entity = response.getEntity();
//			String content = EntityUtils.toString(entity);
//			EntityUtils.consume(entity);
//			Document doc = Jsoup.parse(content);
//	        Elements es = doc.getElementsByTag("form");  
//	  
//	        Elements ele = es.select("input");  
//	        for (Element element : ele) {  
//	            map.put(element.attr("name"), element.attr("value"));  
//	        } 
//	        //POST 参数  
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//				b.addParameter(entry.getKey(), entry.getValue());
//			}
//	        
//			req = b.build();
//			response = hc.execute(req);
//			JdLoginTest3.showResponseInfo(response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public void createOrder(String itemUrl, int count) {
		if (itemUrl != null && !"".equals(itemUrl)) {
			this.addCart(itemUrl);
			this.createOrder(count);
			this.clientClose();
		}
	}
}
