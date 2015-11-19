package org.nitrogen.typeinit.util;

import java.util.logging.Logger;

public class StringUtil {
	
	private static final Logger LOGGER = Logger.getLogger(StringUtil.class.getName());
	private static final String CLASS_NAME = StringUtil.class.getName();
	
	public static final String removeTabsAndNewline(String line){
		final String METHOD_NAME = "removeAllSpaces";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		line = line.replaceAll("\t", "");
		line = line.replaceAll("\n", "");
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return line;
	}

}
