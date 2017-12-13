package shiyan6.util;

import java.util.UUID;

/**
 * 用于产生主键（使用uuid）
 * @author Changsheng
 *
 */
public class Common {
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
	}
}
