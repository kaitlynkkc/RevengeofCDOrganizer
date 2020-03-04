/**
 * Represents a linked list of cd's.
 * Cd's are organized and can be inserted, viewed, and searched through.
 */
public class CDOrganizer {
    CDLink head;
    private int listSize = 0;

    /**
     * Inserts new CD into linked list at the appropriate position.
     * @param newCD the new cd being inserted into the list.
     *              Formatted as a CDLink and thus contains artist,
     *              title, genre, year, and link.
     */
    public void insert(CDLink newCD) {
        CDLink curr = head;
        if (curr != null) {
            if (curr.comesBefore(newCD)) {
                head = newCD;
                head.setNext(curr);
            } else {
                while (curr.next() != null && !(curr.next().comesBefore(newCD))) {
                    curr = curr.next();
                }
                if (curr.next() == null) {
                    curr.setNext(newCD);
                    newCD.setNext(null);
                } else {
                    newCD.setNext(curr.next());
                    curr.setNext(newCD);
                }
            }
        } else {
            head = newCD;
        }
        listSize++;
    }

    public void delete(String artist, String title) {
        CDLink curr = head;
        CDLink prev = null;
        for (int i = 0; i < listSize; i++) {
            if (curr.artist().equals(artist.toUpperCase()) && curr.title().equals(title.toUpperCase())) {
                    if (curr == head) {
                        head = curr.next();
                    } else if (curr.next() == null) {
                        prev.setNext(null);
                    } else {
                        prev.setNext(curr.next());
                    }
                    listSize--;
            }
            prev = curr;
            curr = curr.next();
            viewList();
        }
    }

    /**
     * Prints the ordered cd list to the screen.
     */
    public void viewList() {
        System.out.println("Artist Title Genre Year");
        System.out.println(toString());
    }

    /**
     * Searches cd list for cd by partial/all artist name.
     * @param artist partial/all artist name searched by user.
     */
    public void artistSearch(String artist) {
        CDLink curr = head;
        String artistSearchList = "";
        int recordCounter = 0;
        for (int i = 0; i < listSize; i++) {
            if (curr.artist().substring(0, artist.length()).equals(artist.toUpperCase())) {
                artistSearchList += curr.toString();
                recordCounter++;
            }
            curr = curr.next();
        }
        System.out.println("Artist Title Genre Year");
        System.out.println(artistSearchList);
        System.out.println(recordCounter + " records found.");
        System.out.println();
    }

    /**
     * Searches cd list for cd by genre number.
     * @param genre genre number searched by user.
     */
    public void genreSearch(int genre) {
        CDLink curr = head;
        String genreSearchList = "";
        int recordCounter = 0;
        for (int i = 0; i < listSize; i++) {
            if (curr.genre() == genre) {
                genreSearchList += curr.toString();
                recordCounter++;
            }
            curr = curr.next();
        }
        System.out.println("Artist Title Genre Year");
        System.out.println(genreSearchList);
        System.out.println(recordCounter + " records found.");
        System.out.println();
    }

    /**
     * Puts full cd list into a printable string.
     * @return string of cd list.
     */
    public String toString() {
        String cdList = "";
        cdList += head.toString();
        CDLink curr = head;
        for (int i = 0; i < listSize - 1; i++) {
            cdList += curr.next().toString();
            curr = curr.next();
        }
        return cdList;
    }
}
