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

public class Cameras {

	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<CameraContentTypes, IVAContentType> contentTypes;
	
	public enum CameraContentTypes {
		CAMERA,
		CAMERA_DETAILS;
	}
	
	public Cameras() {
		
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		
		this.contentTypes = new HashMap<Cameras.CameraContentTypes, IVAContentType>();
		this.contentTypes.put(CameraContentTypes.CAMERA, new IVAContentType("SVSProxy", "cameras"));
		this.contentTypes.put(CameraContentTypes.CAMERA_DETAILS, new IVAContentType("SVSProxy", "cameras:camerasDetails"));
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(CameraContentTypes camContentType, IVAContentType contentType) {
		this.contentTypes.put(camContentType, contentType);
	}
	
	public void removeContentType(CameraContentTypes camContentType) {
		this.contentTypes.remove(camContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(CameraContentTypes contentType) {
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
