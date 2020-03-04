import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        CDOrganizer myCDOrganizer = new CDOrganizer();
        Scanner inFile = new Scanner(new File("genre.txt"));
        String genreList = printGenreList(inFile);
        Scanner fileInput = new Scanner(System.in);
        System.out.println("Please enter the filename for your CD storage collection.");
        System.out.println("If you do not yet have a collection, enter a filename where" +
                " you wish your CDs to be stored.");
        System.out.print("Filename: ");
        String fileName = fileInput.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            if (file.length() ==0) {
                System.out.println();
                System.out.println("There are no CDs in the collection");
                System.out.println();
            } else {
                //print file
                System.out.println();
                Scanner existingFile = new Scanner(System.in);
                String cds;

                while(existingFile.hasNextLine()) {
                    cds = existingFile.nextLine();
                    String[] cdParts = cds.split("\t", 4);
                    String artist = cdParts[0].toUpperCase();
                    String title = cdParts[1].toUpperCase();
                    int genre = Integer.parseInt(cdParts[2]);
                    int year = Integer.parseInt(cdParts[3]);
                    CDLink existingCD = new CDLink(artist, title, genre, year, null);
                    myCDOrganizer.insert(existingCD);
                }

                myCDOrganizer.viewList();
            }
        }
        PrintWriter p = new PrintWriter(file);
        Scanner input = new Scanner(System.in);
        startupOptions();
        int userAnswer = input.nextInt();
        while (userAnswer != 9) {
            switch (userAnswer) {
                case 1:
                    Scanner input1 = new Scanner(System.in);
                    System.out.print("Enter the artist: ");
                    String artist = input1.nextLine().toUpperCase();
                    System.out.print("Enter the CD title: ");
                    String title = input1.nextLine().toUpperCase();
                    System.out.print("Enter the year of the CD: ");
                    int year = input1.nextInt();
                    System.out.println(genreList);
                    System.out.print("Enter the genre number for this CD: ");
                    int genre = input1.nextInt();
                    myCDOrganizer.insert(new CDLink(artist, title, genre, year, null));
                    System.out.println();
                    break;
                case 2:
                    if (myCDOrganizer.head != null) {
                        System.out.println();
                        myCDOrganizer.viewList();
                    } else {
                        System.out.println();
                        System.out.println("No CDs found.");
                        System.out.println();
                    }
                    break;
                case 3:
                    Scanner searchInput = new Scanner(System.in);
                    System.out.println("Search by (1) Artist or (2) Genre?");
                    int search = searchInput.nextInt();
                    if (search == 1) {
                        System.out.print("Enter Artist (all or partial name): ");
                        searchInput.nextLine();
                        String artistSearch = searchInput.nextLine();
                        System.out.println();
                        myCDOrganizer.artistSearch(artistSearch);
                    } else if (search == 2) {
                        System.out.println(genreList);
                        System.out.print("Enter Genre number for which to search: ");
                        int genreSearch = searchInput.nextInt();
                        System.out.println();
                        myCDOrganizer.genreSearch(genreSearch);
                    }
                    break;
                case 4:
                    Scanner deleteInput = new Scanner(System.in);
                    System.out.println("Enter the title and artist of the CD to delete");
                    System.out.print("Artist: ");
                    String deleteArtist = deleteInput.nextLine();
                    System.out.print("Title: ");
                    String deleteTitle = deleteInput.nextLine();
                    myCDOrganizer.delete(deleteArtist, deleteTitle);
                    myCDOrganizer.viewList();
                    break;
                case 5:
                    p.print(myCDOrganizer);
                    p.close();
                    System.out.println();
                    System.out.println("**Save Successful**");
                    break;
            }
            startupOptions();
            userAnswer = input.nextInt();
        }
        Scanner close = new Scanner(file);
        String checkList = "";
        while(close.hasNextLine()) {
            checkList += close.nextLine() + '\n';
        }
        if (!(checkList.equals(myCDOrganizer.toString()))) {
            System.out.println("**WARNING** Your CD collection has changed since you last saved to disk.");
            System.out.print("Save your changes? (y/n): ");
            Scanner saveAnswer = new Scanner(System.in);
            String save = saveAnswer.nextLine();
            if (save.equals('y')) {
                p.print(myCDOrganizer.toString());
                p.close();
                System.out.println();
                System.out.println("**Save Successful**");
            } else if (save.equals('n')) {

            }
        }
    }

    /**
     * Prints options for CD Organizer.
     */
    public static void startupOptions() {
        System.out.println(
                "** CD Organizer -- Enter your choice **\n" +
                        "1. Enter a New CD\n" +
                        "2. View all CDs\n" +
                        "3. Search for a CD\n" +
                        "4. Delete a CD\n" +
                        "5. Save CD collection to disk\n" +
                        "9. Exit the program");
    }

    /**
     * Prints list of genre options from file genre.txt.
     * @param inFile the Scanner reading genre.txt
     * @return the genre list from genre.txt
     */
    public static String printGenreList(Scanner inFile) {
        String genreList = "";
        int lineNum = Integer.parseInt(inFile.nextLine());
        for (int i=0; i<lineNum+1; i++) {
            String s = inFile.nextLine();
            genreList += s + "\n";
        }
        return genreList;
    }
}