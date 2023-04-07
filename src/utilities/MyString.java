package utilities;

public class MyString {
	public static String removeLastChar(String src, char lastChar) {
		if (src != null && !src.isEmpty() && src.charAt(src.length() - 1) == lastChar) {
			src = src.substring(0, src.length() - 1);
		}
		return src;
	}
}
