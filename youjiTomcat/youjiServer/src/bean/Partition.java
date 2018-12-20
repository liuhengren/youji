
package bean;

public class Partition {
	
	
	private int id;
	private String text;
	
	
	
	
	public Partition(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	public Partition(String text) {
		super();
		this.text = text;
	}
	public Partition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
