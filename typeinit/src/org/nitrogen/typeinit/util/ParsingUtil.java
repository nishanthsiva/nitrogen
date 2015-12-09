package org.nitrogen.typeinit.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.nitrogen.typeinit.objects.FunctionBean;
import org.nitrogen.typeinit.objects.TypeBean;
import org.nitrogen.typeinit.objects.TypeInitBean;

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
		if(statement.matches("[0-9a-zA-Z]+(_[0-9a-zA-Z]+)*+ *\\([^\\)]*\\)(\\.[^\\)]*\\))?;*?")){
			isFunctionCall = true;
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return isFunctionCall;
	}
	
	public static FunctionBean parseCallSite(String callSite, String filePath) throws IOException{
		final String METHOD_NAME = "parseFunctionCall";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		FunctionBean fnBean = new FunctionBean();
		List<TypeInitBean> paramList = new ArrayList<TypeInitBean>();
		if(callSite.contains("(") && callSite.contains(")")){
			String [] tokens = callSite.split("\\(");
			fnBean.setFunctionName(StringUtil.removeTabsAndNewline(tokens[0]));
			tokens = tokens[1].split("\\)");
			String [] params  = tokens[0].split(",");
			LOGGER.log(Level.FINEST,"Parsing - "+callSite);
			for(String param: params){
				LOGGER.info("Param "+param);
				TypeInitBean typeInit = new TypeInitBean();
				TypeBean typeBean = new TypeBean();
				String varType = ParsingUtil.getPrimitiveType(param);
				
				if(varType == "undefined"){
					LOGGER.info("Searching in "+filePath);
					typeBean.setTypeName(FileUtil.getVariableType(param,filePath));
				}else{
					typeBean.setTypeName(varType);
				}
				typeInit.setTypeBean(typeBean);
				paramList.add(typeInit);
				LOGGER.info("Param Type -"+typeBean.getTypeName());
			}
			fnBean.setParamCount(params.length);
			fnBean.setParams(paramList);
		}
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return fnBean;
	}
	
	public static String getPrimitiveType(String param){
		final String METHOD_NAME = "getPrimitiveType";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		String paramType = null;
		if(param.contains("\"") && param.contains("\"")){
			paramType = "char*";
		}else if(param.contains("'") && param.contains("'")){
			paramType = "char";
		}else{
			try{
				Double.parseDouble(param);
				paramType = "int";
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		if(paramType == null){
			paramType = "undefined";
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return paramType;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(FileUtil.getVariableType("files","/Users/nishanthsivakumar/Workspace/testprojects/gnuprojects/out/coreutils/src/chcon.c"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
