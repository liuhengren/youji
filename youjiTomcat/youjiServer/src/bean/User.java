package bean;

import java.sql.Date;

public class User {

	private int id;
	private String phone;
	private String password;
	private String name;
	private String sex;
	private Date birthday;
	private String address;
	private int funnum;
	private int collection_num;
	private String touxiang_url;
	private String backgrond_url;
	private String instruction;
	public User(int id, String phone, String password, String name, String sex, Date date, String address,
			int funnum, int collection_num, String touxiang_url, String backgrond_url, String instruction) {
		super();
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birthday = date;
		this.address = address;
		this.funnum = funnum;
		this.collection_num = collection_num;
		this.touxiang_url = touxiang_url;
		this.backgrond_url = backgrond_url;
		this.instruction = instruction;
	}
	public User(String phone, String password, String name, String sex,
			Date birthday, String address, int funnum,
			int collection_num, String touxiang_url, String backgrond_url, String instruction) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
		this.funnum = funnum;
		this.collection_num = collection_num;
		this.touxiang_url = touxiang_url;
		this.backgrond_url = backgrond_url;
		this.instruction = instruction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFunnum() {
		return funnum;
	}
	public void setFunnum(int funnum) {
		this.funnum = funnum;
	}
	public int getCollection_num() {
		return collection_num;
	}
	public void setCollection_num(int collection_num) {
		this.collection_num = collection_num;
	}
	public String getTouxiang_url() {
		return touxiang_url;
	}
	public void setTouxiang_url(String touxiang_url) {
		this.touxiang_url = touxiang_url;
	}
	public String getBackgrond_url() {
		return backgrond_url;
	}
	public void setBackgrond_url(String backgrond_url) {
		this.backgrond_url = backgrond_url;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
}
