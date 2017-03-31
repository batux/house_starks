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

public class Channel {

	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<ChannelContentTypes, IVAContentType> contentTypes;
	
	public enum ChannelContentTypes {
		CHANNEL_DETAILS,
		CHANNEL_GROUP_HIER;
	}
	
	public Channel() {
		
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		
		this.contentTypes = new HashMap<ChannelContentTypes, IVAContentType>();
		this.contentTypes.put(ChannelContentTypes.CHANNEL_DETAILS, new IVAContentType("SVSProxy", "channel:channelDetails"));
		this.contentTypes.put(ChannelContentTypes.CHANNEL_GROUP_HIER, new IVAContentType("SVSProxy", "channel", ":channelGroupHier"));
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(ChannelContentTypes chContentType, IVAContentType contentType) {
		this.contentTypes.put(chContentType, contentType);
	}
	
	public void removeContentType(ChannelContentTypes chContentType) {
		this.contentTypes.remove(chContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(ChannelContentTypes contentType) {
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
