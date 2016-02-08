package fr.esiag.isidis.yadi.messagerie.metier;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import fr.esiag.isidis.yadi.messagerie.model.Contact;
import fr.esiag.isidis.yadi.messagerie.model.Message;

public interface IManagerMetier extends Remote {

	public Contact inscription(Contact obj) throws RemoteException;
	
	public Contact authentification(String login, String password) throws RemoteException;
	
	public Contact findContact(String login) throws RemoteException;

	public Message envoieMSG(Message m) throws RemoteException;

	public List<Message> listMSGRecu(String login) throws RemoteException;

	public List<Message> listMSGEnvoye(String login) throws RemoteException;
}
