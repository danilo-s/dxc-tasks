package it.danilo.email;

import it.danilo.encryption.EncryptionType;

public class Email {
	
	private BodyType bodyType;
	private boolean outside;
	private boolean retry;
	private EncryptionType encryptionType;
	
	public Email() {}
	
	public Email(BodyType bodyType, boolean outside, boolean retry, EncryptionType encryptionType) {
		super();
		this.bodyType = bodyType;
		this.outside = outside;
		this.retry = retry;
		this.encryptionType = encryptionType;
	}
	
	public BodyType getBodyType() {
		return bodyType;
	}
	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}
	public boolean isOutside() {
		return outside;
	}
	public void setOutside(boolean outside) {
		this.outside = outside;
	}
	public boolean isRetry() {
		return retry;
	}
	public void setRetry(boolean retry) {
		this.retry = retry;
	}
	public EncryptionType getEncryptionType() {
		return encryptionType;
	}
	public void setEncryptionType(EncryptionType encryptionType) {
		this.encryptionType = encryptionType;
	}

}
