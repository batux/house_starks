package rest.client.enums;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public enum QueryParameter {

	LOCALE("locale"),
	MIME("mime"),
	TIME_ZONE("timezone"),
	TIME_STAMP_FORMAT("timestampformat"),
	DATE_FORMAT("dateformat"),
	DETAILED_STATUS("detailedstatus"),
	METHOD("method"),
	PAGESIZE("pagesize"),
	PAGE("page"),
	ORDER_BY("orderby"),
	COMPLETE_ITEM("completeitem"),
	TOTAL_ITEMS("totalitems"),
	DELETE_ZERO_ROW_FAILURE("deletezerorowfailure"),
	UPDATE_ZERO_ROW_FAILURE("updatezerorowfailure"),
	BIN_SIZE_MINS("binsizeinmins"),
	ROI("roi");
	
	private String queryPropertyName;
	QueryParameter(String queryPropertyName) {
		this.queryPropertyName = queryPropertyName;
	}

	@Override
	public String toString() {
		return queryPropertyName;
	}
}