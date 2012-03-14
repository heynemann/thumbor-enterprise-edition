package com.globo.thumbor.thumboree;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

public class CryptoURL {
	
	private String key;
	private int width = 0;
	private int height = 0;
	private String imageURL;
	
	public CryptoURL(String key, String imageURL) {
		this.key = key + "=";
		this.imageURL = imageURL;
	}

	public String generate() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
		String url = this.requestPath();
		byte[] urlBytes = url.getBytes();
		
		SecretKeySpec key = new SecretKeySpec(this.key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] cipherText = new byte[cipher.getOutputSize(urlBytes.length)];
		int ctLength = cipher.update(urlBytes, 0, urlBytes.length, cipherText, 0);
		ctLength += cipher.doFinal(cipherText, ctLength);
		
		System.out.println(new String(cipherText));
		System.out.println(ctLength);
		
		return new String(cipherText);
	}
	
	public String requestPath(){
		List<String> parts = new ArrayList<String>();
		
		if (this.width != 0 || this.height != 0) {
			parts.add(this.width + "x" + this.height);
		}
		
		String url = "/";
		for (String part : parts) {
			url += part + "/";
		}
		return url + this.imageURL;
	}

	public CryptoURL resize(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}
	
	public String toString() {
		try {
			return this.generate();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ShortBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "WTF?";
	}
}
