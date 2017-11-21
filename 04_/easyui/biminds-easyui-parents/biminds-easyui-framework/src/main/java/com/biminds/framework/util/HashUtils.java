package com.biminds.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class HashUtils {

	/**
	 * Create random salt
	 * 
	 * @return random salt
	 */
	public static String createRandomSalt() {
		byte[] saltBytes = new byte[4];
		SecureRandom random = new SecureRandom();
		random.nextBytes(saltBytes);

		return new String(Base64.encodeBase64(saltBytes));
	}

	/**
	 * Compute salted password hash
	 * 
	 * @param password
	 *            password
	 * @param salt
	 *            random salt
	 */
	public static String computeSaltedHash(String password, String salt) {
		try {
			// Create Byte array of password string
			byte[] secretBytes = password.getBytes("utf-16le");

			// Create a new salt
			byte[] saltBytes = Base64.decodeBase64(salt);

			// append the two arrays
			byte[] toHash = new byte[secretBytes.length + saltBytes.length];
			System.arraycopy(secretBytes, 0, toHash, 0, secretBytes.length);
			System.arraycopy(saltBytes, 0, toHash, secretBytes.length,
					saltBytes.length);

			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(toHash);
			byte[] computedHash = md.digest();

			return new String(Base64.encodeBase64(computedHash));
		} catch (UnsupportedEncodingException ex) {
			// handled
		} catch (NoSuchAlgorithmException ex) {
			// handled
		}
		return null;
	}
}
