/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connection.ConnectionFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruno
 */
public abstract class BrunoPersistence {

    public void save() throws Exception {
        EntityManagerFactory emf = Connection.ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            Connection.ConnectionFactory.closeEntityManagerFactory();
        }
    }

    public void save(EntityManagerFactory emf) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
