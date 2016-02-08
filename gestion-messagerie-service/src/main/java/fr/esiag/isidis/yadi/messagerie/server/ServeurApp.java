package fr.esiag.isidis.yadi.messagerie.server;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import fr.esiag.isidis.yadi.messagerie.metier.MessagerieMetier;
import fr.esiag.isidis.yadi.messagerie.service.MessagerieService;

public class ServeurApp {

	private Server server = null;
	private static String port;

	public ServeurApp(String _port) throws IOException {
		this.port = _port;
		
		Registry reg = LocateRegistry.createRegistry(Integer.parseInt(port)+1);
		reg.rebind("metier", new MessagerieMetier());

		server = new Server(Short.parseShort(port));

		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");

		server.setHandler(context);

		ServletHolder servlet = context.addServlet(ServletContainer.class, "/*");
		servlet.setInitOrder(0);
		servlet.setInitParameter("jersey.config.server.provider.classnames",
				MessagerieService.class.getCanonicalName());

		try {
			server.start();
			server.join();
		} catch (Exception e) {

		} finally {

		}
	}

	public static void main(String[] args) {
		try {
			String port = args[1]!=null?args[1]:"2500";
			
			new ServeurApp(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
