package rest.client.contents.responses;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class LoginResponse {

	public class LoginDetail {
		
		private boolean success;
		private String code;
		private String message;
		
		public LoginDetail() {}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		@Override
		public String toString() {
			String loginDetailStr = "";
			loginDetailStr += this.isSuccess() + " " + this.getCode() + " " + this.getMessage();
			return loginDetailStr;
		} 
	}
	
	private LoginDetail status;
	
	public LoginResponse() {}

	public LoginDetail getStatus() {
		return status;
	}

	public void setStatus(LoginDetail status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return this.getStatus().toString();
	}
}
