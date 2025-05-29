package de.cuzim1tigaaa.colorlib.gradients;

import de.cuzim1tigaaa.colorlib.Patterns;
import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.regex.Matcher;

public class RadialGradient implements Gradient {

	@Override
	public String translate(String message) {
		Matcher matcher = Patterns.linearGradient.matcher(message);
		StringBuilder finalMessage = new StringBuilder();
		int start = -1, end;
		String lastGroup = null;

		while(matcher.find()) {
			end = matcher.start();
			if(start > 0 && lastGroup != null) {
				String txt = message.substring(start, end);
				if(!txt.isEmpty())
					finalMessage.append(interpolateSection(lastGroup, txt, matcher.group(1)));
			}
			lastGroup = matcher.group(1);
			start = matcher.end();
		}
		return ChatColor.translateAlternateColorCodes('&', finalMessage.toString());
	}

	private String interpolateSection(String from, String txt, String to) {
		Color c1 = Color.decode(from);
		Color c2 = Color.decode(to);
		int length = txt.replace(" ", "").length();
		double center = Math.floor(length / 2.0);

		StringBuilder builder = new StringBuilder();
		char[] chars = txt.toCharArray();

		for(int i = 0; i < length; i++) {
			double distance = Math.abs(i - center) / center;
			builder.append(Gradient.interpolate(c1, c2, distance)).append(chars[i]);
		}
		return builder.toString();
	}
}