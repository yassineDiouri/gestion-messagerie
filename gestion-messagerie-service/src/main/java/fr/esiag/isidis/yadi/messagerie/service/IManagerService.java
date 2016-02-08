package fr.esiag.isidis.yadi.messagerie.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.esiag.isidis.yadi.messagerie.model.Contact;
import fr.esiag.isidis.yadi.messagerie.model.Message;

@Path("/messagerie")
public interface IManagerService  {

	@POST
	@Path("/inscription")
	@Consumes("application/json")
	@Produces("application/json")
	public Contact inscription(Contact obj);
	
	@POST
	@Path("/envoie")
	@Consumes("application/json")
	@Produces("application/json")
	public Message envoieMSG(Message m);

	@GET
	@Path("/contact/{login}")
	@Consumes("application/json")
	@Produces("application/json")
	public Contact findContact(@PathParam("login") String login);

	@POST
	@Path("/authentification")
	@Consumes("application/json")
	@Produces("application/json")
	public Contact authentification(Contact c);
	
	@POST
	@Path("/messages/list")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Message> listMSGRecu(String login);
	
	@POST
	@Path("/messages/sent")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Message> listMSGEnvoye(String login);
	
	@GET
	@Path("/debug")
	@Produces("application/json")
	public Contact debug();
}
