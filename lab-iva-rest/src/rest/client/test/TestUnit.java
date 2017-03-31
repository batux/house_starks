package rest.client.test;

import rest.client.IVAClientUtil;
import rest.client.contents.IVAContentType;
import rest.client.contents.responses.AlertResponse;
import rest.client.contents.responses.LoginResponse;
import rest.client.contents.responses.SessionResponse;
import rest.client.contents.types.AlertExternal;
import rest.client.contents.types.AlertExternal.AlertExternalContentTypes;
import rest.client.contents.types.AlertExternal.AlertExternalProperties;
import rest.client.contents.types.Cameras;
import rest.client.contents.types.Cameras.CameraContentTypes;
import rest.client.contents.types.Channel;
import rest.client.contents.types.Channel.ChannelContentTypes;
import rest.client.contents.types.FarField;
import rest.client.contents.types.FarField.FarFieldConfidenceColors;
import rest.client.contents.types.FarField.FarFieldContentTypes;
import rest.client.contents.types.FarField.FarFieldTypes;
import rest.client.contents.types.FarField.FarFieldProperties;
import rest.client.contents.types.MidField;
import rest.client.contents.types.MidField.MidFieldConfidenceColors;
import rest.client.contents.types.MidField.MidFieldContentTypes;
import rest.client.contents.types.MidField.MidFieldProperties;
import rest.client.contents.types.NearField;
import rest.client.contents.types.NearField.NearFieldConfidenceColors;
import rest.client.contents.types.NearField.NearFieldContentTypes;
import rest.client.contents.types.NearField.NearFieldProperties;
import rest.client.contents.types.Session;
import rest.client.contents.types.Session.SessionContentTypes;
import rest.client.contents.types.TrackSummary.TrackSummaryContentTypes;
import rest.client.contents.types.TrackSummary.TrackSummaryProperties;
import rest.client.contents.types.TrackSummary.TrackSummaryTypes;
import rest.client.contents.types.TrackSummary;
import rest.client.contents.types.VmsServer;
import rest.client.contents.types.VmsServer.WmsServerContentTypes;
import rest.client.enums.ContentBodyTypes;
import rest.client.enums.HttpMethodTypes;
import rest.client.enums.OperandTypes;
import rest.client.enums.QueryParameter;
import rest.client.parameters.IVAHttpFunctionParameter;
import rest.client.parameters.IVAHttpNormalParameter;
import rest.client.parameters.IVAHttpParameterBuilder;
import rest.client.query.IVAQueryBuilder;
import rest.client.query.IVAQueryCriteria;
import rest.client.requests.BaseHttpRequest;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class TestUnit {

	public static void main(String[] args) {
		
		IVAClientUtil.createIvaClient("http://IP_ADDRESS/milsng/");
		
		sessionSample();
//		vmsServerSample();
//		camerasSample();
//		channelSample();
		alertsExternalSample();
//		midFieldSample();
//		nearFieldSample();
//		trackSummarySample();
		
	}
	
	public static void sessionSample() {
		String loginJson = "";
		loginJson = "{" + 		
				"\"items\"" + ": [{" +
				"\"userId\"" + ":" + "\"" + "pvadmin" + "\"," +
				"\"password\"" +":"+ "\"" + "passw0rd" + "\"," +
				"\"locale\"" + ":" + "\"en_US\"," +
				"\"timestampFormat\"" + ":" + "\"yyyy-MM-dd'T'HH.mm.ss.SSS\"," +
				"\"timezone\"" + ":" + "\"America/New_York\"" + "}]" + "}";
		
		Session session = new Session();
		IVAContentType contentType = session.findContentType(SessionContentTypes.SESSION);
		LoginResponse loginResp = session.login(contentType, session.findHttpRequest(HttpMethodTypes.POST), loginJson);
		System.out.println(loginResp);
		
		contentType.setResponseType(SessionResponse.class);
		SessionResponse response = session.getHttpResponse(contentType, session.findHttpRequest(HttpMethodTypes.GET), null, null, null);
		System.out.println(response.toString());
	}
	
	public static void vmsServerSample() {
		
		VmsServer vmsServer = new VmsServer();
		IVAContentType contentType = vmsServer.findContentType(WmsServerContentTypes.WMSSERVER_DETAILS);
		contentType.setResponseType(String.class);
		BaseHttpRequest httpRequest = vmsServer.findHttpRequest(HttpMethodTypes.GET);
		String response = vmsServer.getHttpResponse(contentType, httpRequest, null, null, null);
		System.out.println(response);
	}
	
	public static void camerasSample() {
		Cameras cameras = new Cameras();
		IVAContentType contentType = cameras.findContentType(CameraContentTypes.CAMERA_DETAILS);
		String response = cameras.getHttpResponse(contentType, cameras.findHttpRequest(HttpMethodTypes.GET), null, null, null);
		System.out.println(response);
	}
	
	public static void channelSample() {
		Channel channel = new Channel();
		String response = channel.getHttpResponse(channel.findContentType(ChannelContentTypes.CHANNEL_DETAILS), channel.findHttpRequest(HttpMethodTypes.GET), null, null, null);
		System.out.println(response);
	}
	
	public static void alertsExternalSample() {
		AlertExternal alertExternal = new AlertExternal();
		BaseHttpRequest httpRequest = alertExternal.findHttpRequest(HttpMethodTypes.GET);
		IVAContentType contentType = alertExternal.findContentType(AlertExternalContentTypes.ALERTS_EXTERNAL);
		contentType.addSubContent("=7012");
		
		IVAQueryBuilder queryBuilderForAlerts = new IVAQueryBuilder();
		queryBuilderForAlerts.setQuery(
								(new IVAQueryCriteria<String>(OperandTypes.BETWEEN, AlertExternalProperties.ALERT_START.toString(), "2014-03-30T09.54.09.000", "2014-04-04T09.54.09.000")).getQueryCriteriaString()
							 );
		
		IVAHttpParameterBuilder httpParameterBuilder = new IVAHttpParameterBuilder();
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGE, 1));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGESIZE, 40));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Boolean>(QueryParameter.TOTAL_ITEMS, true));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<String>(QueryParameter.ORDER_BY, AlertExternalProperties.ALERT_START.toString(), "desc"));
		
		AlertResponse response = alertExternal.getHttpResponse(contentType, httpRequest, queryBuilderForAlerts, httpParameterBuilder, null);
		System.out.println(response.toString());

	}
	
	public static void farFieldSample() {
		
		FarField inDoorTracking = new FarField(FarFieldTypes.INDOOR_TRACKING);
		
		IVAContentType contentType = inDoorTracking.findContentType(FarFieldContentTypes.FARFIELD_STATS);
		contentType.addSubContent("=7012");
		
		BaseHttpRequest httpRequest = inDoorTracking.findHttpRequest(HttpMethodTypes.GET);
		
		IVAQueryBuilder queryBuilderForInDoor = new IVAQueryBuilder();
		queryBuilderForInDoor.setQuery(
										  queryBuilderForInDoor.and(
												  					new IVAQueryCriteria<String>(OperandTypes.BETWEEN, FarFieldProperties.START.toString(), "2013-10-22T09.54.09.000", "2013-10-22T13.54.09.000"), 
												  					new IVAQueryCriteria<Integer>(OperandTypes.GREATER_THAN_EQUALS, FarField.FarFieldProperties.TRACK_DURATION.toString(), 2)
														           )
									  );
		
		
		IVAHttpParameterBuilder httpParameterBuilder = new IVAHttpParameterBuilder();
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<String>(FarFieldConfidenceColors.WHITE, "(1100)"));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<String>(FarFieldConfidenceColors.GREEN, "(5000)"));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.BIN_SIZE_MINS, 20));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGE, 1));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGESIZE, 40));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Boolean>(QueryParameter.TOTAL_ITEMS, true));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<String>(QueryParameter.ORDER_BY, FarFieldProperties.WEIGHTED_COMBINED_CONFIDENCE.toString(), "desc"));
		
		String response = inDoorTracking.getHttpResponse(contentType, httpRequest, queryBuilderForInDoor, httpParameterBuilder, null);
		System.out.println(response);
	}
	
	public static void midFieldSample() {
		
		MidField midField = new MidField();
		
		IVAContentType contentType = midField.findContentType(MidFieldContentTypes.MIDFIELD_STATS);
		contentType.addSubContent("=7012");
		
		BaseHttpRequest httpRequest = midField.findHttpRequest(HttpMethodTypes.GET);
		
		IVAQueryBuilder queryBuilder = new IVAQueryBuilder();
		queryBuilder.setQuery(
								queryBuilder.and(
											new IVAQueryCriteria<String>(OperandTypes.BETWEEN, MidFieldProperties.START.toString(), "2013-10-22T09.54.09.000","2013-10-22T13.54.09.000"),
											new IVAQueryCriteria<Integer>(OperandTypes.GREATER_THAN_EQUALS, MidFieldProperties.TRACK_DURATION.toString(), 2)
										)
							 );
		
		IVAHttpParameterBuilder httpParameterBuilder = new IVAHttpParameterBuilder();
		httpParameterBuilder.addHttpParameter(new IVAHttpFunctionParameter<String>(MidFieldProperties.ATTRIBUTE_ID, ContentBodyTypes.LOWER_BODY.toString(), MidFieldConfidenceColors.WHITE.toString(), "1100"));
		httpParameterBuilder.addHttpParameter(new IVAHttpFunctionParameter<String>(MidFieldProperties.ATTRIBUTE_ID, ContentBodyTypes.UPPER_BODY.toString(), MidFieldConfidenceColors.GREEN.toString(), "5000"));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.BIN_SIZE_MINS, 20));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGE, 1));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGESIZE, 40));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Boolean>(QueryParameter.TOTAL_ITEMS, true));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<String>(QueryParameter.ORDER_BY, MidFieldProperties.WEIGHTED_COMBINED_CONFIDENCE.toString(), "desc"));
	
		String response = midField.getHttpResponse(contentType, httpRequest, queryBuilder, httpParameterBuilder, null);
		System.out.println(response);
	}

	public static void nearFieldSample() {
		
		NearField nearField = new NearField();
		
		IVAContentType contentType = nearField.findContentType(NearFieldContentTypes.NEARFIELD_STATS);
		contentType.addSubContent("=7012");
		
		BaseHttpRequest httpRequest = nearField.findHttpRequest(HttpMethodTypes.GET);
		
		IVAQueryBuilder queryBuilder = new IVAQueryBuilder();
		queryBuilder.setQuery(
								queryBuilder.and(
											new IVAQueryCriteria<String>(OperandTypes.BETWEEN, NearFieldProperties.START.toString(), "2013-10-22T09.54.09.000","2013-10-22T13.54.09.000"),
											new IVAQueryCriteria<Integer>(OperandTypes.GREATER_THAN_EQUALS, NearFieldProperties.TRACK_DURATION.toString(), 2)
										)
							 );
		
		IVAHttpParameterBuilder httpParameterBuilder = new IVAHttpParameterBuilder();
		httpParameterBuilder.addHttpParameter(new IVAHttpFunctionParameter<String>(NearFieldProperties.ATTRIBUTE_ID, ContentBodyTypes.LOWER_BODY.toString(), NearFieldConfidenceColors.WHITE.toString(), "1100"));
		httpParameterBuilder.addHttpParameter(new IVAHttpFunctionParameter<String>(NearFieldProperties.ATTRIBUTE_ID, ContentBodyTypes.UPPER_BODY.toString(), NearFieldConfidenceColors.GREEN.toString(), "5000"));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.BIN_SIZE_MINS, 20));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGE, 1));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGESIZE, 40));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Boolean>(QueryParameter.TOTAL_ITEMS, true));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<String>(QueryParameter.ORDER_BY, NearFieldProperties.WEIGHTED_COMBINED_CONFIDENCE.toString(), "desc"));
	
		String response = nearField.getHttpResponse(contentType, httpRequest, queryBuilder, httpParameterBuilder, null);
		System.out.println(response);
		
	}
	
	public static void trackSummarySample() {
		
		TrackSummary trackSummary = new TrackSummary(TrackSummaryTypes.PEOPLE_COUNTING);
		
		IVAContentType contentType = trackSummary.findContentType(TrackSummaryContentTypes.TRACK_SUMMARY_STATS);
		contentType.addSubContent("=7012");
		
		BaseHttpRequest httpRequest = trackSummary.findHttpRequest(HttpMethodTypes.GET);
		
		IVAQueryBuilder queryBuilder = new IVAQueryBuilder();
		queryBuilder.setQuery(
								new IVAQueryCriteria<String>(OperandTypes.BETWEEN, TrackSummaryProperties.START.toString(), "2013-10-22T09.54.09.000","2013-10-22T13.54.09.000").getQueryCriteriaString()
							 );
		
		IVAHttpParameterBuilder httpParameterBuilder = new IVAHttpParameterBuilder();
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.BIN_SIZE_MINS, 20));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGE, 1));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Integer>(QueryParameter.PAGESIZE, 40));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<Boolean>(QueryParameter.TOTAL_ITEMS, true));
		httpParameterBuilder.addHttpParameter(new IVAHttpNormalParameter<String>(QueryParameter.ORDER_BY, TrackSummaryProperties.START.toString(), "desc"));
	
		String response = trackSummary.getHttpResponse(contentType, httpRequest, queryBuilder, httpParameterBuilder, null);
		System.out.println(response);
		
	}
	
}
