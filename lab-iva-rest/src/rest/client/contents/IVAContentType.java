package rest.client.contents;

import rest.client.contents.interfaces.ContentType;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAContentType implements ContentType {
	
	private String contentTypePart;
	private Class<?> responseType;
	
	public IVAContentType() {
		this.contentTypePart = "";
	}
	
	public IVAContentType(String contentName,String...subContents) {
		this.setContentType(contentName);
		if(subContents != null) { addAllSubContentTypes(subContents); }
	}
	
	public IVAContentType(String contentName,Class<?> responseType, String...subContents) {
		this.setResponseType(responseType);
		this.setContentType(contentName);
		if(subContents != null) { addAllSubContentTypes(subContents); }
	}
	
	private void addAllSubContentTypes(String[] subContents) {
		for(String subContent  : subContents) {
			this.contentTypePart = this.contentTypePart + subContent + "/";
		}
	}

	@Override
	public void setContentType(String contentName) {
		contentTypePart = contentName + "/";
	}

	@Override
	public String getContentType() {
		return contentTypePart;
	}

	@Override
	public void addSubContent(String subContent) {
		contentTypePart = contentTypePart + subContent + "/";
	}

	@Override
	public void setResponseType(Class<?> responseClass) {
		this.responseType = responseClass;
	}

	@Override
	public Class<?> getResponseType() {
		return this.responseType;
	}

}
