package fr.esiag.isidis.yadi.messagerie.dao;

import java.rmi.Remote;
import java.util.List;

import fr.esiag.isidis.yadi.messagerie.model.Contact;

public interface IContactList extends Remote {

	public Contact create(Contact obj);

	public List<Contact> readAll();

	public Contact searchById(Integer id);
}
