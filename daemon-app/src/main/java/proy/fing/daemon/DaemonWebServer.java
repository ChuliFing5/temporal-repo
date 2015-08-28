package proy.fing.daemon;

import java.util.TimeZone;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import proy.fing.daemon.context.WebContext;
import proy.fing.daemon.service.container.BaseIpContainer;

public class DaemonWebServer {

	private static final String DISPLAY_NAME = "daemon-app";
	private static final String CONTEXT_NAME = "/";
	private static final int DEFAULT_PORT = 8082;

	private final Server server;

	public DaemonWebServer(String[] args) {
		this.server = this.createNewServer(args);
	}

	public static void main(String[] args) throws Exception {
		DaemonWebServer server = new DaemonWebServer(args);

		BaseIpContainer base = BaseIpContainer.getInstance();
		base.serBaseIp(args[0]);

		server.run();
	}

	private Server createNewServer(String[] args) {
		int port = DEFAULT_PORT;

		WebAppContext webHandler = this.buildWebAppContext(args);

		HandlerList handlers = new HandlerList();
		handlers.addHandler(webHandler);

		Server server = new Server(port);
		server.setHandler(handlers);
		server.setStopAtShutdown(true);

		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

		return server;
	}

	private void run() throws Exception {
		this.server.start();
		this.server.join();
	}

	private WebAppContext buildWebAppContext(String[] args) {

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(WebContext.class);

		WebAppContext handler = new WebAppContext();
		handler.setContextPath(CONTEXT_NAME);
		handler.setDisplayName(DISPLAY_NAME);

		handler.setInitParameter("useFileMappedBuffer", "false");
		handler.setBaseResource(Resource.newClassPathResource("/config"));
		handler.setResourceAlias("/WEB-INF/classes/", "/classes/");

		this.appendListeners(applicationContext, handler);
		this.appendSpringDispatcherServlet(applicationContext, handler);

		applicationContext.close();
		return handler;
	}

	private void appendListeners(AnnotationConfigWebApplicationContext applicationContext,
			ServletContextHandler handler) {
		// Para que funcione Spring con su contexto
		handler.addEventListener(new ContextLoaderListener(applicationContext));
	}

	private void appendSpringDispatcherServlet(AnnotationConfigWebApplicationContext applicationContext,
			ServletContextHandler handler) {
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
		servletHolder.setName("spring");
		servletHolder.setInitOrder(1);
		handler.addServlet(servletHolder, "/daemon-app/*");
	}

}
