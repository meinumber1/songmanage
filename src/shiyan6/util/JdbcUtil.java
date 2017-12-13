package shiyan6.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 数据库帮助类，主要用于获取数据库连接
 * @author Changsheng
 *
 */
public class JdbcUtil {
	// 表示定义数据库的用户名
	private static String USERNAME;

	// 定义数据库的密码
	private static String PASSWORD;

	// 定义数据库的驱动信息
	private static String DRIVER;

	// 定义访问数据库的地址
	private static String URL;

	// 定义数据库的连接
	private static Connection connection;

	/**
	 * 静态加载数据库配置信息，并给相关的属性赋值
	 */
	static {
		try {
			InputStream inStream = JdbcUtil.class.getResourceAsStream("/shiyan6/jdbc.properties");
			Properties prop = new Properties();
			prop.load(inStream);
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
		} catch (Exception e) {
			throw new RuntimeException("读取数据库配置文件异常！", e);
		}
	}
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConn() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 用来测试一下是否获得了链接
	public static void main(String[] args) {
		System.out.println(JdbcUtil.getConn());
	}

}
