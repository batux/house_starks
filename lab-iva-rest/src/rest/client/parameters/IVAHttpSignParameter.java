package rest.client.parameters;

import rest.client.parameters.interfaces.HttpParameter;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAHttpSignParameter<T> implements HttpParameter{

	private String sign;
	private Object parameterName;
	private T[] parameterValues;
	
	public IVAHttpSignParameter() {
		this.setParameterName(null);
		this.setSign(null);
		this.parameterValues = null;
	}
	
	public IVAHttpSignParameter(Object parameterName, String sign, T...parameterValues) {
		this.setParameterName(parameterName);
		this.setParameterValues(parameterValues);
		this.setSign(sign);
	}

	@Override
	public Object getParameterName() {
		return parameterName;
	}

	public void setParameterName(Object parameterName) {
		this.parameterName = parameterName;
	}

	public T[] getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(T... parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String getParameterString() {
		
		String httpParameterStr = "";
		if((this.getParameterName() == null || this.getParameterName().toString().length() == 0) || (this.getParameterValues() == null || this.getParameterValues().length == 0)) {
			return httpParameterStr;
		}
		httpParameterStr = this.getParameterName().toString() + "=" + this.getSign() + "(";
		
		int valueCounter = 0;
		for(T value : this.getParameterValues()) {
			httpParameterStr = httpParameterStr + value.toString();
			if(valueCounter < (this.getParameterValues().length - 1)) {
				httpParameterStr = httpParameterStr + ",";
			}
			valueCounter++;
		}
		httpParameterStr = httpParameterStr + ")";
		return httpParameterStr;
	}

}
