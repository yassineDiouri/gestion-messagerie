package fr.esiag.isidis.yadi.messagerie.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.esiag.isidis.yadi.messagerie.model.Contact;
import fr.esiag.isidis.yadi.messagerie.model.Message;

/**
 * 
 * @author Yassine Diouri
 *
 */
public class ClientManager {

	private static final ResteasyClient client = new ResteasyClientBuilder().build();
	private String basePath = "messagerie/";
	
	public Boolean inscription(Contact c, String host, String port) {
		ResteasyWebTarget wTarget = client.target("http://" + host + ":" + port).path(basePath + "inscription");

		Response responseRestEasy = wTarget.request().post(Entity.entity(c, MediaType.APPLICATION_JSON));
		responseRestEasy.close();
		if (responseRestEasy.getStatus() == 200) return true;
		else return false;
	}

	public Contact authentification(Contact c, String host, String port) {
		ResteasyWebTarget wTarget = client.target("http://" + host + ":" + port).path(basePath + "authentification");

		Response responseRestEasy = wTarget.request().post(Entity.entity(c, MediaType.APPLICATION_JSON));
		c = responseRestEasy.readEntity(Contact.class);
		responseRestEasy.close();
		return c;
	}

	public Message envoieMSG(Message m, String host, String port) {
		ResteasyWebTarget wTarget = client.target("http://" + host + ":" + port).path(basePath + "envoie");

		Response responseRestEasy = wTarget.request().post(Entity.entity(m, MediaType.APPLICATION_JSON));
		m = responseRestEasy.readEntity(Message.class);
		responseRestEasy.close();
		return m;
	}

	public List<Message> listMSGRecu(String login, String host, String port) {
		ResteasyWebTarget wTarget = client.target("http://" + host + ":" + port).path(basePath + "messages/list");

		Response responseRestEasy = wTarget.request().post(Entity.entity(login, MediaType.APPLICATION_JSON));

		String result = responseRestEasy.readEntity(String.class);
		List<Message> list = new ArrayList<>();
		Gson gson = new Gson();
		list = gson.fromJson(result, new TypeToken<ArrayList<Message>>(){}.getType());

		responseRestEasy.close();
		return list;
	}

	public List<Message> listMSGEnvoye(String login, String host, String port) {
		ResteasyWebTarget wTarget = client.target("http://" + host + ":" + port).path(basePath + "messages/sent");

		Response responseRestEasy = wTarget.request().post(Entity.entity(login, MediaType.APPLICATION_JSON));

		String result = responseRestEasy.readEntity(String.class);
		List<Message> list = new ArrayList<>();
		Gson gson = new Gson();
		list = gson.fromJson(result, new TypeToken<ArrayList<Message>>(){}.getType());

		responseRestEasy.close();
		return list;
	}
}
