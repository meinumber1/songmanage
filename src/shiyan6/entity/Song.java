package shiyan6.entity;

/**
 * 歌曲实体类
 *
 */
public class Song {
	private String id; // 编号
	private String name; // 歌曲名
	private String language; // 语言
	private String category; // 类别
	private String singer;  // 歌手名
	/**
	 * 有参构造函数
	 * @param name
	 * @param language
	 * @param category
	 * @param singer
	 */
	public Song(String id, String name, String language, String category, String singer) {
		this.id = id;
		this.name = name;
		this.language = language;
		this.category = category;
		this.singer = singer;
	}
	/**
	 * 无参构造函数
	 */
	public Song() {
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		 return "Song [id=" + id + ", name=" + name + ", language=" + language + ", category=" + category + ", singer="
				+ singer + "]\n";
	}
}
