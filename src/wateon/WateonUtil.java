package wateon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import sun.misc.BASE64Encoder;

class Base64 {
	public static String encode(byte[] encodeBytes) {
		byte[] buf = null;
		String strResult = null;

		BASE64Encoder base64Encoder = new BASE64Encoder();
		ByteArrayInputStream bin = new ByteArrayInputStream(encodeBytes);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		try {
			base64Encoder.encodeBuffer(bin, bout);
		} catch (Exception e) {
			e.printStackTrace();
		}
		buf = bout.toByteArray();
		strResult = new String(buf).trim();
		return strResult;
	}
}

public class WateonUtil {
	public static String generateGroupId(String input) {
		return Base64.encode(input.getBytes()).replace("=", "").replace("/", "");
	}
}
