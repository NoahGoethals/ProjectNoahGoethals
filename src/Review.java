/**
 Klasse die een review beschrijft, de auteur, beschrijving, score, en bijbehorende film.
 */
public class Review {
    private String author;
    private String description;
    private int score;
    private String filmTitle;

    /**
     een reviewobject aan te maken.

     */
    public Review(String author, String description, int score, String filmTitle) {
        this.author = author;
        this.description = description;
        this.score = score;
        this.filmTitle = filmTitle;
    }

    /**
     Geeft de naam van de reviewer terug.

     */
    public String getAuthor() {
        return author;
    }

    /**
     * Stelt de naam van de reviewer in.

     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Geeft de beschrijving van de review terug.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Stelt de beschrijving van de review in.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Geeft de score van de review terug.
     */
    public int getScore() {
        return score;
    }

    /**
     * Stelt de score van de review in.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Geeft de titel van de film waarvoor de review is geschreven terug.
     */
    public String getFilmTitle() {
        return filmTitle;
    }

    /**
     * Stelt de titel van de film in waarvoor de review is geschreven.
     */
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    @Override
    public String toString() {
        return "Review for \"" + filmTitle + "\" by " + author + ": " + description + " (Score: " + score + ")";
    }
}
