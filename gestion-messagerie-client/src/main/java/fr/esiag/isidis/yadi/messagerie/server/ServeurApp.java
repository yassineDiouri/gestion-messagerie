package fr.esiag.isidis.yadi.messagerie.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class ServeurApp {

	private Server server = null;
	private short port;

	public ServeurApp() {
		final String _port = System.getProperty("server.http.port", "2500");
		port = Short.parseShort(_port);

		server = new Server(port);

		WebAppContext context = new WebAppContext();
		context.setDescriptor("../gestion-messagerie-client/src/main/webapp/WEB-INF/web.xml");
		context.setResourceBase("../gestion-messagerie-client/src/main/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);
		
		
//		ServletContextHandler context = new ServletContextHandler();
//		context.setContextPath("/");
		server.setHandler(context);
//		
////		context.setResourceBase("./src/main/webapp");
////		context.setClassLoader(Thread.currentThread().getContextClassLoader());
//		context.addServlet(new ServletHolder(new ContactServlet()), "/contact");


		try {
			server.start();
			server.join();
		} catch (Exception e) {

		} finally {

		}
	}

	public static void main(String[] args) {
		new ServeurApp();
	}
}
