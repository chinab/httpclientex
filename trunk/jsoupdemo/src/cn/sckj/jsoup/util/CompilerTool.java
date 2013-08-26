package cn.sckj.jsoup.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CompilerTool {

	public static Object compile(String class_path , String fileName , String className){
		Object obj = null;
		String path = System.getProperty("user.dir");
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
			@SuppressWarnings("rawtypes")
			Iterable units = fileMgr.getJavaFileObjects(path+fileName);
			@SuppressWarnings("unchecked")
			CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
			t.call();
			fileMgr.close();
			//load into memory and create an instance
			URL[] urls = new URL[] {new URL("file:/"+path+class_path)};
			URLClassLoader ul = new URLClassLoader(urls);
			@SuppressWarnings("rawtypes")
			Class c = ul.loadClass(className);
			obj = c.newInstance();
			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
		 
	} 

}
