package it.danilo.email;

public class Email {

	private String body;
	private BodyType bodyType;
	private boolean retry;

	public Email() {
	}

	public Email(String body, BodyType bodyType, boolean retry) {
		this.body=body;
		this.bodyType = bodyType;
		this.retry = retry;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}

	public boolean isRetry() {
		return retry;
	}

	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
