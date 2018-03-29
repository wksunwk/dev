package com.test;

import java.util.List;

import org.apache.http.cookie.Cookie;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class JdMainTest {

	public static void main(String[] args) {
		try {
			JdLoginTest3 login = new JdLoginTest3();
			List<Cookie> cookies = login.doLogin();
			
			// 创建一个webclient
			WebClient webClient = new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);

			// 参数设置
			// 1 启动JS
			webClient.getOptions().setJavaScriptEnabled(true);
			// 2 禁用Css，可避免自动二次请求CSS进行渲染
			webClient.getOptions().setCssEnabled(true);
			// 3 启动客户端重定向
			webClient.getOptions().setRedirectEnabled(true);
			// 4 运行错误时，是否抛出异常
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			// 5 设置超时
			webClient.getOptions().setTimeout(50000);
			// 6 设置忽略证书
			// webClient.getOptions().setUseInsecureSSL(true);
			// 7 设置Ajax
			// webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			// 8设置cookie
			webClient.getCookieManager().setCookiesEnabled(true);

//			new SimpleConectionListener(webClient);

			 // 获取cookie
//			Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//			Map<String, String> responseCookies = new HashMap<String, String>();
//			for (Cookie c : cookies) {
//				responseCookies.put(c.getName(), c.getValue());
//			}
			CookieManager cm = webClient.getCookieManager();
			for (Cookie c : cookies) {
				cm.addCookie(transfer(c));
			}
			// 获取页面
			HtmlPage page = webClient.getPage("http://www.jd.com");
			
			// 等待JS驱动dom完成获得还原后的网页
			webClient.waitForBackgroundJavaScript(2000);
			System.out.println(page.asXml());
			// 关闭webclient
			webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static com.gargoylesoftware.htmlunit.util.Cookie transfer(Cookie c) {
		com.gargoylesoftware.htmlunit.util.Cookie cookie = new com.gargoylesoftware.htmlunit.util.Cookie(c.getDomain(),
				c.getName(), c.getValue(), c.getPath(), c.getExpiryDate(), c.isSecure(), true);
		return cookie;
	}
}
