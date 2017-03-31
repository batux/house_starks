package rest.client.contents.types;

import java.util.HashMap;

import rest.client.IVAClientUtil;
import rest.client.contents.IVAContentType;
import rest.client.enums.HttpMethodTypes;
import rest.client.parameters.IVAHttpParameterBuilder;
import rest.client.query.IVAQueryBuilder;
import rest.client.requests.BaseHttpRequest;
import rest.client.requests.IVAHttpGetRequest;
import rest.client.requests.IVAHttpPutRequest;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class NearField {

	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<NearFieldContentTypes, IVAContentType> contentTypes;
	
	public enum NearFieldContentTypes {
		NEARFIELD,
		NEARFIELD_STATS,
		NEARFIELD_ARCHIVE;
	}
	
	public enum NearFieldProperties {
		
		TRACK_ID("nfp.trackId"),
		VIEW_ID("nfp.viewId"),
		TRACK_DURATION("nfp.trackDuration"),
		WEIGHTED_COMBINED_CONFIDENCE("nfp.weightedCombinedConfidence"),
		ATTRIBUTE_ID("nfpc.attributeId"),
		START("nfp.start");
		
		private String propertyName;
		NearFieldProperties(String propertyName) {
			this.propertyName = propertyName;
		}
		
		@Override
		public String toString() {
			return this.propertyName;
		}
	}
	
	public enum NearFieldConfidenceColors {
		
		WHITE("nfpc.confidenceWhite"),
		BLACK("nfpc.confidenceBlack"),
		RED("nfpc.confidenceRed"),
		YELLOW("nfpc.confidenceYellow"),
		GREEN("nfpc.confidenceGreen"),
		BLUE("nfpc.confidenceBlue"),
		ORANGE("nfpc.confidenceBlue"),
		CYAN("nfpc.confidenceBlue"),
		MAGENTA("nfpc.confidenceBlue"),
		BROWN("nfpc.confidenceBlue"),
		BEIGE("nfpc.confidenceBlue"),
		LIGHTGRAY("nfpc.confidenceBlue"),
		DARKGRAY("nfpc.confidenceBlue");
		
		private String confidenceColorName;
		NearFieldConfidenceColors(String confidenceColorName) {
			this.confidenceColorName = confidenceColorName;
		}
		
		@Override
		public String toString() {
			return this.confidenceColorName;
		}
	}
	
	public NearField() {
		
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		this.httpRequests.put(HttpMethodTypes.PUT, new IVAHttpPutRequest());
		
		this.contentTypes = new HashMap<NearFieldContentTypes, IVAContentType>();
		this.contentTypes.put(NearFieldContentTypes.NEARFIELD, new IVAContentType("SVSProxy", "nearfieldPeople"));
		this.contentTypes.put(NearFieldContentTypes.NEARFIELD_ARCHIVE, new IVAContentType("SVSProxy", "nearfieldPeople", ":archive"));
		this.contentTypes.put(NearFieldContentTypes.NEARFIELD_STATS, new IVAContentType("SVSProxy", "nearfieldPeople", ":stats"));
		
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(NearFieldContentTypes nfpContentType, IVAContentType contentType) {
		this.contentTypes.put(nfpContentType, contentType);
	}
	
	public void removeContentType(NearFieldContentTypes nfpContentType) {
		this.contentTypes.remove(nfpContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(NearFieldContentTypes contentType) {
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
