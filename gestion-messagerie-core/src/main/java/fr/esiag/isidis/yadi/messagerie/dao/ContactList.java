package fr.esiag.isidis.yadi.messagerie.dao;

import java.util.ArrayList;
import java.util.List;

import fr.esiag.isidis.yadi.messagerie.model.Contact;

public class ContactList implements IContactList{

	private static ContactList instance = null;
	private List<Contact> contacts = new ArrayList<Contact>();
	private Integer next = 1;
	
	private ContactList() {
	}
	
	public static ContactList getInstance(){
		if(instance == null){
			instance = new ContactList();
		}
		return instance;
	}
	
	public Contact create(Contact obj) {
		if(obj.getId() == 0 && !exists(obj)){
			obj.setId(next);
			contacts.add(obj);
			next++;
		}
		return obj;
	}

	public List<Contact> readAll() {
		return contacts;
	}

	public Contact searchById(Integer id) {
		if(id != 0){
			return contacts.get(id);
		}
		return null;
	}

	public Contact searchByloginPass(String login, String password) {
		for(Contact c : contacts){
			if(c.getLogin().equals(login) && c.getPassword().equals(password))
				return c;
		}
		return null;
	}

	public Contact searchBylogin(String login) {
		for(Contact c : contacts){
			if(c.getLogin() == login)
				return c;
		}
		return null;
	}
	
	public boolean exists(Contact c){
		for(Contact x : contacts){
			if(x.getLogin().equals(c.getLogin())) return true;
		}
		return false;
	}
}
