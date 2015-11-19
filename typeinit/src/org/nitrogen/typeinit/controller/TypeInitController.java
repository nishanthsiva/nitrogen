package org.nitrogen.typeinit.controller;

import java.util.logging.Logger;

import org.nitrogen.typeinit.objects.TypeInitBean;

public class TypeInitController {
	
	private TypeInitBean typeObject;
	
	private static final Logger LOGGER = Logger.getLogger(TypeInitController.class.getName());
	private static final String CLASS_NAME = TypeInitController.class.getName();
	
	public TypeInitBean getTypeInitialization(String typeName, String programmingLanguage){
		final String METHOD_NAME = "getTypeInitialization";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		//Write logic to fetch from Cache or call the core classes to mine the sequence
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
		return typeObject;
	}
}
