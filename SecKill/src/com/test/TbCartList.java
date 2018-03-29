package com.test;

import java.net.URI;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

public class TbCartList {

	public static void main(String[] args) {
		TbLogin2 login = new TbLogin2();
		try {
			List<Cookie> cs = login.doLogin();
			CloseableHttpClient hc = login.getHttpclient();
			
			String cartUrl = login.getCartUrl();

			RequestBuilder b = RequestBuilder.get().setUri(new URI("https:" + cartUrl));
			
//			:authority:cart.taobao.com
//			:method:GET
//			:path:/cart.htm?spm=a21bo.2017.1997525049.1.64e4167eB5U6w3&from=mini&ad_id=&am_id=&cm_id=&pm_id=1501036000a02c5c3739
//			:scheme:https
//			accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//			accept-encoding:gzip, deflate, sdch
//			accept-language:zh-CN,zh;q=0.8
//			cache-control:no-cache
//			cookie:cna=p8IiETC60noCAdJIIcjSrRGQ; thw=cn; l=AigohYN3j2/s35UzGXf-PQvEeBw6Coxb; _tb_token_=7073eef03e03e; v=0; uc3=sg2=AnPF7jfMt5LA2rHrJalTDnnO2yV5km7yScaHMry%2Bg9Q%3D&nk2=FPKxNSJprw%3D%3D&id2=UNky7FsX3m6L&vt3=F8dBzLWm4crDFea6hWg%3D&lg2=V32FPkk%2Fw0dUvg%3D%3D; existShop=MTUxMzA0NzI0NA%3D%3D; uss=BqtY98KgQeRTJzMqAPgFYODqujignkfdzXwAvcvehZbLjHVtfMSSWTSszg%3D%3D; lgc=wksunwk; tracknick=wksunwk; cookie2=231a84c676eec0638b6785af6c6b8eac; sg=k7b; mt=np=&ci=-1_1; cookie1=Aiggl%2FPRf8cMnfVI3HsfxtFwwS2lZf%2FcnpitvdDc2fU%3D; unb=395415567; skt=8c248c3930511e2a; t=9640d05a5f88413f369e54053c7265a3; _cc_=U%2BGCWk%2F7og%3D%3D; tg=0; _l_g_=Ug%3D%3D; _nk_=wksunwk; cookie17=UNky7FsX3m6L; swfstore=61656; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&cookie21=U%2BGCWk%2F7pY%2FF&cookie15=URm48syIIVrSKA%3D%3D&existShop=false&pas=0&cookie14=UoTdeA0EQDHPwg%3D%3D&cart_m=0&tag=8&lng=zh_CN; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0%26__ll%3D-1%26_ato%3D0; isg=Anl5FPnEB0eCxNvSHr3cMJEZiOVSh2w5UgYa8puu9KAfIpm049Z9COdw0BEv; ubn=p; ucn=unzbyun; whl=-1%260%260%261513047809231
//			pragma:no-cache
//			referer:https://www.taobao.com/
//			upgrade-insecure-requests:1
//			user-agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36

			b.setHeader(":authority", "cart.taobao.com");
			b.setHeader(":method", "GET");
			b.setHeader(":path", "/" + cartUrl.substring(2));
			b.setHeader(":scheme", "https");
			b.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			b.setHeader("accept-encoding", "gzip, deflate, sdch");
			b.setHeader("accept-language", "zh-CN,zh;q=0.8");
			b.setHeader("cache-control", "no-cache");
			
			
			StringBuffer sb = new StringBuffer();
			for (Cookie c : cs) {
				sb.append(c.getName() + "=" + c.getValue() + ";");
			}
			sb.deleteCharAt(sb.length() - 1);
			b.setHeader("Cookie", sb.toString());
			b.setHeader("Pragma", "no-cache");
			b.setHeader("Referer", "https://www.taobao.com/");
			b.setHeader("Upgrade-Insecure-Requests", "1");
			b.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
			
			HttpUriRequest req = b.build();
//			JdLoginTest3.showRequestInfo(req);
			CloseableHttpResponse response = hc.execute(req);
			JdLoginTest3.showResponseInfo(response);
			
			hc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
