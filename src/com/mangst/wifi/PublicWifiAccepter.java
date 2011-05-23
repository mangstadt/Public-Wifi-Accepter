package com.mangst.wifi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Allows the user to accept the terms and conditions of a public wi-fi hotspot
 * without having to open a browser.
 * @author michael
 */
public class PublicWifiAccepter {
	/**
	 * True if status messages should be printed to stdout.
	 */
	private static boolean verbose = false;

	public static void main(String args[]) throws Exception {
		// get arguments
		if (args.length > 0) {
			if (args[0].equals("--help") || args[0].equals("-h")) {
				System.out.println("Public Wi-Fi Terms and Conditions Accepter");
				System.out.println("Many public wi-fi hotspots require that you first open a webpage to agree");
				System.out.println("to their terms and conditions. This program will accept those terms and");
				System.out.println("conditions for you so you don't have to open a browser window and accept");
				System.out.println("them yourself.");
				System.out.println();
				System.out.println("Note: This program makes certain assumptions about the wi-fi hotspot and");
				System.out.println("is not guaranteed to work in all locations.");
				System.out.println();
				System.out.println("by Michael Angstadt - github.com/mangstadt");
				System.out.println();
				System.out.println("Example:");
				System.out.println("java com.mangst.wifi.PublicWifiAccepter --verbose");
				System.out.println();
				System.out.println("Arguments:");
				System.out.println("--verbose, -v");
				System.out.println("   Verbose output.");
				System.out.println("--help, -h");
				System.out.println("   Displays this help text.");
				System.exit(0);
			}
			verbose = args[0].equals("--verbose") || args[0].equals("-v");
		}

		URL googleUrl = new URL("http://www.google.com");

		// disable the automatic following of redirects
		// a 3xx response can be used to determine whether or not the computer
		// is already connected to the Internet
		HttpURLConnection.setFollowRedirects(false);

		// try to visit a website
		print("Attempting to visit [" + googleUrl + "]...");
		HttpURLConnection conn = (HttpURLConnection) googleUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(false);
		conn.setRequestMethod("GET");
		int responseCode = conn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
			// if you haven't accepted the terms and conditions yet, 302 is returned, redirecting you to the login page

			println("FAILED with " + responseCode); // it should fail to visit the website

			// get the Location header, which contains the redirect URL
			String redirectUrlStr = conn.getHeaderField("Location");

			// go to the redirect URL, which is the Starbucks login page
			conn.disconnect();
			URL redirectUrl = new URL(redirectUrlStr);
			print("Downloading Philadelphia Airport wifi login page [" + redirectUrl + "]...");
			conn = (HttpURLConnection) redirectUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			// get the HTML of the webpage
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuilder html = new StringBuilder();
			while ((line = in.readLine()) != null) {
				html.append(line);
			}
			in.close();
			conn.disconnect();

			println("SUCCESS");

			// parse the form info out of the HTML
			print("Parsing Philadelphia Airport wifi login page...");
			HtmlForm formInfo = new HtmlForm(redirectUrl, html.toString());
			println("SUCCESS");

			// prepare to submit the form
			print("Accepting the terms and conditions...");
			conn = (HttpURLConnection) formInfo.actionUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(formInfo.method);

			// output parameters to request body
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : formInfo.parameters.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8") + '=' + URLEncoder.encode(entry.getValue(), "UTF-8") + '&');
			}
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(sb.substring(0, sb.length() - 1)); // remove the last '&'
			out.flush();

			// send request
			conn.getResponseCode();
			conn.disconnect();

			// try to connect to the Internet again to see if it worked
			conn = (HttpURLConnection) googleUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");
			responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				println("SUCCESS");
				println("The terms and conditions have been agreed to and you can now connect to the Internet!");
			} else {
				println("FAILED");
				System.err.println("Error: Approval of terms and conditions failed.");
				System.exit(1);
			}
		} else if (responseCode == HttpURLConnection.HTTP_OK) {
			println("SUCCESS");
			println("You are already connected to the Internet.");
		} else {
			println("ERROR");
			System.err.println("Unknown error: HTTP status code " + responseCode);
			System.exit(1);
		}
	}

	/**
	 * Prints a message to stdout if verbose mode is enabled.
	 * 
	 * @param message the message to print
	 */
	public static void print(String message) {
		if (verbose) {
			System.out.print(message);
		}
	}

	/**
	 * Prints a message to stdout if verbose mode is enabled. A newline is added
	 * to the end of the message.
	 * 
	 * @param message the message to print
	 */
	public static void println(String message) {
		if (verbose) {
			System.out.println(message);
		}
	}
}
