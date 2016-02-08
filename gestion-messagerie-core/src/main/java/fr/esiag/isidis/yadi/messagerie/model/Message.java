package fr.esiag.isidis.yadi.messagerie.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private static final long serialVersionUID = 4568826413041879012L;
	private Integer id;
	private String  object;
	private String  from;
	private String  to;
	private String  body;

	public Message() {
		this.id = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
