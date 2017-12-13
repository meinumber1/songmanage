package shiyan6.service;

import java.util.List;

import shiyan6.entity.Song;

public interface SongService {
	/**
	 * 显示所有歌曲
	 * @return
	 */
	List<Song> findAll();
	/**
	 * 通过id查找信息
	 * @return
	 */
	Song findById(String id);
	/**
	 * 按条件歌曲名查询歌曲
	 * @param condition
	 * @return
	 */
	List<Song> findByName(String name);
	/**
	 * 按语言查询歌曲
	 * @param language
	 * @return
	 */
	List<Song> findBylanguage(String language);
	/**
	 * 根据歌手来查询歌曲
	 * @param singer
	 * @return
	 */
	List<Song> findBySinger(String singer);
	/**
	 * 格局歌曲类别来查询歌曲
	 * @param category
	 * @return
	 */
	List<Song> findByCategory(String category);
	
	/**
	 * 删除歌曲
	 * @param id
	 * @return
	 */
	boolean deletSong(String id);
	/**
	 * 添加歌曲
	 * @param song
	 * @return
	 */
	boolean addSong(Song song);
	/**
	 * 修改歌曲
	 * @param song
	 * @return
	 */
	boolean updateSong(Song song);
}
