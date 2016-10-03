package VO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*{
	"access_token":"fedb64045b74dbf530190d7108f5b76e3c4562e6",
	"query":{
		"query_params": {
			"package_name":"com.nianticlabs.pokemongo"
		}
	},
	"limit":"1"
}*/
public class PostContent implements Serializable {

    private String access_token;
    private Query query;
    private String limit;
	public PostContent() {
		// TODO Auto-generated constructor stub
	}
	public PostContent(String packageName) {
		this.access_token="fedb64045b74dbf530190d7108f5b76e3c4562e6";
		this.query=new Query(new QueryParams(packageName));
		this.limit = "1";
	}
	public PostContent(String access_token, Query query, String limit) {
		this.access_token = access_token;
		this.query = query;
		this.limit = limit;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "Content [access_token=" + access_token + ", limit=" + limit + "]";
	}
    
    
    
    

   
}