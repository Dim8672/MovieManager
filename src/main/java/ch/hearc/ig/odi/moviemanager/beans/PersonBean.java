/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.beans;

import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.service.Services;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author dimitri.mella
 */
@Named(value = "personBean")
@RequestScoped
public class PersonBean {

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
        currentPerson = services.getPersonWithId(currentPersonId);
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

}
