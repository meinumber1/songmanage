package shiyan6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shiyan6.entity.Song;
import shiyan6.util.JdbcUtil;

public class SongDaoImpl implements SongDao {
	// 获取数据库连接
	private Connection connection = null;
	// prestatement用来执行动态sql语句，比statement要好
	private PreparedStatement pst = null;
	// ResultSet 用来存放结果
	private ResultSet rs = null;

	@Override
	public List<Song> findAll() {
		// sql语句
		String sql = "SELECT * FROM song ORDER BY orderby";
		// 用来存放结果
		List<Song> songs = new ArrayList<>();
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLanguage(rs.getString("language"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		return songs;
	}

	public Song findById(String id) {
		// sql语句
		String sql = "SELECT * FROM song WHERE id = ? ORDER BY orderby";
		// 用来存放结果
		try {
			connection = JdbcUtil.getConn();

			pst = connection.prepareStatement(sql);
			// 添加参数的值
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Song song = new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLanguage(rs.getString("language"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				return song;
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
		return null;
	}

	@Override
	public List<Song> findByName(String name) {
		// sql语句
		String sql = "SELECT * FROM song WHERE name LIKE concat('%',?,'%') ORDER BY orderby";
		// 用来存放结果
		List<Song> songs = new ArrayList<>();
		try {
			connection = JdbcUtil.getConn();

			pst = connection.prepareStatement(sql);
			// 添加参数的值
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLanguage(rs.getString("language"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		return songs;
	}

	@Override
	public List<Song> findBylanguage(String language) {
		// sql语句
		String sql = "SELECT * FROM song WHERE language LIKE concat('%',?,'%') ORDER BY orderby";
		// 用来存放结果
		List<Song> songs = new ArrayList<>();
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			// 添加参数的值
			pst.setString(1, language);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLanguage(rs.getString("language"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		return songs;
	}

	@Override
	public List<Song> findBySinger(String singer) {
		// sql语句
		String sql = "SELECT * FROM song WHERE singer LIKE concat('%',?,'%') ORDER BY orderby";
		// 用来存放结果
		List<Song> songs = new ArrayList<>();
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			// 添加参数的值
			pst.setString(1, singer);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLanguage(rs.getString("language"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		return songs;
	}

	@Override
	public List<Song> findByCategory(String category) {
		// sql语句
		String sql = "SELECT * FROM song WHERE category LIKE concat('%',?,'%') ORDER BY orderby";
		// 用来存放结果
		List<Song> songs = new ArrayList<>();
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			// 添加参数的值
			pst.setString(1, category);
			rs = pst.executeQuery();
			while (rs.next()) {
				Song song = new Song();
				song.setId(rs.getString("id"));
				song.setName(rs.getString("name"));
				song.setLanguage(rs.getString("language"));
				song.setCategory(rs.getString("category"));
				song.setSinger(rs.getString("singer"));
				songs.add(song);
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
		return songs;
	}

	@Override
	public boolean deletSong(String id) {
		int flag = 0;
		// sql语句
		String sql = "DELETE FROM song WHERE id = ?";
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			// 添加参数的值
			pst.setString(1, id);
			flag = pst.executeUpdate();
			if (flag == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean addSong(Song song) {
		int flag = 0;
		// sql语句
		String sql = "INSERT INTO song(id,name,language,category,singer) VALUES(?,?,?,?,?)";
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);
			pst.setString(1, song.getId());
			pst.setString(2, song.getName());
			pst.setString(3, song.getLanguage());
			pst.setString(4, song.getCategory());
			pst.setString(5, song.getSinger());
			// 添加参数的值
			flag = pst.executeUpdate();
			if (flag == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean updateSong(Song song) {
		int flag = 0;
		// sql语句
		String sql = "UPDATE song SET name=?,language=?,category=?,singer=? WHERE id=?";
		try {
			connection = JdbcUtil.getConn();
			pst = connection.prepareStatement(sql);

			pst.setString(1, song.getName());
			pst.setString(2, song.getLanguage());
			pst.setString(3, song.getCategory());
			pst.setString(4, song.getSinger());
			pst.setString(5, song.getId());
			// 添加参数的值
			flag = pst.executeUpdate();
			if (flag == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

}
