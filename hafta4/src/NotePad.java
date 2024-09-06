import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotePad {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        NotePad notePad = new NotePad();
        notePad.run();
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n--- Notepad Application ---");
            System.out.println("1. List Notes");
            System.out.println("2. Add a New Note ");
            System.out.println("3. Delete The Note");
            System.out.println("4. Clear All Notes");
            System.out.println("5. Exit");
            System.out.print("Make Your Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listNotes();
                    break;
                case 2:
                    System.out.print("Enter your new note: ");
                    String note = scanner.nextLine();
                    addNote(note);
                    break;
                case 3:
                    System.out.print("Enter the number of the note you want to delete: ");
                    int noteIndex = scanner.nextInt();
                    scanner.nextLine();
                    deleteNote(noteIndex);
                    break;
                case 4:
                    clearAllNotes();
                    break;
                case 5:
                    System.out.println("Checking out...");
                    break;
                default:
                    System.out.println("Invalid election! Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }


    private void listNotes() {
        List<String> notes = readNotesFromFile();
        if (notes.isEmpty()) {
            System.out.println("There is no recorded note yet.");
        } else {
            System.out.println("\n--- Current Notes ---");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }


    private void addNote(String note) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(note);
            writer.newLine();
            System.out.println("The note was added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the note.");
            e.printStackTrace();
        }
    }

    private void deleteNote(int noteIndex) {
        List<String> notes = readNotesFromFile();
        if (noteIndex <= 0 || noteIndex > notes.size()) {
            System.out.println("Invalid note number.");
            return;
        }

        notes.remove(noteIndex - 1);
        System.out.println("The note was successfully deleted.");
    }


    private void clearAllNotes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("");
            System.out.println("All notes were successfully cleaned.");
        } catch (IOException e) {
            System.out.println("An error occurred while cleaning the notes.");
            e.printStackTrace();
        }
    }

    private List<String> readNotesFromFile() {
        List<String> notes = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("The note file was not found. A new file will be created.");
            return notes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the notes.");
            e.printStackTrace();
        }

        return notes;
    }

    private void writeNotesToFile(List<String> notes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing notes to the file.");
            e.printStackTrace();
        }
    }
}
