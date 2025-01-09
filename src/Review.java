/**
 * Deze klasse beschrijft een review, met de auteur, beschrijving, score en de bijbehorende film.
 *
 * @author Noah Goethals
 * @version 1.0
 */
public class Review {
    private String author;
    private String description;
    private int score;
    private String filmTitle;

    /**
     * Maakt een nieuw Review-object aan.
     *
     * @param author De naam van de reviewer
     * @param description De beschrijving van de review
     * @param score De score van de review (1-10)
     * @param filmTitle De titel van de film waarvoor de review is geschreven
     */
    public Review(String author, String description, int score, String filmTitle) {
        this.author = author;
        this.description = description;
        this.score = score;
        this.filmTitle = filmTitle;
    }

    /**
     * Geeft de naam van de reviewer terug.
     *
     * @return De naam van de reviewer
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Stelt de naam van de reviewer in.
     *
     * @param author De naam van de reviewer
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Geeft de beschrijving van de review terug.
     *
     * @return De beschrijving van de review
     */
    public String getDescription() {
        return description;
    }

    /**
     * Stelt de beschrijving van de review in.
     *
     * @param description De nieuwe beschrijving van de review
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Geeft de score van de review terug.
     *
     * @return De score van de review
     */
    public int getScore() {
        return score;
    }

    /**
     * Stelt de score van de review in.
     *
     * @param score De nieuwe score van de review
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Geeft de titel van de film waarvoor de review is geschreven terug.
     *
     * @return De titel van de film
     */
    public String getFilmTitle() {
        return filmTitle;
    }

    /**
     * Stelt de titel van de film in waarvoor de review is geschreven.
     *
     * @param filmTitle De nieuwe titel van de film
     */
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    /**
     * Geeft een stringrepresentatie van de review.
     *
     * @return Een string met de details van de review
     */
    @Override
    public String toString() {
        return "Review for \"" + filmTitle + "\" by " + author + ": " + description + " (Score: " + score + ")";
    }
}
