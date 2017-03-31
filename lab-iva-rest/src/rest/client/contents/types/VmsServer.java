package rest.client.contents.types;

import java.util.HashMap;

import rest.client.IVAClientUtil;
import rest.client.contents.IVAContentType;
import rest.client.enums.HttpMethodTypes;
import rest.client.parameters.IVAHttpParameterBuilder;
import rest.client.query.IVAQueryBuilder;
import rest.client.requests.BaseHttpRequest;
import rest.client.requests.IVAHttpGetRequest;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class VmsServer {

	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<WmsServerContentTypes, IVAContentType> contentTypes;
	
	public enum WmsServerContentTypes {
		WMSSERVER,
		WMSSERVER_DETAILS;
	}
	
	public VmsServer() {
		
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		
		this.contentTypes = new HashMap<WmsServerContentTypes, IVAContentType>();
		this.contentTypes.put(WmsServerContentTypes.WMSSERVER, new IVAContentType("SVSProxy", "vmsServer"));
		this.contentTypes.put(WmsServerContentTypes.WMSSERVER_DETAILS, new IVAContentType("SVSProxy", "vmsServer", ":vmsServerDetails"));
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(WmsServerContentTypes wmsContentType, IVAContentType contentType) {
		this.contentTypes.put(wmsContentType, contentType);
	}
	
	public void removeContentType(WmsServerContentTypes wmsContentType) {
		this.contentTypes.remove(wmsContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(WmsServerContentTypes contentType) {
		return this.contentTypes.get(contentType);
	}
	
	public <T> T getHttpResponse(IVAContentType contentType, BaseHttpRequest httpRequest, IVAQueryBuilder queryBuilder, IVAHttpParameterBuilder httpParameterBuilder, String input) {
		
		httpRequest.setContentType(contentType);
		httpRequest.setInput(input);
		httpRequest.setParamsBuilder(httpParameterBuilder);
		httpRequest.setQueryBuilder(queryBuilder);
		
		return IVAClientUtil.getIvaClient().makeHttpRequest(httpRequest);
	}
}
