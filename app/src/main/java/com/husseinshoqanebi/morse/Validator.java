package com.husseinshoqanebi.morse;

public class Validator {
	static public boolean isMorse(String text) {
		return text.trim().matches("[.-]{1,5}(?> [.-]{1,5})*(?>[ ]{3}[.-]{1,5}(?> [.-]{1,5})*)*");
	}

	static public boolean isValidText(String text) {
		return text.trim().matches("^[a-zA-Z0-9 \n\r]*$");
	}
}
