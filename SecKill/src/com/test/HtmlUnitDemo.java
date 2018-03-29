package com.test;

public class HtmlUnitDemo {

//	private WebClient webClient = null;
//
//	private int timeout = 50000;
//
//	public HtmlUnitDemo() {
//		this(null);
//	}
//
//	/**
//	 * Get����
//	 * 
//	 * @param url
//	 * @return
//	 * @throws Exception
//	 */
//	public byte[] sendGetRequest(String url) throws Exception {
//		WebRequest webRequest = new WebRequest(new URL(url));
//		webRequest.setHttpMethod(HttpMethod.GET);
//		return sendRequest(webRequest);
//	}
//
//	/**
//	 * Post ����
//	 * 
//	 * @param url
//	 * @param params
//	 * @return
//	 * @throws Exception
//	 */
//	public byte[] sendPostRequest(String url, Map<String, String> params) throws Exception {
//		WebRequest webRequest = new WebRequest(new URL(url));
//		webRequest.setHttpMethod(HttpMethod.POST);
//		if (params != null && params.size() > 0) {
//			for (Entry<String, String> param : params.entrySet()) {
//				webRequest.getRequestParameters().add(new NameValuePair(param.getKey(), param.getValue()));
//			}
//		}
//		return sendRequest(webRequest);
//	}
//
//	// �ײ�����
//	private byte[] sendRequest(WebRequest webRequest) throws Exception {
//		byte[] responseContent = null;
//		Page page = webClient.getPage(webRequest);
//
//		WebResponse webResponse = page.getWebResponse();
//
//		int status = webResponse.getStatusCode();
//
//		System.out.println("Charset : " + webResponse.getContentCharset());
//
//		System.out.println("ContentType : " + webResponse.getContentType());
//
//		// ��ȡ��������
//		if (status == 200) {
//			if (page.isHtmlPage()) {
//				// �ȴ�JSִ�����
//				webClient.waitForBackgroundJavaScript(100000);
//				responseContent = ((HtmlPage) page).asXml().getBytes();
//			} else {
//				InputStream bodyStream = webResponse.getContentAsStream();
//				responseContent = ByteStreams.toByteArray(bodyStream);
//				bodyStream.close();
//			}
//		}
//		// �ر���Ӧ��
//		webResponse.cleanUp();
//
//		return responseContent;
//	}
//
//	public HtmlUnitDemo(HttpProxy proxy) {
//		webClient = new WebClient();
//		configWebClient();
//		// ���ô���
//		if (proxy != null) {
//			setProxy(proxy);
//		}
//	}
//
//	private void configWebClient() {
//		// ����webClient����ز���
//		// 1 ����JS
//		webClient.getOptions().setJavaScriptEnabled(true);
//		// 2 ����Css���ɱ����Զ���������CSS������Ⱦ
//		webClient.getOptions().setCssEnabled(false);
//		// 3 �����ͻ����ض���
//		webClient.getOptions().setRedirectEnabled(true);
//
//		// 4 js���д���ʱ���Ƿ��׳��쳣
//		webClient.getOptions().setThrowExceptionOnScriptError(false);
//		// 5 ���ó�ʱ
//		webClient.getOptions().setTimeout(timeout);
//	}
//
//	private void setProxy(HttpProxy proxy) {
//		ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
//		proxyConfig.setProxyHost(proxy.getHost());
//		proxyConfig.setProxyPort(proxy.getPort());
//
//		DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
//				.getCredentialsProvider();
//		credentialsProvider.addCredentials(proxy.getUser(), proxy.getPassword());
//	}
//
//	@SuppressWarnings("unused")
//	private Map<String, String> getResponseCookies() {
//		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//		Map<String, String> responseCookies = Maps.newHashMap();
//		for (Cookie c : cookies) {
//			responseCookies.put(c.getName(), c.getValue());
//		}
//		return responseCookies;
//	}
//
//	@SuppressWarnings("unused")
//	private void setCookies(String domain, Map<String, String> cookies) {
//		if (cookies != null && cookies.size() > 0) {
//			webClient.getCookieManager().setCookiesEnabled(true);// enable
//																	// cookies
//			for (Entry<String, String> c : cookies.entrySet()) {
//				Cookie cookie = new Cookie(domain, c.getKey(), c.getValue());
//				webClient.getCookieManager().addCookie(cookie);
//
//				System.out.println("Set Cookies : " + c.getKey() + " | " + c.getValue());
//			}
//		}
//	}
//
//	/**
//	 * �������cookie
//	 */
//	public void clearCookies() {
//		webClient.getCookieManager().clearCookies();
//	}
//
//	public void shutdown() throws IOException {
//		webClient.closeAllWindows();
//	}
//
//	/**
//	 * ��google �����ٶ�
//	 * 
//	 * @param args
//	 * @throws Exception
//	 */
//	public void demo() throws Exception {
//		String url = "http://www.google.com.hk";
//
//		final WebClient webClient = new WebClient();
//		HtmlPage htmlPage = webClient.getPage(url);
//
//		// HtmlUnit domģ��
//		// ��ȡ�� ,���form��ǩname����=f
//		HtmlForm form = htmlPage.getFormByName("f");
//		// ��ȡ�����, ��ȡ input��ǩ ��name����=q
//		HtmlTextInput text = form.getInputByName("q");
//		// �����ٶ�
//		text.setText("baidu");
//		// ��ȡ�ύ��ť
//		HtmlSubmitInput button = form.getInputByName("btnG");
//		// �ύ��
//		HtmlPage listPage = button.click();
//
//		System.out.println(listPage.asXml());
//		webClient.closeAllWindows();
//	}
//
//	/**
//	 * ��google �����ٶ�
//	 * 
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void main(String[] args) throws Exception {
//		String url = "http://www.google.com.hk";
//
//		HtmlUnitDemo htmlUnit = new HtmlUnitDemo();
//		byte[] getResponse = htmlUnit.sendGetRequest(url);
//		System.out.println("Get Body : " + new String(getResponse, "utf-8"));
//		byte[] postResponse = htmlUnit.sendPostRequest(url, null);
//		System.out.println("Get Body : " + new String(postResponse, "utf-8"));
//
//		htmlUnit.shutdown();
//	}
}
