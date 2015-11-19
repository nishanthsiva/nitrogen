package org.nitrogen.typeinit.objects;

import java.util.List;

public class FunctionBean {
	
	private int functionId;
	private String functionName;
	private int paramCount;
	private List<TypeInitBean> params;
	
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
	public List<TypeInitBean> getParams() {
		return params;
	}
	public void setParams(List<TypeInitBean> params) {
		this.params = params;
	}
	
	@Override
	public boolean equals(Object obj) {
		FunctionBean fnBean = (FunctionBean) obj;
		if(this.functionName == fnBean.getFunctionName() && this.paramCount == fnBean.paramCount){
			return true;
		}
		return false;
	}

}
