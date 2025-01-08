import java.util.ArrayList;
import java.util.List;

/**
 * Klasse die informatie over een film bevat, zoals titel, regisseur, releasejaar, en reviews.
 */
public class Film {
    private String title;
    private String director;
    private int releaseYear;
    private String rating;
    private String duration;
    private List<Review> reviews;

    /**
     * een filmobject aan te maken.

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
     Geeft de titel van de film terug.
     */
    public String getTitle() {
        return title;
    }

    /**
     Geeft de regisseur van de film terug.
     */
    public String getDirector() {
        return director;
    }

    /**
     Geeft het releasejaar van de film terug.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     Geeft de leeftijdsclassificatie van de film terug.
     */
    public String getRating() {
        return rating;
    }

    /**
     Geeft de duur van de film terug.
     */
    public String getDuration() {
        return duration;
    }

    /**
     Geeft de lijst van reviews voor de film terug.
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     Voegt een review toe aan de lijst van reviews.
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    /**
     Print alle reviews van de film.
     */
    public void printReviews() {
        if (reviews.isEmpty()) {
            System.out.println("Geen reviews beschikbaar voor deze film.");
        } else {
            reviews.forEach(System.out::println);
        }
    }

    @Override
    public String toString() {
        return "Film: " + title + " (" + releaseYear + "), Regisseur: " + director + ", Rating: " + rating + ", Duur: " + duration;
    }
}
