package de.cuzim1tigaaa.colorlib;

import java.util.regex.Pattern;

public class Patterns {

	public static final Pattern linearGradient = Pattern.compile("<(#[a-fA-F0-9]{6})>");
	public static final Pattern singleColor = Pattern.compile("(?<!<)(#[a-fA-F0-9]{6})|(#[a-fA-F0-9]{6})(?!>)");

}