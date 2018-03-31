package org.jit.dcg.pub;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Common {

	//web项目前端展现资源文件
	public static Properties g_UIPro = new Properties();

	//web项目前端展现资源文件
	public static Properties g_JSPro = new Properties();

	//web项目前端展现资源文件
	public static Properties g_JavaPro=new Properties();

	//web项目前端展现资源文件路径
	public static String pagepro="/resource/page.properties";

	//web项目前端提示信息资源文件路径
	public static String jspro="/resource/js.properties";

	//web项目业务处理信息资源文件路径
	public static String javapro="/resource/java.properties";

	//获得返回的载入文件
//	public static Properties Config1 = getUIConfig();
//	public static Properties Config2 = getJSConfig();
//	public static Properties Config3 = getJavaConfig();



	/**
	 * 定义Properties类型的一个方法来获取加载得到的配置文件
	 * @return
	 */
	public static Properties getUIConfig(){

		//判断web前台展现全局资源文件是否存在
		if((g_UIPro != null && g_UIPro.size() != 0)){
			return g_UIPro;
		}

		//文件输入流处理对象
		InputStreamReader in = null;

		//提示语句
		System.out.println("********开始加载文件*********");
		try {
			//以UTF-8格式去读入相对路径的文件
			in = new InputStreamReader(Common.class.getResourceAsStream(pagepro),"UTF-8");

			//加载web项目前端展现资源文件到系统的全局变量中
			g_UIPro.load(in);


			System.out.println("********获取资源文件*********"+g_UIPro.get("username"));
		} catch (FileNotFoundException e) {
			//当试图打开指定路径名表示的文件失败时，抛出此异常。
			e.printStackTrace();
		} catch (IOException e) {
			//捕获文件流处理的IO异常
			e.printStackTrace();
		}finally{
			//关闭文件流处理对象
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("********结束加载文件*********");

		//返回已经载入数据的properties类对象
		return g_UIPro;
	}

	//读取Config.properties配置文件

	/**
	 * 定义Properties类型的一个方法来获取加载得到的配置文件
	 * @return
	 */
	public static Properties getJSConfig(){

		//判断web前台展现全局资源文件是否存在
		if((g_JSPro != null && g_JSPro.size() != 0)){
			return g_JSPro;
		}

		//文件输入流处理对象
		InputStreamReader in = null;

		//提示语句
		System.out.println("********开始加载文件*********");
		try {
			//以UTF-8格式去读入相对路径的文件
			in = new InputStreamReader(Common.class.getResourceAsStream(jspro),"UTF-8");

			//加载web项目前端展现资源文件到系统的全局变量中
			g_JSPro.load(in);
		} catch (FileNotFoundException e) {
			//当试图打开指定路径名表示的文件失败时，抛出此异常。
			e.printStackTrace();
		} catch (IOException e) {
			//捕获文件流处理的IO异常
			e.printStackTrace();
		}finally{
			//关闭文件流处理对象
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("********结束加载文件*********");

		//返回已经载入数据的properties类对象
		return g_JSPro;
	}

	//读取js.properties配置文件

	/**
	 * 定义Properties类型的一个方法来获取加载得到的配置文件
	 * @return
	 */
	public static Properties getJavaConfig(){

		//判断web前台展现全局资源文件是否存在
		if((g_JavaPro != null && g_JavaPro.size() != 0)){
			return g_JavaPro;
		}

		//文件输入流处理对象
		InputStreamReader in = null;

		//提示语句
		System.out.println("********开始加载文件*********");
		try {
			//以UTF-8格式去读入相对路径的文件
			in = new InputStreamReader(Common.class.getResourceAsStream(javapro),"UTF-8");

			//加载web项目前端展现资源文件到系统的全局变量中
			g_JavaPro.load(in);
		} catch (FileNotFoundException e) {
			//当试图打开指定路径名表示的文件失败时，抛出此异常。
			e.printStackTrace();
		} catch (IOException e) {
			//捕获文件流处理的IO异常
			e.printStackTrace();
		}finally{
			//关闭文件流处理对象
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("********结束加载文件*********");

		//返回已经载入数据的properties类对象
		return g_JavaPro;
	}

	//读取java.properties配置文件



	/**
	 * 从全局变量中获得web页面展现资源文件
	 * @param key
	 * @return
	 */
	public static String getUIString(String key) {
		//判断key值是否为空
		if(key == null){
			return "";
		}

		//判断web工程项目资源文件是否为空，如果为空则重新加载资源文件
		if(g_UIPro == null || g_UIPro.size() == 0){
			g_UIPro = getUIConfig();
		}

		//根据key值获取资源信息
		String value = g_UIPro.getProperty(key)==null?"":g_UIPro.getProperty(key);

		//返回properties对象中key所相对应的value值
		return value;
	}

	public static String getJSString(String key) {
		//判断key值是否为空
		if(key == null){
			return "";
		}

		//判断web工程项目资源文件是否为空，如果为空则重新加载资源文件
		if(g_JSPro == null || g_JSPro.size() == 0){
			g_JSPro = getJSConfig();
		}

		//根据key值获取资源信息
		String value =g_JSPro.getProperty(key)==null?"":g_JSPro.getProperty(key);

		//返回properties对象中key所相对应的value值
		return value;
	}

	public static String getJavaString(String key) {
		//判断key值是否为空
		if(key == null){
			return "";
		}

		//判断web工程项目资源文件是否为空，如果为空则重新加载资源文件
		if(g_JavaPro == null || g_JavaPro.size() == 0){
			g_JavaPro = getJavaConfig();
		}

		//根据key值获取资源信息
		String value =g_JavaPro.getProperty(key)==null?"":g_JavaPro.getProperty(key);

		//返回properties对象中key所相对应的value值
		return value;
	}


	public static void main(String[] args){
		Common c = new Common();
		System.out.println(g_UIPro.getProperty("username"));
	}
}
