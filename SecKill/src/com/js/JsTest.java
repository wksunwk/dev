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
	 * 加载脚本引擎，并在java中调用js方法
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
	 * 在java中调用js，jdk1.6中有加载js引擎类，然后由它来调用js方法。 
	 * 并通过JDK平台给script的方法中的形参赋值
	 */
	public void test(String name) {
		ScriptEngineManager sem = new ScriptEngineManager();
        /*    
         * sem.getEngineByExtension(String extension)参数为js
         * sem.getEngineByMimeType(String mimeType) 参数为application/javascript 或者text/javascript
         * sem.getEngineByName(String shortName)参数为js或javascript或JavaScript     
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
     * 演示如何将java中对象作为js中全局变量，同时演示将file类赋给脚本语言，并获得其属性。    
     */      
	public void testScriptVariables() {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("js");
		File file = new File("E:\\waldos\\test\\aaab.data");
		engine.put("f", file);
		try {
			String s = (String) engine.eval("'path:'+f.getPath();");// 无法使用alert方法
			System.out.println(s);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 演示如何在java中如何通过线程来启动一个js方法
	 */
	public void testScriptInterface() throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("js");
		engine.put("out", System.out);
		String script = "var obj=new Object();obj.run=function(){out.println('test thread')}";
		engine.eval(script);
		Object obj = engine.get("obj");// 获取js中对象
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
			// 读取js文件
			FileReader reader = new FileReader(jsFileName);
			// 执行指定脚本
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
