package com.globo.thumbor.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.globo.thumbor.thumboree.CryptoURL;

public class RequestPathTest {

	private CryptoURL cryptoURL;

	@Before
	public void setUp() throws Exception {
		this.cryptoURL = new CryptoURL("my-security-keym", "my.server.com/some/path/to/image.jpg");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
