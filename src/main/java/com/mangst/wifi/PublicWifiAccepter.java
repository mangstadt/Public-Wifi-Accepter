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
		Arguments arguments = new Arguments(args);

		//display help message
		if (arguments.exists("h", "help")) {
			System.out.println("Public Wi-Fi Terms and Conditions Accepter");
			System.out.println();
			System.out.println("Many public wi-fi hotspots require that you first open a webpage to agree");
			System.out.println("to their terms and conditions. This program will accept those terms and");
			System.out.println("conditions for you so you don't have to open a browser window and accept");
			System.out.println("them yourself.");
			System.out.println();
			System.out.println("Note: This program makes certain assumptions about the wi-fi hotspot and");
			System.out.println("is not guaranteed to work in all locations.");
			System.out.println();
			System.out.println("Also note: By running this program, you are still responsible for adhering to");
			System.out.println("the network owner's terms and conditions. This program does not in any way");
			System.out.println("absolve you from having to follow the terms and conditions.");
			System.out.println();
			System.out.println("by Michael Angstadt - github.com/mangstadt");
			System.out.println();
			System.out.println("Example:");
			System.out.println("java -jar wifi.jar --verbose");
			System.out.println();
			System.out.println("Arguments:");
			System.out.println("--verbose, -v");
			System.out.println("   Displays progress messages as the program runs.");
			System.out.println("--testUrl, -u");
			System.out.println("   The URL to use when testing for Internet connectivity.");
			System.out.println("   Defaults to http://www.cnn.com");
			System.out.println("--help, -h");
			System.out.println("   Displays this help text.");
			System.exit(0);
		}

		//is verbose mode enabled?
		verbose = arguments.exists("v", "verbose");

		String testUrlStr = arguments.value("u", "testUrl", "http://www.cnn.com");
		URL testUrl = new URL(testUrlStr);

		//disable the automatic following of redirects
		//a 3xx response can be used to determine whether or not the computer is already connected to the Internet
		HttpURLConnection.setFollowRedirects(false);

		//try to visit a website
		print("Attempting to visit [" + testUrl + "]...");
		HttpURLConnection conn = (HttpURLConnection) testUrl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(false);
		conn.setRequestMethod("GET");
		int responseCode = conn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
			//if you haven't accepted the terms and conditions yet, 302 is returned, redirecting you to the login page

			println("FAILED with " + responseCode); //it should fail to visit the website

			//get the Location header, which contains the redirect URL
			String redirectUrlStr = conn.getHeaderField("Location");

			//go to the redirect URL, which is the Starbucks login page
			conn.disconnect();
			URL redirectUrl = new URL(redirectUrlStr);
			print("Downloading wi-fi login page [" + redirectUrl + "]...");
			conn = (HttpURLConnection) redirectUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");

			//get the HTML of the webpage
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuilder html = new StringBuilder();
			while ((line = in.readLine()) != null) {
				html.append(line);
			}
			in.close();
			conn.disconnect();

			println("SUCCESS");

			//parse the form info out of the HTML
			print("Parsing wi-fi login page...");
			HtmlForm formInfo = new HtmlForm(redirectUrl, html.toString());
			println("SUCCESS");

			//prepare to submit the form
			print("Accepting the terms and conditions...");
			conn = (HttpURLConnection) formInfo.getActionUrl().openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(formInfo.getMethod());

			//output parameters to request body
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : formInfo.getParameters().entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8") + '=' + URLEncoder.encode(entry.getValue(), "UTF-8") + '&');
			}
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(sb.substring(0, sb.length() - 1)); //remove the last '&'
			out.flush();

			//send request
			conn.getResponseCode();
			conn.disconnect();

			//try to connect to the Internet again to see if it worked
			conn = (HttpURLConnection) testUrl.openConnection();
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
	 * @param message the message to print
	 */
	private static void print(String message) {
		if (verbose) {
			System.out.print(message);
		}
	}

	/**
	 * Prints a message to stdout if verbose mode is enabled. A newline is added
	 * to the end of the message.
	 * @param message the message to print
	 */
	private static void println(String message) {
		if (verbose) {
			System.out.println(message);
		}
	}
}
