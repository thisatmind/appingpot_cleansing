package appingpot.FourtyTwoMatterResultfulService;

import java.util.List;

public class PostResultVO {
	List<Results> results;
	int number_results;
	boolean has_next;
	int page;
	int num_pages;
	String limit;
	public PostResultVO() {
		// TODO Auto-generated constructor stub
	}
	public PostResultVO(List<Results> results, int number_results, boolean has_next, int page, int num_pages,
			String limit) {
		this.results = results;
		this.number_results = number_results;
		this.has_next = has_next;
		this.page = page;
		this.num_pages = num_pages;
		this.limit = limit;
	}
	public List<Results> getResults() {
		return results;
	}
	public void setResults(List<Results> results) {
		this.results = results;
	}
	public int getNumber_results() {
		return number_results;
	}
	public void setNumber_results(int number_results) {
		this.number_results = number_results;
	}
	public boolean isHas_next() {
		return has_next;
	}
	public void setHas_next(boolean has_next) {
		this.has_next = has_next;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNum_pages() {
		return num_pages;
	}
	public void setNum_pages(int num_pages) {
		this.num_pages = num_pages;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "PostResultVO [results=" + results + ", number_results=" + number_results + ", has_next=" + has_next
				+ ", page=" + page + ", num_pages=" + num_pages + ", limit=" + limit + "]";
	}


	
	
	
	
	
	

}
