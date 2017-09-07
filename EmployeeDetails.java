package elastic.searchguard;

import java.util.Scanner;

import org.elasticsearch.action.search.SearchRequestBuilder;
import elastic.searchguard.EsCall;

public class EmployeeDetails {
	private String empid;
	private double salary;
	private double percentage;
	
	Scanner stringScanner = new Scanner(System.in);
		
	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getPercentage() {
		return percentage;
	}


	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public static void main(String[] args) {

		try {
			System.out.println("Connected");
			System.out.println("Port Number: " + EsCall.getPort());
			System.out.println("ClusterName: "+ EsCall.getClusterName());
			SearchRequestBuilder sr=RequestBuilder.getSearchRequestBuilder("fir", "cases");
			 			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	}