package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class TianyaTestByHtmlUnit {
	
	public static void main(String[] args) {
		try {
			WebClient webClient = new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);  
            //参数设置  
            // 1 启动JS  
            webClient.getOptions().setJavaScriptEnabled(true);  
            // 2 禁用Css，可避免自动二次请求CSS进行渲染  
            webClient.getOptions().setCssEnabled(false);  
            //3 启动客户端重定向  
            webClient.getOptions().setRedirectEnabled(true);  
//            // 4 运行错误时，是否抛出异常  
//            webClient.getOptions().setThrowExceptionOnScriptError(false);  
//            // 5 设置超时  
//            webClient.getOptions().setTimeout(50000);  
//            //6 设置忽略证书  
//            //webClient.getOptions().setUseInsecureSSL(true);  
//            //7 设置Ajax  
//            //webClient.setAjaxController(new NicelyResynchronizingAjaxController());  
            //8设置cookie  
//            webClient.getCookieManager().setCookiesEnabled(true); 
            
//			new SimpleConectionListener(webClient);
	        // 拿到这个网页  
	        HtmlPage page = webClient.getPage("https://passport.tianya.cn/login");  
	        
	        // 填入用户名和密码  
//	        HtmlInput username = (HtmlInput) page.getElementById("vwriter");  
//	        username.type("waldos");  
//	        HtmlInput password = (HtmlInput) page.getElementById("vpassword");  
//	        password.type("waldos0000"); 
	        HtmlForm form = page.getFormByName("loginform");
	        HtmlInput username = form.getInputByName("vwriter");
	        username.setValueAttribute("waldos");
	        HtmlInput password = form.getInputByName("vpassword");
	        password.setValueAttribute("");
	        HtmlHiddenInput fowardURL = form.getInputByName("fowardURL");
	        fowardURL.setValueAttribute("http://www.tianya.cn");
//	        fowardURL.setValueAttribute("http%3A%2F%2Fwww.tianya.cn%2F1764043");
	        // &userthird=&regOrlogin=%E7%99%BB%E5%BD%95%E4%B8%AD......&t=1510906523269&k=84618e055aef3f872da6573c5d360e42&c=e9788727e3a006df5c59d53dee001147
	        
//	        HtmlHiddenInput from = form.getInputByName("from");
//	        from.setValueAttribute("");
	        
	        HtmlHiddenInput sid = form.getInputByName("__sid");
	        sid.setValueAttribute("1#1#1.0#db4826bf-8098-456d-a33e-b4d533c66120");
//	        HtmlHiddenInput method = form.getInputByName("method");
//	        method.setValueAttribute("name");
//	        HtmlHiddenInput rmflag = form.getInputByName("rmflag");
//	        rmflag.setValueAttribute("1");
	  
	        // 提交  
	        HtmlButton submit = (HtmlButton) page.getElementById("loginBtn");  
	        HtmlPage nextPage = submit.click();  
	        System.out.println(nextPage.asXml());
//	        LOGGER.debug(nextPage.asXml());
	        
			// 获取cookie
			Set<Cookie> cookies = webClient.getCookieManager().getCookies();
			Map<String, String> responseCookies = new HashMap<String, String>();
			for (Cookie c : cookies) {
				responseCookies.put(c.getName(), c.getValue());
				System.out.println(c.getName() + ":" + c.getValue());
			}
	        webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
