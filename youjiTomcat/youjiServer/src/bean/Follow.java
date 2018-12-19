package bean;

public class Follow {

	private int id;
	private int user_id;
	private int follow_user_id;
	public Follow(int id, int user_id, int follow_user_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.follow_user_id = follow_user_id;
	}
	
	public Follow(int user_id, int follow_user_id) {
		super();
		this.user_id = user_id;
		this.follow_user_id = follow_user_id;
	}

	public Follow() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getFollow_user_id() {
		return follow_user_id;
	}
	public void setFollow_user_id(int follow_user_id) {
		this.follow_user_id = follow_user_id;
	}
	
}
