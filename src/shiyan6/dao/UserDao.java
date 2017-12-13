package shiyan6.dao;

import java.util.List;

import shiyan6.entity.User;

/**
 * UserDao 接口
 * @author Changsheng
 *
 */
public interface UserDao {
	/**
	 * 查询所有普通用户信息
	 * @return
	 */
	List<User> findAll(); 
	/**
	 * 通过用户名查看用户是否存在
	 * @param name
	 * @return
	 */
	int findCountByName(String name);
	/**
	 * 通过用户名查看用户
	 * @param name
	 * @return
	 */
	List<User> findByName(String name);
	/**
	 * 通过用户id查询信息
	 * @param id
	 * @return
	 */
	User findById(String id);
	/**
	 * 通过登录名和密码查询用户
	 * @param name
	 * @param password
	 * @return
	 */
	User findByNameAndPass(String name, String password);
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	boolean editUser(User user);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	boolean deleteUser(String id);
}
