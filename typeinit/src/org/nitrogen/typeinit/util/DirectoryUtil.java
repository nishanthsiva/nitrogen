package org.nitrogen.typeinit.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DirectoryUtil {

	
	private static final Logger LOGGER = Logger.getLogger(DirectoryUtil.class.getName());
	private static final String CLASS_NAME = DirectoryUtil.class.getName();
	
	
	public static List<String> getAllSubDirectories(String dirPath){
		final String METHOD_NAME = "getAllSubDirectories";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		File dir = new File(dirPath);
		List<String> subDirs = new ArrayList<String>();
		if(dir.isDirectory()){
			subDirs.add(dir.getAbsolutePath());
			for(String listItem: dir.list()){
				subDirs.addAll(getAllSubDirectories(dir.getAbsolutePath()+"/"+listItem));
			}
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return subDirs;
	}
	
	public static List<String> getAllCFiles(String dirPath){
		final String METHOD_NAME = "getAllCFiles";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		List<String> cFiles = new ArrayList<String>();
		File dir = new File(dirPath);
		if(dir.isDirectory()){
			for(String dirName : getAllSubDirectories(dirPath)){
				File subDir = new File(dirName);
				for(File file: subDir.listFiles()){
					if(file.getName().endsWith(".c")){
						cFiles.add(subDir.getAbsolutePath()+'/'+file.getName());
					}
				}
			}
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return cFiles;
	}
	public static void main(String[] args) {
		for(String dir : getAllSubDirectories("/Users/nishanthsivakumar/Workspace/testprojects/gnuprojects/out/a2ps")){
			System.out.println(dir);
		}
		for(String file : getAllCFiles("/Users/nishanthsivakumar/Workspace/testprojects/gnuprojects/out/a2ps")){
			System.out.println(file);
		}
	}

}
