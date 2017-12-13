package shiyan6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shiyan6.entity.User;
import shiyan6.util.JdbcUtil;

/**
 * UserDao的实现类
 *
 */
public class UserDaoImpl implements UserDao {
	// 获取数据库连接
	private Connection connection = null;
	// prestatement用来执行动态sql语句，比statement要好
	private PreparedStatement pst = null;
	// ResultSet 用来存放结果
	private ResultSet rs = null;

	@Override
	public List<User> findAll() {
		// sql语句
		String sql = "SELECT * FROM user WHERE role = 1 ORDER BY orderby";
		// 用来存储结果
		List<User> users = new ArrayList<>();
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery(); // 执行sql
			// 把查询到信息给封装到User实体类中，再放到list中
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}

	public int findCountByName(String name) {
		int count = 0;
		// sql语句，?号相当于占位符
		String sql = "SELECT COUNT(*) as count FROM user WHERE name = ?";
		// 存放结果
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			pst.setString(1, name); // 填充参数
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
				System.out.println("count" + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public User findById(String id) {
		// sql语句，?号相当于占位符
		String sql = "SELECT * FROM user WHERE id = ?";
		// 存放结果
		User user = null;
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			pst.setString(1, id); // 填充参数
			rs = pst.executeQuery();
			if (rs.next()) {
				// 初始化User对象
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User findByNameAndPass(String name, String password) {
		// sql语句，?号相当于占位符
		String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
		// 存放结果
		User user = null;
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			pst.setString(1, name); // 填充参数
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				// 初始化User对象
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean addUser(User user) {
		// sql语句，?号相当于占位符
		String sql = "INSERT INTO user(id, name, password, role) VALUES(?,?,?,?)";
		// 是否成功
		boolean flag = false;
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			pst.setString(1, user.getId()); // 填充参数
			pst.setString(2, user.getName());
			pst.setString(3, user.getPassword());
			pst.setInt(4, user.getRole());
			if (pst.executeUpdate() == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean editUser(User user) {
		// sql语句，?号相当于占位符
		String sql = "UPDATE user SET name=?, password=?, role=? " + "WHERE id=? ";
		// 是否成功
		boolean flag = false;
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getRole());
			pst.setString(4, user.getId()); // 填充参数
			if (pst.executeUpdate() == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean deleteUser(String id) {
		// sql语句，?号相当于占位符
		String sql = "DELETE FROM user WHERE id=?";
		// 是否成功
		boolean flag = false;
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			pst.setString(1, id);
			if (pst.executeUpdate() == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public List<User> findByName(String name) {
		// sql语句
		String sql = "SELECT * FROM user WHERE role = 1 AND name LIKE concat('%',?,'%') ORDER BY orderby";
		// 用来存放结果
		List<User> users = new ArrayList<>();
		try {
			connection = JdbcUtil.getConn();

			pst = connection.prepareStatement(sql);
			// 添加参数的值
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return users;
	}
}
