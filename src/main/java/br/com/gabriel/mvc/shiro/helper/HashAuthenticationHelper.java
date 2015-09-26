package br.com.gabriel.mvc.shiro.helper;

import org.apache.shiro.codec.Base64;

public class HashAuthenticationHelper {

	public static String[] decode(final String encodedString) {
		final byte [] decodedBytes = Base64.decode(encodedString.replace("Basic ", "") .getBytes()); 
		final String pair = new String(decodedBytes);
		final String[] userDetails = pair.split(":", 2);
		return userDetails;
	}
	
	public static String encode(final String usuario, final String senha) {
		String encodeString = "Basic " + Base64.encodeToString((usuario+":"+senha).getBytes());
		return encodeString;
	}
}