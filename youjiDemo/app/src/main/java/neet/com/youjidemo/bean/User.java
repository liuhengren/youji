package neet.com.youjidemo.bean;

public class User {
    private int user_id;
    private String user_name;
    private String user_phone;
    private String user_psssword;
    private String user_sex;
    private String user_birthday;
    private String user_address;
    private int user_funnum;
    private int user_collection_num;
    private String user_touxiang_url;
    private String user_background_url;
    private String user_introduction;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_psssword='" + user_psssword + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_birthday='" + user_birthday + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_funnum=" + user_funnum +
                ", user_collection_num=" + user_collection_num +
                ", user_touxiang_url='" + user_touxiang_url + '\'' +
                ", user_background_url='" + user_background_url + '\'' +
                ", user_introduction='" + user_introduction + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_psssword() {
        return user_psssword;
    }

    public void setUser_psssword(String user_psssword) {
        this.user_psssword = user_psssword;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public int getUser_funnum() {
        return user_funnum;
    }

    public void setUser_funnum(int user_funnum) {
        this.user_funnum = user_funnum;
    }

    public int getUser_collection_num() {
        return user_collection_num;
    }

    public void setUser_collection_num(int user_collection_num) {
        this.user_collection_num = user_collection_num;
    }

    public String getUser_touxiang_url() {
        return user_touxiang_url;
    }

    public void setUser_touxiang_url(String user_touxiang_url) {
        this.user_touxiang_url = user_touxiang_url;
    }

    public String getUser_background_url() {
        return user_background_url;
    }

    public void setUser_background_url(String user_background_url) {
        this.user_background_url = user_background_url;
    }

    public String getUser_introduction() {
        return user_introduction;
    }

    public void setUser_introduction(String user_introduction) {
        this.user_introduction = user_introduction;
    }
}
