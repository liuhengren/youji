package bean;

public class Comment {

	private int id;
	private String text;
	private int dynamic_id;
	private int like_num;
	private int user_id;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String text, int dynamic_id, int like_num, int user_id) {
		super();
		this.text = text;
		this.dynamic_id = dynamic_id;
		this.like_num = like_num;
		this.user_id = user_id;
	}

	
	public Comment(int id, String text, int dynamic_id, int like_num, int user_id) {
		super();
		this.id = id;
		this.text = text;
		this.dynamic_id = dynamic_id;
		this.like_num = like_num;
		this.user_id = user_id;
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

	public int getDynamic_id() {
		return dynamic_id;
	}

	public void setDynamic_id(int dynamic_id) {
		this.dynamic_id = dynamic_id;
	}

	public int getLike_num() {
		return like_num;
	}

	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
