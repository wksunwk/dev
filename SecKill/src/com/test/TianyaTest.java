package com.test;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class TianyaTest {

	public static void main(String[] args) throws Exception {
		try {
			// ����һ�����ԣ�Ҳ��Ϊ���ô�ҿ��ĸ����������ʱ�׿�����淶�ԣ���Ҫ�������Ҷཨ��һ���ֲ�������
			// �õ���֤https�����������
			HttpClient client = getSSLInsecureClient();
			// �õ�������Ҫ��post��
			HttpPost post = getPost();
			// ʹ�����ǵ������ȥִ�������,�õ����ǵĽ��
			HttpResponse hr = client.execute(post);
			// �ڿ���̨���������Ҫ��һЩ��Ϣ
			showResponseInfo(hr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void showResponseInfo(HttpResponse hr) throws ParseException, IOException {

		System.out.println("��Ӧ״̬����Ϣ��" + hr.getStatusLine());
		System.out.println("---------------------------------------------------------------");

		System.out.println("��Ӧͷ��Ϣ��");
		Header[] allHeaders = hr.getAllHeaders();
		for (int i = 0; i < allHeaders.length; i++) {
			System.out.println(allHeaders[i].getName() + ":" + allHeaders[i].getValue());
		}

		System.out.println("---------------------------------------------------------------");
		System.out.println("��Ӧ���ģ�");
		System.out.println(EntityUtils.toString(hr.getEntity()));

	}

	// �õ�һ����֤https���ӵ�HttpClient������Ϊ���ǽ�Ҫ�����ĵ�¼��Https�ģ�
	// ��������ι��������Ǻ�����ᵽ��
	private static HttpClient getSSLInsecureClient() throws Exception {
		// ����һ����֤�����ģ��Ͽ����а�ȫ���ӣ���Ȼ��������Ϊ���ǽ����ǲ��ԣ�ʵ�����Ͽ����а�ȫ������Σ�յ�
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom().//
				setSSLSocketFactory(sslsf)//
				.setProxy(new HttpHost("10.6.30.135", 8080))
				.build();
	}

	// ��ȡ������Ҫ��Post����������ǰ��ҵĴ��븴�ƹ�ȥ����ǵø���Ϊ����û���������
	private static HttpPost getPost() {
		HttpPost post = new HttpPost("https://passport.tianya.cn/login");

		// �������ǳ�ʼ������ͷ
		post.addHeader("Referer", "https://passport.tianya.cn/login.jsp");
		post.addHeader("Host", "passport.tianya.cn");
		post.addHeader("Origin", "http://passport.tianya.cn");

		// Ȼ����������������Ҫ���ݵı���������ҪҲ���Ǵ������ǵ��û��������룩
		// ���ǿ����Ƚ���һ��List��֮��ͨ��post.setEntity�������뼴��
		// д��һ����Ҫ��Ϊ�˴�ҿ��������㣬�������ʽʹ�õĵ�Ȼ��Ҫ�ֿ������Ż�����ṹ��
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		/*
		 * �������Ҫ�Ĳ�������Щ����ͨ���鿴������е����翴�����������ҵĽ�ͼ�п�����һ��
		 * �������õ���firebut,httpWatch�����ǹȸ��Դ��Ĳ鿴��Ҳ��,���ܲ鿴����������Ƽ������������鿴�� Ҫ�ѱ���Ҫ�Ĳ��������룬˳��Ӱ��
		 */
		paramsList.add(new BasicNameValuePair("Submit", ""));
		paramsList.add(new BasicNameValuePair("fowardURL", "http://www.tianya.cn"));
		paramsList.add(new BasicNameValuePair("from", ""));
		paramsList.add(new BasicNameValuePair("method", "name"));
		paramsList.add(new BasicNameValuePair("returnURL", ""));
		paramsList.add(new BasicNameValuePair("rmflag", "1"));
		paramsList.add(new BasicNameValuePair("__sid", "1#1#1.0#a6c606d9-1efa-4e12-8ad5-3eefd12b8254"));

		// ���������һ�����ĵ��˺� ���������д������滻Ϊ����û���������
		paramsList.add(new BasicNameValuePair("vwriter", "waldos"));// �滻Ϊ����û���
		paramsList.add(new BasicNameValuePair("vpassword", ""));// �������

		// ���������list���õ�post��
		post.setEntity(new UrlEncodedFormEntity(paramsList, Consts.UTF_8));
		return post;
	}
}
