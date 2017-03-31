package rest.client.contents.responses;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "_markup", "_page", "_hasMoreItems" })
public class AlertResponse implements Serializable {

	@JsonIgnoreProperties(value = { "start", "end", "videoProxy", "preRollTime", "archive", "timestamp", "showOnMils", "transfer2Console", "centroid", "bBox", "initialAlertStartTimestamp" })
	public static class AlertItem implements Serializable{
		
		@JsonIgnoreProperties(value = { "class" })
		public static class AlertSummary implements Serializable{
			private String identity;
			private String trackId;
			private Double confidence;
			public String getIdentity() {
				return identity;
			}
			public void setIdentity(String identity) {
				this.identity = identity;
			}
			public String getTrackId() {
				return trackId;
			}
			public void setTrackId(String trackId) {
				this.trackId = trackId;
			}
			public Double getConfidence() {
				return confidence;
			}
			public void setConfidence(Double confidence) {
				this.confidence = confidence;
			}
			
			@Override
			public String toString() {
				String response = "";
				response += this.getIdentity() + "\n";
				response += this.getTrackId() + "\n";
				response += this.getConfidence() + "\n";
				return response;
			}
		}
		
		public static class KeyFrame implements Serializable{
			
			public static class FrameTime implements Serializable{
				private String timestamp;
				private Long frameNumber;
				public String getTimestamp() {
					return timestamp;
				}
				public void setTimestamp(String timestamp) {
					this.timestamp = timestamp;
				}
				public Long getFrameNumber() {
					return frameNumber;
				}
				public void setFrameNumber(Long frameNumber) {
					this.frameNumber = frameNumber;
				}
				
				@Override
				public String toString() {
					String response = "";
					response += this.getTimestamp() + "\n";
					response += this.getFrameNumber() + "\n";
					return response;
				}
			}
			
			private Long display;
			private String image;
			private FrameTime frameTime;
			public Long getDisplay() {
				return display;
			}
			public void setDisplay(Long display) {
				this.display = display;
			}
			public String getImage() {
				return image;
			}
			public void setImage(String image) {
				this.image = image;
			}
			public FrameTime getFrameTime() {
				return frameTime;
			}
			public void setFrameTime(FrameTime frameTime) {
				this.frameTime = frameTime;
			}
			
			@Override
			public String toString() {
				String response = "";
				response += this.getImage() + "\n";
				response += this.getDisplay() + "\n";
				response += this.getFrameTime().toString();
				return response;
			}
		}
		
		private Long alertId;
		private Long viewId;
		private AlertSummary alertSummary;
		private KeyFrame keyframe;
		private Long alertPriority;
		private String alertType;
		
		public Long getAlertId() {
			return alertId;
		}
		public void setAlertId(Long alertId) {
			this.alertId = alertId;
		}
		public Long getViewId() {
			return viewId;
		}
		public void setViewId(Long viewId) {
			this.viewId = viewId;
		}
		public AlertSummary getAlertSummary() {
			return alertSummary;
		}
		public void setAlertSummary(AlertSummary alertSummary) {
			this.alertSummary = alertSummary;
		}
		public KeyFrame getKeyframe() {
			return keyframe;
		}
		public void setKeyframe(KeyFrame keyframe) {
			this.keyframe = keyframe;
		}
		public Long getAlertPriority() {
			return alertPriority;
		}
		public void setAlertPriority(Long alertPriority) {
			this.alertPriority = alertPriority;
		}
		public String getAlertType() {
			return alertType;
		}
		public void setAlertType(String alertType) {
			this.alertType = alertType;
		}
		
		@Override
		public String toString() {
			String response = "";
			response += this.getAlertType() + "\n";
			response += this.getAlertId() + "\n";
			response += this.getAlertPriority() + "\n";
			response += this.getAlertSummary().toString() + "\n";
			response += this.getKeyframe().toString() + "\n";
			response += this.getViewId() + "\n";
			return response;
		}
	}
	
	private String _type;
	private String _totalItems;
	private List<AlertItem> items;
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public String get_totalItems() {
		return _totalItems;
	}
	public void set_totalItems(String _totalItems) {
		this._totalItems = _totalItems;
	}
	public List<AlertItem> getItems() {
		return items;
	}
	public void setItems(List<AlertItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		String response = "";
		response += this.get_type() + "\n";
		response += this.get_totalItems() + "\n";
		for (Iterator<AlertItem> iterator = this.getItems().iterator(); iterator.hasNext();) {
			AlertItem item = (AlertItem) iterator.next();
			response += item.toString();
		}
		return response;
	}
}
