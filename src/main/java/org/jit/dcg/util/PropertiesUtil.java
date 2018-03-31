/**
 * 创建资源文件读取工具
 */
package org.jit.dcg.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	//采用单例模式创建Properties对象，实现对资源文件的读取
    private static volatile  Properties instance = null;
    public static Properties getInstance() {  
        if (instance == null) {  
            synchronized (Properties.class) {  
                if (instance == null) {  
                    instance = new Properties();  
                }  
            }  
        }  
        return instance;  
    }  
	
    public Properties readProperties(String propertiesName) throws Exception {
    	
    	InputStream ins = this.getClass().getClassLoader()
    			.getResourceAsStream(propertiesName);
    	Properties p = getInstance();
    	p.load(ins);	
    	return p;
	}
    
}
