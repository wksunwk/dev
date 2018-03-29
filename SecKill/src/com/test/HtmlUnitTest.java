package com.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HtmlUnitTest {

	public static void main(String[] args) {
		try {
			WebClient webClient=new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);
//			DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
//			credentialsProvider.addCredentials("username","password");
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			HtmlPage page = webClient.getPage("http://www.baidu.com");
//			HtmlDivision div=(HtmlDivision)page.getElementById("head");
//			HtmlDivision div = (HtmlDivision) page.getByXPath("//div").get(0);
//			System.out.println(div.asXml());
//			System.out.println(page.asText());
			HtmlForm form = page.getFormByName("f");
			HtmlSubmitInput button = form.getInputByValue("百度一下");
			HtmlTextInput textField = form.getInputByName("wd");
			textField.setValueAttribute("郑春雄");
			HtmlPage page2 = button.click();
			System.out.println(page2.asXml());
			
//			List<HtmlAnchor> achList = page.getAnchors();
//			for (HtmlAnchor ach : achList) {
//				if (ach.getHrefAttribute().indexOf("news") != -1) {
//					HtmlPage page3 = ach.click();
////					page3.executeJavaScript("alert(123);");
//					System.out.println(page3.asXml());
//				}
////				System.out.println(ach.getHrefAttribute());
//			}
//			webClient.closeAllWindows();
			webClient.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
