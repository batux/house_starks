package rest.client.contents.responses;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

@JsonIgnoreProperties(value = { "_type", "_markup","_page","_hasMoreItems" })
public class SessionResponse {

	public static class SessionItem {
		
		private String userId;
		private String userName;
		private String role;
		private int utcOffsetMins;
	
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public int getUtcOffsetMins() {
			return utcOffsetMins;
		}

		public void setUtcOffsetMins(int utcOffsetMins) {
			this.utcOffsetMins = utcOffsetMins;
		}
		
		@Override
		public String toString() {
			String response = "";
			response += this.getUserId() + "\n";
			response += this.getUserName() + "\n";
			response += this.getRole() + "\n";
			response += this.getUtcOffsetMins() + "\n";
			return response;
		}
	}
	
	private List<SessionItem> items;
	
	public SessionResponse() {}

	public List<SessionItem> getItems() {
		return items;
	}

	public void setItems(List<SessionItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		String response = "";
		for(SessionItem item : items) {
			response += item.toString() + "\n";
		}
		return response;
	}
}
