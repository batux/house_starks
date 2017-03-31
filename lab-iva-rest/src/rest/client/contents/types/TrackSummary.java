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

public class TrackSummary {

	private TrackSummaryTypes trackSummaryType;
	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<TrackSummaryContentTypes, IVAContentType> contentTypes;
	
	public enum TrackSummaryTypes {
		
		IPR_DEVICE("lprDevice"),
		IPR_VMS("lprVMS"),
		PEOPLE_COUNTING("peopleCounting");
		
		private String trackSummaryTypeName;
		TrackSummaryTypes(String trackSummaryTypeName) {
			this.trackSummaryTypeName = trackSummaryTypeName;
		}
		
		@Override
		public String toString() {
			return this.trackSummaryTypeName;
		}
	}
	
	public enum TrackSummaryContentTypes {
		TRACK_SUMMARY_FIELD,
		TRACK_SUMMARY_ARCHIVE,
		TRACK_SUMMARY_STATS;
	}
	
	public enum TrackSummaryProperties {
		
		TRACK_ID("trackId"),
		VIEW_ID("viewId"),
		START("start");
		
		private String propertyName;
		TrackSummaryProperties(String propertyName) {
			this.propertyName = propertyName;
		}
		
		@Override
		public String toString() {
			return this.propertyName;
		}
	}

	public TrackSummary(TrackSummaryTypes trackSummaryType) {
		
		this.setTrackSummaryType(trackSummaryType);
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		this.httpRequests.put(HttpMethodTypes.PUT, new IVAHttpPutRequest());
		
		this.contentTypes = new HashMap<TrackSummaryContentTypes, IVAContentType>();
		this.contentTypes.put(TrackSummaryContentTypes.TRACK_SUMMARY_FIELD, new IVAContentType("SVSProxy", this.getTrackSummaryType().toString()));
		this.contentTypes.put(TrackSummaryContentTypes.TRACK_SUMMARY_ARCHIVE, new IVAContentType("SVSProxy", this.getTrackSummaryType().toString(), ":archive"));
		this.contentTypes.put(TrackSummaryContentTypes.TRACK_SUMMARY_STATS, new IVAContentType("SVSProxy", this.getTrackSummaryType().toString(), ":stats"));
		
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(TrackSummaryContentTypes tscContentType, IVAContentType contentType) {
		this.contentTypes.put(tscContentType, contentType);
	}
	
	public void removeContentType(TrackSummaryContentTypes tscContentType) {
		this.contentTypes.remove(tscContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(TrackSummaryContentTypes contentType) {
		return this.contentTypes.get(contentType);
	}
	
	public TrackSummaryTypes getTrackSummaryType() {
		return trackSummaryType;
	}

	public void setTrackSummaryType(TrackSummaryTypes trackSummaryType) {
		this.trackSummaryType = trackSummaryType;
	}
	
	public <T> T getHttpResponse(IVAContentType contentType, BaseHttpRequest httpRequest, IVAQueryBuilder queryBuilder, IVAHttpParameterBuilder httpParameterBuilder, String input) {
		
		httpRequest.setContentType(contentType);
		httpRequest.setInput(input);
		httpRequest.setParamsBuilder(httpParameterBuilder);
		httpRequest.setQueryBuilder(queryBuilder);
		
		return IVAClientUtil.getIvaClient().makeHttpRequest(httpRequest);
	}
	
}
