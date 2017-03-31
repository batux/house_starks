package rest.client;

import javax.ws.rs.core.Cookie;

import rest.client.contents.responses.LoginResponse;
import rest.client.enums.HttpMethodTypes;
import rest.client.requests.IVAHttpDeleteRequest;
import rest.client.requests.IVAHttpPostRequest;
import rest.client.requests.IVAHttpPutRequest;
import rest.client.requests.interfaces.HttpRequest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAClient {

	private String baseUrl;
	private WebResource webResource;
	private Cookie currentCookie;
	
	public IVAClient() {
		this.setBaseUrl("");
	}
	
	public LoginResponse login(HttpRequest httpRequest) {
		
		webResource = IVAClientUtil.getWebResource(this.getBaseUrl() + httpRequest.getUrl());
		
		if(httpRequest.getHttpMethod() == HttpMethodTypes.POST) {
			ClientResponse response = webResource.accept(httpRequest.getMediaType()).post(ClientResponse.class, ((IVAHttpPostRequest)httpRequest).getInput());
			if(response != null) {
				if(response.getCookies().size() > 0) {
					setCurrentCookie(response.getCookies().get(0));
					return IVAClientUtil.getResponse(response.getEntity(String.class), httpRequest.getContentType().getResponseType());
				}
			}
		}
		return null;
	}
	
	public <T> T makeHttpRequest(HttpRequest httpRequest) {
		
		String url = this.getBaseUrl() + httpRequest.getUrl();
		System.out.println("URL >> " + url);
		webResource = IVAClientUtil.getWebResource(url);
		
		// Response Type default type is String.
		if(httpRequest.getContentType().getResponseType() == null) { httpRequest.getContentType().setResponseType(String.class); }
		
		ClientResponse response = null;
		if(httpRequest.getHttpMethod() == HttpMethodTypes.GET) {
			response = webResource.accept(httpRequest.getMediaType()).cookie(this.getCurrentCookie()).get(ClientResponse.class);
		}
		else if(httpRequest.getHttpMethod() == HttpMethodTypes.POST) {
			String input = ((IVAHttpPostRequest)httpRequest).getInput();
			if(input == null) {
				response = webResource.accept(httpRequest.getMediaType()).cookie(this.getCurrentCookie()).post(ClientResponse.class);
			}
			else {
				response = webResource.accept(httpRequest.getMediaType()).cookie(this.getCurrentCookie()).post(ClientResponse.class, input);
			}
		}
		else if(httpRequest.getHttpMethod() == HttpMethodTypes.DELETE) {
			String input = ((IVAHttpDeleteRequest)httpRequest).getInput();
			if(input == null) {
				response = webResource.accept(httpRequest.getMediaType()).cookie(this.getCurrentCookie()).delete(ClientResponse.class);
			}
			else {
				response = webResource.accept(httpRequest.getMediaType()).cookie(this.getCurrentCookie()).delete(ClientResponse.class, input);
			}
		}
		else if(httpRequest.getHttpMethod() == HttpMethodTypes.PUT) {
			String input = ((IVAHttpPutRequest)httpRequest).getInput();
			if(input == null) {
				response = webResource.accept(httpRequest.getMediaType()).cookie(this.getCurrentCookie()).put(ClientResponse.class);
			}
			else {
				response = webResource.accept(httpRequest.getMediaType()).cookie(this.getCurrentCookie()).put(ClientResponse.class, input);
			}
		}
		
		return IVAClientUtil.getResponse(response.getEntity(String.class), httpRequest.getContentType().getResponseType());
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public WebResource getWebResource() {
		return webResource;
	}

	public void setWebResource(WebResource webResource) {
		this.webResource = webResource;
	}

	public Cookie getCurrentCookie() {
		return currentCookie;
	}

	public void setCurrentCookie(Cookie currentCookie) {
		this.currentCookie = currentCookie;
	}
}
