
package com.coppel.main;

import com.coppel.coppelframework.config.ApplicationConfiguration;
import com.coppel.services.Main;
import java.io.IOException;
import java.net.BindException;
import java.util.Properties;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.HibernateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Clase principal de la aplicación. Es necesario indicarle a la aplicación
 * la ruta (válida) de un archivo de configuración para que funcione adecuadamente.
 * No es necesaria su modificación.
 * @author ${user}
 */
public class App {
    
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());
    
    public static void main(String[] args) {
        
        if(args.length == 0) {
            System.err.println("Debe especificar un archivo de configuración.");
            System.exit(-1);
        }
        
        Main main = null;
        ServletHolder servlet = null;
        Server jettyServer = null;
        ServletContextHandler context = null;
        
        int port = 0;
         try {
            main = new Main();
            Properties appConfig = ApplicationConfiguration
                            .getInstance(args[0])
                            .getProperties();
            port = Integer.parseInt(appConfig.getProperty("port"));
            StatisticsHandler statisticsHandler = new StatisticsHandler();
            servlet = new ServletHolder(new ServletContainer(main));
            int maxThreads = Integer.parseInt(
                    appConfig.getProperty("maxThreads")
            );
            int minThreads = Integer.parseInt(
                    appConfig.getProperty("minThreads")
            );
            QueuedThreadPool threadPool = new QueuedThreadPool(
                    maxThreads,
                    minThreads
            );
            jettyServer = new Server(threadPool);
            HttpConfiguration httpConfig = new HttpConfiguration();
            httpConfig.setSendServerVersion(false);
            httpConfig.setSendDateHeader(false);
            ServerConnector http = new ServerConnector(
                    jettyServer,
                    new HttpConnectionFactory(httpConfig)
            );
            http.setPort(port);
            jettyServer.addConnector(http);
            statisticsHandler.setServer(jettyServer);
            context = new ServletContextHandler(jettyServer, "/");
            context.addServlet(servlet, "/*");
            jettyServer.start();
            jettyServer.join();
        } catch(BindException ex) {
            System.err.println(
                    String.format(
                        "El puerto %d ya se encuentra en uso",
                        port
                    )
            );
            System.exit(-1);
        }
        catch(IOException ex) {
             LOGGER.error("Excepción al intentar leer el archivo: ", ex);
        } catch(HibernateException ex) {
             LOGGER.error("Excepción de Hibernate: ", ex);
        } catch (Exception ex) {
             LOGGER.error("Excepción no controla: ", ex);
        }
        finally {
            if(jettyServer != null) {
                jettyServer.destroy();
            }
        }
    }
    
}
