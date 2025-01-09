import java.util.ArrayList;
import java.util.List;

/**
 * Deze klasse bevat informatie over een film, zoals de titel, regisseur, releasejaar, en reviews.
 *
 * @author Noah Goethals
 * @version 1.0
 */
public class Film {
    private String title;
    private String director;
    private int releaseYear;
    private String rating;
    private String duration;
    private List<Review> reviews;

    /**
     * Maakt een nieuw Film-object aan.
     *
     * @param title De titel van de film
     * @param director De regisseur van de film
     * @param releaseYear Het jaar waarin de film is uitgebracht
     * @param rating De leeftijdsclassificatie van de film
     * @param duration De duur van de film
     */
    public Film(String title, String director, int releaseYear, String rating, String duration) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
        this.reviews = new ArrayList<>();
    }

    /**
     * Geeft de titel van de film terug.
     *
     * @return De titel van de film
     */
    public String getTitle() {
        return title;
    }

    /**
     * Geeft de regisseur van de film terug.
     *
     * @return De regisseur van de film
     */
    public String getDirector() {
        return director;
    }

    /**
     * Geeft het releasejaar van de film terug.
     *
     * @return Het releasejaar van de film
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Geeft de leeftijdsclassificatie van de film terug.
     *
     * @return De leeftijdsclassificatie van de film
     */
    public String getRating() {
        return rating;
    }

    /**
     * Geeft de duur van de film terug.
     *
     * @return De duur van de film
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Geeft de lijst van reviews voor de film terug.
     *
     * @return Een lijst van reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Voegt een review toe aan de lijst van reviews.
     *
     * @param review De review die toegevoegd moet worden
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    /**
     * Print alle reviews van de film naar de console.
     */
    public void printReviews() {
        if (reviews.isEmpty()) {
            System.out.println("Geen reviews beschikbaar voor deze film.");
        } else {
            reviews.forEach(System.out::println);
        }
    }

    /**
     * Geeft een stringrepresentatie van de film.
     *
     * @return Een string met de belangrijkste informatie over de film
     */
    @Override
    public String toString() {
        return "Film: " + title + " (" + releaseYear + "), Regisseur: " + director + ", Rating: " + rating + ", Duur: " + duration;
    }
}
