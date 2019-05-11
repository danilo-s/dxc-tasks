package it.danilo.email;

import it.danilo.encryption.EncryptionType;
import it.danilo.util.Constants;

public class EmailFactory {

	public static Email getEmail(String scenario1) {
		Email result=null;
		switch (scenario1) {
		case Constants.SCENARIO1:
			//sending a plain text email to an outside resource, with a disclaimer added at the end, unencrypted and no retry
			result=new Email(BodyType.PLAIN_TEXT, true, false, EncryptionType.NONE);
			break;
		case Constants.SCENARIO2:
			//sending an HTML email to an internal server (so without the disclaimer), encrypted with DES, with the retry functionality
			result=new Email(BodyType.HTML, false, true, EncryptionType.DES);
			break;
		case Constants.SCENARIO3:
			//sending an HTML email to an outside resource, with a disclaimer added at the end and encrypted with AES with retries in case of errors
			result=new Email(BodyType.HTML, true, true, EncryptionType.AES);
			break;
		case Constants.SCENARIO4:
			//sending a plain text email to an outside resource and encrypted first with DES and then with AES
			result=new Email(BodyType.PLAIN_TEXT, true, false, EncryptionType.DES_AES);
			break;
		default:
			//add other scenarios..
			break;
		}
		return result;
	}
	
	

}
