package rest.client.enums;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public enum OperandTypes {

	EQUALS("eq"),
	GREATER_THAN("gt"),
	GREATER_THAN_EQUALS("gte"),
	LESS_THAN("lt"),
	LESS_THAN_EQUALS("lte"),
	BETWEEN("btw"),
	IN("in"),
	LIKE("like");
	
	private String operandName;
	OperandTypes(String operandName) {
		this.operandName = operandName;
	}
	
	@Override
	public String toString() {
		return this.operandName;
	}
}
