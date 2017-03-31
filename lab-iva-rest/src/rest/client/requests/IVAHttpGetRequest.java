package rest.client.requests;

import javax.ws.rs.core.MediaType;

import rest.client.contents.interfaces.ContentType;
import rest.client.enums.HttpMethodTypes;
import rest.client.parameters.IVAHttpParameterBuilder;
import rest.client.query.IVAQueryBuilder;
import rest.client.requests.interfaces.HttpRequest;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAHttpGetRequest extends BaseHttpRequest implements HttpRequest {
	
	public IVAHttpGetRequest() {
		super.setQueryBuilder(null);
		super.setParamsBuilder(null);
		super.setContentType(null);
		super.setMediaType(MediaType.APPLICATION_JSON);
	}
	
	public IVAHttpGetRequest(IVAQueryBuilder queryBuilder, IVAHttpParameterBuilder paramsBuilder, ContentType contentType, String mediaType) {
		super.setQueryBuilder(queryBuilder);
		super.setParamsBuilder(paramsBuilder);
		super.setContentType(contentType);
		super.setMediaType(mediaType);
	}

	@Override
	public String getUrl() {
		
		String url = "";
		if(this.getContentType() != null) {
			url = url + super.getContentType().getContentType();
		}
		
		if(this.getQueryBuilder() != null) {
			url = url + super.getQueryBuilder().getQuery();
		}
		
		if(this.getParamsBuilder() != null) {
			url = url + "?" + super.getParamsBuilder().getHttpParameterString();
		}
		
		return url;
	}

	@Override
	public HttpMethodTypes getHttpMethod() {
		return HttpMethodTypes.GET;
	}
}
