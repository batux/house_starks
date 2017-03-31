package rest.client.requests.interfaces;

import rest.client.contents.interfaces.ContentType;
import rest.client.enums.HttpMethodTypes;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public interface HttpRequest {

	public String getUrl();
	public HttpMethodTypes getHttpMethod();
	public String getMediaType();
	public void setContentType(ContentType contentType);
	public ContentType getContentType();
	
}
