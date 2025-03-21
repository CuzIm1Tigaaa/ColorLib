package de.cuzim1tigaaa.colorlib;

import de.cuzim1tigaaa.colorlib.gradients.Gradient;
import de.cuzim1tigaaa.colorlib.gradients.SingleColor;
import net.md_5.bungee.api.ChatColor;

import java.util.Set;

public class ColorLib {

	public static String format(String message) {
		Gradient singleColor = new SingleColor();
		return ChatColor.translateAlternateColorCodes('&', singleColor.translate(message));
	}

	public static String format(String message, Gradient gradient) {
		return gradient.translate(format(message));
	}

	public static String format(String message, Object... args) {
		return String.format(format(message), args);
	}

	public static String format(String message, Set<Gradient> gradient, Object... args) {
		for(Gradient g : gradient)
			message = g.translate(message);
		return format(message, args);
	}

	public static String format(String message, Gradient gradient, Object... args) {
		return format(message, Set.of(gradient), args);
	}
}
