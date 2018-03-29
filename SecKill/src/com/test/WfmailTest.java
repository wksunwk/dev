package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class WfmailTest {

	public static void main(String[] args) {
		// http://mail.wftech.com.cn/
		
		try {
			// 创建一个webclient
			WebClient webClient = new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);  

			// 参数设置
			// 1 启动JS
			webClient.getOptions().setJavaScriptEnabled(true);
			// 2 禁用Css，可避免自动二次请求CSS进行渲染
			webClient.getOptions().setCssEnabled(false);
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

			// 获取页面
			HtmlPage page = webClient.getPage("http://mail.wftech.com.cn/");
//			HtmlElement frm = page.getHtmlElementById("frameforlogin");
////			System.out.println(frm.getChildNodes().size());
//			DomNodeList<DomNode> ns = frm.getChildNodes();
//			for (DomNode domNode : ns) {
//				System.out.println(domNode.asXml());
//			}
			
//			FrameWindow frm = page.getFrameByName("frameforlogin");
//			System.out.println(frm.);
			
			// 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
			HtmlForm form = page.getFormByName("loginform");
			
//			DomNodeList<HtmlElement> es = form.getElementsByTagName("input");
//			System.out.println(page.asXml());
			HtmlTextInput username = (HtmlTextInput) form.getInputByName("user");
			HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
			username.setValueAttribute("sunwenke");
			password.setValueAttribute("");
			HtmlButton button = (HtmlButton) page.getHtmlElementById("submitBtn");
			HtmlPage retPage = (HtmlPage) button.click();
			// 等待JS驱动dom完成获得还原后的网页
			webClient.waitForBackgroundJavaScript(10000);
			// 输出网页内容
			System.out.println(retPage.asXml());
			// 获取cookie
			Set<Cookie> cookies = webClient.getCookieManager().getCookies();
			;
			Map<String, String> responseCookies = new HashMap<String, String>();
			for (Cookie c : cookies) {
				responseCookies.put(c.getName(), c.getValue());
				System.out.println(c.getName() + ":" + c.getValue());
			}

			// 关闭webclient
			webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
