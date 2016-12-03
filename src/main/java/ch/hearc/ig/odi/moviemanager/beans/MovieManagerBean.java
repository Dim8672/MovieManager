/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.beans;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author dimitri.mella
 */
@Named(value = "movieManagerBean")
@RequestScoped
public class MovieManagerBean {

    @Inject Services services;
    private List<Person> peopleList;
    private List<Movie> moviesList;
    
    public MovieManagerBean() {
        
    }
    
    public void init(){
        this.peopleList = services.getPeopleList();
        this.moviesList = services.getMoviesList();
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }
    
       
}
