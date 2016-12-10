/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.service;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import java.util.Objects;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

/**
 *
 * @author dimitri.mella
 */
@Named(value = "movieLOVConverter")
@Dependent
public class MovieLOVConverter implements Converter{
    
    @Inject Services services;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if (value == null){
           return null;
       } else {
           for(Movie movie : services.getMoviesList()){
               if(Objects.equals(movie.getId(), Long.valueOf(value))) {
                   return movie;
               }
           }
       }
       return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        } else {
            Movie movie = (Movie) value;
            return movie.getId().toString();
        }
    }
    
}
