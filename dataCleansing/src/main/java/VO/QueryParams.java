package VO;

public class QueryParams {
	private String package_name;

	public QueryParams() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueryParams(String package_name) {
		super();
		this.package_name = package_name;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	@Override
	public String toString() {
		return "QueryParams [package_name=" + package_name + "]";
	}
	
	

}
