package com.test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SuNingLoginTest {

	private Log logger = LogFactory.getLog(SuNingLoginTest.class);
	private WebClient client;
	private String username = "18600346762";
	private String kkk = "";

	private String filePath = "E://waldos//code.jpg";

	public static String getCode() {
		System.out.println("请输入验证码：");
		Scanner sc = new Scanner(System.in);
		String code = sc.nextLine();
		sc.close();
		return code;
	}

	public WebClient getClient() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME, "10.6.30.135", 8080);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		return webClient;
	}

	public void login() throws IOException {
		client = getClient();
		HtmlPage htmlPage = client.getPage(
				"https://mpassport.suning.com/ids/login?service=https%3A%2F%2Fscs.suning.com%2Fsps%2Fauth%3FtargetUrl%3Dhttp%253A%252F%252Fscs.suning.com%252Fsps%252Fmember%252Flogon.do&loginTheme=scs");//
		logger.info(htmlPage.asText());
		HtmlInput un = htmlPage.getHtmlElementById("username");
		un.setValueAttribute(username);
		HtmlInput pwd = htmlPage.getHtmlElementById("pwd");
		pwd.setValueAttribute(kkk);
		HtmlImage codeImg = htmlPage.getHtmlElementById("vcodeimg1");
		codeImg.saveAs(new File(filePath));
		logger.info("验证码存储位置：" + filePath);
		String code = getCode();
		HtmlInput codeInput = htmlPage.getHtmlElementById("validateCode");
		codeInput.setValueAttribute(code);
		ScriptResult res = htmlPage.executeJavaScript("javascript:logon();");
		HtmlPage page2 = (HtmlPage) res.getNewPage();
		System.out.println(page2.asXml());
		logger.info(page2.asText());
		if (page2.asText().contains("您好！欢迎回来！")) {
			logger.info("登录成功！");
		}
	}

	public static void main(String[] args) throws IOException {
		SuNingLoginTest test = new SuNingLoginTest();
		test.login();
	}
}
