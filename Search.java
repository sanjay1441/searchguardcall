package elastic.searchguard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;

import elastic.searchguard.RequestBuilder;

public class Search {
	
	EmployeeDetails empDetails = new EmployeeDetails();
	
	Scanner stringScanner = new Scanner(System.in);
	
	String indexName = "fir",type = "cases",id,field,fieldValue;
	boolean endOrSkipEntryFlag, invalidString;
	Map<String,Object> empData=new HashMap<String,Object>();
	
	public SearchResponse searchRequest(){
		
		SearchRequestBuilder searchReq = RequestBuilder.getSearchRequestBuilder(indexName, type);
			
		System.out.println("Enter the field on which you want to search");
		field = stringScanner.nextLine();
		
		System.out.println("Enter the fieldValue to search");
		fieldValue = stringScanner.nextLine();
		
		if(!field.isEmpty()){
			while(fieldValue.isEmpty()){
				System.out.println("Since field is given, Field Value is Mandatory");
				fieldValue = stringScanner.nextLine();
		}
			searchIndexOnField(indexName, type, field, fieldValue);
		}
		else
			searchRequest(indexName, type, fieldValue);
		
		SimpleQueryStringBuilder querySimpleString = QueryBuilders.simpleQueryStringQuery(fieldValue);
		searchReq.setQuery(querySimpleString);
		return searchReq.execute().actionGet();
	}
	
	public SearchResponse searchRequest(String indexName, String type, String fieldValue){
		
		SearchRequestBuilder searchReq=RequestBuilder.getSearchRequestBuilder(indexName, type);
		searchReq.setIndices(indexName);
		searchReq.setTypes(type);
	
		SimpleQueryStringBuilder querySimpleString = QueryBuilders.simpleQueryStringQuery(fieldValue);
		searchReq.setQuery(querySimpleString);
	
		return searchReq.execute().actionGet();
	}
	
	public SearchResponse searchIndexOnField(String indexName, String type, String field, String fieldValue){
		
		SearchRequestBuilder searchReq=RequestBuilder.getSearchRequestBuilder(indexName, type);
		searchReq.setIndices(indexName);
		searchReq.setTypes(type);
		MatchQueryBuilder queryOnField = QueryBuilders.matchQuery(field,fieldValue);
		searchReq.setQuery(queryOnField);
		return searchReq.execute().actionGet();
	}

}
