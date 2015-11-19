package org.nitrogen.typeinit.delegate;

import java.util.logging.Logger;
import org.nitrogen.typeinit.objects.TypeInitBean;

public class TypeInitDelegate {
	
	private static final Logger LOGGER = Logger.getLogger(TypeInitDelegate.class.getName());
	private static final String CLASS_NAME = TypeInitDelegate.class.getName();
	
	public TypeInitBean getTypeInit(String typeName, String ProgrammingLanguage){
		final String METHOD_NAME = "getTypeInit";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		TypeInitBean typeObject = new TypeInitBean();
		//Write code to call DAO and check if the INIT sequence is available in the database
		
		//Initiate Mining of Large Code Base
		
		
		//Save Retreived Sequence to Data base
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return typeObject;
	}

}
