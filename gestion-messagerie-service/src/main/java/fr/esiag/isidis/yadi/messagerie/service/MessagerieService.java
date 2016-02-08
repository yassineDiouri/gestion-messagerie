package fr.esiag.isidis.yadi.messagerie.service;

import java.rmi.RemoteException;
import java.util.List;

import fr.esiag.isidis.yadi.messagerie.metier.IManagerMetier;
import fr.esiag.isidis.yadi.messagerie.metier.MessagerieMetier;
import fr.esiag.isidis.yadi.messagerie.model.Contact;
import fr.esiag.isidis.yadi.messagerie.model.Message;

public class MessagerieService implements IManagerService {

	private IManagerMetier metier;
	
	public MessagerieService() {
		try {
			metier = (IManagerMetier) DynamicProxy.newInstance(new MessagerieMetier());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public Contact inscription(Contact obj) {
		try {
			return metier.inscription(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Contact authentification(Contact c) {
		try {
			return metier.authentification(c.getLogin(), c.getPassword());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Contact findContact(String login) {
		try {
			return metier.findContact(login);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Message envoieMSG(Message m) {
		try {
			return metier.envoieMSG(m);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Message> listMSGRecu(String login) {
		try {
			return metier.listMSGRecu(login);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Message> listMSGEnvoye(String login) {
		try {
			return metier.listMSGEnvoye(login);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Contact debug() {
		Contact c = new Contact();
		return c;
	}
}
