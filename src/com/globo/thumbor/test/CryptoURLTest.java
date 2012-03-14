package com.globo.thumbor.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.globo.thumbor.thumboree.CryptoURL;

public class CryptoURLTest {
	
	private CryptoURL cryptoURL;

	@Before
	public void setup() {
		this.cryptoURL = new CryptoURL("my-security-keym", "my.server.com/some/path/to/image.jpg");
	}
		
//	Given
//    A security key of 'my-security-key'
//    And an image URL of "my.server.com/some/path/to/image.jpg"
//    And a width of 300
//    And a height of 200
//	When
//    I ask my library for an encrypted URL
//	Then
//    I get
//    '/l42l54VqaV_J-EcB5quNMP6CnsN9BX7htrh-QbPuDv0C7adUXX7LTo6DHm_woJtZ/my.server.com/some/path/to/image.jpg'
//    as url
	@Test
	public void canDecryptEncryptedURL() {
		String expectedURL = "/l42l54VqaV_J-EcB5quNMP6CnsN9BX7htrh-QbPuDv0C7adUXX7LTo6DHm_woJtZ/my.server.com/some/path/to/image.jpg";
		String actualURL = this.cryptoURL.resize(300, 200).toString();

		assertEquals(actualURL, expectedURL);
	}
	
	public void canEncriptWithMeta() {
		String expectedUrl = "/Jj2Xp-__GWUzZ5zemvPGW2B3j5atA7X1ntF0irz-YGXUcE3-QpqkDbDnVUmBhHi-/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.withMetaData().toString());
	}

}
