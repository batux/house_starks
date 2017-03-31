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

public class FarField {

	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<FarFieldContentTypes, IVAContentType> contentTypes;
	private FarFieldTypes farFieldType;

	public enum FarFieldTypes {
		
		INDOOR_TRACKING("indoorTracking"),
		MIDFIELD_VEHICLE("midfieldVehicle"),
		OUTDOOR_TRACKING("outdoorTracking"),
		RAILWAY_MONITORING("railwayMonitoring");
		
		private String fieldTypeName;
		FarFieldTypes(String fieldName) {
			this.fieldTypeName = fieldName;
		}
		
		@Override
		public String toString() {
			return this.fieldTypeName;
		}
	}
	
	public enum FarFieldContentTypes {
		FARFIELD,
		FARFIELD_TRACKS,
		FARFIELD_STATS,
		FARFIELD_HEATMAP,
		FARFIELD_ARCHIVE;
	}
	
	public enum FarFieldProperties {
		
		TRACK_ID("idt.trackId"),
		VIEW_ID("idt.viewId"),
		TRACK_DURATION("idt.trackDuration"),
		WEIGHTED_COMBINED_CONFIDENCE("idt.weightedCombinedConfidence"),
		START("idt.start");
		
		private String propertyName;
		FarFieldProperties(String propertyName) {
			this.propertyName = propertyName;
		}
		
		@Override
		public String toString() {
			return this.propertyName;
		}
	}
	
	public enum FarFieldConfidenceColors {
		
		WHITE("idt.confidenceWhite"),
		BLACK("idt.confidenceBlack"),
		RED("idt.confidenceRed"),
		YELLOW("idt.confidenceYellow"),
		GREEN("idt.confidenceGreen"),
		BLUE("idt.confidenceBlue");
		
		private String confidenceColorName;
		FarFieldConfidenceColors(String confidenceColorName) {
			this.confidenceColorName = confidenceColorName;
		}
		
		@Override
		public String toString() {
			return this.confidenceColorName;
		}
	}
	
	public FarField(FarFieldTypes farFieldType) {
	
		this.farFieldType = farFieldType;
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		this.httpRequests.put(HttpMethodTypes.PUT, new IVAHttpPutRequest());
		
		this.contentTypes = new HashMap<FarFieldContentTypes, IVAContentType>();
		this.contentTypes.put(FarFieldContentTypes.FARFIELD, new IVAContentType("SVSProxy", this.farFieldType.toString()));
		this.contentTypes.put(FarFieldContentTypes.FARFIELD_ARCHIVE, new IVAContentType("SVSProxy", this.farFieldType.toString(), ":archive"));
		this.contentTypes.put(FarFieldContentTypes.FARFIELD_HEATMAP, new IVAContentType("SVSProxy", this.farFieldType.toString(), ":heatmap"));
		this.contentTypes.put(FarFieldContentTypes.FARFIELD_STATS, new IVAContentType("SVSProxy", this.farFieldType.toString(), ":stats"));
		this.contentTypes.put(FarFieldContentTypes.FARFIELD_TRACKS, new IVAContentType("SVSProxy", this.farFieldType.toString(), ":tracks"));
		
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(FarFieldContentTypes idtContentType, IVAContentType contentType) {
		this.contentTypes.put(idtContentType, contentType);
	}
	
	public void removeContentType(FarFieldContentTypes idtContentType) {
		this.contentTypes.remove(idtContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(FarFieldContentTypes contentType) {
		return this.contentTypes.get(contentType);
	}

	public FarFieldTypes getFarFieldType() {
		return farFieldType;
	}

	public void setFarFieldType(FarFieldTypes farFieldType) {
		this.farFieldType = farFieldType;
	}
	
	public <T> T getHttpResponse(IVAContentType contentType, BaseHttpRequest httpRequest, IVAQueryBuilder queryBuilder, IVAHttpParameterBuilder httpParameterBuilder, String input) {
		
		httpRequest.setContentType(contentType);
		httpRequest.setInput(input);
		httpRequest.setParamsBuilder(httpParameterBuilder);
		httpRequest.setQueryBuilder(queryBuilder);
		
		return IVAClientUtil.getIvaClient().makeHttpRequest(httpRequest);
	}
	
}
