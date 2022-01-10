package com.husseinshoqanebi.morse;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class Utils {
	private final Context context;

	public Utils(Context context) {
		this.context = context;
	}

	public void copy(String title, String text) {
		ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		clipboard.setPrimaryClip(ClipData.newPlainText(title, text.trim()));
	}

	public void showErrorMessage(int strID) {
		Toast.makeText(context, strID, Toast.LENGTH_LONG).show();
	}

	public void showMessage(int strID) {
		Toast.makeText(context, strID, Toast.LENGTH_LONG).show();
	}
}
