package ch.hearc.ig.odi.moviemanager.business;

import ch.hearc.ig.odi.moviemanager.exception.NullParameterException;
import ch.hearc.ig.odi.moviemanager.exception.UniqueException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dimitri.mella
 */
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private List<Movie> movies;

    /*
    * Return a new Instance of Person
    */
    public Person() {
        this.movies = new ArrayList<>();
    }
    /*
    * Parameter constructor for this
    * @param id Id of this
    * @param firstName firstName of this
    * @param lastName lastName of this
    */
    public Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    /*
    * Adding a Movie to this and adding a Person to the Movie
    * @param movie the movie which will be added and which will have one more Person who watched it
    */
    public void addMovie(Movie movie) throws UniqueException, NullParameterException {
        this.movies.add(movie);
        movie.addPerson(this);
    }
    /*
    * Remove a Movie to this and removing a Person to the Movie
    * @param movie the movie which will be removed and which will have one less Person who watched it
    */
    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
        movie.removePerson(this);
    }

}
