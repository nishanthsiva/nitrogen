package org.nitrogen.typeinit.core;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.nitrogen.typeinit.constants.CoreConstants;
import org.nitrogen.typeinit.objects.FunctionBean;

public class SequenceMining {
	private static final Logger LOGGER = Logger.getLogger(SequenceMining.class.getName());
	private static final String CLASS_NAME = TypeInitializer.class.getName();
	
	private HashMap<String,Integer> sequenceCount = new HashMap<String,Integer>();
	private HashMap<String,List<FunctionBean>> sequenceProp = new HashMap<String,List<FunctionBean>>();
	private int maxOccurance;
	private String maxOccuranceKey;
	
	public void addSequence(String sequence, List<FunctionBean> fnSequence){
		final String METHOD_NAME ="addSequence";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		LOGGER.info("Adding Sequence "+sequence);
		if(sequenceCount.containsKey(sequence)){
			int old_count = sequenceCount.get(sequence);
			sequenceCount.put(sequence, old_count+1);
			if(old_count+1 > maxOccurance){
				maxOccurance = old_count+1;
				maxOccuranceKey = sequence;
			}
		}else{
			sequenceCount.put(sequence,new Integer(1));
			sequenceProp.put(sequence, fnSequence);
		}
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
	}
	
	public boolean checkThreshold(){
		final String METHOD_NAME = "getMaxOccuranceSequence";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		if(maxOccurance >= CoreConstants.SEQUENCE_MINING_THRESHOLD){
			return true;
		}
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return false;
	}
	
	public List<FunctionBean> getMaxOccuranceSequence(){
		final String METHOD_NAME = "getMaxOccuranceSequence";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		LOGGER.warning(sequenceCount.toString());
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return sequenceProp.get(maxOccuranceKey);
	}
	public int getMaxOccuranceCount(){
		final String METHOD_NAME = "getMaxOccuranceCount";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
			
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return maxOccurance;
	}

}
