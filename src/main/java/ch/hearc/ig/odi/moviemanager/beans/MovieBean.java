/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.beans;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.exception.NullParameterException;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author dimitri.mella
 */
@Named(value = "movieBean")
@ViewScoped
public class MovieBean implements Serializable {
    
     @Inject
    Services services;
    private Movie currentMovie;
    private Long currentMovieId;

    /**
     * Creates a new instance of MovieBean
     */
    public MovieBean() {
    }
    
    /**
     * Initialise the current Movie
     */
    public void initCurrentMovie(){
        if(currentMovieId == null){
            currentMovie = new Movie();
        } else {
        currentMovie = this.services.getMovieWithId(currentMovieId);
        }
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    public Long getCurrentMovieId() {
        return currentMovieId;
    }

    public void setCurrentMovieId(Long currentMovieId) {
        this.currentMovieId = currentMovieId;
    }
    
    public String save(){
        try {
            services.saveMovie(currentMovie);
        } catch (NullParameterException ex) {
            Logger.getLogger(PersonBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/index.xhtml?faces-redirect=true";
    }
      
}