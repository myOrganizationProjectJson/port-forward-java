package com.forward;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hello world!
 *
 */
public class TransPortData extends Thread{
	

	 private transient static Log log = LogFactory.getLog(TranslatePort.class);
	 
	 Socket getDataSocket;
	 Socket putDataSocket;
	 
	 String type;
	 
	 public TransPortData(Socket getDataSocket , Socket putDataSocket ,String type){
	  this.getDataSocket = getDataSocket;
	  this.putDataSocket = putDataSocket;
	  this.type = type;
	 }
	 
	 public void run(){
	  try {
	   while(true){
	    InputStream in = getDataSocket.getInputStream() ;
	    OutputStream out = putDataSocket.getOutputStream() ;
	    //读入数据
	    byte[] data = new byte[1024];
	    int readlen = in.read(data);
	    
	    
	    String text = new String(data,0,readlen); 
	    //System.out.println(":"+text); 
	    
	    System.out.println(text.toString());
	    System.out.println("===============================");
	    
	    //如果没有数据，则暂停
	    if(readlen<=0){
	     Thread.sleep(300);
	     continue;
	    }
	   // System.out.println(data);
	    
	    out.write(data ,0,readlen);
	    out.flush();
	   }
	  } catch (Exception e) {
	   log.error("type:"+type,e);
	  }
	  finally{
	   //关闭socket
	   try {
	    if(putDataSocket != null){
	     putDataSocket.close();
	    }
	   } catch (Exception exx) {
	   }
	   
	   try {
	    if(getDataSocket != null){
	     getDataSocket.close();
	    }
	   } catch (Exception exx) {
	   }
	  }
	 }
}
