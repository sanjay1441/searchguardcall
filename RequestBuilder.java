package elastic.searchguard;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;

public class RequestBuilder {

	public static SearchRequestBuilder getSearchRequestBuilder(String index,String type) {
			 Client client = null;
			 {
			try {
		client = EsCall.getConnection();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		Search search = new Search();
		search.searchRequest();
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
		
			 return searchRequestBuilder;
		 	}
	}
}