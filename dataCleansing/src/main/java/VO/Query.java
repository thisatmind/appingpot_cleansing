package VO;
/*"query":{
	"query_params": {
		"package_name":"com.nianticlabs.pokemongo"
	}
},*/
public class Query {
	QueryParams query_params;

	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Query(QueryParams query_params) {
		super();
		this.query_params = query_params;
	}

	public QueryParams getQuery_params() {
		return query_params;
	}

	public void setQuery_params(QueryParams query_params) {
		this.query_params = query_params;
	}

	@Override
	public String toString() {
		return "Query [query_params=" + query_params + "]";
	}
	
	
	
	

}
