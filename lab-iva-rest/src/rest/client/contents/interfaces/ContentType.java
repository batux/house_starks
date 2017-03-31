package rest.client.contents.interfaces;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public interface ContentType {

	public void setContentType(String contentName);
	public String getContentType();
	public void addSubContent(String subContent);
	public void setResponseType(Class<?> responseClass);
	public Class<?> getResponseType();
	
}
