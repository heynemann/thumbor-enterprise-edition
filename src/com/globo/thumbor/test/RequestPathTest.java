package com.globo.thumbor.test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.globo.thumbor.exceptions.NoImageURLSpecifiedException;
import com.globo.thumbor.thumboree.CryptoURL;

public class RequestPathTest {

	private CryptoURL cryptoURL;

	@Before
	public void setUp() throws Exception {
		this.cryptoURL = new CryptoURL("my-security-keym", "my.server.com/some/path/to/image.jpg");
	}

//	Given
//	    An image URL of "my.server.com/some/path/to/image.jpg"
//	When
//	    I ask my library for an URL
//	Then
//	    I get "84996242f65a4d864aceb125e1c4c5ba" as URL
	@Test
	public void testNoOptionsSpecifiedURL() throws NoImageURLSpecifiedException {
		String expected = "84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.requestPath();
		
		assertEquals(expected, actual);
	}
	
//	Given
//	    An image URL of null
//	When
//	    I ask my library for an URL
//	Then
//	    I get an exception that says image URL is mandatory
	@Test(expected= NoImageURLSpecifiedException.class)
	public void testShouldRaiseIfNullImageURLSpecified() throws NoImageURLSpecifiedException {
		cryptoURL = new CryptoURL("my-security-keym", null);
		this.cryptoURL.requestPath();
	}

//	Given
//    An image URL of null
//	When
//    I ask my library for an URL
//	Then
//    I get an exception that says image URL is mandatory
	@Test(expected= NoImageURLSpecifiedException.class)
	public void testShouldRaiseIfEmptyImageURLSpecified() throws NoImageURLSpecifiedException {
		cryptoURL = new CryptoURL("my-security-keym", "");
		this.cryptoURL.requestPath();
	}
	
}
