package rest.client.query.interfaces;

import rest.client.enums.OperandTypes;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public interface QueryCriteria<T> {
	
	public OperandTypes getOperand();
	public void setOperand(OperandTypes operand);
	public String getPropertyName();
	public void setPropertyName(String propertyName);
	public void setPropertyValue(T... propertyValue);
	public T[] getPropertyValue();
	public String getQueryCriteriaString();
	
}
