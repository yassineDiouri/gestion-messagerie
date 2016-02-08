package fr.esiag.isidis.yadi.messagerie.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import fr.esiag.isidis.yadi.messagerie.model.Contact;

public class ContactServlet extends HttpServlet {

	private static final long serialVersionUID = 123409140373400889L;
	private static final ResteasyClient client = new ResteasyClientBuilder().build();
	private String host = "localhost";
	private String port = "8080";
	private String basePath = "gestion-messagerie-service/rest/messagerie/";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		ResteasyWebTarget wTarget = client.target("http://" + host + ":" + port).path(basePath + "inscription");
//		
//		String login = request.getParameter("login");
//		Contact c = new Contact();
//		c.setLogin(login);
//
//		Response responseRestEasy = wTarget.request().post(Entity.entity(c, MediaType.APPLICATION_JSON));
//		responseRestEasy.close();
		System.out.println("GET");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/contact.jsp");
		dispatcher.forward(request, response);
	}
}
