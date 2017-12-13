package shiyan6.service;

import java.util.List;

import shiyan6.entity.User;

/**
 * User的业务层
 *
 */
public interface UserService {
	/**
	 * 查询所有普通用户
	 * @return
	 */
	List<User> findAll();
	/**
	 * 根据用户名查询
	 * @param name
	 * @return
	 */
	List<User> findByName(String name);
	/**
	 * 用户登录
	 * @param name，登录名
	 * @param password
	 * @return
	 */
	User login(String name, String password);
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	boolean updatePassword(User user);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	boolean register(User user);
	/**
	 * 查看用户是否存在
	 * @param name
	 * @return
	 */
	boolean checkUser(String name);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	boolean deleteUser(String id);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	boolean updateUserInfo(User user);
}
