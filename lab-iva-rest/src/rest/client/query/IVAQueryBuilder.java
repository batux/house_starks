package rest.client.query;

import rest.client.query.interfaces.QueryCriteria;

/**
 * 
 * @author Batuhan Duzgun (batux) Computer Engineer / Industrial Engineer
 *
 */

public class IVAQueryBuilder {
	
	private String query;
	
	public IVAQueryBuilder() {
		this.query = "";
	}

	public String and(QueryCriteria<?>... queryCriteria) {
		return "and(" + logicalQuery(queryCriteria) + ")";
	}
	
	public String or(QueryCriteria<?>... queryCriteria) {
		return "or(" + logicalQuery(queryCriteria) + ")";
	}
	
	private String logicalQuery(QueryCriteria<?>... queryCriteria) {
		String andQueries = "";
		int queryCounter = 0;
		for(QueryCriteria<?> criteria : queryCriteria) {
			andQueries = andQueries + criteria.getQueryCriteriaString();
			if(queryCounter < (queryCriteria.length - 1)) {
				andQueries = andQueries + ",";
			}
			queryCounter++;
		}
		return andQueries;
	}
	
	public String addQueryCriteria(QueryCriteria<?> queryCriteria) {
		return queryCriteria.getQueryCriteriaString();
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = "(" + query + ")";
	}
}
