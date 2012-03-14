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

	/**
	 * Scenario 1
	 */
	@Test
	public void canDecryptEncryptedURL() {
		String expectedURL = "/l42l54VqaV_J-EcB5quNMP6CnsN9BX7htrh-QbPuDv0C7adUXX7LTo6DHm_woJtZ/my.server.com/some/path/to/image.jpg";
		String actualURL = this.cryptoURL.resize(300, 200).toString();

		assertEquals(actualURL, expectedURL);
	}

	/**
	 * Scenario 3
	 */
	@Test
	public void canEncriptWithMeta() {
		String expectedUrl = "/Jj2Xp-__GWUzZ5zemvPGW2B3j5atA7X1ntF0irz-YGXUcE3-QpqkDbDnVUmBhHi-/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.withMetaData().toString());
	}
	
	/**
	 * Scenario 4
	 */
	@Test
	public void canEncriptWithSmart() {
		String expectedUrl = "/YV6ASUwnbI8XwBw6LpMdv1wy7xC-EHp44LIQqyPYPIqa-dX7JCv4LSeObHxPyY17/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.withSmartCropping().toString());
	}
	
	/**
	 * Scenario 5
	 */
	@Test
	public void canEncriptWithFitIn() {
		String expectedUrl = "/nZlz3CEKZFMVFcNo7KKFzFWKWb7W2fFEqo_LQ2omj13fQPzSSENNk7Iz8Pc4sFen/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.fitIn(0,0).toString());
	}
	
	/**
	 * Scenario 6
	 */
	@Test
	public void canEncriptWitnFlipHorizontally() {
		String expectedUrl = "/lMySk3L-Z2oa-RXQs4MgWWB3j5atA7X1ntF0irz-YGXUcE3-QpqkDbDnVUmBhHi-/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.flipHorizontally().toString());
	}
	
	/**
	 * Scenario 7
	 */
	@Test
	public void canEncriptWitnFlipVertically() {
		String expectedUrl = "/Yq1tjo95ZWIKrntANgW-UGB3j5atA7X1ntF0irz-YGXUcE3-QpqkDbDnVUmBhHi-/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.flipVertically().toString());
	}

	/**
	 * Scenario 8
	 */
	@Test
	public void canEncriptWitnHorizontalAlign() {
		String expectedUrl = "/R7JICjkMQjLpWu7yS49k81wy7xC-EHp44LIQqyPYPIqa-dX7JCv4LSeObHxPyY17/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.align(CryptoURL.VAling.MIDDLE).toString());
	}
}
