package com.meigu.community.util.common;

import java.io.*;
import java.util.*;
import org.apache.tools.zip.*;

/**文件解压缩工具*/
@SuppressWarnings({"serial","unchecked"})
public class ZipFileUtil {
	
	/**压缩*/
	public static class Compress{
		/**压缩文件
		 * @param path 		压缩文件地址
		 * @param filepath	输出文件名(全路径)
		 * @param base		压缩虚拟路径
		 * @return
		 */
		public boolean zip(String path,String filepath,String base) {
			boolean flag=false;
			try {
				if(StringUtil.isNotEmpty(path)){
					File sourceFile=new File(path);
					File outfile=new File(filepath);
					flag=zip(sourceFile, outfile, base);
				}else{
					flag=false;
					throw new Exception("source path can't be empty!!!");
				}
			} catch (Throwable e) {
				flag=false;
				e.printStackTrace();
			}
			return flag;
		}
		
		/**
		 *	压缩文件
		 * @param source 	源文件(文件/目录)
		 * @param outfile	输出文件(全路径)
		 * @param base		压缩虚拟路径
		 * @return
		 */
		public boolean zip(File source,File outfile,String base) {
			boolean flag=false;
			FileOutputStream fos = null;
			ZipOutputStream out 	=null;
			try {
				if(source.exists()){
					fos = new FileOutputStream(outfile);
					out = new ZipOutputStream(new BufferedOutputStream(fos));
					out.setEncoding("GBK");
					zip(out,source,outfile, base);
					flag=true;
				}else{
					flag=false;
					throw new Exception("source file does not exist!!!");
				}
			} catch (Throwable e) {
				e.printStackTrace();
				flag=false;
			}finally{
				if(out!=null){
					try {
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return flag;
		}
		
		/**执行压缩任务
		 * @param out 		输出流
		 * @param source	源文件(文件/目录)
		 * @param outfile	输出文件(全路径)
		 * @param base		压缩虚拟路径
		 */
		public void zip(ZipOutputStream out,File source,File outfile,String base) {
			try {
				if(!outfile.getParentFile().exists()){
					outfile.getParentFile().mkdirs();
				}
				if(source.isDirectory()){
					if(StringUtil.isNotEmpty(base)){
						if(base.endsWith(File.separator)||base.endsWith("/")){
							base=String.format("%s",base);
						}else{
							base=String.format("%s/",base);
						}
						out.putNextEntry(new ZipEntry(base));
					}else{
						base="";
					}
					File[] filelist=source.listFiles();
					System.out.println(base);
					for (File file : filelist) {
						zip(out,file,outfile, base+file.getName());
					}
				}else{
					if(StringUtil.isNotEmpty(base)){
						if(base.indexOf(source.getName())!=-1){
							base=String.format("%s",base);
						}else{
							if(base.endsWith(File.separator)||base.endsWith("/")){
								base=String.format("%s",base);
							}else{
								base=String.format("%s/",base);
							}
							base=String.format("%s%s",base,source.getName());
						}
					}else{
						base=source.getName();
					}
					System.out.println(base);
					out.putNextEntry(new ZipEntry(base));
					BufferedInputStream bis=new BufferedInputStream(new FileInputStream(source));
					try {
						byte buffer[]=new byte[1024];
						while (bis.read(buffer)!=-1) {
							out.write(buffer);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						bis.close();
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		/**压缩文件集
		 * @param filelist	文件集合
		 * @param outpath	文件输出路径名称
		 * @param base		压缩虚拟路径
		 * @return
		 */
		public boolean zipByFileList(List<File> filelist,String outpath,String base) {
			boolean flag=false;
			ZipOutputStream out=null;
			try {
				File outfile=new File(outpath);
				if(!outfile.getParentFile().exists()){
					outfile.getParentFile().mkdirs();
				}
				out=new ZipOutputStream(new FileOutputStream(outfile));
				for (File file : filelist) {
					if(file.exists()){
						zip(out,file, outfile, base);
					}
				}
				flag=true;
			} catch (Throwable e) {
				 flag=false;
				e.printStackTrace();
			}finally{
				try {
					if(out!=null){
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return flag;
		}
		/**压缩文件集
		 * @param pathlist	文件路径集合
		 * @param outpath	文件输出路径名称
		 * @param base		压缩虚拟路径
		 * @return
		 */
		public boolean zipByPathList(List<String> pathlist,String outpath,String base) {
			boolean flag=false;
			List<File> filelist=new ArrayList<File>();
			try {
				File file=null;
				for (String path : pathlist) {
					file=new File(path);
					if(file.exists()){
						filelist.add(file);
					}
				}
				flag=zipByFileList(filelist,outpath,base);
			} catch (Throwable e) {
				flag=false;
				e.printStackTrace();
			}
			return flag;
		}
		
		/**压缩文件
		 * @param file		待压缩的文件
		 * @param outpath	文件输出路径
		 * @param base		压缩虚拟目录
		 * @return
		 */
		public boolean zipByFile(File file,String outpath,String base) {
			boolean flag=false;
			try {
				if(file.exists()){
					File outfile=new File(outpath);
					flag=zip(file,outfile, base);
				}else{
					flag=false;
					throw new Exception(String.format("the file does not exist!"));
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return flag;
		}
		/**压缩文件
		 * @param file		待压缩的文件
		 * @param outpath	文件输出路径
		 * @param base		压缩虚拟目录
		 * @return
		 */
		public boolean zipByPath(String path,String outpath,String base) {
			boolean flag=false;
			try {
				if(StringUtil.isNotEmpty(path)){
					File source=new File(path);
					if(source.exists()){
						flag=zipByFile(source, outpath, base);
					}else{
						flag=false;
						throw new Exception(String.format("the file with this path:'%s' does not exist!",path));
					}
				}else{
					flag=false;
					throw new Exception(String.format("This path:'%s' is not as a file.",path));
				}
			} catch (Throwable e) {
				flag=false;
				e.printStackTrace();
			}
			return flag;
		}
	}
	/**解压*/
	public static  class Decompres{
		
		/**
		 * @param zipfile 	目标压缩文件
		 * @param path		解压路径
		 * @return			解压成功?
		 */
		public boolean decompres(File zipfile,String path){
			boolean flag=false;
			try{
				if(zipfile!=null){
					if(zipfile.exists()){
						if(StringUtil.isNotEmpty(path)){
							File file=new File(path);
							if(!file.exists()){
								throw new Exception(String.format("the file with this path:'%s' does not exist!",path));
							}
						}else{
							path=zipfile.getParent();
						}
						ZipFile zipFile=new ZipFile(zipfile);
						Enumeration<ZipEntry> entries=zipFile.getEntries();
						File fout=null;
						InputStream is=null;
						FileOutputStream fos=null;
						BufferedOutputStream bos=null;
						while(entries.hasMoreElements()){
							ZipEntry entry=entries.nextElement();
							fout=new File(path,entry.getName());
							if(!entry.isDirectory()){
								if(!fout.getParentFile().exists()){
									fout.getParentFile().mkdirs();
									System.out.println(fout.getParentFile().getAbsolutePath());
								}
								is=zipFile.getInputStream(entry);
								fos=new FileOutputStream(fout);
								byte buffer[]=new byte[1024];
								bos=new BufferedOutputStream(fos);
								int len=0;
								while((len=is.read(buffer))!=-1){
									bos.write(buffer,0,len);
								}
								System.out.println(fout.getAbsolutePath());
								bos.flush();bos.close();
								fos.flush();fos.close();
							}else{
								if(!fout.exists()){
									fout.mkdirs();
									System.out.println(fout.getAbsolutePath());
								}
							}
						}
						zipFile.close();
						flag=true;
					}else{
						throw new Exception("the file does not exist!!");
					}
				}else{
					throw new Exception("file can't be null!");
				}
			}catch(Exception e){
				flag=false;
				e.printStackTrace();
			}
			return flag;
		}
		
		/**
		 * @param source 	目标压缩文件路径
		 * @param path		解压路径(目录)
		 * @return			解压成功?
		 */
		public boolean decompres(String source,String path){
			boolean flag=false;
			try{
				if(StringUtil.isNotEmpty(source)){
					File file=new File(source);
					if(file.exists()){
						flag=decompres(file,path);
					}else {
						flag=false;
						throw new Exception(String.format("the file with this path:'%s' does not exist!",source));
					}
				}else {
					flag=false;
					throw new Exception(String.format("This path:'%s' is not as a file.",source));
				}
			}catch(Exception e){
				flag=false;
				e.printStackTrace();
			}
			return flag;
		}
	}

	public static void main0(String[] args) {
		List<String> list=new ArrayList<String>(){
			{
				this.add("C:\\test\\filelist_测试.zip");
				this.add("C:\\test\\filelist_测试_0.zip");
				this.add("C:\\test\\filelist_测试_1.zip");
			}
		};
		String filepath="C:\\test\\filelist_测试_0.0.zip";
		String base="测试/1/2/3";
		Compress compress=new Compress();
		boolean flag=false;
		flag=compress.zipByPathList(list, filepath, base);
		System.out.println("success?:"+flag);
	}
	public static void main1(String[] args) {
		String filepath="C:\\test\\";
		String base="测试/1/2/3";
		boolean flag=false;
		Compress compress=new Compress();
		flag=compress.zipByPath(filepath,"C:\\测试_0.zip", base);
		System.out.println("success?:"+flag);
	}
	public static void main3(String[] args) {
		String source="C:\\测试_0.zip"; String path=null;
		Decompres decompres=new Decompres();
		boolean flag=decompres.decompres(source, path);
		System.out.println("success?:"+flag);
	}
	public static void main4(String[] args) {
		String filepath="C:\\测试";
		String base="";
		boolean flag=false;
		Compress compress=new Compress();
		flag=compress.zipByPath(filepath,"C:\\测试_1.zip", base);
		System.out.println("success?:"+flag);
	}
	public static void main5(String[] args) {
		String source="C:\\测试.zip"; String path=null;
		Decompres decompres=new Decompres();
		boolean flag=decompres.decompres(source, path);
		System.out.println("success?:"+flag);
	}
}
