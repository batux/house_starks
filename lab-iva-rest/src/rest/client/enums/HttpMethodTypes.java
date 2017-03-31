package rest.client.enums;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public enum HttpMethodTypes {

	PUT("PUT"),
	GET("GET"),
	POST("POST"),
	DELETE("DELETE");
	
	String methodName = null;
	
	HttpMethodTypes(String methodName) {
		this.methodName = methodName;
	}
	
	@Override
	public String toString() {
		return this.methodName;
	}
}
