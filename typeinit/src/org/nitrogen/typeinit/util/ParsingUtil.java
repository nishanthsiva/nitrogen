package org.nitrogen.typeinit.util;

import java.util.logging.Logger;

import org.nitrogen.typeinit.objects.FunctionBean;

public class ParsingUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ParsingUtil.class.getName());
	private static final String CLASS_NAME = ParsingUtil.class.getName();
	
	public static boolean isAssignStatement(String statement){
		final String METHOD_NAME = "isAssignStatement";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		boolean isAssignment = false;
		if(statement.contains("=") && statement.contains(";"))
			isAssignment = true;
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return isAssignment;
	}
	
	public static boolean isFunctionCall(String statement){
		final String METHOD_NAME = "hasFunctionCall";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		boolean isFunctionCall = false;
		if(statement.matches("[a-zA-Z0-9]+\\[([ ,.a-zA-Z0-9]+)\\]")){
			isFunctionCall = true;
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return isFunctionCall;
	}
	
	public static FunctionBean parseCallSite(String callSite){
		final String METHOD_NAME = "parseFunctionCall";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		FunctionBean fnBean = new FunctionBean();
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return fnBean;
	}

}
