package de.cuzim1tigaaa.colorlib.gradients;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.regex.Matcher;

public class LinearGradient implements Gradient {

	@Override
	public String translate(String message) {
		Matcher matcher = Patterns.linearGradient.matcher(message);
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
		return ChatColor.translateAlternateColorCodes('&', finalMessage.toString());
	}

	private String interpolateSection(String from, String txt, String to) {
		Color c1 = Color.decode(from), c2 = Color.decode(to);
		int r1 = c1.getRed(), g1 = c1.getGreen(), b1 = c1.getBlue();
		int r2 = c2.getRed(), g2 = c2.getGreen(), b2 = c2.getBlue();
		int length = txt.replace(" ", "").length();

		int rDelta = (r2 - r1);
		int gDelta = (g2 - g1);
		int bDelta = (b2 - b1);

		StringBuilder builder = new StringBuilder();
		int tLength = 0;
		for(int i = 0; i < txt.length(); i++) {
			if(txt.charAt(i) == ' ') {
				builder.append(' ');
				continue;
			}

			double t = (length > 1) ? (double) tLength++ / (length - 1) : 0;

			int r = (int) (r1 + (rDelta * t));
			int g = (int) (g1 + (gDelta * t));
			int b = (int) (b1 + (bDelta * t));

			builder.append(ChatColor.of(String.format("#%02x%02x%02x", r, g, b))
					.toString()).append(txt.charAt(i));
		}
		return builder.toString();
	}
}
