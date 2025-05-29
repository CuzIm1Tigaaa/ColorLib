package de.cuzim1tigaaa.colorlib.gradients;

import de.cuzim1tigaaa.colorlib.Patterns;
import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleColor implements Gradient {

	@Override
	public String translate(String message) {
		Pattern pattern = Patterns.singleColor;
		Matcher matcher = pattern.matcher(message);
		while(matcher.find()) {
			String color = message.substring(matcher.start(), matcher.end());
			message = message.replace(color, ChatColor.of(color) + "");
			matcher = pattern.matcher(message);
		}
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}