package de.cuzim1tigaaa.colorlib.gradients;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinearGradient implements Gradient {

	private final Pattern pattern
			= Pattern.compile("<(#([a-fA-F0-9]){6})>");

	@Override
	public String translate(String message) {
		Matcher matcher = pattern.matcher(message);
		int start = -1, end;
		String lastGroup = null;
		StringBuilder finalMessage = new StringBuilder();

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
		return finalMessage.toString();
	}

	private String interpolateSection(String from, String txt, String to) {
		Color c1 = Color.decode(from), c2 = Color.decode(to);
		int r1 = c1.getRed(), g1 = c1.getGreen(), b1 = c1.getBlue();
		int r2 = c2.getRed(), g2 = c2.getGreen(), b2 = c2.getBlue();
		int length = txt.replace(" ", "").length();

		int rDelta = (r2 - r1) / length;
		int gDelta = (g2 - g1) / length;
		int bDelta = (b2 - g1) / length;

		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < txt.length(); i++) {
			if(txt.charAt(i) == ' ') {
				builder.append(' ');
				continue;
			}

			int r = r1 + rDelta * i;
			int g = g1 + gDelta * i;
			int b = b1 + bDelta * i;
			builder.append(ChatColor.of(String.format("#%02x%02x%02x", r, g, b)).toString())
					.append(txt.charAt(i));
		}
		return builder.toString();
	}
}
