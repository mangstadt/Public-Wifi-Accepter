package com.mangst.wifi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the HtmlForm class.
 * @author michael
 */
public class HtmlFormTest {
	/**
	 * The URL of the Starbucks login page.
	 */
	private static final String url = "http://nmd.sbx13386.philapa.wayport.net/index.adp?MacAddr=00%3a22%3a43%3a05%3aF7%3aCD&IpAddr=192%2e168%2e5%2e120&vsgpId=5095780c%2d6d49%2d11dd%2db9bf%2d0090fb1eba3a&vsgId=101559&UserAgent=&ProxyHost=";

	/**
	 * The HTML of the login page.
	 */
	private static final String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">\n <head>\n  <title>AT&T Wi-Fi Service @ Starbucks</title>\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n  <link rel=\"shortcut icon\" type=\"image/ico\" href=\"/favicon.ico\" />\n  <script type=\"text/javascript\" language=\"javascript\" src=\"/dhtml/master.js\"></script>\n  <script type=\"text/javascript\" language=\"javascript\" src=\"/dhtml/x_core.js\"></script>\n  <script type=\"text/javascript\" language=\"javascript\" src=\"/dhtml/x_event.js\"></script>\n  <script type=\"text/javascript\" language=\"javascript\" src=\"/dhtml/x_xhr.js\"></script>\n  <script type=\"text/javascript\" src=\"/dhtml/aws/dhtml.js\"></script>\n  <script type=\"text/javascript\" src=\"/dhtml/jquery/jquery-1.3.2.js\"></script>\n  <script type=\"text/javascript\" language=\"javascript\" src=\"/dhtml/jquery/aws_jclock_2.2.0.js\"></script>\n  <script type=\"text/javascript\" language=\"javascript\">\n$(function($) {\n	var options = {\n		format: '%i:%M<span class=\"clock_ampm\">%P</span>'\n	}\n	$('.jclock').jclock(options);\n});\n  </script> \n\n  <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/att/themes/sbux/laptop_free_v2.css\"/>\n  <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/att/themes/sbux/sbux_auto_addl_v2.css\"/>\n\n </head>\n <body>\n  <div id=\"free_wrap\">\n   <div id=\"topblock\">\n    <div id=\"topmost\">\n	 <div id=\"free_sbux_logo\"><a href=\"http://www.starbucks.com\"></a></div id=\"free_sbux_logo\">\n	 <div id=\"free_sbux_location\"><div id=\"location_city\">PHILADELPHIA</div id=\"id=\"location_city\"><div id=\"location_state\">PA</div id=\"location_state\"></div id=\"free_sbux_location\">\n	 <div id=\"free_sbux_clocktime\">\n	  <div id=\"clock_display\">\n	  <span class=\"jclock\"></span>\n	  </div id=\"clock_display\">\n	 </div id=\"free_sbux_clocktime\">\n	</div id=\"topmost\">\n	<div id=\"topmid\">\n	 <div id=\"free_sbux_mainform\">\n	  <div id=\"iframe_free\">\n	  <iframe id=\"sbux_iframe\" src=\"http://www.starbucks.com/coffeehouse/wi-fi-landing\" scrolling=\"no\">\n				</iframe>\n	  </div id=\"iframe_free\">\n	  <div id=\"inline_free_form\">\n	  <form method=\"post\" action=\"http://nmd.sbx13386.philapa.wayport.net/connect.adp\" onsubmit=\"return validateAWSform(this);\">\n<input type=\"hidden\" name=\"NmdId\" value=\"27684\"/>\n<input type=\"hidden\" name=\"ReturnHost\" value=\"nmd.sbx13386.philapa.wayport.net\"/>\n<input type=\"hidden\" name=\"MacAddr\" value=\"00:22:43:05:F7:CD\"/>\n<input type=\"hidden\" name=\"IpAddr\" value=\"192.168.5.120\"/>\n<input type=\"hidden\" name=\"NduMacAddr\" value=\"00:21:D8:DC:98:F6\"/>\n<input type=\"hidden\" name=\"NduPort\" value=\"4\"/>\n<input type=\"hidden\" name=\"PortType\" value=\"Wireless\"/>\n<input type=\"hidden\" name=\"PortDesc\" value=\"attwifi::AP1\"/>\n<input type=\"hidden\" name=\"UseCount\" value=\"\"/>\n<input type=\"hidden\" name=\"PaymentMethod\" value=\"Passthrough\"/>\n<input type=\"hidden\" name=\"ChargeAmount\" value=\"\"/>\n<input type=\"hidden\" name=\"Style\" value=\"ATT\"/>\n<input type=\"hidden\" name=\"vsgpId\" value=\"5095780c-6d49-11dd-b9bf-0090fb1eba3a\"/>\n<input type=\"hidden\" name=\"pVersion\" value=\"2\"/>\n<input type=\"hidden\" name=\"ValidationHash\" value=\"28ca6d9df10db5a1344885d2dd668339\"/>\n<input type=\"hidden\" name=\"origDest\" value=\"\"/>\n<input type=\"hidden\" name=\"ProxyHost\" value=\"\"/>\n<input type=\"hidden\" name=\"vsgId\" value=\"101559\"/>\n<input type=\"hidden\" name=\"ts\" value=\"1287671589\"/>\n\n       <input name=\"AUPConfirmed\" value=\"1\" type=\"hidden\">\n	   <div id=\"free_aup_line\">\n        <input id=\"aupAgree\" name=\"aupAgree\" value=\"1\" tabindex=\"4\" type=\"checkbox\"> I agree to the <a href=\"http://secure.sbc.com/tosaup.adp\">Terms of Service and Acceptable Use Policy</a>\n	   </div id=\"free_aup_line\">\n	   <div id=\"free_submit_row\">\n        <div id=\"free_need_help\">\n	     <a href=\"http://secure.sbc.com/help.adp\">Need Help?</a>\n	    </div id=\"free_need_help\">\n		<div id=\"free_submit_btn\">\n		 <input type=\"submit\" tabindex=\"5\" id=\"connect\" name=\"connect\" alt=\"Connect\" value=\"\"/>\n		</div id=\"free_submit_btn\">\n	   </div id=\"free_submit_row\">\n	  </form>\n	  </div id=\"inline_free_form\">\n	 </div id=\"free_sbux_mainform\">\n	</div id=\"topmid\">\n   </div id=\"topblock\">\n   <div id=\"botblock\">\n    <div id=\"botblock_int\">\n     <div id=\"botblock_left\">\n	  <div id=\"free_aws_logo\">&nbsp;</div id=\"free_aws_logo\">\n	 </div id=\"botblock_left\">\n	 <div id=\"botblock_mid\">\n	  <div id=\"free_powered_block\">\n	   <div id=\"free_powered_by\">AT&amp;T Wi-Fi</div id=\"free_powered_by\">\n      </div id=\"free_powered_block\">\n	  <div id=\"free_mid_hr\">&nbsp;</div>\n	  <div id=\"botblock_mid_links\">\n	   <div id=\"bbml_one\" class=\"bbml\"><a href=\"http://www.att.com\">ATT.com</a></div>\n	   <div id=\"bbml_two\" class=\"bbml\"><a href=\"http://www.attwifi.com\">AT&amp;T Wi-Fi</a></div>\n	   <div id=\"bbml_three\" class=\"bbml\"><a href=\"http://www.att.com/attwifi/locations\">AT&amp;T Wi-Fi Locations</a></div>\n	   <div id=\"bbml_four\" class=\"bbml\"><a href=\"http://secure.sbc.com/help.adp\">AT&amp;T Wi-Fi Help</a></div>\n	   <div id=\"bbml_five\" class=\"bbml\"><a href=\"http://secure.sbc.com/tosaup.adp\">AT&amp;T Wi-Fi Terms of Service and Acceptable Use Policy</a></div>\n	   <div id=\"bbml_six\" class=\"bbml\"><a href=\"http://www.att.com/gen/privacy-policy?pid=2506\">AT&amp;T Privacy</a></div>\n	  </div id=\"botblock_mid_links\">\n	  <div id=\"botblock_copyright\">\n	   Copyright &copy; 2010 Starbucks Corporation. All Rights Reserved<br/>\n	   Copyright &copy; 2010 AT&amp;T Intellectual Property. All Rights Reserved<br/>\n	  </div id=\"botblock_copyright\">\n	  <div id=\"botblock_tc_links\">\n	   <div id=\"bbtcl_one\" class=\"bbtcl\"><a href=\"http://www.starbucksentertainment.com/terms\">Terms and Conditions</a></div>\n	   <div id=\"bbcldiv_one\" class=\"bbtcl_div\">&nbsp;</div>\n	   <div id=\"bbtcl_two\" class=\"bbtcl\"><a href=\"http://www.starbucks.com/customer/privacy.asp\">Privacy</a></div>\n	  </div id=\"botblock_tc_links\">\n	 </div id=\"botblock_mid\">\n	 <div id=\"botblock_right\">\n	  <div id=\"jiwire_adblock\">\n       <script type=\"text/javascript\" language=\"JavaScript\">\ndocument.write('<script language=\"JavaScript\" type=\"text/javascript\" src=\"http://tags.jiwire.com/?prid=110477&netid=165-Starbucks&lid=prelog&embed=1&jps=1&pa=1\"></scr'+'ipt>');\n       </script>\n       <noscript>\n        <iframe src=\"http://tags.jiwire.com/?prid=110477&netid=165-Starbucks&lid=prelog&jps=1&pa=1\" scrolling=\"no\" frameborder=\"0\" width=\"300\" height=\"250\"></iframe>\n       </noscript>\n	  </div id=\"jiwire_adblock\">\n	  <div id=\"jiwire_ad_linkblock\">\n	   <div id=\"jiwire_credits\">Wi-Fi Ads by JiWire</div>\n	   <div id=\"jiwire_ad_link\">\n	    <a href=\"http://starbucks.jiwire.com\">Advertise Here</a>\n       </div id=\"jiwire_ad_link\">\n	  </div id=\"jiwire_ad_linkblock\">\n     </div id=\"botblock_right\">\n	</div id=\"botblock_int\">\n   </div id=\"botblock\">\n   <div id=\"botblock_ext\">\n\n   </div id=\"botblock_ext\">\n  </div id=\"free_wrap\">\n </body>\n</html> \n";

	/**
	 * Tests the class against the real Starbucks webpage.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWithRealHtml() throws Exception {
		HtmlForm htmlForm = new HtmlForm(new URL(url), html);

		Assert.assertEquals("http://nmd.sbx13386.philapa.wayport.net/connect.adp", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);

		Map<String, String> params = new HashMap<String, String>();
		params.put("NmdId", "27684");
		params.put("ReturnHost", "nmd.sbx13386.philapa.wayport.net");
		params.put("MacAddr", "00:22:43:05:F7:CD");
		params.put("IpAddr", "192.168.5.120");
		params.put("NduMacAddr", "00:21:D8:DC:98:F6");
		params.put("NduPort", "4");
		params.put("PortType", "Wireless");
		params.put("PortDesc", "attwifi::AP1");
		params.put("UseCount", "");
		params.put("PaymentMethod", "Passthrough");
		params.put("ChargeAmount", "");
		params.put("Style", "ATT");
		params.put("vsgpId", "5095780c-6d49-11dd-b9bf-0090fb1eba3a");
		params.put("pVersion", "2");
		params.put("ValidationHash", "28ca6d9df10db5a1344885d2dd668339");
		params.put("origDest", "");
		params.put("ProxyHost", "");
		params.put("vsgId", "101559");
		params.put("ts", "1287671589");
		params.put("AUPConfirmed", "1");
		params.put("aupAgree", "1");
		Assert.assertEquals(params, htmlForm.parameters);
	}

	/**
	 * Tests to make sure it throws the appropriate exceptions at the
	 * appropriate times.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testErrors() throws Exception {
		URL url = new URL("http://www.example.com");

		//no "form" tag anywhere
		try {
			new HtmlForm(url, "<html><body>Hello world!</body></html>");
			Assert.fail();
		} catch (InvalidFormException e) {
			//this exception should be thrown
		} catch (MalformedURLException e) {
			Assert.fail();
		}

		//no "action" attribute
		try {
			new HtmlForm(url, "<form>");
			Assert.fail();
		} catch (InvalidFormException e) {
			//this exception should be thrown
		} catch (MalformedURLException e) {
			Assert.fail();
		}
	}

	/**
	 * Tests other aspects of the class, feeding it specially-crafted input.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMisc() throws Exception {
		URL url = new URL("http://www.example.com/foo/bar/");
		HtmlForm htmlForm;

		//there's no "method" attribute, so it should default to "GET"
		htmlForm = new HtmlForm(url, "<form action=\"index.html\">");
		Assert.assertEquals("http://www.example.com/foo/bar/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("GET", htmlForm.method);
		Assert.assertEquals(0, htmlForm.parameters.size());

		//there is a "method" attribute, it should be parsed as all upper-case
		htmlForm = new HtmlForm(url, "<form action=\"index.html\" method=\"post\">");
		Assert.assertEquals("http://www.example.com/foo/bar/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(0, htmlForm.parameters.size());

		//"action" attribute is absolute
		htmlForm = new HtmlForm(url, "<form action=\"http://www.foobar.com/index.html\" method=\"post\">");
		Assert.assertEquals("http://www.foobar.com/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(0, htmlForm.parameters.size());

		//"action" attribute uses "../"
		htmlForm = new HtmlForm(url, "<form action=\"../index.html\" method=\"post\">");
		Assert.assertEquals("http://www.example.com/foo/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(0, htmlForm.parameters.size());

		//"action" attribute uses "/"
		htmlForm = new HtmlForm(url, "<form action=\"/index.html\" method=\"post\">");
		Assert.assertEquals("http://www.example.com/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(0, htmlForm.parameters.size());

		//switch order of attributes
		htmlForm = new HtmlForm(url, "<form method=\"post\" action=\"index.html\">");
		Assert.assertEquals("http://www.example.com/foo/bar/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(0, htmlForm.parameters.size());

		//there are attributes in the form tag which can be ignored, along with multiple whitespace characters between attributes
		htmlForm = new HtmlForm(url, "<form  \t method=\"post\"  \n\n doubleQuotes=\"typical usage\"\t attrWithNoValue  noQuotes=uglyHtml  singleQuotes='an alternative' action=\"index.html\" \n attrWithNoValue2>");
		Assert.assertEquals("http://www.example.com/foo/bar/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(0, htmlForm.parameters.size());

		//there some parameters
		htmlForm = new HtmlForm(url, "<form action=\"index.html\" method=\"post\"><input type=\"hidden\" name=\"foo\" value=\"bar\" /><input type=\"hidden\" name='sq' value='single quotes' /><input type=\"hidden\" name=nq value=no-quotes /><input type=\"hidden\" value=\"noname\" /><input type=\"hidden\" name=\"novalue\" /><input name=\"notype\" value=\"thereisnotypeattribute\" /><input type=\"submit\" name=\"submit\" value=\"button\" />");
		Assert.assertEquals("http://www.example.com/foo/bar/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(5, htmlForm.parameters.size());
		Assert.assertEquals("bar", htmlForm.parameters.get("foo"));
		Assert.assertEquals("single quotes", htmlForm.parameters.get("sq"));
		Assert.assertEquals("no-quotes", htmlForm.parameters.get("nq"));
		Assert.assertEquals("", htmlForm.parameters.get("novalue"));
		Assert.assertEquals("thereisnotypeattribute", htmlForm.parameters.get("notype"));
		Assert.assertNull(htmlForm.parameters.get("submit"));

		//there is more than one form...it will only look at the attributes of the first form, but will read all the parameters of all of the forms
		htmlForm = new HtmlForm(url, "<form action=\"index.html\" method=\"post\"><input type=\"hidden\" name=\"foo\" value=\"bar\" /><input type=\"hidden\" value=\"noname\" /><input type=\"hidden\" name=\"novalue\" /></form><form action=\"http://www.google.com\"><input name=\"param\" value=\"inanotherform\" />");
		Assert.assertEquals("http://www.example.com/foo/bar/index.html", htmlForm.actionUrl.toExternalForm());
		Assert.assertEquals("POST", htmlForm.method);
		Assert.assertEquals(3, htmlForm.parameters.size());
		Assert.assertEquals("bar", htmlForm.parameters.get("foo"));
		Assert.assertEquals("", htmlForm.parameters.get("novalue"));
		Assert.assertEquals("inanotherform", htmlForm.parameters.get("param"));
	}
}
