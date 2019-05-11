package it.danilo.util;

import java.io.InputStream;
import java.util.Properties;

import it.danilo.encryption.EncryptionType;

public class MailUtility {

	final static String MAIL_PROPERTY_FILE = "mail.properties";

	public static String getPropertyValue(String propertyKey) {
		String result = null;
		try (InputStream input = MailUtility.class.getClassLoader().getResourceAsStream(MAIL_PROPERTY_FILE)) {
			Properties prop = new Properties();
			prop.load(input);
			result = (String) prop.get(propertyKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String encryptBody(StringBuilder stringBuilder, EncryptionType encryptionType) {
		String result = null;
		switch (encryptionType) {
		case AES:
			result = AESEncryption.encrypt(stringBuilder.toString(), "key");
			break;
		case DES:
			result = DESEncryption.encrypt(stringBuilder.toString());
			break;
		case DES_AES:
			result = AESEncryption.encrypt(DESEncryption.encrypt(stringBuilder.toString()), "key");
			break;
		case NONE:
			result = stringBuilder.toString();
			break;

		default:
			break;
		}
		return result;
	}

}
