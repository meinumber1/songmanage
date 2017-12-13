package shiyan6.test;


import org.junit.Test;

import shiyan6.dao.SongDao;
import shiyan6.dao.SongDaoImpl;
import shiyan6.entity.Song;
import shiyan6.util.Common;

/**
 * Dao 层测试类
 * @author Changsheng
 *
 */
public class SongDaoTest {
	private SongDao songDao = new SongDaoImpl();
	@Test
	public void testFindAll() {
		System.out.println(songDao.findAll());
	}

	@Test
	public void testFindByName() {
		System.out.println(songDao.findByName("test"));
	}

	@Test
	public void testFindBylanguage() {
		System.out.println(songDao.findBylanguage("中"));
	}

	@Test
	public void testFindBySinger() {
		System.out.println(songDao.findBySinger("test"));
	}

	@Test
	public void testFindByCategory() {
		System.out.println(songDao.findByCategory("流行"));
	}

	@Test
	public void testDeletSong() {
		System.out.println(songDao.deletSong("213"));
	}

	@Test
	public void testAddSong() {
		Song song = new Song(Common.getUuid(), "testadd", "英文", "乡村", "test");
		System.out.println(songDao.addSong(song));
	}

}
