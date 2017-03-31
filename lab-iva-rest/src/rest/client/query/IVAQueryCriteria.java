package rest.client.query;

import rest.client.enums.OperandTypes;
import rest.client.query.interfaces.QueryCriteria;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAQueryCriteria<T> implements QueryCriteria<T> {

	private String propertyName;
	private OperandTypes operand;
	private T[] propertyValues;
	
	public IVAQueryCriteria() {
		this.propertyName = null;
		this.propertyValues = null;
		this.operand = null;
	}
	
	public IVAQueryCriteria(OperandTypes operand, String propertyName, T...propertyValues) {
		this.setOperand(operand);
		this.setPropertyName(propertyName);
		this.setPropertyValue(propertyValues);
	}
	
	@Override
	public String getPropertyName() {
		return this.propertyName;
	}

	@Override
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	@Override
	public void setPropertyValue(T... propertyValue) {
		this.propertyValues = propertyValue;
	}
	
	@Override
	public T[] getPropertyValue() {
		return this.propertyValues;
	}

	@Override
	public OperandTypes getOperand() {
		return this.operand;
	}

	@Override
	public void setOperand(OperandTypes operand) {
		this.operand = operand;
	}

	@Override
	public String getQueryCriteriaString() {
		
		String queryCriteriaStr = "";
		if(this.operand == null || this.propertyName == null || this.propertyValues == null || this.propertyValues.length == 0) {
			return queryCriteriaStr;
		}
		
		String valuesStr = "";
		int valueCounter = 0;
		for(T value : this.getPropertyValue()) {
			valuesStr = valuesStr + value.toString();
			if(valueCounter < (this.getPropertyValue().length - 1)) {
				valuesStr = valuesStr + ",";
			}
			valueCounter++;
		}
		
		if(valuesStr.length() == 0) {
			return queryCriteriaStr;
		}
		
		queryCriteriaStr = this.getOperand().toString() + "(" + this.getPropertyName() + "," + valuesStr + ")";
		return queryCriteriaStr;
	}

}
