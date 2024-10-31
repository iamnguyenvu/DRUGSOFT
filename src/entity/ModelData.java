package entity;

public class ModelData {
	private String month;
	private double total;

	public ModelData() {
		super();
	}

	public ModelData(String month, double total) {
		super();
		this.month = month;
		this.total = total;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
