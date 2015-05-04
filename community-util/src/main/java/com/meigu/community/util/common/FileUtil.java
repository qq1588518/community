package com.meigu.community.util.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;




public class FileUtil
{
	
	
	
	/**
	 * 创建文件的存储路径，并创建文件。
	 * @param fileName_上传文件时得到的文件名，这里用来解析后缀的。
	 * @return
	 */
	public static String generateUploadPath(String fileName_,String savePath) throws Exception
	{
		
		String suffix="";
		if(fileName_.lastIndexOf(".")>0)
		suffix = fileName_.substring(fileName_.lastIndexOf("."));
		/*
		 * 取当前微秒作为两级目录名和文件名 得到的值为14位长度 取第9、10位做第一级目录，第11、12位做第二级目录 整个值作为文件名
		 * System.nanoTime()长度可能会变化 返回结果类似"/12/80/11932505128086"
		 */
		String fileName = Long.toHexString(System.nanoTime());
		int nameLength = fileName.length();
		// 如果得到的长度小于6，再生成一次将两次结果连起来
		if(nameLength < 6)
		{
			fileName = fileName + Long.toHexString(System.nanoTime());
			nameLength = fileName.length();
		}
		String firstFolder = fileName.substring(nameLength - 6, nameLength - 4);
		String secondFolder = fileName.substring(nameLength - 4, nameLength - 2);
		String twoFolders = "/" + firstFolder + "/" + secondFolder;
		// 创建多级文件夹
		createFolder(savePath + twoFolders);
		return twoFolders + "/" + fileName + suffix;
	}
	
	public static void createFolder(String folderPath) throws Exception
	{
		String[] splitedFolder = folderPath.split("/");
		StringBuilder path = new StringBuilder();
		for (int i = 0; i < splitedFolder.length; i++)
		{
			if(i > 0)
			{
				path.append("/");
			}
			path.append(splitedFolder[i]);
			File filePath = new File(path.toString());
			if(!filePath.exists())
				filePath.mkdir();
		}
	}
	
	
	/**
	 * 文件的拷贝，将一文件拷贝为另一文件。主要用来将上传的临时文件拷贝到本地
	 * @param src 临时文件
	 * @param targetPath 服务器存储文件的路径。
	 * @return size 文件的大小
	 * @throws Exception
	 */
	public static int fileCopy(File src, String targetPath) throws Exception
	{
		
		File targetFile=new File(targetPath);
		InputStream in = null;
		OutputStream out = null; 
		int size=0;
		try
		{
			in = new BufferedInputStream(new FileInputStream(src), 5120);
			out = new BufferedOutputStream(new FileOutputStream(targetFile), 5120);
			byte[] buffer = new byte[5120];
			int len = 0;
			while ((len = in.read(buffer)) > 0)
			{
				out.write(buffer, 0, len);
				size+=len;
			}
			out.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
			if(out != null)
			{
				try
				{
					out.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		return size;
	}
	
	/**
	 * 说 明：删除指定文件<br>
	 * 注 释：可选择删除包含此文件的目录的个数，如果目录不为空，则不删除目录<br>
	 *
	 * 创建时间：2012-4-10  上午11:41:23<br>
	 *
	 * @param filePath 文件路径
	 * @param dirNum 删除目录的个数
	 * @throws Exception
	 */
	public static void removeFile(String filePath, Integer dirNum) throws IOException
	{
	    if(!StringUtils.isBlank(filePath))
	    {
		File targetFile = null;
		
		//删除文件
		targetFile  = new File(filePath);
		if(targetFile.exists())
		{
		    targetFile.delete();
		}
		
		//删除指定数量的包含文件夹
		if(dirNum != null && dirNum > 0)
		{
		    for(int i = 0; i < dirNum; i++)
		    {
			filePath = filePath.substring(0, filePath.lastIndexOf("/"));
			targetFile = new File(filePath);
			if(targetFile.exists())
			{
			    targetFile.delete();
			}
		    }
		}
	    }
	    
	}
	
	/** 新建文件 */
	public static boolean createFile(File file) {
		boolean flag = false;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			flag = file.createNewFile();
		} catch (Throwable e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	/** 新建文件 */
	public static boolean createFile(String filepath){
		if(StringUtil.isNotEmpty(filepath)){
			File newfile=new File(filepath);
			return createFile(newfile);
		}else {
			return false;
		}
	}
	/**
	 * 写文件
	 * @param in
	 *            文件输入流
	 * @param outfile
	 *            输出文件(全路径)
	 */
	public static boolean writeFile(InputStream in, File outfile) {
		boolean flag = false;
		BufferedInputStream bis = null;
		BufferedOutputStream  bos=null;
		try{
			if(in!=null&&outfile!=null){
				bis = new BufferedInputStream(in);
				bos= new BufferedOutputStream(new FileOutputStream(outfile));
				byte buffer[] = new byte[1024];
				int len = 0;
				while ((len = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				flag = true;
			}else {
				flag=false;
			}
		}catch(Throwable e){
			flag=false;
			e.printStackTrace();
		}finally{
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static void springMvcUpload(MultipartFile file,String path,String fileName){
		try {
			File arg0 = new File(path, fileName);
			FileUtil.createFolder(path);
			file.transferTo(arg0);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
