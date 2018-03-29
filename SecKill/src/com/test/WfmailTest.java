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
			// ����һ��webclient
			WebClient webClient = new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);  

			// ��������
			// 1 ����JS
			webClient.getOptions().setJavaScriptEnabled(true);
			// 2 ����Css���ɱ����Զ���������CSS������Ⱦ
			webClient.getOptions().setCssEnabled(false);
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

			// ��ȡҳ��
			HtmlPage page = webClient.getPage("http://mail.wftech.com.cn/");
//			HtmlElement frm = page.getHtmlElementById("frameforlogin");
////			System.out.println(frm.getChildNodes().size());
//			DomNodeList<DomNode> ns = frm.getChildNodes();
//			for (DomNode domNode : ns) {
//				System.out.println(domNode.asXml());
//			}
			
//			FrameWindow frm = page.getFrameByName("frameforlogin");
//			System.out.println(frm.);
			
			// ����form�����ֻ�ȡҳ�����Ҳ����ͨ����������ȡ��page.getForms().get(0)
			HtmlForm form = page.getFormByName("loginform");
			
//			DomNodeList<HtmlElement> es = form.getElementsByTagName("input");
//			System.out.println(page.asXml());
			HtmlTextInput username = (HtmlTextInput) form.getInputByName("user");
			HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
			username.setValueAttribute("sunwenke");
			password.setValueAttribute("");
			HtmlButton button = (HtmlButton) page.getHtmlElementById("submitBtn");
			HtmlPage retPage = (HtmlPage) button.click();
			// �ȴ�JS����dom��ɻ�û�ԭ�����ҳ
			webClient.waitForBackgroundJavaScript(10000);
			// �����ҳ����
			System.out.println(retPage.asXml());
			// ��ȡcookie
			Set<Cookie> cookies = webClient.getCookieManager().getCookies();
			;
			Map<String, String> responseCookies = new HashMap<String, String>();
			for (Cookie c : cookies) {
				responseCookies.put(c.getName(), c.getValue());
				System.out.println(c.getName() + ":" + c.getValue());
			}

			// �ر�webclient
			webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
