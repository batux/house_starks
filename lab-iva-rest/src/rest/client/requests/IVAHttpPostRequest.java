package rest.client.requests;

import javax.ws.rs.core.MediaType;

import rest.client.contents.interfaces.ContentType;
import rest.client.enums.HttpMethodTypes;
import rest.client.requests.interfaces.HttpRequest;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAHttpPostRequest extends BaseHttpRequest implements HttpRequest {

	public IVAHttpPostRequest() {
		super.setContentType(null);
		super.setInput(null);
		super.setMediaType(MediaType.APPLICATION_JSON);
	}
	
	public IVAHttpPostRequest(ContentType contentType, String mediaType, String input) {
		super.setContentType(contentType);
		super.setMediaType(mediaType);
		super.setInput(input);
	}
	
	@Override
	public String getUrl() {
		return super.getContentType().getContentType();
	}

	@Override
	public HttpMethodTypes getHttpMethod() {
		return HttpMethodTypes.POST;
	}

}
