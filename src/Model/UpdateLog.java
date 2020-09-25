/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Bruno
 */
@Entity
public class UpdateLog extends BrunoPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String oldObject;
    private String newObject;
    private String user;
    private java.sql.Timestamp createdAt;

    public UpdateLog() {
    }

    public UpdateLog(String oldObject, String newObject, String user) {
        this.oldObject = oldObject;
        this.newObject = newObject;
        this.user = user;
        this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldObject() {
        return oldObject;
    }

    public void setOldObject(String oldObject) {
        this.oldObject = oldObject;
    }

    public String getNewObject() {
        return newObject;
    }

    public void setNewObject(String newObject) {
        this.newObject = newObject;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
    
    
}
