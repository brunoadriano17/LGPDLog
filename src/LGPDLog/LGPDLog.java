/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LGPDLog;

import Model.FindLog;
import Model.UpdateLog;
import com.google.gson.Gson;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruno
 */
public class LGPDLog {
    /*
        Parameter 1: Object search
        Parameter 2: Object or ID from user responsible for the change
    */
    public static FindLog saveFind(Object theObject, Object theUser, EntityManagerFactory emf) throws Exception{
        FindLog object = null;
        try {
            Gson gson = new Gson();
            String jsonObject = gson.toJson(theObject);
            String jsonUser = gson.toJson(theUser);
            object = new FindLog(jsonObject, jsonUser);
            object.save(emf);
        } catch (Exception e) {
            throw e;
        }
        return object;
    }
    
    /*
        Parameter 1: Object before changes
        Parameter 2: Object after changes
        Parameter 3: Object or ID from user responsible for the change
    */
    public static UpdateLog saveUpdate(Object oldObject, Object newObject, Object user, EntityManagerFactory emf) throws Exception{
        UpdateLog object = null;
        try {
            Gson gson = new Gson();
            String oldOb = gson.toJson(oldObject);
            String newOb = gson.toJson(newObject);
            String theUser = gson.toJson(user);
            object = new UpdateLog(oldOb, newOb, theUser);
            object.save(emf);
        } catch (Exception e) {
            throw e;
        }
        return object;
    }
}
