/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 * This generic class is an alternative for performance, as passing the whole object for every single select on database can create a lot of data
 * Here it's possible to create an object with the object id reference and the className to use as the parameter object on log persistence
 */
public class GenericObject {
   private Integer id;
   private String className;

    public GenericObject() {
    }

    public GenericObject(Integer id, String className) {
        this.id = id;
        this.className = className;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
