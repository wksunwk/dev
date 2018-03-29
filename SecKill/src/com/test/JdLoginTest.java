package com.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class JdLoginTest {

	public static void main(String[] args) {
		// https://passport.jd.com/
		try {
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
			webClient.getOptions().setThrowExceptionOnScriptError(true);
			// 5 ���ó�ʱ
			webClient.getOptions().setTimeout(50000);
			// 6 ���ú���֤��
			// webClient.getOptions().setUseInsecureSSL(true);
			// 7 ����Ajax
			// webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			// 8����cookie
			webClient.getCookieManager().setCookiesEnabled(true);

//			new SimpleConectionListener(webClient);

			// ��ȡҳ�� // https://passport.jd.com/uc/login?ltype=logout//https://passport.jd.com/uc/loginService
			HtmlPage page = webClient.getPage("https://passport.jd.com/new/login.aspx?");
//			HtmlPage page = webClient.getPage("https://passport.jd.com/uc/login?ltype=logout");
//			System.out.println(page.asXml());
			// HtmlElement frm = page.getHtmlElementById("frameforlogin");
			//// System.out.println(frm.getChildNodes().size());
			// DomNodeList<DomNode> ns = frm.getChildNodes();
			// for (DomNode domNode : ns) {
			// System.out.println(domNode.asXml());
			// }

			// FrameWindow frm = page.getFrameByName("frameforlogin");
			// System.out.println(frm.);

			// ����form�����ֻ�ȡҳ�����Ҳ����ͨ����������ȡ��page.getForms().get(0)
//			HtmlForm form = page.getFormByName("formlogin");
			HtmlForm form = page.getForms().get(0);
//			System.out.println(form.asXml());

			// DomNodeList<HtmlElement> es = form.getElementsByTagName("input");
//			System.out.println(form.hasChildNodes());
//			HtmlHiddenInput saToken = form.getInputByName("sa_token");
//			saToken.setValueAttribute("B68C442BE645754F33277E701208059080DD726A94A73F76DEC3053A838549C06EB7D3797CE1C5BBE7C2B2EF9CA7D4675A6DF79DCA1D28DABEDC6CA705B37131E76D53542DB2E7ECF6EDDFF0E0D0BBABDBF6F846C3115C55D3204308AAA73405BDD837C388973FFD1712BAC12D427DBC63AC81140814BE2BB9865AD175B6CB0892217B3279A0E7F8453F5E5AF7738024406FA60B3A526866F9AC965F3E7E58D5D68EB3E56D421087E286C3C4010904FB73E3E00BE407D32DEDEA46569FBA9055AB96F8217F17DAB9525FC5767FC25AF6B26EB2CC515A9C93661048487F1DA3E833BDDB25E161AAA6BB080569A7DD83B4B512AECB55031C1FC7BCB661302B979FEFEDE72DE7165DB90D052FDD16927F0A621D4B382C35B0BF9EDD04DD5B6B4057C13563ACC3133415BCC6B5D14428FE4D442C468B2EF66CD6FBB3B837AEA550F3F9152579CC632FD59C1FDB21AE5FD41EFB278FABDDC7A4CAF50EF4E1EDC08829AD9A10150EA2C4F8DB1F3ECD681FCC3B");
//			HtmlHiddenInput uuid = form.getInputByName("uuid");
//			uuid.setValueAttribute("597fa1ef-14d0-45be-9d56-b117a70f967b");
//			HtmlHiddenInput eid = form.getInputByName("eid");
//			eid.setValueAttribute("");
//			HtmlHiddenInput sessionId = form.getInputByName("fp");
//			sessionId.setValueAttribute("");
//			HtmlHiddenInput token = form.getInputByName("_t");
//			token.setValueAttribute("_t");
//			HtmlHiddenInput loginType = form.getInputByName("loginType");
//			loginType.setValueAttribute("c");
//			HtmlHiddenInput pubKey = form.getInputByName("pubKey");
//			pubKey.setValueAttribute("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDC7kw8r6tq43pwApYvkJ5laljaN9BZb21TAIfT/vexbobzH7Q8SUdP5uDPXEBKzOjx2L28y7Xs1d9v3tdPfKI2LR7PAzWBmDMn8riHrDDNpUpJnlAGUqJG9ooPn8j7YNpcxCa1iybOlc2kEhmJn5uwoanQq+CA6agNkqly2H4j6wIDAQAB");
//			HtmlHiddenInput ran = form.getInputByName("PFWrfsjZgh");
//			ran.setValueAttribute("cGLsz");
			HtmlTextInput username = (HtmlTextInput) form.getInputByName("loginname");
			HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("nloginpwd");
			username.setValueAttribute("wksunwk");
			password.setValueAttribute("");
//			HtmlButton button = (HtmlButton) page.getHtmlElementById("loginBtn");
//			HtmlPage retPage = (HtmlPage) button.click();
//			HtmlAnchor anchor = page.getHtmlElementById("loginsubmit");
//			HtmlAnchor anchor = page.getAnchorByText("��????¼");
//			System.out.println(anchor.asXml());
//			HtmlPage retPage = (HtmlPage) anchor.click();
//			System.out.println(retPage.asXml());
			
			// �ȴ�JS����dom��ɻ�û�ԭ�����ҳ
//			webClient.waitForBackgroundJavaScript(10000);
			// �����ҳ����
//			System.out.println(retPage.asXml());
			ScriptResult res = page.executeJavaScript("javascript:loginSubmit({});");
			HtmlPage page2 = (HtmlPage) res.getNewPage();
			System.out.println(page2.asXml());
			// // ��ȡcookie
			// Set<Cookie> cookies = webClient.getCookieManager().getCookies();
			// ;
			// Map<String, String> responseCookies = new HashMap<String, String>();
			// for (Cookie c : cookies) {
			// responseCookies.put(c.getName(), c.getValue());
			// System.out.println(c.getName() + ":" + c.getValue());
			// }

			// �ر�webclient
			webClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
