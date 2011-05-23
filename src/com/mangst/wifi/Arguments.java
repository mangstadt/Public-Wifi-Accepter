package com.mangst.wifi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.collections.map.MultiValueMap;

/**
 * A class that is used to parse command-line arguments.
 * @author mangst
 */
public class Arguments {
	/**
	 * The command-line arguments and their values.
	 */
	private MultiValueMap args = MultiValueMap.decorate(new HashMap<String, String>());

	/**
	 * Constructs a new arguments object.
	 * @param args the command line arguments
	 */
	public Arguments(String args[]) {
		for (String arg : args) {
			//ignore arguments that doesn't start with "-" (or "--")
			if (!arg.startsWith("-")) {
				continue;
			}

			//remove dashes
			arg = (arg.startsWith("--")) ? arg.substring(2) : arg.substring(1);

			String key, value;
			int equals = arg.indexOf('=');
			if (equals >= 0) {
				key = arg.substring(0, equals);
				if (equals < arg.length() - 1) {
					value = arg.substring(equals + 1);
				} else {
					value = "";
				}
			} else {
				key = arg;
				value = null;
			}

			this.args.put(key, value);
		}
	}

	/**
	 * Determines whether the given argument was included (flag arguments).
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @return true if the flag argument exists, false if not
	 */
	public boolean exists(String shortArg, String longArg) {
		return args.containsKey(shortArg) || args.containsKey(longArg);
	}

	/**
	 * Gets the value of an argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @return the argument's value or null if it has no value (example: "bar"
	 * is returned for the argument "--foo=bar")
	 */
	public String value(String shortArg, String longArg) {
		return value(shortArg, longArg, null);
	}

	/**
	 * Gets the value of an argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @param defaultValue the value to return if the argument has no value
	 * @return the argument's value or defaultValue if it has no value (example:
	 * "bar" is returned for the argument "--foo=bar")
	 */
	public String value(String shortArg, String longArg, String defaultValue) {
		Collection<String> values = valueList(shortArg, longArg, defaultValue);
		if (values.isEmpty()){
			return null;
		}
		return values.iterator().next();
	}

	/**
	 * If an argument is defined multiple times, this will return the values of
	 * each definition.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @return each of the argument's values or null if the argument does not
	 * exist (example: ["bar", "car"] is returned for the argument
	 * "--foo=bar --foo=car")
	 */
	public Collection<String> valueList(String shortArg, String longArg) {
		return valueList(shortArg, longArg, null);
	}

	/**
	 * If an argument is defined multiple times, this will return the values of
	 * each definition.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @param defaultValue the value to return if the argument has no value
	 * @return each of the argument's values or a Collection containing
	 * defaultValue if the argument does not exist, or null if defaultValue is
	 * null (example: ["bar", "car"] is returned for the argument
	 * "--foo=bar --foo=car")
	 */
	@SuppressWarnings("unchecked")
	public Collection<String> valueList(String shortArg, String longArg, String defaultValue) {
		Collection<String> shortArgValues = (Collection<String>) args.getCollection(shortArg);
		Collection<String> longArgValues = (Collection<String>) args.getCollection(longArg);
		
		Collection<String> values = new ArrayList<String>();
		if (shortArgValues == null && longArgValues == null && defaultValue != null) {
			values.add(defaultValue);
		} else {
			if (shortArgValues != null) {
				values.addAll(shortArgValues);
			}
			if (longArgValues != null) {
				values.addAll(longArgValues);
			}
		}
		return values;
	}

	/**
	 * Gets the value of an integer argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or null if it has no value (example: 2011 is
	 * returned for the argument "--year=2011")
	 */
	public Integer valueInt(String shortArg, String longArg) {
		return valueInt(shortArg, longArg, null);
	}

	/**
	 * Gets the value of an integer argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @param defaultValue the value to return if the argument has no value
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or defaultValue if it has no value (example:
	 * 2011 is returned for the argument "--year=2011")
	 */
	public Integer valueInt(String shortArg, String longArg, Integer defaultValue) {
		String value = value(shortArg, longArg);

		if (value == null) {
			return defaultValue;
		} else if (value.isEmpty()) {
			return 0;
		}
		return Integer.valueOf(value);
	}

	/**
	 * Gets the value of a floating-point argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or null if it has no value (example: 12.5 is
	 * returned for the argument "--cash=12.50")
	 */
	public Double valueDouble(String shortArg, String longArg) {
		return valueDouble(shortArg, longArg, null);
	}

	/**
	 * Gets the value of a floating-point argument.
	 * @param shortArg the short version of the argument (example: "h" for "-h")
	 * @param longArg the long version of the argument (example: "help" for
	 * "--help")
	 * @param defaultValue the value to return if the argument has no value
	 * @throws NumberFormatException if it can't parse the value into a number
	 * @return the argument's value or defaultValue if it has no value (example:
	 * 12.5 is returned for the argument "--cash=12.50")
	 */
	public Double valueDouble(String shortArg, String longArg, Double defaultValue) {
		String value = value(shortArg, longArg);

		if (value == null) {
			return defaultValue;
		} else if (value.isEmpty()) {
			return 0.0;
		}
		return Double.valueOf(value);
	}
}
