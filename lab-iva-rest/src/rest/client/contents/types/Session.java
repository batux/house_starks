package rest.client.contents.types;

import java.util.HashMap;

import rest.client.IVAClientUtil;
import rest.client.contents.IVAContentType;
import rest.client.contents.responses.LoginResponse;
import rest.client.enums.HttpMethodTypes;
import rest.client.parameters.IVAHttpParameterBuilder;
import rest.client.query.IVAQueryBuilder;
import rest.client.requests.BaseHttpRequest;
import rest.client.requests.IVAHttpDeleteRequest;
import rest.client.requests.IVAHttpGetRequest;
import rest.client.requests.IVAHttpPostRequest;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class Session {

	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<SessionContentTypes, IVAContentType> contentTypes;
	
	public enum SessionContentTypes {
		SESSION
	}
	
	public Session() {
		
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		this.httpRequests.put(HttpMethodTypes.POST, new IVAHttpPostRequest());
		this.httpRequests.put(HttpMethodTypes.DELETE, new IVAHttpDeleteRequest());
		
		this.contentTypes = new HashMap<SessionContentTypes, IVAContentType>();
		this.contentTypes.put(SessionContentTypes.SESSION, new IVAContentType("SVSProxy", LoginResponse.class, "session"));
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(SessionContentTypes sesContentType, IVAContentType contentType) {
		this.contentTypes.put(sesContentType, contentType);
	}
	
	public void removeContentType(SessionContentTypes sesContentType) {
		this.contentTypes.remove(sesContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(SessionContentTypes contentType) {
		return this.contentTypes.get(contentType);
	}
	
	public LoginResponse login(IVAContentType contentType, BaseHttpRequest httpRequest, String input) {
		
		httpRequest.setContentType(contentType);
		httpRequest.setInput(input);
		
		return IVAClientUtil.getIvaClient().login(httpRequest);
	}
	
	public <T> T getHttpResponse(IVAContentType contentType, BaseHttpRequest httpRequest, IVAQueryBuilder queryBuilder, IVAHttpParameterBuilder httpParameterBuilder, String input) {
		
		httpRequest.setContentType(contentType);
		httpRequest.setInput(input);
		httpRequest.setParamsBuilder(httpParameterBuilder);
		httpRequest.setQueryBuilder(queryBuilder);
		
		return IVAClientUtil.getIvaClient().makeHttpRequest(httpRequest);
	}
	
}
