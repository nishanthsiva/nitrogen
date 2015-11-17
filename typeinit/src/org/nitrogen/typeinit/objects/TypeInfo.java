package org.nitrogen.typeinit.objects;

import java.util.List;

public class TypeInfo {
	
	private int typeId;
	private String typeName;
	private List<FunctionInfo> initSequence;
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<FunctionInfo> getInitSequence() {
		return initSequence;
	}
	public void setInitSequence(List<FunctionInfo> initSequence) {
		this.initSequence = initSequence;
	}
	
	

}
