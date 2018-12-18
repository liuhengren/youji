package bean;

public class Collection {

	private int id;
	private int user_id;
	private int dynamic_id;
	
	public Collection(int user_id, int dynamic_id) {
		super();
		this.user_id = user_id;
		this.dynamic_id = dynamic_id;
	}

	public Collection(int id, int user_id, int dynamic_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.dynamic_id = dynamic_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getDynamic_id() {
		return dynamic_id;
	}

	public void setDynamic_id(int dynamic_id) {
		this.dynamic_id = dynamic_id;
	}
	
}
