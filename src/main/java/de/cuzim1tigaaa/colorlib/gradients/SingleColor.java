package de.cuzim1tigaaa.colorlib.gradients;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleColor implements Gradient {

	private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

	@Override
	public String translate(String message) {
		Matcher matcher = pattern.matcher(message);
		while(matcher.find()) {
			String color = message.substring(matcher.start(), matcher.end());
			message = message.replace(color, ChatColor.of(color) + "");
			matcher = pattern.matcher(message);
		}
		return message;
	}

}
