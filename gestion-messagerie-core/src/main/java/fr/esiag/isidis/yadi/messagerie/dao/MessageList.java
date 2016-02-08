package fr.esiag.isidis.yadi.messagerie.dao;

import java.util.ArrayList;
import java.util.List;

import fr.esiag.isidis.yadi.messagerie.model.Message;

public class MessageList implements IMessageList {

	private static MessageList instance = null;
	private List<Message> messages = new ArrayList<Message>();
	private Integer next = 1;
	
	private MessageList() {
	}
	
	public static MessageList getInstance(){
		if(instance == null){
			instance = new MessageList();
		}
		return instance;
	}
	
	public Message create(Message obj) {
		if(obj.getId() == 0 && !exists(obj)){
			obj.setId(next);
			messages.add(obj);
			next++;
		}
		return obj;
	}

	public List<Message> readAll() {
		return messages;
	}

	public Message searchById(Integer id) {
		if(id != 0){
			return messages.get(id);
		}
		return null;
	}

	public List<Message> searchByTo(String login) {
		List<Message> result = new ArrayList<Message>();
		for(Message m : messages){
			if (m.getTo().equals(login)) result.add(m);
		}
		return result;
	}

	public List<Message> searchByFrom(String login) {
		List<Message> result = new ArrayList<Message>();
		for(Message m : messages){
			if (m.getFrom().equals(login)) result.add(m);
		}
		return result;
	}
	
	public boolean exists(Message m){
		for(Message x : messages){
			if(x.getObject().equals(m.getObject())) return true;
		}
		return false;
	}
}
