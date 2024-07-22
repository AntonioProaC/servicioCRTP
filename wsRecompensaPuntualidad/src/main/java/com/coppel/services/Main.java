
package com.coppel.services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Esta clase registra todas las rutas (clases Service) y los filtros de
 * la aplicación. No modificar.
 * @author ${user}
 */
@ApplicationPath("api")
public class Main extends ResourceConfig {
    
    public Main() {
        packages("com.coppel.services", "com.coppel.coppelframework.filters");
    }
    
}
