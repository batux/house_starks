package rest.client.requests;

import rest.client.contents.interfaces.ContentType;
import rest.client.parameters.IVAHttpParameterBuilder;
import rest.client.query.IVAQueryBuilder;
import rest.client.requests.interfaces.HttpRequest;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public abstract class BaseHttpRequest implements HttpRequest{

	private IVAQueryBuilder queryBuilder;
	private IVAHttpParameterBuilder paramsBuilder;
	private ContentType contentType;
	private String mediaType;
	private String input;
	
	public BaseHttpRequest() {
		
		this.setContentType(null);
		this.setInput(null);
		this.setMediaType(null);
		this.setParamsBuilder(null);
		this.setQueryBuilder(null);
	}
	
	@Override
	public ContentType getContentType() {
		return contentType;
	}

	@Override
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public IVAQueryBuilder getQueryBuilder() {
		return queryBuilder;
	}

	public void setQueryBuilder(IVAQueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	public IVAHttpParameterBuilder getParamsBuilder() {
		return paramsBuilder;
	}

	public void setParamsBuilder(IVAHttpParameterBuilder paramsBuilder) {
		this.paramsBuilder = paramsBuilder;
	}
	
	public String getMediaType() {
		return this.mediaType;
	}
	
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
}
