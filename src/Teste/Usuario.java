/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import LGPDLog.LGPDLog;
import Model.BrunoPersistence;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

/**
 *
 * @author Bruno
 */
@Entity
public class Usuario extends BrunoPersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private java.sql.Timestamp createdAt;

    public Usuario() {
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public static Usuario doLogin(String email) {
        Usuario usuario = null;
        EntityManagerFactory emf = Connection.ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("FROM Usuario WHERE email = '" + email + "'");
            q.setMaxResults(1);
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
            Connection.ConnectionFactory.closeEntityManagerFactory();
        }
        return usuario;
    }

    //Antes
    public static Usuario find(String email) {
        Usuario usuario = null;
        EntityManagerFactory emf = Connection.ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("FROM Usuario WHERE email = '" + email + "'");
            q.setMaxResults(1);
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
            Connection.ConnectionFactory.closeEntityManagerFactory();
        }
        return usuario;
    }

    //Depois
    public static Usuario find(String email, Usuario user) throws Exception {
        Usuario usuario = null;
        EntityManagerFactory emf = Connection.ConnectionFactory.getEntityManager();
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("FROM Usuario WHERE email = '" + email + "'");
            q.setMaxResults(1);
            usuario = (Usuario) q.getSingleResult();
            //É salvo o registro de consulta ao usuário joão por bruno
            if(usuario != null){
                LGPDLog.saveFind(usuario, user, emf);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
            Connection.ConnectionFactory.closeEntityManagerFactory();
        }
        return usuario;
    }
}
