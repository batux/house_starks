package rest.client.parameters;

import java.util.ArrayList;
import java.util.List;

import rest.client.parameters.interfaces.HttpParameter;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAHttpParameterBuilder {

	private List<HttpParameter> httpParameterList;
	
	public IVAHttpParameterBuilder() {
		this.setHttpParameterList(new ArrayList<HttpParameter>());
	}
	
	public void addHttpParameter(HttpParameter httpParameter) {
		this.getHttpParameterList().add(httpParameter);
	}
	
	public void removeHttpParameter(Object parameterName) {
		
		for(HttpParameter httpParameter : this.getHttpParameterList()) {
			if(httpParameter.getParameterName() == null) { continue; }
			if(httpParameter.getParameterName().toString().equals(parameterName.toString()) == true) {
				this.httpParameterList.remove(httpParameter);
				break;
			}
		}
	}
	
	public void clearHttpParameters() {
		this.httpParameterList.clear();
	}

	public List<HttpParameter> getHttpParameterList() {
		return this.httpParameterList;
	}

	public void setHttpParameterList(List<HttpParameter> httpParameterList) {
		this.httpParameterList = httpParameterList;
	}
	
	public String getHttpParameterString() {
		
		String httpParameterString = "";
		
		int httpParamCounter = 0;
		for(HttpParameter httpParameter : this.getHttpParameterList()) {
			httpParameterString = httpParameterString + httpParameter.getParameterString();
			if(httpParamCounter < (getHttpParameterList().size() - 1)) {
				httpParameterString = httpParameterString + "&";
			}
			httpParamCounter++;
		}
		return httpParameterString;
	}
}
