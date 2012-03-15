package com.globo.thumbor.test;

import static org.junit.Assert.assertEquals;

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

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testNoOptionsSpecifiedURL() throws NoImageURLSpecifiedException {
		String expected = "84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.requestPath();
		
		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*	    An image URL of null
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get an exception that says image URL is mandatory
	*/
	@Test(expected= NoImageURLSpecifiedException.class)
	public void testShouldRaiseIfNullImageURLSpecified() throws NoImageURLSpecifiedException {
		cryptoURL = new CryptoURL("my-security-keym", null);
		this.cryptoURL.requestPath();
	}

	/**
	*	Given
	*    	An image URL of null
	*	When
	*    	I ask my library for an URL
	*	Then
	*    	I get an exception that says image URL is mandatory
	*/
	@Test(expected= NoImageURLSpecifiedException.class)
	public void testShouldRaiseIfEmptyImageURLSpecified() throws NoImageURLSpecifiedException {
		cryptoURL = new CryptoURL("my-security-keym", "");
		this.cryptoURL.requestPath();
	}
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a width of 300
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "300x0/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithWidthAndNoHeight() throws NoImageURLSpecifiedException {
		String expected = "300x0/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.resize(300, 0).requestPath();
		
		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a height of 300
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "0x300/84996242f65a4d864aceb125e1c4c5ba" as URL	
	*/
	@Test
	public void testURLWithHeightAndNoWidth() throws NoImageURLSpecifiedException {
		String expected = "0x300/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.resize(0, 300).requestPath();
		
		assertEquals(expected, actual);
	}	

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a width of 200
	*	    And a height of 300
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "200x300/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithHeightAndWidth() throws NoImageURLSpecifiedException {
		String expected = "200x300/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.resize(200, 300).requestPath();
		
		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a width of 200
	*	    And a height of 300
	*	    And the smart flag
	*	When
	*	    I ask my library for an URL
	*	Then
    *		I get "200x300/smart/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithHeightAndWidthAndSmart() throws NoImageURLSpecifiedException {
		String expected = "200x300/smart/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.resize(200, 300).withSmartCropping().requestPath();
		
		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a width of 200
	*	    And a height of 300
	*	    And the fit-in flag
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "fit-in/200x300/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithFitIn() throws NoImageURLSpecifiedException {
		String expected = "fit-in/200x300/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.fitIn(200, 300).requestPath();
		
		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And the flip flag
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "-0x0/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithHorizontalFlip() throws NoImageURLSpecifiedException {
		String expected = "-0x0/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.flipHorizontally().requestPath();
		
		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And the flop flag
	*	When
	*    	I ask my library for an URL
	*	Then
	*    	I get "0x-0/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithVerticalFlip() throws NoImageURLSpecifiedException {
		String expected = "0x-0/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.flipVertically().requestPath();
		
		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And the flip flag
	*	    And the flop flag
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "-0x-0/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithBothFlips() throws NoImageURLSpecifiedException {
		String expected = "-0x-0/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.flipHorizontally().flipVertically().requestPath();
		
		assertEquals(expected, actual);
	} 
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a width of 200
	*	    And the flip flag
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "-200x0/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithFlipHorizontallyWithWidth() throws NoImageURLSpecifiedException {
		String expected = "-200x0/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.flipHorizontally().resize(200, 0).requestPath();
		
		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a height of 200
	*	    And the flop flag
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "0x-200/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithFlipVerticallyWithHeight() throws NoImageURLSpecifiedException {
		String expected = "0x-200/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.flipVertically().resize(0, 200).requestPath();

		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a 'left' horizontal alignment option
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "left/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithHorizontalAlignment() throws NoImageURLSpecifiedException {
		String expected = "left/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.align(CryptoURL.HAlign.LEFT).requestPath();

		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a 'center' horizontal alignment option
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "center/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithCentralAlignment() throws NoImageURLSpecifiedException {
		String expected = "center/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.align(CryptoURL.HAlign.CENTER).requestPath();

		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a 'top' vertical alignment option
	*	When
	*    	I ask my library for an URL
	*	Then
	*    	I get "top/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithTopAlignment() throws NoImageURLSpecifiedException {
		String expected = "top/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.align(CryptoURL.VAlign.TOP).requestPath();

		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a 'middle' vertical alignment option
	*	When
	*    	I ask my library for an URL
	*	Then
	*    	I get "middle/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithMiddleAlignment() throws NoImageURLSpecifiedException {
		String expected = "middle/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.align(CryptoURL.VAlign.MIDDLE).requestPath();

		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a 'left' horizontal alignment option
	*	    And a 'top' vertical alignment option
	*	When
	*    	I ask my library for an URL
	*	Then
	*    	I get "left/top/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithBothAlignments() throws NoImageURLSpecifiedException {
		String expected = "left/top/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.align(CryptoURL.HAlign.LEFT, CryptoURL.VAlign.TOP).requestPath();

		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	  	An image URL of "my.server.com/some/path/to/image.jpg"
	*	  	And a 'meta' flag
	*	When
	*  		I ask my library for an URL
	*	Then
	*  		I get "meta/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithMetadataOnly() throws NoImageURLSpecifiedException {
		String expected = "meta/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.metaDataOnly().requestPath();

		assertEquals(expected, actual);
	}
	
	/**
	*	Given
	*    	An image URL of "my.server.com/some/path/to/image.jpg"
	*    	And a manual crop left-top point of (10, 20)
	*    	And a manual crop right-bottom point of (30, 40)
	*	When
	*    	I ask my library for an URL
	*	Then
    *		I get "10x20:30x40/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithManualCropOnly() throws NoImageURLSpecifiedException {
		String expected = "10x20:30x40/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.crop(10, 20, 30, 40).requestPath();

		assertEquals(expected, actual);
	}	

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a manual crop left-top point of (0, 0)
	*	    And a manual crop right-bottom point of (0, 0)
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLWithNullCrop() throws NoImageURLSpecifiedException {
		String expected = "84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.crop(0, 0, 0, 0).requestPath();

		assertEquals(expected, actual);
	}

	/**
	*	Given
	*	    An image URL of "my.server.com/some/path/to/image.jpg"
	*	    And a 'smart' flag
	*	    And a 'left' horizontal alignment option
	*	When
	*	    I ask my library for an URL
	*	Then
	*	    I get "left/smart/84996242f65a4d864aceb125e1c4c5ba" as URL
	*/
	@Test
	public void testURLLeftAlignAndSmart() throws NoImageURLSpecifiedException {
		String expected = "left/smart/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.align(CryptoURL.HAlign.LEFT).withSmartCropping().requestPath();

		assertEquals(expected, actual);
	}	

//	Given
//	    An image URL of "my.server.com/some/path/to/image.jpg"
//	    And a 'quality(20)' filter
//	    And a 'brightness(10)' filter
//	When
//	    I ask my library for an URL
//	Then
//	    I get "filters:quality(20):brightness(10)/84996242f65a4d864aceb125e1c4c5ba" as URL
	@Test
	public void testFiltersURL() throws NoImageURLSpecifiedException {
		String expected = "filters:quality(20):brightness(10)/84996242f65a4d864aceb125e1c4c5ba";
		String actual = this.cryptoURL.withFilter("quality(20)").withFilter("brightness(10)").requestPath();

		assertEquals(expected, actual);
	}
	
}
