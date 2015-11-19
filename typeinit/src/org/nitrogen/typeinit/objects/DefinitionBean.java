package org.nitrogen.typeinit.objects;


public class DefinitionBean {
	
	private int lineNumber;
	private String filePath;
	private TypeBean typeBean;
	private String[] defSequence;
	private String[] functionSequence;
	
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public TypeBean getTypeBean() {
		return typeBean;
	}
	public void setTypeBean(TypeBean typeBean) {
		this.typeBean = typeBean;
	}
	public String[] getDefSequence() {
		return defSequence;
	}
	public void setDefSequence(String[] defSequence) {
		this.defSequence = defSequence;
	}
	public String[] getFunctionSequence() {
		return functionSequence;
	}
	public void setFunctionSequence(String[] fnSequence) {
		this.functionSequence = fnSequence;
	}

}
