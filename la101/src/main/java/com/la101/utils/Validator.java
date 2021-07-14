package com.la101.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private static Pattern pattern;
	private static Matcher matcher;

	private static final String VALID_PHONE_REGEX = "^\\d{10,}$";

	public static boolean isPhone(String phone) {
		pattern = Pattern.compile(VALID_PHONE_REGEX);
		matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	public static boolean isNumber(String number) {

		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;

		}
		return true;
	}

	public static boolean isDate(String date) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		try {
			dateTimeFormatter.parse(date);
		} catch (DateTimeParseException e) {
			return false;
		}

		return true;

	}

	public static boolean isTime(String date) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

		try {
			dateTimeFormatter.parse(date);
		} catch (DateTimeParseException e) {
			return false;
		}

		return true;

	}

	public static void main(String[] args) {
		System.out.println(isTime("11:3e"));

	}

}
