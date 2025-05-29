package de.cuzim1tigaaa.colorlib.gradients;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

@FunctionalInterface
public interface Gradient {

	String translate(String message);

	static ChatColor interpolate(Color c1, Color c2, double t) {
		int r = (int) (c1.getRed() + (c2.getRed() - c1.getRed()) * t);
		int g = (int) (c1.getGreen() + (c2.getGreen() - c1.getGreen()) * t);
		int b = (int) (c1.getBlue() + (c2.getBlue() - c1.getBlue()) * t);
		return ChatColor.of(String.format("#%02x%02x%02x", r, g, b));
	}
}