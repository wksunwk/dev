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
		// 得到了这个响应，你想怎么处理就怎么处理了，不多写了

		WebResponse response = super.getResponse(request);
		String url = response.getWebRequest().getUrl().toString();

//		if (LOGGER.isDebugEnabled()) {
//			LOGGER.error("下载文件链接：" + url);
//		}
		System.err.println("下载文件链接：" + url);
		if (check(url)) {
			return createWebResponse(response.getWebRequest(), "", "application/javascript", 200, "Ok");
		}
		return response;
	}

	private boolean check(String url) {
		// TODO 加入你自己的判断什么的
		return false;
	}
}
