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
			
			// ����һ��webclient
			WebClient webClient = new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);

			// ��������
			// 1 ����JS
			webClient.getOptions().setJavaScriptEnabled(true);
			// 2 ����Css���ɱ����Զ���������CSS������Ⱦ
			webClient.getOptions().setCssEnabled(true);
			// 3 �����ͻ����ض���
			webClient.getOptions().setRedirectEnabled(true);
			// 4 ���д���ʱ���Ƿ��׳��쳣
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			// 5 ���ó�ʱ
			webClient.getOptions().setTimeout(50000);
			// 6 ���ú���֤��
			// webClient.getOptions().setUseInsecureSSL(true);
			// 7 ����Ajax
			// webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			// 8����cookie
			webClient.getCookieManager().setCookiesEnabled(true);

//			new SimpleConectionListener(webClient);

			 // ��ȡcookie
//			Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//			Map<String, String> responseCookies = new HashMap<String, String>();
//			for (Cookie c : cookies) {
//				responseCookies.put(c.getName(), c.getValue());
//			}
			CookieManager cm = webClient.getCookieManager();
			for (Cookie c : cookies) {
				cm.addCookie(transfer(c));
			}
			// ��ȡҳ��
			HtmlPage page = webClient.getPage("http://www.jd.com");
			
			// �ȴ�JS����dom��ɻ�û�ԭ�����ҳ
			webClient.waitForBackgroundJavaScript(2000);
			System.out.println(page.asXml());
			// �ر�webclient
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
