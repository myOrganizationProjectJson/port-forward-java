package com.forward;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hello world!
 *
 */
public class TranslatePort {
	 private transient static Log log = LogFactory.getLog(TranslatePort.class);
	   
	public static void main(String[] args) {
		
		
		   //获取本地监听端口、远程IP和远程端口
		   int localPort = 3307;//Integer.parseInt(args[0].trim()); 
		   String remoteIp = "10.1.0.47";//args[1].trim();
		   int remotePort = 3306;//Integer.parseInt(args[2].trim());
		   
	
		   
		   //启动本地监听端口
		   try {
			ServerSocket serverSocket = new ServerSocket(localPort);
		   log.info("localPort="+localPort + ";remoteIp=" + remoteIp +
		     ";remotePort="+remotePort+";启动本地监听端口" + localPort + "成功！");
		   
	
		   while(true){
			    Socket clientSocket = null;
			    Socket remoteServerSocket = null;
			    try {
			    	
			    	 
			     //获取客户端连接
			     clientSocket = serverSocket.accept();
			     log.info("accept one client");
			     //建立远程连接
			     remoteServerSocket = new Socket(remoteIp ,remotePort);
			     log.info("create remoteip and port success");
			     //启动数据转换接口
			     (new TransPortData(clientSocket ,remoteServerSocket ,"1")).start();
			     (new TransPortData(remoteServerSocket ,clientSocket,"2")).start();
			    } catch (Exception ex) {
			     log.error("",ex);
			    }
			    //建立连接远程
			   }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	

}
