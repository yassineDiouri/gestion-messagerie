package fr.esiag.isidis.yadi.messagerie.dao;

import java.rmi.Remote;
import java.util.List;

import fr.esiag.isidis.yadi.messagerie.model.Message;

public interface IMessageList extends Remote {

	public Message create(Message obj);

	public List<Message> readAll();

	public Message searchById(Integer id);
}
