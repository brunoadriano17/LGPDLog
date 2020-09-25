/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LGPDLog;

import Model.FindLog;
import com.google.gson.Gson;

/**
 *
 * @author Bruno
 */
public class FindRegister {
    /*
        Parameter 1: Object search
        Parameter 2: Object or ID from user responsible for the change
    */
    public static void save(Object theObject, Object theUser) throws Exception{
        try {
            Gson gson = new Gson();
            String jsonObject = gson.toJson(theObject);
            String jsonUser = gson.toJson(theUser);
            FindLog fl = new FindLog(jsonObject, jsonUser);
            fl.save();
        } catch (Exception e) {
            throw e;
        }
    }
}
