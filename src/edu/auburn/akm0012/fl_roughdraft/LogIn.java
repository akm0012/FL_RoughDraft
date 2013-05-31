package edu.auburn.akm0012.fl_roughdraft;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class LogIn extends Activity {

	private RelativeLayout loginLayout;
	private EditText userNameField, passwordField, confirmPasswordField;
	private Button newUserButton, logInButton;

	private boolean isNewUser;

	Toast testToasts_FailedLogin_PassDontMatch, testToasts_SuccessfulLogin,
			testToasts_FailedLogin_UsernameBlank,
			testToasts_FailedLogin_PasswordBlank;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);

		// Instantiate all Buttons and Views
		userNameField = (EditText) findViewById(R.id.UserNameField);
		passwordField = (EditText) findViewById(R.id.PasswordField);
		confirmPasswordField = (EditText) findViewById(R.id.ConfirmPasswordField);

		newUserButton = (Button) findViewById(R.id.NewUserButton);
		logInButton = (Button) findViewById(R.id.LogInButton);

		loginLayout = (RelativeLayout) findViewById(R.id.LoginLayout);

		isNewUser = false;

		testToasts_FailedLogin_PassDontMatch = Toast.makeText(this,
				"Log In Failed, Passwords Don't Match", Toast.LENGTH_SHORT);

		testToasts_SuccessfulLogin = Toast.makeText(this,
				"Log In Successfull!!", Toast.LENGTH_SHORT);

		testToasts_FailedLogin_UsernameBlank = Toast.makeText(this,
				"Log In Failed, Username Blank", Toast.LENGTH_SHORT);

		testToasts_FailedLogin_PasswordBlank = Toast.makeText(this,
				"Log In Failed, Password Blank", Toast.LENGTH_SHORT);

		// User clicks the "New User" button
		newUserButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (!isNewUser) {
					// Create new Confirm Password Field, changes the button to
					// a register button.
					addPasswordConfirmField();
				}

				else {

					// Check to make sure the 2 password fields match
					if (!passwordField.getText().toString()
							.matches(confirmPasswordField.getText().toString())) {

						testToasts_FailedLogin_PassDontMatch.show();

						passwordField.setText("");
						confirmPasswordField.setText("");

						passwordField.setHint("Password");
						confirmPasswordField.setHint("Confirm Password");

						negativeReinforcement();

					}

					// Check for blank User Name Field
					else if (userNameField.getText().toString().matches("")) {

						testToasts_FailedLogin_UsernameBlank.show();

						userNameField.setText("");
						userNameField.setHint("User Name");

						negativeReinforcement();

					}

					// Check if password field is blank (Only need to check this
					// field, since we check if the confirm filed matches
					// earlier)
					else if (passwordField.getText().toString().matches("")) {

						testToasts_FailedLogin_PasswordBlank.show();

						passwordField.setText("");
						confirmPasswordField.setText("");

						passwordField.setHint("Password");
						confirmPasswordField.setHint("Confirm Password");

						negativeReinforcement();

					}

					else {

						registerNewUser(userNameField.getText().toString(),
								passwordField.getText().toString());
						testToasts_SuccessfulLogin.show();

						positiveReinforcement();
					}
				}
			}
		});

		// User clicks the "Log In" button
		logInButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				executeLogin();
			}
		});

		// This should make sure the keyboard does not automatically pop up.
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	private void negativeReinforcement() {
		loginLayout.setBackgroundResource(R.drawable.red_gradient);
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		vib.vibrate(500);
	}

	private void positiveReinforcement() {
		loginLayout.setBackgroundResource(R.drawable.green_gradient);
	}

	// This may be helpful when rotating the screen
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

	// Couldn't get this to work, maybe come back to later.
	// private void changeBackround() {
	//
	// loginLayout.setBackgroundResource(R.drawable.green_red_transition);
	//
	// final TransitionDrawable transition = (TransitionDrawable) loginLayout
	// .getBackground();
	//
	// //transition.setCrossFadeEnabled(true);
	// transition.startTransition(2000);
	//
	// }

	private void addPasswordConfirmField() {

//		// Set the Layout parameters for the new EditText
//		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		layoutParams.addRule(RelativeLayout.BELOW, R.id.PasswordField);
//		layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.PasswordField);
//
//		confirmPasswordField = new EditText(this);
//		confirmPasswordField.setInputType(InputType.TYPE_CLASS_TEXT
//				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
//		confirmPasswordField.setHint("Confirm Password");
//		confirmPasswordField.setEms(10);
//		confirmPasswordField.setTextAppearance(this,
//				android.R.style.TextAppearance_Large);
//		loginLayout.addView(confirmPasswordField, layoutParams);

		confirmPasswordField.setVisibility(View.VISIBLE);
		
		loginLayout.removeView(logInButton);

		// Set some boolean so we know the "New User" button has changed to a
		// "Register Button"
		isNewUser = true;

		newUserButton.setText("Register");

	}

	// Returns a boolean if the login was successful.
	private boolean registerNewUser(String newUserName, String newPassword) {

		// Here we know the newUserName and newPassword are valid.
		// However, we don't know if there is a duplicate in the database yet.

		return false;
	}

	public void executeLogin() {

		String username, password;

		username = userNameField.getText().toString();
		password = passwordField.getText().toString();

		// Username field is blank
		if (username.matches("")) {
			// Turn background red
			userNameField.setHint("Username");

			testToasts_FailedLogin_UsernameBlank.show();
			negativeReinforcement();
		}

		else {
			// Continue Login Progress
		}

		// Password field is blank
		if (password.matches("")) {
			// Turn background red
			passwordField.setHint("Password");

			testToasts_FailedLogin_PasswordBlank.show();
			negativeReinforcement();
		}

		else {
			// Continue Login Progress
			testToasts_SuccessfulLogin.show();
			positiveReinforcement();

		}

		// Intent intent = new Intent(this, FragmentSwitcher.class);
		// startActivity(intent);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.log_in, menu);
	// return true;
	// }

}
