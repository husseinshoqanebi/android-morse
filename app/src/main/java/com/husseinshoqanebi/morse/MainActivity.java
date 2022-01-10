package com.husseinshoqanebi.morse;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.husseinshoqanebi.morse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private final String EMPTY = "";
	private String inputText = "";
	private State state = State.TextToMorse;
	private ActivityMainBinding binding;
	private Utils utils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		utils = new Utils(getApplicationContext());

		binding.input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
				inputText = charSequence.toString();
				runOnTextChange();
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});

		binding.changeState.setOnClickListener(v -> {
			if (state == State.TextToMorse) {
				changeStateToText();
			} else if (state == State.MorseToText) {
				changeStateToMorse();
			}
		});

		binding.copy.setOnClickListener(v -> {
			switch (state) {
				case TextToMorse:
					if (Validator.isValidText(binding.input.getText().toString())) {
						utils.copy("text", Converter.textToMorse(binding.input.getText().toString()));
						utils.showMessage(R.string.morse_copy);
					} else {
						utils.showErrorMessage(R.string.morse_copy_fail);
					}
					break;
				case MorseToText:
					if (Validator.isMorse(binding.input.getText().toString())) {
						utils.copy("Morse", Converter.morseToText(binding.input.getText().toString()));
						utils.showMessage(R.string.text_copy);
					} else {
						utils.showErrorMessage(R.string.text_copy_fail);
					}
					break;
			}
		});
	}

	void onValidationSuccess() {
		binding.outputPanel.setVisibility(View.VISIBLE);
		binding.input.setTextColor(Color.BLACK);
		binding.input.setBackground(AppCompatResources.getDrawable(
				getApplicationContext(),
				R.drawable.input_border)
		);
		switch (state) {
			case TextToMorse:
				binding.output.setText(Converter.textToMorse(inputText));
				break;
			case MorseToText:
				binding.output.setText(Converter.morseToText(inputText));
				break;
		}
	}

	void onValidationFail() {
		binding.outputPanel.setVisibility(View.GONE);
		binding.input.setTextColor(Color.RED);
		binding.input.setBackground(AppCompatResources.getDrawable(
				getApplicationContext(),
				R.drawable.input_border_error)
		);
		binding.output.setText(EMPTY);
	}

	void changeStateToMorse() {
		state = State.TextToMorse;
		binding.from.setText(R.string.text);
		binding.to.setText(R.string.morse);
		binding.input.setHint(R.string.text_hint);
		runOnTextChange();
	}

	void changeStateToText() {
		state = State.MorseToText;
		binding.from.setText(R.string.morse);
		binding.to.setText(R.string.text);
		binding.input.setHint(R.string.morse_hint);
		runOnTextChange();
	}

	private void runOnTextChange() {
		if (inputText.isEmpty()) {
			onEmptyInputText();
			return;
		}

		switch (state) {
			case MorseToText:
				if (Validator.isMorse(inputText)) {
					onValidationSuccess();
				} else {
					onValidationFail();
				}
				break;
			case TextToMorse:
				if (Validator.isValidText(inputText)) {
					onValidationSuccess();
				} else {
					onValidationFail();
				}
				break;
		}
	}

	void onEmptyInputText() {
		binding.outputPanel.setVisibility(View.GONE);
		binding.input.setTextColor(Color.BLACK);
		binding.input.setBackground(AppCompatResources.getDrawable(
				getApplicationContext(),
				R.drawable.input_border)
		);
		binding.output.setText(EMPTY);
	}
}