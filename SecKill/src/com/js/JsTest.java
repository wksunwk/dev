package com.js;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.alibaba.fastjson.JSON;

public class JsTest {

	/*
	 * ���ؽű����棬����java�е���js����
	 */
	public void test2() {
		ScriptEngineManager manager = new ScriptEngineManager();

		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			String str = "2&1";
			Integer d = (Integer) engine.eval(str);
			// Integer i = d.intValue();
			System.out.println(d);
		} catch (ScriptException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * ��java�е���js��jdk1.6���м���js�����࣬Ȼ������������js������ 
	 * ��ͨ��JDKƽ̨��script�ķ����е��βθ�ֵ
	 */
	public void test(String name) {
		ScriptEngineManager sem = new ScriptEngineManager();
        /*    
         * sem.getEngineByExtension(String extension)����Ϊjs
         * sem.getEngineByMimeType(String mimeType) ����Ϊapplication/javascript ����text/javascript
         * sem.getEngineByName(String shortName)����Ϊjs��javascript��JavaScript     
         */  
		ScriptEngine se = sem.getEngineByName("js");
		try {
			String script = "function say(){ return 'hello," + name + "'; }";
			se.eval(script);
			Invocable inv2 = (Invocable) se;
			String res = (String) inv2.invokeFunction("say", name);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /*    
     * ��ʾ��ν�java�ж�����Ϊjs��ȫ�ֱ�����ͬʱ��ʾ��file�ำ���ű����ԣ�����������ԡ�    
     */      
	public void testScriptVariables() {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("js");
		File file = new File("E:\\waldos\\test\\aaab.data");
		engine.put("f", file);
		try {
			String s = (String) engine.eval("'path:'+f.getPath();");// �޷�ʹ��alert����
			System.out.println(s);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ��ʾ�����java�����ͨ���߳�������һ��js����
	 */
	public void testScriptInterface() throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("js");
		engine.put("out", System.out);
		String script = "var obj=new Object();obj.run=function(){out.println('test thread')}";
		engine.eval(script);
		Object obj = engine.get("obj");// ��ȡjs�ж���
		Invocable inv = (Invocable) engine;
		Runnable r = inv.getInterface(obj, Runnable.class);
		Thread t = new Thread(r);
		t.start();
	}
	
	public void testHeatMap() {

		try {
			ScriptEngineManager sem = new ScriptEngineManager();
			ScriptEngine engine = sem.getEngineByName("js");
			engine.put("out", System.out);
			String jsFileName = "js/heatmap.js";
			// ��ȡjs�ļ�
			FileReader reader = new FileReader(jsFileName);
			// ִ��ָ���ű�
			engine.eval(reader);
			String show = "function show(obj) {var props = ''; for ( var p in obj ){if (obj.hasOwnProperty(p)) {props += p + \" = \" + obj [ p ] + \";\";}} return props;}";
			engine.eval(show);
//			String script = "out.println(show(h337));";
//			engine.eval(script);
			Div div = new Div();
			StringBuffer hm = new StringBuffer();
			hm.append("var heatmapInstance = h337.create(" + JSON.toJSONString(div) + ");");
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
//			hm.append("");
//			hm.append("");
//			hm.append("");
			engine.eval(hm.toString());
//			Invocable inv = (Invocable) engine;
//			inv.invokeFunction(name, args)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		JsTest js = new JsTest();
//		js.test2();
//		js.test("XXSS");
//		js.testScriptVariables();
//		try {
//			js.testScriptInterface();
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		js.testHeatMap();
	}
	
	class Div {
		
	}
}
