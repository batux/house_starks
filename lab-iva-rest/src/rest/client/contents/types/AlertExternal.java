package rest.client.contents.types;

import java.util.HashMap;

import rest.client.IVAClientUtil;
import rest.client.contents.IVAContentType;
import rest.client.contents.responses.AlertResponse;
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

public class AlertExternal {

	private HashMap<HttpMethodTypes, BaseHttpRequest> httpRequests;
	private HashMap<AlertExternalContentTypes, IVAContentType> contentTypes;
	
	public enum AlertExternalContentTypes {
		ALERTS_EXTERNAL,
		ALERTS_EXTERNAL_STATS,
		ALERTS_EXTERNAL_ALERT_DETAILS,
		ALERTS_EXTERNAL_ARCHIVE;
	}
	
	public enum AlertExternalProperties {
		
		ALERT_ID("alertId"),
		VIEW_ID("viewId"),
		ALERT_START("ae.alertstart");
		
		private String propertyName;
		
		AlertExternalProperties(String pName) {
			this.propertyName = pName;
		}

		@Override
		public String toString() {
			return this.propertyName;
		}
	}
	
	public AlertExternal() {
		
		this.httpRequests = new HashMap<HttpMethodTypes, BaseHttpRequest>();
		this.httpRequests.put(HttpMethodTypes.GET, new IVAHttpGetRequest());
		this.httpRequests.put(HttpMethodTypes.PUT, new IVAHttpPutRequest());
		
		this.contentTypes = new HashMap<AlertExternal.AlertExternalContentTypes, IVAContentType>();
		this.contentTypes.put(AlertExternalContentTypes.ALERTS_EXTERNAL, new IVAContentType("SVSProxy", AlertResponse.class, "alertsExternal"));
		this.contentTypes.put(AlertExternalContentTypes.ALERTS_EXTERNAL_STATS, new IVAContentType("SVSProxy", "alertsExternal", ":stats"));
		this.contentTypes.put(AlertExternalContentTypes.ALERTS_EXTERNAL_ALERT_DETAILS, new IVAContentType("SVSProxy", "alertsExternal", ":compoundAlertDetails"));
		this.contentTypes.put(AlertExternalContentTypes.ALERTS_EXTERNAL_ARCHIVE, new IVAContentType("SVSProxy", ":archive"));
	}
	
	public void addHttpRequest(HttpMethodTypes httpMethodType, BaseHttpRequest httpRequest) {
		this.httpRequests.put(httpMethodType, httpRequest);
	}
	
	public void removeHttpRequest(HttpMethodTypes httpMethodType) {
		this.httpRequests.remove(httpMethodType);
	}
	
	public void addContentType(AlertExternalContentTypes alertContentType, IVAContentType contentType) {
		this.contentTypes.put(alertContentType, contentType);
	}
	
	public void removeContentType(AlertExternalContentTypes alertContentType) {
		this.contentTypes.remove(alertContentType);
	}
	
	public BaseHttpRequest findHttpRequest(HttpMethodTypes methodType) {
		return this.httpRequests.get(methodType);
	}
	
	public IVAContentType findContentType(AlertExternalContentTypes contentType) {
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
