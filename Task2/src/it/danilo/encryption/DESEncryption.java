package it.danilo.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

public class DESEncryption {

	private static Cipher ecipher;
	private static Cipher dcipher;

	private static SecretKey key;

	private static void setKey() throws Exception {
		key = KeyGenerator.getInstance("DES").generateKey();
		ecipher = Cipher.getInstance("DES");
		dcipher = Cipher.getInstance("DES");
		ecipher.init(Cipher.ENCRYPT_MODE, key);

		dcipher.init(Cipher.DECRYPT_MODE, key);
	}

	public static String encrypt(String str) throws Exception {
		String result = null;
		setKey();
		byte[] utf8 = str.getBytes("UTF8");
		byte[] enc = ecipher.doFinal(utf8);
		enc = BASE64EncoderStream.encode(enc);
		result = new String(enc);

		return result;
	}

	public static String decrypt(String str) throws Exception {
		String result = null;
		setKey();
		byte[] dec = BASE64DecoderStream.decode(str.getBytes());
		byte[] utf8 = dcipher.doFinal(dec);
		result = new String(utf8, "UTF8");

		return result;
	}

}
