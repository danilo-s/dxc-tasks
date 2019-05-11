package it.danilo.util;

import java.io.InputStream;
import java.util.Properties;

import it.danilo.encryption.AESEncryption;
import it.danilo.encryption.DESEncryption;
import it.danilo.encryption.EncryptionType;

public class MailUtility {

	final static String MAIL_PROPERTY_FILE = "mail.properties";

	public static String getPropertyValue(String propertyKey) throws Exception {
		String result = null;
		InputStream input = MailUtility.class.getClassLoader().getResourceAsStream(MAIL_PROPERTY_FILE);
		Properties prop = new Properties();
		prop.load(input);
		result = (String) prop.get(propertyKey);
		input.close();
		return result;
	}

	public static String encryptBody(String body, EncryptionType encryptionType) throws Exception {
		String result = null;
		switch (encryptionType) {
		case AES:
			result = AESEncryption.encrypt(body, "key");
			break;
		case DES:
			result = DESEncryption.encrypt(body);
			break;
		default:
			break;
		}
		return result;
	}

}
