package com.bigdataaccess.support;

public class LanguageChecker {

	public static final int ENGLISH_WORD = 0;
	public static final int CHINESE_WORD = 1;
	public static final int KOREAN_WORD = 2;
	public static final int COMPLEX_WORD = 3;
	public static final int UNKNOWN_WORD = -1;

	private static boolean isComplexWord(char[] ch) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch[0]);
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];

			if (ub != Character.UnicodeBlock.of(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Analyze which country's word.
	 * @param word
	 * @return country code.
	 */
	public static int whichLanguage(String word) {
		int wordType = UNKNOWN_WORD;
		char[] ch = word.toCharArray();

		if (ch.length > 0) {
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch[0]);

			if (ub == Character.UnicodeBlock.BASIC_LATIN) {
				wordType = ENGLISH_WORD;
			} else if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
				wordType = CHINESE_WORD;
			} else if (ub == Character.UnicodeBlock.HANGUL_SYLLABLES) {
				wordType = KOREAN_WORD;
			} else {
				wordType = UNKNOWN_WORD;
				// System.out.println(ub);
			}
		}

		if (wordType != UNKNOWN_WORD) {
			if (isComplexWord(ch)) {
				wordType = COMPLEX_WORD;
			}
		}
		return wordType;
	}
}
