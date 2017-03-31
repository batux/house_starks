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

public class MidField {
	
	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<MidFieldContentTypes, IVAContentType> contentTypes;
	
	public enum MidFieldContentTypes {
		MIDFIELD,
		MIDFIELD_TRACKS,
		MIDFIELD_STATS,
		MIDFIELD_HEATMAP,
		MIDFIELD_ARCHIVE;
	}
	
	public enum MidFieldProperties {
		
		TRACK_ID("mfp.trackId"),
		VIEW_ID("mfp.viewId"),
		TRACK_DURATION("mfp.trackDuration"),
		WEIGHTED_COMBINED_CONFIDENCE("mfp.weightedCombinedConfidence"),
		ATTRIBUTE_ID("mfpc.attributeId"),
		START("mfp.start");
		
		private String propertyName;
		MidFieldProperties(String propertyName) {
			this.propertyName = propertyName;
		}
		
		@Override
		public String toString() {
			return this.propertyName;
		}
	}
	
	public enum MidFieldConfidenceColors {
		
		WHITE("mfpc.confidenceWhite"),
		BLACK("mfpc.confidenceBlack"),
		RED("mfpc.confidenceRed"),
		YELLOW("mfpc.confidenceYellow"),
		GREEN("mfpc.confidenceGreen"),
		BLUE("mfpc.confidenceBlue"),
		ORANGE("mfpc.confidenceBlue"),
		CYAN("mfpc.confidenceBlue"),
		MAGENTA("mfpc.confidenceBlue"),
		BROWN("mfpc.confidenceBlue"),
		BEIGE("mfpc.confidenceBlue"),
		LIGHTGRAY("mfpc.confidenceBlue"),
		DARKGRAY("mfpc.confidenceBlue");
		
		private String confidenceColorName;
		MidFieldConfidenceColors(String confidenceColorName) {
			this.confidenceColorName = confidenceColorName;
		}
		
		@Override
		public String toString() {
			return this.confidenceColorName;
		}
	}
	
	public MidField() {
		
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		this.httpRequests.put(HttpMethodTypes.PUT, new IVAHttpPutRequest());
		
		this.contentTypes = new HashMap<MidFieldContentTypes, IVAContentType>();
		this.contentTypes.put(MidFieldContentTypes.MIDFIELD, new IVAContentType("SVSProxy", "midfieldPeople"));
		this.contentTypes.put(MidFieldContentTypes.MIDFIELD_ARCHIVE, new IVAContentType("SVSProxy", "midfieldPeople", ":archive"));
		this.contentTypes.put(MidFieldContentTypes.MIDFIELD_HEATMAP, new IVAContentType("SVSProxy", "midfieldPeople", ":heatmap"));
		this.contentTypes.put(MidFieldContentTypes.MIDFIELD_STATS, new IVAContentType("SVSProxy", "midfieldPeople", ":stats"));
		this.contentTypes.put(MidFieldContentTypes.MIDFIELD_TRACKS, new IVAContentType("SVSProxy", "midfieldPeople", ":tracks"));
		
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(MidFieldContentTypes mfpContentType, IVAContentType contentType) {
		this.contentTypes.put(mfpContentType, contentType);
	}
	
	public void removeContentType(MidFieldContentTypes mfpContentType) {
		this.contentTypes.remove(mfpContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(MidFieldContentTypes contentType) {
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
