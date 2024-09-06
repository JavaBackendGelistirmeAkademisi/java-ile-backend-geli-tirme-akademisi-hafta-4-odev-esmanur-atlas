import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderManagement {
    private static final String FILE_NAME = "orders.txt";

    public void main(String[] args) {
        OrderManagement management = new OrderManagement();
        management.run();

    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Task Management System--- ");
            System.out.println("1. List the Tasks");
            System.out.println("2. Add a New Task");
            System.out.println("3. Exit");
            System.out.println("Make Your Choice:");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listOrders();
                    break;
                case 2:
                    System.out.println("Enter the new task:");
                    String orders = scanner.nextLine();
                    addOrder(orders);
                    break;
                case 3:
                    System.out.println("The exit is being made...");
                    break;
                default:
                    System.out.println(("Invalid election! Please try again."));
            }
        } while (choice != 3);
        scanner.close();
      }
    }

    private void listOrders() {
        List<String> orders = readOrdersFromFile();
        if ( (orders.isEmpty())) {
            System.out.println("The task could not be found.");
        } else {
            System.out.println("\n---Current Tasks---");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + "."+ orders.get(i));
            }
        }

    }

   private void addOrder(String order) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(OrderManagement.FILE_NAME, true))) {
        writer.write(order);
        writer.newLine();
        System.out.println("The task was added successfully.");
    } catch (IOException e) {
        System.out.println("An error occurred while adding the task.");
        e.printStackTrace();

    }
   }

   private List<String> readOrdersFromFile() {
    List<String> orders = new ArrayList<>();
    File file = new File(OrderManagement.FILE_NAME) ;

    if (!file.exists()) {
        System.out.println("The task file was not found. A new file will be created.");
        return  orders;
    }

       try (BufferedReader reader = new BufferedReader(new FileReader(OrderManagement.FILE_NAME))) {
           String line;
           while ((line = reader.readLine()) != null) {
               orders.add(line);

           }
       } catch (IOException e) {
           System.out.println("An error occurred while reading tasks.");
           e.printStackTrace();
       }

       return orders;
   }


    public void main() {

}

