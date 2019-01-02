package bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Dynamic {

	private int id;
	private int user_id;
	private String text;
	private String img;
	private int collection_num;
	private int like_num;
	private int comment_num;
	private String address;
	private Timestamp time;
	private int partition_id;
	
	public Dynamic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dynamic(int id, int user_id, String text, String img, int collection_num, int like_num, int comment_num,
			String address, Timestamp time, int partition_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.text = text;
		this.img = img;
		this.collection_num = collection_num;
		this.like_num = like_num;
		this.comment_num = comment_num;
		this.address = address;
		this.time = time;
		this.partition_id = partition_id;
	}
	public Dynamic(int user_id, String text, String img, int collection_num, int like_num, int comment_num,
			String address, Timestamp time, int partition_id) {
		super();
		this.user_id = user_id;
		this.text = text;
		this.img = img;
		this.collection_num = collection_num;
		this.like_num = like_num;
		this.comment_num = comment_num;
		this.address = address;
		this.time = time;
		this.partition_id = partition_id;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getCollection_num() {
		return collection_num;
	}
	public void setCollection_num(int collection_num) {
		this.collection_num = collection_num;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getPartition_id() {
		return partition_id;
	}
	public void setPartition_id(int partition_id) {
		this.partition_id = partition_id;
	}
	
	
}
