package com.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TianyaTestByHtmlUnit2 {

	public static void main(String[] args) {
		try {
			WebClient webClient = new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);  
//			new SimpleConectionListener(webClient);
			
			// 加载的页面有js语法错误会抛出异常
			webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
			webClient.getOptions().setCssEnabled(false); // 禁用css支持
			// 设置Ajax异步处理控制器即启用Ajax支持
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			// 当出现Http error时，程序不抛异常继续执行
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			// 防止js语法错误抛出异常
			webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
 
			
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
	        password.setValueAttribute("waldos0000");
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
	        webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 灞忚斀骞垮憡寮€閫歏IP寮曞娴眰鈥斺€斿紑閫氫細鍛樼偣鍑?
		}
	}
}
