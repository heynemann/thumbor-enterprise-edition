package com.globo.thumbor.thumboree;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.globo.thumbor.exceptions.NoImageURLSpecifiedException;

public class CryptoURL {
	
	public enum HAlign {
		RIGHT("right"),
		CENTER("center"),
		LEFT("left");
		
		private String type;
		
		HAlign(String type) {
			this.type = type;
		}
		
		public String toString() {
			return this.type;
		}
	}
	
	public enum VAlign {
		TOP("top"),
		MIDDLE("middle"),
		BOTTOM("bottom");
		
		private String type;
		
		VAlign(String type) {
			this.type = type;
		}
		
		public String toString() {
			return this.type;
		}
	}
	
	private String key;
	private int width = 0;
	private int height = 0;
	private String imageURL;
	private boolean meta = false;
	private boolean smart = false;
	private boolean fitIn = false;
	private boolean flipHorizontally = false;
	private boolean flipVertically = false;
	private HAlign halign = null;
	private VAlign valign = null;
	
	public CryptoURL(String key, String imageURL) {
		this.key = key;
		this.imageURL = imageURL;
	}

	public String generate() throws NoSuchAlgorithmException, 
									NoSuchPaddingException, 
									InvalidKeyException, 
									IllegalBlockSizeException, 
									ShortBufferException, 
									BadPaddingException,
									NoImageURLSpecifiedException {
		String url = this.requestPath();
		
		url = CryptoURL.rightPad(url, '{');
		
		byte[] urlBytes = url.getBytes();
		
		SecretKeySpec key = new SecretKeySpec(this.key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] cipherText = new byte[cipher.getOutputSize(urlBytes.length)];
		int ctLength = cipher.update(urlBytes, 0, urlBytes.length, cipherText, 0);
		ctLength += cipher.doFinal(cipherText, ctLength);
		
		String encryptedUrl = Base64.encodeBase64URLSafeString(cipherText);

		return "/" + encryptedUrl + "/" + this.imageURL;
	}
	
	public String requestPath() throws NoImageURLSpecifiedException {
		if (this.imageURL == null || this.imageURL == "") {
			throw new NoImageURLSpecifiedException("The image url can't be null or empty.");
		}

		List<String> parts = new ArrayList<String>();
		
		if (this.meta) {
			parts.add("meta");
		}
		
		if (this.fitIn) {
			parts.add("fit-in");
		}

		if (this.width != 0 || this.height != 0 || this.flipHorizontally || this.flipVertically) {
			String sizeString = "";
			
			if (this.flipHorizontally) {
				sizeString += "-";
			}
			sizeString += this.width;
			
			sizeString += 'x';
			
			if (this.flipVertically) {
				sizeString += "-";
			}
			sizeString += this.height;
			
			parts.add(sizeString);
		}
		
		if (this.halign != null) {
			parts.add(this.halign.toString());
		}
		
		if (this.valign != null) {
			parts.add(this.valign.toString());
		}
		
		if (this.smart) {
			parts.add("smart");
		}
		
		String url = "";
		for (String part : parts) {
			url += part + "/";
		}
		
		String imageHash = CryptoURL.md5(this.imageURL);
		url += imageHash;
		return url;
	}
	
	public static String rightPad(String url, char padChar) {
		int numberOfChars = 16 - url.length() % 16;

		if (numberOfChars == 0) {
			return url;
		}
		
		for (int i=0; i < numberOfChars; i++) {
			url += padChar;
		}
		
		return url;
	}
	
	public static String md5(String imageUrl) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(imageUrl.getBytes()));
		return hash.toString(16);
	}

	public CryptoURL resize(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}
	
	public CryptoURL withMetaDataOnly() {
		this.meta = true;
		return this;
	}
	
	public CryptoURL withSmartCropping() {
		this.smart = true;
		return this;
	}
	
	public CryptoURL fitIn(int width, int height) {
		this.width = width;
		this.height = height;
		this.fitIn = true;
		return this;
	}
	
	public CryptoURL align(HAlign halign, VAlign valign) {
		this.halign = halign;
		this.valign = valign;
		return this;
	}
	
	public CryptoURL align(VAlign valign) {
		this.valign = valign;
		return this;
	}
	
	public CryptoURL align(HAlign halign) {
		this.halign = halign;
		return this;
	}
	
	public String toString() {
		try {
			return this.generate();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CryptoURL flipHorizontally() {
		this.flipHorizontally  = true;
		return this;
	}
	
	public CryptoURL flipVertically() {
		this.flipVertically  = true;
		return this;
	}
}
