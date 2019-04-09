package wisielec.wisielec.com.services;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wisielec.wisielec.com.interfaces.Validator;


public class PasswordValidatorService implements Validator {
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[~!@#$%^&*()\\-_.=+\\[{\\]}|;:<>/?,]).{8,40})";
    private static final int MINIMAL_PASSWORD_LENGTH = 8;
    private Pattern pattern;

    public PasswordValidatorService() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean validate(EditText passwordInput) {
        if (passwordInput.getText().length() < MINIMAL_PASSWORD_LENGTH) {
            return false;
        }
        Matcher matcher = pattern.matcher(passwordInput.getText().toString());
        return matcher.matches();
    }
}
