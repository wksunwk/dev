package com.test;

import java.util.Iterator;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Email163LoginTest {

	public static void main(String[] args) {
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

			new SimpleConectionListener(webClient);
			
			// ��ȡҳ��
			HtmlPage page = webClient.getPage("http://mail.163.com/");
//			HtmlElement frm = page.getHtmlElementById("frameforlogin");
////			System.out.println(frm.getChildNodes().size());
//			DomNodeList<DomNode> ns = frm.getChildNodes();
//			for (DomNode domNode : ns) {
//				System.out.println(domNode.asXml());
//			}
			
//			FrameWindow frm = page.getFrameByName("frameforlogin");
//			System.out.println(frm.);
			
			// ����form�����ֻ�ȡҳ�����Ҳ����ͨ����������ȡ��page.getForms().get(0)
			HtmlForm form = page.getFormByName("login163");
			
//			DomNodeList<HtmlElement> es = form.getElementsByTagName("input");
			System.out.println(form.hasChildNodes());
//			HtmlTextInput username = (HtmlTextInput) form.getInputByName("username");
//			HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
//			username.setValueAttribute("wksunwk");
//			password.setValueAttribute("");
//			HtmlButton button = (HtmlButton) page.getHtmlElementById("loginBtn");
//			HtmlPage retPage = (HtmlPage) button.click();
//			// �ȴ�JS����dom��ɻ�û�ԭ�����ҳ
//			webClient.waitForBackgroundJavaScript(10000);
//			// �����ҳ����
//			System.out.println(retPage.asXml());
//			// ��ȡcookie
//			Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//			;
//			Map<String, String> responseCookies = new HashMap<String, String>();
//			for (Cookie c : cookies) {
//				responseCookies.put(c.getName(), c.getValue());
//				System.out.println(c.getName() + ":" + c.getValue());
//			}

			// �ر�webclient
			webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
