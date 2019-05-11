package it.danilo.encryption;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

	private static SecretKeySpec secretKey;
	private static byte[] key;

	private static void setKey(String myKey) throws Exception{
		MessageDigest sha = null;
		key = myKey.getBytes("UTF-8");
		sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		secretKey = new SecretKeySpec(key, "AES");
	}

	public static String encrypt(String strToEncrypt, String secret) throws Exception {
		String result = null;
		setKey(secret);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		result = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		return result;
	}

	public static String decrypt(String strToDecrypt, String secret) throws Exception {
		String result = null;
		setKey(secret);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		result = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

		return result;
	}

}
