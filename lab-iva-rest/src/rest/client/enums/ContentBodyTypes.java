package rest.client.enums;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public enum ContentBodyTypes {
	
	LOWER_BODY(1),
	UPPER_BODY(2);
	
	private int value;
	ContentBodyTypes(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
