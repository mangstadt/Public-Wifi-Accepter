package com.mangst.wifi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses HTML form data out of an HTML page.
 * @author michael
 */
public class HtmlForm {

	/**
	 * All of the input parameters on the form and their values.
	 */
	private Map<String, String> parameters;

	/**
	 * The HTTP method the form uses (e.g. "POST"). Will be in upper-case.
	 */
	private String method;

	/**
	 * The value of the form's "action" attribute.
	 */
	private URL actionUrl;

	/**
	 * The regex pattern used to find a form element in HTML.
	 */
	private static final Pattern formPattern = Pattern.compile("<form(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	/**
	 * The regex pattern to find a form input parameter in HTML.
	 */
	private static final Pattern inputPattern = Pattern.compile("<input(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	/**
	 * The regex pattern used to parse out all of the attributes from a HTML
	 * tag. The source string must contain only the attributes, not the tag name
	 * or brackets. Extracts attributes that are in the following formats:
	 * <ul>
	 * <li>foo="bar"</li>
	 * <li>foo='bar'</li>
	 * <li>foo=bar</li>
	 * <li>foo (no value)</li>
	 * </ul>
	 */
	private static final Pattern attributePattern = Pattern.compile("([\\w:\\-]+)(=(\"(.*?)\"|'(.*?)'|([^ ]*))|(\\s+|\\z))");

	/**
	 * Parses information about a form out of an HTML page.
	 * 
	 * @param url the URL of the HTML page
	 * @param html the HTML page
	 * @throws MalformedURLException if the "action" attribute of the tag isn't
	 * a valid URL (can be absolute or relative)
	 * @throws InvalidFormException if there is a problem parsing or finding the
	 * form
	 */
	public HtmlForm(URL url, String html) throws MalformedURLException, InvalidFormException {
		// get the action URL and method of the form
		Matcher matcher = formPattern.matcher(html);
		if (matcher.find()) {
			Map<String, String> attributes = parseAttributes(matcher.group(1));

			// get action URL
			String action = attributes.get("action");
			if (action != null) {
				actionUrl = new URL(url, action);
			} else {
				throw new InvalidFormException("No \"action\" attribute found in the form.");
			}

			// get method
			method = attributes.get("method");
			if (method == null) {
				method = "GET";
			} else {
				method = method.toUpperCase(); // it must be in upper case in
												// order for HttpURLConnection
												// to recognize it
			}
		} else {
			throw new InvalidFormException("No form found in the HTML.");
		}

		// pull out all parameters in the form
		parameters = new HashMap<String, String>();
		matcher = inputPattern.matcher(html);
		while (matcher.find()) {
			Map<String, String> attributes = parseAttributes(matcher.group(1));

			// ignore buttons
			String type = attributes.get("type");
			if (type != null && (type.equalsIgnoreCase("submit") || type.equalsIgnoreCase("button") || type.equalsIgnoreCase("image"))) {
				continue;
			}

			String name = attributes.get("name");
			if (name != null) {
				String value = attributes.get("value");
				if (value == null) {
					value = "";
				}
				parameters.put(name, value);
			}
		}
	}

	/**
	 * Given a String that contains only the attributes of a tag, parse out all
	 * the attributes and their values into a Map.
	 * 
	 * @param attributesStr all of the tag's attributes. Should not contain the
	 * tag name or the brackets. For example:
	 * "doubleQuotes=\"typical usage\" attrWithNoValue noQuotes=uglyHtml singleQuotes='an alternative'"
	 * @return the attributes and their values
	 */
	private static Map<String, String> parseAttributes(String attributesStr) {
		Map<String, String> attributes = new HashMap<String, String>();

		Matcher matcher = attributePattern.matcher(attributesStr);
		while (matcher.find()) {
			String key = matcher.group(1);
			String value = null;
			if (matcher.group(2).trim().length() == 0) {
				// it's an attribute with no value
				value = "";
			} else {
				for (int i = 4; i <= 6; i++) {
					String g = matcher.group(i);
					if (g != null) {
						value = g;
						break;
					}
				}
			}
			attributes.put(key, value.trim());
		}

		return attributes;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getMethod() {
		return method;
	}

	public URL getActionUrl() {
		return actionUrl;
	}
}
