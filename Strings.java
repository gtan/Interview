public class Strings {
	public boolean uniqueCharacters(String str) {
		if (str.length() > 128) return false;
		boolean[] found = new boolean[128];
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			if (found[c]) {
				return false;
			}
			found[c] = true;
		}
		return true; 
		/* 	Could also sort string in O(nlogn) then iterate over string
			Could compare each character to every other character in O(n^2) */
	}

	// If no additional data structures are allowed
	public boolean uniqueCharacters2(String str) {
		if (str.length() > 128) return false;
		int found = 0;
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i) - 'a'; // if string only contains 'a' through 'z'
			if ((found & (1 << c)) > 0) {
				return false;
			} 
			found = found | (1 << c);
		}
		return true;
	}

	public boolean isPermutation(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		int[] count = new int[128];
		char[] s = s1.toCharArray();
		for (char c : s) {
			count[c]++;
		}
		for (int i = 0; i < s2.length(); i++) {
			int c = s2.charAt(i);
			if (count[c] == 0) {
				return false;
			}
			count[c] -= 1;
		}
		return true;
	}

	/* 	Slower but more simple: first sort both strings, then compare. */
	public boolean isPermutation2(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		return sortString(s1).equals(sortString(s2));
	}

	public String sortString(String str) {
		char[] c = str.toCharArray();
		java.util.Arrays.sort(c);
		return new String(c);
	}

	public void replaceSpaces(char[] str, int length) {
		int spaceCount = 0;
		for (int i = 0; i < length; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		int newLength = length + spaceCount*2;
		str[newLength] ='\0';
		for (int i = length - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[newLength - 1] = '0';
				str[newLength - 2] = '2';
				str[newLength - 3] = '%';
				newLength -= 3;
			} else {
				str[newLength] = str[i];
				newLength -= 1;
			}
		}
	}

	public String compress(String str) {
		StringBuffer retval = new StringBuffer();
		char found = str.charAt(0);
		int numFound = 1;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == found) {
				numFound++;
			} else {
				retval.append(found);
				retval.append(numFound);
				found = str.charAt(i);
				numFound = 1;
			}
		}
		retval.append(found);
		retval.append(numFound);
		String result = retval.toString();
		if (result.length() < str.length()) {
			return result;
		}
		return str;
	}

	public boolean isRotation(String s1, String s2) { 
		int len = s1.length();
		if (len == s2.length() && len > 0) {
			String s1s1 = s1 + s1;
			return isSubstring(s1s1, s2);
		}
		return false;
	}
}