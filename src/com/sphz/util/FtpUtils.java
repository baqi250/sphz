package com.sphz.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;
/**
 * ftp读取配置类
 * @author USER
 *
 */
public class FtpUtils {
	private static Map<String,Object>map=new HashMap<String,Object>();
	public static Map<String,Object> getFtpProperties(){
		
		try {
			ClassLoader classLoder=Thread.currentThread().getContextClassLoader();
			InputStream fs=classLoder.getResourceAsStream("ftp.properties");
			Properties p=new Properties();
				p.load(fs);
				map.put("host", p.getProperty("ftp.host"));
				map.put("port", p.getProperty("ftp.port"));
				map.put("username", p.getProperty("ftp.username"));
				map.put("password", p.getProperty("ftp.password"));
				map.put("hxfwbasepath",p.getProperty("ftp.hxfwbasepath"));
				map.put("smwsbasepath",p.getProperty("ftp.smwsbasepath"));
				map.put("dawjbasepath",p.getProperty("ftp.dawjbasepath"));
				return map;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
	}
	
	 /**
	 * 上传文件
	 * @param hostname FTP服务器地址
	 * @param port FTP服务器端口号
	 * @param username FTP登录帐号
	 * @param password FTP登录密码
	 * @param pathname FTP服务器保存目录
	 * @param filename 上传到FTP服务器后的文件名称
	 * @param inputStream 文件流
	 * @return
	 */
	public static boolean uploadFile(String hostname, int port, String username, String password, String pathname, String fileName, InputStream inputStream){
		boolean flag=false;
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("UTF-8");
		try {
			ftpClient.connect(hostname, port);
			ftpClient.login(username, password);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("ftp connect error");
				return false;
			}
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.enterLocalPassiveMode();// 加上这一句，可以上传文件到远程ftp服务器
			ftpClient.setReceiveBufferSize(1024*1024);
			ftpClient.setBufferSize(1024*1024);
			flag = ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("上传失败" + e.getMessage());
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("上传失败" + e.getMessage());
				}
			}
		}
		return flag;
	
	}
	
	 /**
		 * 上传文件（可对文件进行重命名）
		 * @param hostname FTP服务器地址
		 * @param port FTP服务器端口号
		 * @param username FTP登录帐号
		 * @param password FTP登录密码
		 * @param pathname FTP服务器保存目录
		 * @param filename 上传到FTP服务器后的文件名称
		 * @param originfilename 待上传文件的名称（绝对地址）
		 * @return
		 */
	 public static boolean uploadFileFromProduction(String hostname, int port, String username, String password, String pathname, String filename, MultipartFile file){
		 boolean flag = false;
		 try {
		 InputStream inputStream = file.getInputStream();
		 flag = uploadFile(hostname, port, username, password, pathname, filename, inputStream); 
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 return flag;
		 }
	 
	 
	 /**
		 * 删除文件（可对文件进行重命名）
		 * @param hostname FTP服务器地址
		 * @param port FTP服务器端口号
		 * @param username FTP登录帐号
		 * @param password FTP登录密码
		 * @param pathname FTP服务器文件保存目录
		 * @param filename 上传到FTP服务器后的文件名称
		 * @return
		 */
	 public static boolean deleteFile(String hostname, int port, String username, String password, String pathname, String filename){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("UTF-8");
		try {
			ftpClient.connect(hostname, port);
			ftpClient.login(username, password);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("ftp connect error");
				return false;
			}

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.enterLocalPassiveMode();// 加上这一句，可以上传文件到远程ftp服务器
			flag = ftpClient.deleteFile(filename);
			System.out.println(flag);
			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	 
	 /**
		 * 删除文件夹及其下的文件
		 * @param hostname FTP服务器地址
		 * @param port FTP服务器端口号
		 * @param username FTP登录帐号
		 * @param password FTP登录密码
		 * @param pathname FTP服务器文件保存目录
		 * @param filename 上传到FTP服务器后的文件名称
		 * @return
		 */
	 public static boolean deleteFileFolder(String hostname, int port, String username, String password, String pathname){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("UTF-8");
		try {
			ftpClient.connect(hostname, port);
		
			ftpClient.login(username, password);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("ftp connect error");
				return false;
			}

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			///*ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.enterLocalPassiveMode();// 加上这一句，可以上传文件到远程ftp服务器
			FTPFile[]files=ftpClient.listFiles(pathname);
			if(null!=files&&files.length>0){
				for(FTPFile file:files){
					ftpClient.deleteFile(file.getName());
				}
			}
			
			flag = ftpClient.removeDirectory(pathname);
			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
