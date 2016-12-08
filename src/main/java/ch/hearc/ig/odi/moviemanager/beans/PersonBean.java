/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.beans;

import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.NullParameterException;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author dimitri.mella
 */
@Named(value = "personBean")
@ViewScoped
public class PersonBean implements Serializable {

    @Inject
    Services services;
    private Person currentPerson;
    private Long currentPersonId;

    /**
     * Creates a new instance of PersonBean
     */
    public PersonBean() {
    }
    /**
     * Initialise the current Person
     */
    public void initCurrentPerson() {
        if(currentPersonId == null){
            currentPerson = new Person();
        } else {
        currentPerson = services.getPersonWithId(currentPersonId);
        }
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public Long getCurrentPersonId() {
        return currentPersonId;
    }

    public void setCurrentPersonId(Long currentPersonId) {
        this.currentPersonId = currentPersonId;
    }
    
    public String save(){
        try {
            services.savePerson(currentPerson);
        } catch (NullParameterException ex) {
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "list.xhtml?faces-redirect=true&id=" + currentPersonId;
    }
   
}
