package com.test;

public class JdLoginTest2 {
//	CloseableHttpClient httpClient = null;
//	Fromdata form = new Fromdata();
//	String loginUrl = "http://passport.jd.com/uc/login";
//	String redirectURL = "http://order.jd.com/center/list.action";
//	String home = "http://home.jd.com/";
//
//	public void initpage(String username, String pwd) {
//		String url = "http://passport.jd.com/uc/login";
//		HttpGet httpPost = new HttpGet(url);
//		httpPost.setHeader("user-agent",
//				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36");
//		String html = "";
//		try {
//			CloseableHttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entity = response.getEntity();
//			html = EntityUtils.toString(entity, "utf-8");
//			Elements els = Jsoup.parse(html).getElementsByTag("input");
//			for (Element e : els) {
//				String value = e.val();
//				String name = e.attr("name");
//				if (!"uuid".equals(name) && !"machineNet".equals(name) && !"machineCpu".equals(name)
//						&& !"machineDisk".equals(name) && !"eid".equals(name) && !"fp".equals(name)
//						&& !"_t".equals(name) && !"loginType".equals(name) && !"loginname".equals(name)
//						&& !"nloginpwd".equals(name) && !"loginpwd".equals(name) && !"chkRememberMe".equals(name)
//						&& !"authcode".equals(name)) {
//					form.key = name;
//					form.value = value;
//				}
//				form.init(name, value);
//				form.setLoginname(username);
//				form.setNloginpwd(pwd);
//				form.setLoginpwd(pwd);
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(html);
//	}
//
//	public void login() {
//		String url = UrlConfig.loginurl;
//		url = url.replace("#{uuid}", this.form.getUuid());
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.setHeader("user-agent",
//				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36");
//
//		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(this.form.getbase(), Consts.UTF_8);
//		try {
//			System.out.println(EntityUtils.toString(entity, "utf-8"));
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		httpPost.setEntity(entity);
//		String html = "";
//		try {
//			CloseableHttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entitySort = response.getEntity();
//			html = EntityUtils.toString(entitySort, "utf-8");
//
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("++++++\t" + html);
//
//	}
//
//	public void getImage() {
//		String url = UrlConfig.imageurl;
//		System.out.println(url);
//		url = url.replace("#{acid}", this.form.getUuid()).replace("#{uid}", this.form.getUuid()).replace("#{time}",
//				System.currentTimeMillis() + "");
//
//		System.out.println(url);
//		try {
//
//			HttpGet httpimg = new HttpGet(url);
//			httpimg.setHeader("Connection", "keep-alive");
//			httpimg.setHeader("Host", "authcode.jd.com");
//			httpimg.setHeader("Referer", "https://passport.jd.com/uc/login?ltype=logout");
//			httpimg.setHeader("user-agent",
//					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36");
//
//			HttpResponse responseimg = httpClient.execute(httpimg);
//			File file = new File("D:\\ss.gif");
//			OutputStream out = new FileOutputStream(file);
//			responseimg.getEntity().writeTo(out);
//			out.close();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public String getpage_1(String url) {
//		HttpGet httpPost = new HttpGet(url);
//		httpPost.setHeader("user-agent",
//				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36");
//		try {
//			CloseableHttpResponse response = httpClient.execute(httpPost);
//			BufferedHeader locationHeader = (BufferedHeader) response.getFirstHeader("Location");
//			if (locationHeader == null) {
//				return null;
//			}
//			return locationHeader.getValue();
//
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public void getpage_2(String url) {
//		HttpGet httpPost = new HttpGet(url);
//		httpPost.setHeader("user-agent",
//				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36");
//		String html = "";
//		try {
//			CloseableHttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entitySort = response.getEntity();
//			html = EntityUtils.toString(entitySort, "utf-8");
//
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("getpage_2\n" + html);
//	}
//
//	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);//  ‰»Î
//		JdLoginTest2 jd = new JdLoginTest2();
//		jd.httpClient = HttpClients.createDefault();
//		jd.initpage("xxxxxx", "xxxxxxxxxxx");
//		jd.getImage();
//		String validate = "";
//		validate = scan.next();
//		jd.form.setAuthcode(validate);
//		jd.login();
//		jd.getpage_2(jd.home);
//	}
}
