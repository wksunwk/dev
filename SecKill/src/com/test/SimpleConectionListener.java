package com.test;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;

public class SimpleConectionListener extends FalsifyingWebConnection {

	private static final Log LOGGER = LogFactory.getLog(SimpleConectionListener.class);

	public SimpleConectionListener(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}

	@Override
	public WebResponse getResponse(WebRequest request) throws IOException {
		// �õ��������Ӧ��������ô�������ô�����ˣ�����д��

		WebResponse response = super.getResponse(request);
		String url = response.getWebRequest().getUrl().toString();

//		if (LOGGER.isDebugEnabled()) {
//			LOGGER.error("�����ļ����ӣ�" + url);
//		}
		System.err.println("�����ļ����ӣ�" + url);
		if (check(url)) {
			return createWebResponse(response.getWebRequest(), "", "application/javascript", 200, "Ok");
		}
		return response;
	}

	private boolean check(String url) {
		// TODO �������Լ����ж�ʲô��
		return false;
	}
}
