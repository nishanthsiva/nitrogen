package org.nitrogen.typeinit.objects;

import java.util.List;

public class FunctionInfo {
	
	private int functionId;
	private String functionName;
	private int paramCount;
	private List<TypeInfo> params;
	
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public int getParamCount() {
		return paramCount;
	}
	public void setParamCount(int paramCount) {
		this.paramCount = paramCount;
	}
	public List<TypeInfo> getParams() {
		return params;
	}
	public void setParams(List<TypeInfo> params) {
		this.params = params;
	}
	
	

}
