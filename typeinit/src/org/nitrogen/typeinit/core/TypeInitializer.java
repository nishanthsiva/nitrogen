package org.nitrogen.typeinit.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.nitrogen.typeinit.constants.CoreConstants;
import org.nitrogen.typeinit.objects.DefinitionBean;
import org.nitrogen.typeinit.objects.FunctionBean;
import org.nitrogen.typeinit.objects.TypeBean;
import org.nitrogen.typeinit.objects.TypeInitBean;
import org.nitrogen.typeinit.util.DirectoryUtil;
import org.nitrogen.typeinit.util.FileUtil;
import org.nitrogen.typeinit.util.ParsingUtil;
import org.nitrogen.typeinit.util.StringUtil;

public class TypeInitializer {

	private static final Logger LOGGER = Logger.getLogger(TypeInitializer.class.getName());
	private static final String CLASS_NAME = TypeInitializer.class.getName();
	
	public TypeInitBean getTypeInit(String typeName, String programmingLanguage) throws IOException{
		final String METHOD_NAME = "getTypeInit";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		TypeInitBean typeInitBean = new TypeInitBean();
		List<FunctionBean> initSequence = new ArrayList<FunctionBean>();
		TypeBean typeBean = new TypeBean();
		typeBean.setTypeName(typeName);
		typeBean.setTypeId(0);
		typeInitBean.setTypeBean(typeBean);
		//get all sequences that contain the type
		List<DefinitionBean> defBeanList = getSequencesByType(typeBean);
		LOGGER.log(Level.FINE,"Sequences returned - "+defBeanList.size());

		SequenceMining seqMine = new SequenceMining();
		for(DefinitionBean defBean: defBeanList){
			List<FunctionBean> fnSequence = new ArrayList<FunctionBean>();
			StringBuffer fnNameList = new StringBuffer();
			//populate all function calls from a given sequence
			LOGGER.log(Level.FINER,defBean.toString());
			 if(populateFunctionSequence(defBean)){
				 //resolve function parameters for each function in sequence
				 for(String fnCall: defBean.getFunctionSequence()){
					 if(fnCall != null){
						FunctionBean fnBean = ParsingUtil.parseCallSite(fnCall.split("=")[1], defBean.getFilePath());
					 	fnSequence.add(fnBean);
					 	fnNameList.append(fnBean.getFunctionName()+"--");
					 }
				 }
				 if(fnNameList.toString().trim()!= ""){
					 seqMine.addSequence(fnNameList.toString(),fnSequence);
					 if(seqMine.checkThreshold()){
						 break;
					 }
				 }
				 //pass all function sequence to a sequence mining algorithm 
				 //which will return the most commonly occuring sequence
			 }
		}
		LOGGER.warning(seqMine.getMaxOccuranceSequence()+" "+seqMine.getMaxOccuranceCount());
		for(FunctionBean fnb : seqMine.getMaxOccuranceSequence()){
			LOGGER.warning(StringUtil.buildFunctionPrototype(fnb));
		}

		typeInitBean.setInitSequence(initSequence);
		
		//get all files C files from data store
		//get Sequence from each file
		// mine function calls from Sequence
		// Resolve data types for function parameters
		// construct TypeInfo Object
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return typeInitBean;
	}
	
	private List<DefinitionBean> getSequencesByType(TypeBean typeBean){
		final String METHOD_NAME = "getSequencesByType";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		List<String> cFiles = DirectoryUtil.getAllCFiles(CoreConstants.C_LANG_DATA_STORE_PATH);
		List<DefinitionBean> defSequence = new ArrayList<DefinitionBean>();
		for(String cFile: cFiles){
			LOGGER.log(Level.FINEST,"Scanning file -"+cFile);
			try {
				defSequence.addAll(FileUtil.getDefitionSequence(typeBean,cFile));
			} catch (FileNotFoundException e) {
				LOGGER.log(Level.INFO, e.getMessage(),e);
				//TODO throw the exception to front end
			} catch (IOException e) {
				LOGGER.log(Level.INFO, e.getMessage(),e);
				//TODO throw the exception to front end
			}
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return defSequence;
		
	}
	
	private boolean populateFunctionSequence(DefinitionBean defBean){
		final String METHOD_NAME = "getFunctionSequence";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		String [] fnSequence = new String[defBean.getDefSequence().length];
		int i = 0;
		for(String stmt : defBean.getDefSequence()){
			LOGGER.log(Level.FINEST,"Checking Statement - "+stmt);
			if(ParsingUtil.isAssignStatement(stmt)){
				LOGGER.log(Level.FINER,"Assignment Statements - "+stmt);
				String rhs = stmt.split("=")[1];
				rhs = StringUtil.removeTabsAndNewline(rhs);
				if(ParsingUtil.isFunctionCall(rhs)){
					fnSequence[i++] = stmt; 
					LOGGER.log(Level.FINE,"Function call found -"+stmt);
				}
			}
		}
		if(i > 0){
			defBean.setFunctionSequence(fnSequence);
			return true;
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return false;
	}
	public static void main(String[] args) {
		TypeInitializer initializer = new TypeInitializer();
		try {
			TypeInitBean typeInit = initializer.getTypeInit("FTS", "C");
			List<FunctionBean> initSeq = typeInit.getInitSequence();
			for(FunctionBean fnBean: initSeq){
				System.out.println("Function name -"+fnBean.getFunctionName());
				System.out.print("Function params - [");
				for(TypeInitBean param: fnBean.getParams()){
					System.out.print(param.getTypeBean().getTypeName()+",");
				}
				System.out.println("]");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
