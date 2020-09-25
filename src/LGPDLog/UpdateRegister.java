/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LGPDLog;

import Model.UpdateLog;
import com.google.gson.Gson;

/**
 *
 * @author Bruno
 */
public class UpdateRegister {
    /*
        Parameter 1: Object before changes
        Parameter 2: Object after changes
        Parameter 3: Object or ID from user responsible for the change
    */
    public static void save(Object oldObject, Object newObject, Object user) throws Exception{
        try {
            Gson gson = new Gson();
            String oldOb = gson.toJson(oldObject);
            String newOb = gson.toJson(newObject);
            String theUser = gson.toJson(user);
            UpdateLog ul = new UpdateLog(oldOb, newOb, theUser);
            ul.save();
        } catch (Exception e) {
            throw e;
        }
    }
}
