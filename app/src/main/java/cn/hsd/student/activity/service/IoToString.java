package cn.hsd.student.activity.service;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class IoToString {
	public static String isToString(InputStream is){
		String myform =null;
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			
			byte[] by=new byte[1024];
			
		    int n=	is.read(by);
		    while(n!=-1){
		    	baos.write(by, 0, n);
		    	n=	is.read(by);
		    }
			
		    byte [] all=baos.toByteArray();
			String sds_all = new String(all);
			if (sds_all.contains("utf-8")){
				return sds_all;
			}else if (sds_all.contains("gb2312")){
				return new String(all,"gb2312");
			}else if (sds_all.contains("gbk")){
				return  new String(all,"gbk");
			}
		    
		    myform = new String(all,"gbk");
		    
		} catch (Exception e) {
			e.printStackTrace();
			myform=null;
		}
	    
	    return myform;
	}
}
