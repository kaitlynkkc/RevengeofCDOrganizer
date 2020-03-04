/**
 * Represents a cd.
 * Cd's contain artist, title, genre, year, and link.
 */
public class CDLink {
    private String artist;
    private String title;
    private int genre;
    private int year;
    private CDLink n;

    /**
     * Constructor sets cd's artist, title, genre, year, and link.
     * @param artist user's entered artist name
     * @param title user's entered cd title
     * @param genre user's entered cd genre
     * @param year user's entered cd year
     * @param n link to next cd in list
     */
    CDLink(String artist, String title, int genre, int year, CDLink n) {
        this.artist = artist;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.n = n;
    }

    /**
     * Compares two cds by artist and year and returns true or false
     * based on how they comesBefore
     * @param test the cd being compared by artist and year
     * @return true if test cd comes before compared cd
     *         false if test cd comes after compared cd
     */
    public boolean comesBefore(CDLink test) {
        boolean result;
        int op1 = this.artist.compareToIgnoreCase(test.artist());

        if (op1 > 0) {
            result = true;
        } else if (op1 < 0) {
            result = false;
        } else {
            if (this.year() > test.year()) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * Getter method for artist attribute of CDLink.
     * @return artist attribute of CDLink.
     */
    String artist() {
        return artist;
    }

    /**
     * Getter method for title attribute of CDLink.
     * @return title attribute of CDLink.
     */
    String title() {
        return title;
    }

    /**
     * Getter method for genre attribute of CDLink.
     * @return genre attribute of CDLink.
     */
    int genre() {
        return genre;
    }

    /**
     * Getter method for year attribute of CDLink.
     * @return year attribute of CDLink.
     */
    int year() {
        return year;
    }

    /**
     * Getter method for next link attribute of CDLink.
     * @return next link attribute of CDLink.
     */
    CDLink next() {
        return n;
    }

    /**
     * Setter method for next link attribute of CDLink.
     * @param inn the new next link to set as the next link attribute of CDLink.
     * @return new next link attribute of CDLink.
     */
    CDLink setNext(CDLink inn) {
        return n = inn;
    }

    /**
     * Puts CDLink attributes into a printable string format.
     * @return string concatenation of CDLink attributes.
     */
    public String toString() {
        return artist + " " +  title + " " + genre + " " + year + "\n";
    }
}
