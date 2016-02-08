package fr.esiag.isidis.yadi.messagerie.metier;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import fr.esiag.isidis.yadi.messagerie.dao.ContactList;
import fr.esiag.isidis.yadi.messagerie.dao.MessageList;
import fr.esiag.isidis.yadi.messagerie.model.Contact;
import fr.esiag.isidis.yadi.messagerie.model.Message;

public class MessagerieMetier extends UnicastRemoteObject implements IManagerMetier{

	private static final long serialVersionUID = -3051242951558458578L;
	private ContactList contacts = ContactList.getInstance();
	private MessageList messages = MessageList.getInstance();
	
	public MessagerieMetier() throws RemoteException {
	}
	
	public Contact inscription(Contact obj) {
		return contacts.create(obj);
	}

	public Contact authentification(String login, String password) {
		return contacts.searchByloginPass(login, password);
	}
	
	public Contact findContact(String login){
		return contacts.searchBylogin(login);
	}

	public Message envoieMSG(Message m) {
		return messages.create(m);
	}

	public List<Message> listMSGRecu(String login) {
		return messages.searchByTo(login);
	}

	public List<Message> listMSGEnvoye(String login) {
		return messages.searchByFrom(login);
	}
}
