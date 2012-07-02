package com.globo.thumbor.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.globo.thumbor.thumboree.CryptoURL;

public class CryptoURLTest {
	
	private CryptoURL cryptoURL;

	@Before
	public void setup() {
		this.cryptoURL = new CryptoURL("my-security-key", "my.server.com/some/path/to/image.jpg");
	}

	/**
	 * Scenario 1
	 */
	@Test
	public void canSignURLProperly() {
		String expectedURL = "/8ammJH8D-7tXy6kU3lTvoXlhu4o=/300x200/my.server.com/some/path/to/image.jpg";
		String actualURL = this.cryptoURL.resize(300, 200).toString();

		assertEquals(expectedURL, actualURL);
	}

	/**
	 * Scenario 3
	 */
	@Test
	public void canEncryptWithMeta() {
		String expectedUrl = "/Ps3ORJDqxlSQ8y00T29GdNAh2CY=/meta/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.metaDataOnly().toString());
	}
	
	/**
	 * Scenario 4
	 */
	@Test
	public void canEncryptWithSmart() {
		String expectedUrl = "/-2NHpejRK2CyPAm61FigfQgJBxw=/smart/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.withSmartCropping().toString());
	}
	
	/**
	 * Scenario 5
	 */
	@Test
	public void canEncryptWithFitIn() {
		String expectedUrl = "/uvLnA6TJlF-Cc-L8z9pEtfasO3s=/fit-in/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.fitIn(0,0).toString());
	}
	
	/**
	 * Scenario 6
	 */
	@Test
	public void canEncryptWitnFlipHorizontally() {
		String expectedUrl = "/64KlsO5GWIrhBk9QhDgB6qY-MtI=/-0x0/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.flipHorizontally().toString());
	}
	
	/**
	 * Scenario 7
	 */
	@Test
	public void canEncryptWitnFlipVertically() {
		String expectedUrl = "/2f5XeROT_gHkxe8lorIy2LkPgAU=/0x-0/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.flipVertically().toString());
	}

	/**
	 * Scenario 8
	 */
	@Test
	public void canEncryptWitnHorizontalAlign() {
		String expectedUrl = "/AnH1ULgyGDrl7dkOlgfBU6bN7ok=/right/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.align(CryptoURL.HAlign.RIGHT).toString());
	}
	
	/**
	 * Scenario 9
	 */
	@Test
	public void canEncryptWitnVerticalAlign() {
		String expectedUrl = "/UX2IxhbVTqWAJ4c4gZTVtChEN9A=/top/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.align(CryptoURL.VAlign.TOP).toString());
	}
	
	/**
	 * Scenario 10
	 */
	@Test
	public void canEncryptWithCrop() {
		String expectedUrl = "/yyZJeZk4B3GR3ii5p5NvxDikF1o=/10x20:30x40/my.server.com/some/path/to/image.jpg";
		assertEquals(expectedUrl, this.cryptoURL.crop(10, 20, 30, 40).toString());
	}
}
