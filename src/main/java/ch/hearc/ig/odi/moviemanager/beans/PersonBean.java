/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.beans;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.InvalidParameterException;
import ch.hearc.ig.odi.moviemanager.exception.NullParameterException;
import ch.hearc.ig.odi.moviemanager.exception.UniqueException;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
    private ArrayList<Movie> moviesMissing;
    private Movie movieSelect;

    /**
     * Creates a new instance of PersonBean
     */
    public PersonBean() {
        this.moviesMissing = new ArrayList<>();
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
        String uri = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
        this.searchMoviesMissing();
    }
    /**
     * Getter for the currentPerson
     * @return the currentPerson
     */
    public Person getCurrentPerson() {
        return currentPerson;
    }
    /**
     * Getter for the currentPersonId
     * @return the currentPersonId
     */
    public Long getCurrentPersonId() {
        return currentPersonId;
    }
    /**
     * setter for the currentPersonId
     * @param currentPersonId 
     */
    public void setCurrentPersonId(Long currentPersonId) {
        this.currentPersonId = currentPersonId;
    }
    /**
     * getter for the movieSelect
     * @return the movieSelect
     */
    public Movie getMovieSelect() {
        return movieSelect;
    }
    /**
     * setter for the movieSelect
     * @param movieSelect 
     */
    public void setMovieSelect(Movie movieSelect) {
        this.movieSelect = movieSelect;
    }
    
    /**
    * Delete a movie watched by the current Person
    * @param movie current movie on the table
    * @return the person detail page
    */
    public String deleteMovie(Movie movie){
        try {
            services.removeMovieFromPerson(currentPerson, movie);
        } catch (NullParameterException | InvalidParameterException ex) {
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return "list.xhtml?faces-redirect=true&id=" + currentPerson.getId();
        return "detailPerson";
    }
    /**
    * Adding a movie to the currentPerson
    * @param movie current movie to add
    * @return the person detail page
    */
    public String addMovie(Movie movie){
        try {
            services.addMovieToPerson(currentPerson, movie);
        } catch (NullParameterException | UniqueException ex) {
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "detailPerson";
    }
    
    /**
    * Save a new Person
    * @return the home page
    */
    public String save(){
        try {
            services.savePerson(currentPerson);
        } catch (NullParameterException ex) {
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Index";
    }
    
    /**
    * Searching the movies that the person didn't watched
    */
    public void searchMoviesMissing(){
        List<Movie> allMovies = services.getMoviesList();
        List<Movie> moviesOfPerson = currentPerson.getMovies();
        
        for(Movie movie : allMovies){
            if(moviesOfPerson.indexOf(movie) == -1){
                this.moviesMissing.add(movie);
            }
        }
    }
    /**
     * getter for the MoviesMissing
     * @return the array of Movies missing
     */
    public ArrayList<Movie> getMoviesMissing() {
        return moviesMissing;
    }
    /**
     * Setting the navigation of the next page
     * @return a string that indicate the next page
     */
    public String nav(){
        if(currentPersonId != null){
            return "detailPerson";
        }
        return "Index";
    }
   
}
