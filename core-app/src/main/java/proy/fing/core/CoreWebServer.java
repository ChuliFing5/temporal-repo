package proy.fing.core;

import java.util.TimeZone;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import proy.fing.core.context.WebContext;

public class CoreWebServer {


    private static final String DISPLAY_NAME = "core-app";
    private static final String CONTEXT_NAME = "/";
    private static final int DEFAULT_PORT = 8081;

    private enum Enviroment {
        rc, beta, prod, desa;
    }

    private final Server server;

    public CoreWebServer(String[] args) {
        this.server = this.createNewServer(args);
    }

    public static void main(String[] args) throws Exception {
    	CoreWebServer server = new CoreWebServer(args);
        server.run();
    }

    private Server createNewServer(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        Enviroment env = args.length > 0 ? Enviroment.valueOf(args[1]) : Enviroment.desa;

        WebAppContext webHandler = this.buildWebAppContext(args, env);

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

    private WebAppContext buildWebAppContext(String[] args, Enviroment env) {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebContext.class);

        WebAppContext handler = new WebAppContext();
        handler.setContextPath(CONTEXT_NAME);
        handler.setDisplayName(DISPLAY_NAME);

        String[] resources = null;
        resources = new String[] {"./src/main/resources/config" };

//        handler.setWelcomeFiles(new String[] {"index.html"});
          handler.setInitParameter("useFileMappedBuffer", "false");
          handler.setBaseResource(new ResourceCollection(resources));
          handler.setResourceAlias("/WEB-INF/classes/", "/classes/");

        this.appendListeners(applicationContext, handler);
        this.appendSpringDispatcherServlet(applicationContext, handler);

        applicationContext.close();
        return handler;
    }

    private void appendListeners(AnnotationConfigWebApplicationContext applicationContext, ServletContextHandler handler) {
        // Para que funcione Spring con su contexto
        handler.addEventListener(new ContextLoaderListener(applicationContext));
    }

    private void appendSpringDispatcherServlet(AnnotationConfigWebApplicationContext applicationContext,
        ServletContextHandler handler) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        servletHolder.setName("spring");
        servletHolder.setInitOrder(1);
        handler.addServlet(servletHolder, "/core-app/*");
    }

    
}
