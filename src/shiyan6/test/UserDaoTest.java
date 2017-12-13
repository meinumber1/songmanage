package shiyan6.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import shiyan6.dao.UserDao;
import shiyan6.dao.UserDaoImpl;
import shiyan6.entity.User;

/**
 * UserDao 层测试类 
 *
 */
public class UserDaoTest {
	private UserDao userDao = new UserDaoImpl();
	@Test
	public void testFindAll() {
		List<User> users = userDao.findAll();
		Assert.assertNotNull(users);
		System.out.println(users);
	}

	@Test
	public void testFindById() {
		User user = userDao.findById("1");
		Assert.assertNotNull(user);
		System.out.println(user);
	}

	@Test
	public void testFingByNameAndPass() {
		User user = userDao.findByNameAndPass("admin", "123");
		Assert.assertNotNull(user);
		System.out.println(user);
	}

	@Test
	public void testAddUser() {
		User user = new User("3","testadd","test",2);
		System.out.println(userDao.addUser(user));
	}

	@Test
	public void testEditUser() {
		User user = new User("3","testadd","testedit",2);
		System.out.println(userDao.editUser(user));
	}

	@Test
	public void testDeleteUser() {
		System.out.println(userDao.deleteUser("4"));
	}

}
