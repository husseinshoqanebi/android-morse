package com.husseinshoqanebi.morse;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Converter {
	static String[] morse = {
			".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
			"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
			".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----"
	};
	static String[] character = {
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
	};

	static private String getMorseCode(String letter) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < character.length; i++) {
			map.put(character[i], morse[i]);
		}
		return map.get(letter);
	}

	static private String getCharacter(String letter) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < character.length; i++) {
			map.put(morse[i], character[i]);
		}
		return map.get(letter);
	}

	static public String morseToText(String s) {
		StringBuilder stringJoiner = new StringBuilder();
		String[] words = s.split("[ ]{3}");
		for (String morse : words) {
			String[] letters = morse.split("[ ]");
			for (String letter : letters) {
				stringJoiner.append(getCharacter(letter));
			}
			stringJoiner.append(" ");
		}
		return stringJoiner.toString();
	}

	static public String textToMorse(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		char[] text = s.trim().toLowerCase(Locale.ROOT).toCharArray();
		for (char ch : text) {
			if (ch == '\n') {
				stringBuilder.append("\n");
			} else if (ch == ' ') {
				stringBuilder.append("  ");
			} else {
				stringBuilder.append(getMorseCode(String.valueOf(ch))).append(" ");
			}
		}
		return stringBuilder.toString();
	}
}
