
package com.brower.djnativeswing;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;

public class Test2 extends JPanel {

	private static final long serialVersionUID = 983931549644676329L;

	private JPanel webBrowserPanel;

	private JWebBrowser webBrowser;

	// private String url;

	public Test2(String url) {
			super(new BorderLayout());
			StringBuffer hm = new StringBuffer();
			hm.append("<!DOCTYPE html>");
			hm.append("<html>");
			hm.append("<head>");
			hm.append("<meta charset=\"UTF-8\">");
			hm.append("<title></title>");
			hm.append("<style>");
			hm.append("div {");
			hm.append("width:600px; height:400px;border: 1px solid;border-color: grey;");
			hm.append("}");
			hm.append("</style>");
			hm.append("</head>");
			hm.append("<body>");
			hm.append("<div id=\"heatmap\"></div>");
			hm.append("</body>");
			hm.append("<script src=\"" + System.getProperty("user.dir") + "/js/heatmap.js\"></script>");
			hm.append("<script type=\"text/javascript\">");
			hm.append("var heatmapInstance = h337.create({container: document.getElementById(\"heatmap\")});");
			hm.append("var points = [];");
			hm.append("var max = 0;");
			hm.append("var width = 600;");
			hm.append("var height = 400;");
			hm.append("var len = 200;");
			hm.append("while (len--) {");
			hm.append("var val = Math.floor(Math.random()*100);");
			hm.append("max = Math.max(max, val);");
			hm.append("var point = {");
			hm.append("x: Math.floor(Math.random()*width),");
			hm.append("y: Math.floor(Math.random()*height),");
			hm.append("value: val");
			hm.append("};");
			hm.append("points.push(point);");
			hm.append("}");
			hm.append("var data = {");
			hm.append("max: max,");
			hm.append("data: points");
			hm.append("};");
			hm.append("heatmapInstance.setData(data);");
			hm.append("</script>");
			hm.append("</html>");
			
			StringBuffer js = new StringBuffer();
			// 读取js文件
//			try {
//				String jsFileName = "js/heatmap.js";
//				InputStream is = new FileInputStream(jsFileName);
//				int len = -1;
//				byte[] buf = new byte[1024];
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				while ((len = is.read(buf)) != -1) {
//					os.write(buf, 0, len);
//				}
//				os.flush();
//				is.close();
//				js.append(os.toString("GB2312"));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//			js.append(";");
//			String show = "function show(obj) {var props = ''; for ( var p in obj ){if (obj.hasOwnProperty(p)) {props += p + \" = \" + obj [ p ] + \";\";}} return props;}";
//			js.append(show);
//			System.out.println(js);
			js.append("var div = document.getElementById('heatmap');");
//			js.append("alert(div);");
//			js.append("alert({container: div});");
			js.append("var heatmapInstance = h337.create({container: div});");
			js.append("alert(heatmapInstance);");
			js.append("var points = [];");
			js.append("var max = 0;");
			js.append("var width = 600;");
			js.append("var height = 400;");
			js.append("var len = 200;");
			js.append("while (len--) {");
			js.append("var val = Math.floor(Math.random()*100);");
			js.append("max = Math.max(max, val);");
			js.append("var point = {");
			js.append("x: Math.floor(Math.random()*width),");
			js.append("y: Math.floor(Math.random()*height),");
			js.append("value: val");
			js.append("};");
			js.append("points.push(point);");
			js.append("}");
			js.append("var data = {");
			js.append("max: max,");
			js.append("data: points");
			js.append("};");
			js.append("heatmapInstance.setData(data);");
			
//			this.url = url;
			webBrowserPanel = new JPanel(new BorderLayout());
			webBrowser = new JWebBrowser();
			webBrowser.navigate(url);
//			webBrowser.setHTMLContent(hm.toString());
			webBrowser.setButtonBarVisible(false);
			webBrowser.setMenuBarVisible(false);
			webBrowser.setBarsVisible(false);
			webBrowser.setStatusBarVisible(false);
			webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
			add(webBrowserPanel, BorderLayout.CENTER);
			// webBrowser.executeJavascript("javascrpit:window.location.href='http://www.baidu.com'");
//			webBrowser.executeJavascript(js.toString()); // 执行Js代码
			webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
				public void loadingProgressChanged(WebBrowserEvent e) {
					// 判断是否加载完成
					if (e.getWebBrowser().getLoadingProgress() == 100) {
						webBrowser.executeJavascript(js.toString()); // 执行Js代码
					}
				}
			});
		}

	public static void main(String[] args) {
		// final String url = "http://www.baidu.com";
		final String url = "file://" + System.getProperty("user.dir") + "/html/test.HTML";
		final String title = "DJ NativeSwiting Test";
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new Test(url), BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
	}
}