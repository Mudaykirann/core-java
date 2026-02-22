package com.database.jdbcDemo;

import com.database.jdbcDemo.Repo.BarberRepo;
import com.database.jdbcDemo.model.Barber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class JdbcDemoApplication {

    public static void main(String[] args) {
        // Start the Spring context and get the beans
        ApplicationContext context = SpringApplication.run(JdbcDemoApplication.class, args);
        BarberRepo repo = context.getBean(BarberRepo.class);

        // Scanner for reading console input
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("--- Barber Management System ---");

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Barber (Create)");
            System.out.println("2. View All Barbers (Read)");
            System.out.println("3. Find Barber by ID (Read Single)");
            System.out.println("4. Update Barber (Update)");
            System.out.println("5. Delete Barber (Delete)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left over from nextInt()

            switch (choice) {
                case 1: // CREATE
                    Barber newBarber = new Barber(); // Get a fresh Barber object
                    System.out.print("Enter ID: ");
                    newBarber.setId(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    newBarber.setName(scanner.nextLine());
                    System.out.print("Enter City: ");
                    newBarber.setCity(scanner.nextLine());
                    repo.save(newBarber); // Call repository save
                    break;

                case 2: // READ ALL
                    System.out.println("List of Barbers:");
                    // Iterate through the list returned by findAll() and print
                    repo.findAll().forEach(b -> System.out.println(b.getId() + " | " + b.getName() + " | " + b.getCity()));
                    break;

                case 3: // READ SINGLE
                    System.out.print("Enter ID to find: ");
                    int findId = scanner.nextInt();
                    try {
                        Barber found = repo.findById(findId);
                        System.out.println("Found: " + found.getName() + " from " + found.getCity());
                    } catch (Exception e) {
                        System.out.println("Error: Barber not found!");
                    }
                    break;

                case 4: // UPDATE
                    Barber updateBarber = new Barber();
                    System.out.print("Enter existing ID to update: ");
                    updateBarber.setId(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    updateBarber.setName(scanner.nextLine());
                    System.out.print("Enter New City: ");
                    updateBarber.setCity(scanner.nextLine());
                    repo.update(updateBarber);
                    break;

                case 5: // DELETE
                    System.out.print("Enter ID to delete: ");
                    int deleteId = scanner.nextInt();
                    repo.deleteById(deleteId);
                    break;

                case 0: // EXIT
                    exit = true;
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close(); // Clean up the scanner
    }
}