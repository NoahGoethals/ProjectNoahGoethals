import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dit is de main class van het programma. Het zorgt voor het beheren van films en reviews.
 *
 * @author Noah Goethals
 * @version 1.0
 */
public class Data {

    private static final Logger LOGGER = Logger.getLogger(Data.class.getName());
    private static final List<Review> reviewHistory = new ArrayList<>();

    /**
     * Hier start het programma.
     *
     * @param args Dit zijn de command-line argumenten
     */
    public static void main(String[] args) {
        List<Film> films = new ArrayList<>();
        laadFilms(films);
        startMenu(films);
    }

    /**
     * Deze methode laadt films uit een CSV-bestand.
     *
     * @param films De lijst waar de films worden opgeslagen
     */
    private static void laadFilms(List<Film> films) {
        try (
                InputStream is = Data.class.getClassLoader().getResourceAsStream("netflix_titles.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        ) {
            if (is == null) {
                throw new FileNotFoundException("CSV-bestand niet gevonden.");
            }

            String line;
            reader.readLine(); // Dit overslaat de header

            while ((line = reader.readLine()) != null) {
                try {
                    List<String> columns = parseCsvLine(line, reader);
                    if (columns.size() >= 10) {
                        films.add(createFilm(columns));
                    } else {
                        LOGGER.warning("Onvoldoende kolommen: " + line);
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Fout bij het verwerken van de regel: " + line, e);
                }
            }

            System.out.println("Aantal geladen films: " + films.size());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Er is een fout opgetreden bij het lezen van het CSV-bestand.", e);
        }
    }

    /**
     * Splitst een regel uit het CSV-bestand in kolommen.
     *
     * @param line   De regel uit het bestand
     * @param reader De reader die het bestand leest
     * @return Een lijst met kolommen
     * @throws IOException Als er een fout is bij het lezen
     */
    private static List<String> parseCsvLine(String line, BufferedReader reader) throws IOException {
        List<String> columns = new ArrayList<>();
        StringBuilder currentColumn = new StringBuilder();
        boolean insideQuotes = false;

        while (line != null) {
            for (char ch : line.toCharArray()) {
                if (ch == '"') {
                    insideQuotes = !insideQuotes;
                } else if (ch == ',' && !insideQuotes) {
                    columns.add(currentColumn.toString().trim());
                    currentColumn.setLength(0);
                } else {
                    currentColumn.append(ch);
                }
            }

            if (insideQuotes) {
                line = reader.readLine();
                if (line != null) currentColumn.append("\n");
            } else {
                break;
            }
        }
        columns.add(currentColumn.toString().trim());
        return columns;
    }

    /**
     * Deze methode maakt een Film-object.
     *
     * @param columns De kolommen met informatie over de film
     * @return Een nieuw Film-object
     */
    private static Film createFilm(List<String> columns) {
        String title = columns.get(2).replaceAll("^\"|\"$", "");
        String director = columns.get(3).replaceAll("^\"|\"$", "");
        int releaseYear = safeParseInt(columns.get(7).trim());
        String rating = columns.get(8).replaceAll("^\"|\"$", "");
        String duration = columns.get(9).replaceAll("^\"|\"$", "");
        return new Film(title, director, releaseYear, rating, duration);
    }

    /**
     * Probeert een string naar een getal te converteren.
     *
     * @param value De string die omgezet moet worden
     * @return De integer-waarde, of 0 als het niet lukt
     */
    private static int safeParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Start het menu waar de gebruiker keuzes kan maken.
     *
     * @param films De lijst met films
     */
    private static void startMenu(List<Film> films) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("--- Keuzemenu ---");
            System.out.println("1. Voeg een review toe");
            System.out.println("2. Zoek een film op naam");
            System.out.println("3. Lijst films van een regisseur");
            System.out.println("4. Lijst films van een releasejaar");
            System.out.println("5. Bekijk reviewgeschiedenis");
            System.out.println("6. Stoppen");

            int keuze = getValidInteger(scanner, "Kies een optie: ");

            switch (keuze) {
                case 1 -> voegReviewToe(films, scanner);
                case 2 -> zoekFilmOpNaam(films, scanner);
                case 3 -> lijstFilmsVanRegisseur(films, scanner);
                case 4 -> lijstFilmsVanReleasejaar(films, scanner);
                case 5 -> bekijkReviewGeschiedenis();
                case 6 -> running = false;
                default -> System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
    }

    /**
     * Controleert of de invoer een geldig getal is.
     *
     * @param scanner De scanner om invoer te lezen
     * @param prompt  De tekst die wordt weergegeven aan de gebruiker
     * @return Een geldig getal
     */
    private static int getValidInteger(Scanner scanner, String prompt) {
        int result = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                result = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer. Voer een getal in.");
            }
        }

        return result;
    }

    /**
     * Hiermee kan de gebruiker een review toevoegen.
     *
     * @param films   De lijst met films
     * @param scanner De scanner om invoer te lezen
     */
    private static void voegReviewToe(List<Film> films, Scanner scanner) {
        System.out.print("Titel van de film: ");
        String title = scanner.nextLine();

        Optional<Film> filmOpt = films.stream().filter(f -> f.getTitle().equalsIgnoreCase(title)).findFirst();

        if (filmOpt.isPresent()) {
            Film film = filmOpt.get();
            System.out.print("Naam van de reviewer: ");
            String reviewer = scanner.nextLine();
            System.out.print("Beschrijving: ");
            String description = scanner.nextLine();

            int score = -1;
            while (score < 1 || score > 10) {
                System.out.print("Score (1-10): ");
                try {
                    score = Integer.parseInt(scanner.nextLine());
                    if (score < 1 || score > 10) {
                        System.out.println("Score moet tussen 1 en 10 zijn.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ongeldige invoer. Voer een getal in.");
                }
            }

            Review review = new Review(reviewer, description, score, title);
            film.addReview(review);
            reviewHistory.add(review);
            System.out.println("Review toegevoegd.");
        } else {
            System.out.println("Film niet gevonden.");
        }
    }

    /**
     * Zoekt een film op naam of een deel van de naam.
     *
     * @param films   De lijst met films
     * @param scanner De scanner om invoer te lezen
     */
    private static void zoekFilmOpNaam(List<Film> films, Scanner scanner) {
        System.out.print("Voer (een deel van) de titel in: ");
        String title = scanner.nextLine().toLowerCase();

        List<Film> gevondenFilms = films.stream()
                .filter(f -> f.getTitle().toLowerCase().contains(title))
                .toList();

        if (gevondenFilms.isEmpty()) {
            System.out.println("Geen films gevonden.");
        } else {
            gevondenFilms.forEach(f -> System.out.println(f.getTitle()));
        }
    }

    /**
     * Lijst films van een bepaalde regisseur.
     *
     * @param films   De lijst met films
     * @param scanner De scanner om invoer te lezen
     */
    private static void lijstFilmsVanRegisseur(List<Film> films, Scanner scanner) {
        System.out.print("Naam van de regisseur: ");
        String director = scanner.nextLine().toLowerCase();

        List<Film> gevondenFilms = films.stream()
                .filter(f -> f.getDirector().toLowerCase().contains(director))
                .toList();

        if (gevondenFilms.isEmpty()) {
            System.out.println("Geen films gevonden.");
        } else {
            gevondenFilms.forEach(f -> System.out.println(f.getTitle()));
        }
    }

    /**
     * Lijst films van een bepaald releasejaar.
     *
     * @param films   De lijst met films
     * @param scanner De scanner om invoer te lezen
     */
    private static void lijstFilmsVanReleasejaar(List<Film> films, Scanner scanner) {
        System.out.print("Geef een releasejaar: ");
        int year = Integer.parseInt(scanner.nextLine());

        List<Film> gevondenFilms = films.stream()
                .filter(f -> f.getReleaseYear() == year)
                .toList();

        if (gevondenFilms.isEmpty()) {
            System.out.println("Geen films gevonden.");
        } else {
            gevondenFilms.forEach(f -> System.out.println(f.getTitle()));
        }
    }

    /**
     * Laat de gebruiker de reviewgeschiedenis zien.
     */
    private static void bekijkReviewGeschiedenis() {
        if (reviewHistory.isEmpty()) {
            System.out.println("Geen reviews toegevoegd.");
        } else {
            reviewHistory.forEach(System.out::println);
        }
    }
}
