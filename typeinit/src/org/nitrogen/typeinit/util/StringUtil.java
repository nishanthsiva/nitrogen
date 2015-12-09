package org.nitrogen.typeinit.util;

import java.util.logging.Logger;

import org.nitrogen.typeinit.objects.FunctionBean;
import org.nitrogen.typeinit.objects.TypeInitBean;

public class StringUtil {
	
	private static final Logger LOGGER = Logger.getLogger(StringUtil.class.getName());
	private static final String CLASS_NAME = StringUtil.class.getName();
	
	public static final String removeTabsAndNewline(String line){
		final String METHOD_NAME = "removeAllSpaces";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		line = line.replaceAll("\t", "");
		line = line.replaceAll("\n", "");
		line = line.replace(" ", "");
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return line.trim();
	}
	
	public static String buildFunctionPrototype(FunctionBean fnb){
		final String METHOD_NAME = "getFunctionPrototype";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		StringBuffer functionSeqString = new StringBuffer();
		functionSeqString.append(fnb.getFunctionName()+"(");
		for(TypeInitBean tib : fnb.getParams()){
			functionSeqString.append(tib.getTypeBean().getTypeName()+", ");
		}
		functionSeqString.replace(functionSeqString.length()-2,functionSeqString.length(),")");
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return functionSeqString.toString();
		
	}
	
	public static String removePunctuations(String line){
		final String METHOD_NAME = "removePunctuations";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		line = line.replaceAll("[.!?\\-\\(\\)]","");
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return line;
	}

}
