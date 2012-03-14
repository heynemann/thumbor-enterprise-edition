package com.globo.thumbor.test;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.globo.thumbor.thumboree.CryptoURL;

public class CryptoURLTest {
	
	private CryptoURL cryptoURL;

	@Before
	public void setup() {
		this.cryptoURL = new CryptoURL("my-security-key", "my.server.com/some/path/to/image.jpg");
	}
		
//	Given
//    A security key of 'my-security-key'
//    And an image URL of "my.server.com/some/path/to/image.jpg"
//    And a width of 300
//    And a height of 200
//When
//    I ask my library for an encrypted URL
//Then
//    I get
//    '/l42l54VqaV_J-EcB5quNMP6CnsN9BX7htrh-QbPuDv0C7adUXX7LTo6DHm_woJtZ/my.server.com/some/path/to/image.jpg'
//    as url
	@Test
	public void canDecryptEncryptedURL() {
		String expectedURL = "/l42l54VqaV_J-EcB5quNMP6CnsN9BX7htrh-QbPuDv0C7adUXX7LTo6DHm_woJtZ/my.server.com/some/path/to/image.jpg";
		assertEquals(this.cryptoURL.resize(300, 200).toString(), expectedURL);
	}

}
