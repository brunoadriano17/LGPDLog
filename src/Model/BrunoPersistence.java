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

    public void update() throws Exception {
        EntityManagerFactory emf = Connection.ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            Object ob = em.find(this.getClass(), this.getClass().getMethod("getId").invoke(this));
            em.getTransaction().begin();
            Method[] methods = this.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().contains("set") && !method.getName().equals("setId") && !method.getName().equals("setCreatedAt") && !method.getName().equals("setUpdatedAt") && !method.getName().equals("setDeletedAt")) {
                    if (method.getParameterTypes()[0] == boolean.class) {
                        String variable = method.getName().substring(3);
                        method.invoke(ob, this.getClass().getMethod("is" + variable).invoke(this));
                    } else {
                        String variable = method.getName().substring(3);
                        Object o = this.getClass().getMethod("get" + variable).invoke(this);
                        method.invoke(ob, o);
                    }
                }
                if (method.getName().equals("setUpdatedAt")) {
                    String variable = method.getName().substring(3);
                    method.invoke(ob, new java.sql.Timestamp(System.currentTimeMillis()));
                }
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            Connection.ConnectionFactory.closeEntityManagerFactory();
        }
    }

    public void update(EntityManagerFactory emf) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Object ob = em.find(this.getClass(), this.getClass().getMethod("getId").invoke(this));
            em.getTransaction().begin();
            Method[] methods = this.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().contains("set") && !method.getName().equals("setId") && !method.getName().equals("setCreatedAt") && !method.getName().equals("setUpdatedAt") && !method.getName().equals("setDeletedAt")) {
                    if (method.getParameterTypes()[0] == boolean.class) {
                        String variable = method.getName().substring(3);
                        method.invoke(ob, this.getClass().getMethod("is" + variable).invoke(this));
                    } else {
                        String variable = method.getName().substring(3);
                        method.invoke(ob, this.getClass().getMethod("get" + variable).invoke(this));
                    }
                }
                if (method.getName().equals("setUpdatedAt")) {
                    String variable = method.getName().substring(3);
                    method.invoke(ob, new java.sql.Timestamp(System.currentTimeMillis()));
                }
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void remove() throws Exception {
        EntityManagerFactory emf = ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            Object o = em.find(this.getClass(), this.getClass().getMethod("getId").invoke(this));
            em.getTransaction().begin();
            Method m = o.getClass().getMethod("setDeletedAt", java.sql.Timestamp.class);
            m.invoke(o, new java.sql.Timestamp(System.currentTimeMillis()));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            ConnectionFactory.closeEntityManagerFactory();
        }
    }

    public void remove(EntityManagerFactory emf) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Object o = em.find(this.getClass(), this.getClass().getMethod("getId").invoke(this));
            em.getTransaction().begin();
            Method m = o.getClass().getMethod("setDeletedAt", java.sql.Timestamp.class);
            m.invoke(o, new java.sql.Timestamp(System.currentTimeMillis()));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static Object find(Class objectClass, int theId){
        Object o = null;
        EntityManagerFactory emf = ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            List<Object> list = new ArrayList<>();
            list = em.createQuery("FROM "+objectClass.getName()+" WHERE id = "+theId, Object.class).getResultList();
            if(list.size() > 0){
                o = list.get(0);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }finally{
            em.close();
            ConnectionFactory.closeEntityManagerFactory();
        }
        return o;
    }

    public void enable() throws Exception {
        EntityManagerFactory emf = ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            Object o = em.find(this.getClass(), this.getClass().getMethod("getId").invoke(this));
            em.getTransaction().begin();
            Method m = o.getClass().getMethod("setDeletedAt", java.sql.Timestamp.class);
            m.invoke(o, new Object[]{null});
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            ConnectionFactory.closeEntityManagerFactory();
        }
    }

    public void enable(EntityManagerFactory emf) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Object o = em.find(this.getClass(), this.getClass().getMethod("getId").invoke(this));
            em.getTransaction().begin();
            Method m = o.getClass().getMethod("setDeletedAt", java.sql.Timestamp.class);
            m.invoke(o, new Object[]{null});
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static <T> List listAllActive(Class<T> objectClass) {
        List<T> list = new ArrayList<>();
        EntityManagerFactory emf = ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            list = em.createQuery("FROM " + objectClass.getName() + " WHERE deletedAt is NULL ", objectClass).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            ConnectionFactory.closeEntityManagerFactory();
        }
        return list;
    }
    public static <T> List listAllActive(Class<T> objectClass, EntityManagerFactory emf) {
        List<T> list = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            list = em.createQuery("FROM " + objectClass.getName() + " WHERE deletedAt is NULL ", objectClass).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
        return list;
    }

}
