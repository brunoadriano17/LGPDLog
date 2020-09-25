/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bruno
 */
public class ConnectionFactory {

    private static EntityManagerFactory emf;

    private static synchronized void buildEmf() {
        try {
            emf = Persistence.createEntityManagerFactory("sistema");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna emf para instanciar entity manager
     */
    public static EntityManagerFactory getEntityManager() {
        if (emf == null) {
            buildEmf();
        }
        return emf;
    }

    /**
     * Finaliza emf
     */
    public static void closeEntityManagerFactory() {
        if (emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }
}
