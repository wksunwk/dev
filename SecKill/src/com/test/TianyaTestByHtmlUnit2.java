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
			
			// ���ص�ҳ����js�﷨������׳��쳣
			webClient.getOptions().setJavaScriptEnabled(true); // ����JS��������Ĭ��Ϊtrue
			webClient.getOptions().setCssEnabled(false); // ����css֧��
			// ����Ajax�첽���������������Ajax֧��
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			// ������Http errorʱ���������쳣����ִ��
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			// ��ֹjs�﷨�����׳��쳣
			webClient.getOptions().setThrowExceptionOnScriptError(false); // js���д���ʱ���Ƿ��׳��쳣
 
			
	        // �õ������ҳ  
	        HtmlPage page = webClient.getPage("https://passport.tianya.cn/login");  
	        
	        // �����û���������  
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
	  
	        // �ύ  
	        HtmlButton submit = (HtmlButton) page.getElementById("loginBtn");  
	        HtmlPage nextPage = submit.click();  
	        System.out.println(nextPage.asXml());
//	        LOGGER.debug(nextPage.asXml());
	        webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 屏蔽广告弢�通VIP引导浮层—��开通会员点�?
		}
	}
}
