package org.nitrogen.typeinit.objects;

import java.util.List;

public class TypeInitBean {
	
	private TypeBean typeBean;
	private int typeInitId;
	
	public TypeInitBean() {
		typeBean = new TypeBean();
	}
	public TypeBean getTypeBean() {
		return typeBean;
	}
	public void setTypeBean(TypeBean typeBean) {
		this.typeBean = typeBean;
	}
	private List<FunctionBean> initSequence;
	
	public List<FunctionBean> getInitSequence() {
		return initSequence;
	}
	public void setInitSequence(List<FunctionBean> initSequence) {
		this.initSequence = initSequence;
	}
	public int getTypeInitId() {
		return typeInitId;
	}
	public void setTypeInitId(int typeInitId) {
		this.typeInitId = typeInitId;
	}
	@Override
	public boolean equals(Object obj) {
		TypeInitBean typeInitBean = (TypeInitBean) obj;
		boolean isEqual = true;
		if(this.initSequence.size() == typeInitBean.getInitSequence().size()){
			for(FunctionBean fnBean: this.initSequence){
				if(!typeInitBean.getInitSequence().contains(fnBean)){
					isEqual = false;
					break;
				}
			}
		}
		return isEqual;
	}
	

}
