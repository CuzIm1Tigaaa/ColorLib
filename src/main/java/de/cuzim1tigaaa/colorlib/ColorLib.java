package de.cuzim1tigaaa.colorlib;

import de.cuzim1tigaaa.colorlib.gradients.Gradient;
import de.cuzim1tigaaa.colorlib.gradients.SingleColor;
import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.Set;

public class ColorLib {

	public static final List<Gradient> activeGradients = List.of(
			new SingleColor()
	);

	public static String format(String message) {
		for(Gradient gradient : activeGradients)
			message = gradient.translate(message);
		return ChatColor.translateAlternateColorCodes('&', message);
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