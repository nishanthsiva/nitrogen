package org.nitrogen.typeinit.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.nitrogen.typeinit.constants.UtilityConstants;
import org.nitrogen.typeinit.objects.DefinitionBean;
import org.nitrogen.typeinit.objects.TypeBean;

public class FileUtil {

	
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class.getName());
	private static final String CLASS_NAME = FileUtil.class.getName();
	
	public static List<DefinitionBean> getDefitionSequence(TypeBean typeObject, String filePath) throws IOException{
		final String METHOD_NAME = "getInitSequence";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		FileReader fileReader = new FileReader(filePath);;
		BufferedReader bufReader = new BufferedReader(fileReader);
		List<DefinitionBean> defBeanList = new ArrayList<DefinitionBean>();
		List<String> sequence = new ArrayList<String>();
		int lineNumber = 1;
		while(bufReader.ready()){
			String line = bufReader.readLine();
			if(sequence.size() == Integer.parseInt(UtilityConstants.INIT_SEARCH_LENGTH)/2)
				sequence.remove(0);
			sequence.add(line);
			if(ParsingUtil.isAssignStatement(line) && hasInitType(line,typeObject)){
				sequence.addAll(getFollowSequence(bufReader));
				if(sequence.size() > 0){
					DefinitionBean defBean = new DefinitionBean();
					defBean.setTypeBean(typeObject);
					defBean.setLineNumber(lineNumber);
					defBean.setFilePath(filePath);
					defBean.setDefSequence(sequence.toArray(new String[0]));
					defBeanList.add(defBean);
					sequence = new ArrayList<String>();
				}
			}
			lineNumber++;
		}
		
		bufReader.close();
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return defBeanList;
	}
	
	private static List<String> getFollowSequence(BufferedReader bufReader) throws IOException{
		final String METHOD_NAME = "getFollowSequence";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		List<String> followSequence = new ArrayList<String>();
		int index = 0;
		
		while(bufReader.ready() && index < Integer.parseInt(UtilityConstants.INIT_SEARCH_LENGTH)/2){
			followSequence.add(bufReader.readLine());
			index++;
		}
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return followSequence;
	}
	
	private static boolean hasInitType(String statement, TypeBean typeObject){
		final String METHOD_NAME = "getInitType";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		boolean hasInitType = false;
		String [] lhsTokens = statement.split("=")[0].split(" ");
		for(String token: lhsTokens){
			if(StringUtil.removeTabsAndNewline(token).trim().equals(typeObject.getTypeName())){
				hasInitType = true;
				break;
			}
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return hasInitType;
		
	}
	
	public static String getVariableType(String variable, String filePath) throws IOException{
		final String METHOD_NAME = "getVariableType";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		FileReader fileReader = new FileReader(filePath);;
		BufferedReader bufReader = new BufferedReader(fileReader);
		String variableType = null;
		
		while(bufReader.ready()){
			String line = bufReader.readLine();
			if(line.contains(variable)){
				LOGGER.info("Line found -"+line);
				String[] tokens = line.split(" ");
				for(int i =0; i< tokens.length; i++){
					String tempVar = tokens[i].replaceAll("\\*", "");
					tempVar = tempVar.replaceAll(",", "");
					System.out.println(tokens[i]);
					if(tempVar.equals(variable) && i != 0){
						variableType = StringUtil.removePunctuations(tokens[i-1]);
						break;
					}
				}
				if(variableType != null){
					break;
				}
			}
		}
		
		bufReader.close();
		fileReader.close();
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return variableType;
	}
	public static void main(String[] args) {
		try {
			TypeBean typeObject = new TypeBean();
			typeObject.setTypeName("FILE");
			
			for(DefinitionBean defBean : FileUtil.getDefitionSequence(typeObject,"/Users/nishanthsivakumar/Workspace/testprojects/gnuprojects/out/gawk/debug.c")){
				for(String line: defBean.getDefSequence()){
						System.out.println(line);
				}
				System.out.println("**************");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
