package shiyan6.entity;

/**
 * 用户实体类，其中的role用于分别是普通人员还是管理员
 *
 */
public class User {
	private String id;
	private String name;
	private String password;
	private int role;  // 用户身份，1表示普通用户，2表示管理员
	// 无参构造器
	public User() {
		
	}
	/**
	 * 有参构造器
	 * @param id，使用util包下的Common.getUUID()来产生一个8位的uuid
	 * @param name，用户名
	 * @param password，登录密码
	 */
	public User(String id, String name, String password,int role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + "]\n";
	}
	
}
