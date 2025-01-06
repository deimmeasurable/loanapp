package com.example.loanapp.config;

import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Phone number regex (international format)
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$";

    // Password strength regex
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    public static boolean isValidEmail(String email) {
        return email != null && emailPattern.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phonePattern.matcher(phoneNumber).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && passwordPattern.matcher(password).matches();
    }
}
