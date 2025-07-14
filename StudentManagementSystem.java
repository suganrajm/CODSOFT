import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;
    private String email;

    public Student(String name, String rollNumber, String grade, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade + ", Email: " + email;
    }
}

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.txt";
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> editStudent(scanner);
                case 3 -> removeStudent(scanner);
                case 4 -> searchStudent(scanner);
                case 5 -> displayAllStudents();
                case 6 -> saveToFile();
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        } while (choice != 6);

        System.out.println("📁 Data saved. Exiting...");
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter roll number: ");
        String roll = scanner.nextLine().trim();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty() || email.isEmpty()) {
            System.out.println("❌ All fields are required!");
            return;
        }

        students.add(new Student(name, roll, grade, email));
        System.out.println("✅ Student added successfully!");
    }

    private static void editStudent(Scanner scanner) {
        System.out.print("Enter roll number of student to edit: ");
        String roll = scanner.nextLine().trim();

        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(roll)) {
                System.out.print("Enter new name (press enter to skip): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) s.setName(name);

                System.out.print("Enter new grade (press enter to skip): ");
                String grade = scanner.nextLine();
                if (!grade.isEmpty()) s.setGrade(grade);

                System.out.print("Enter new email (press enter to skip): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) s.setEmail(email);

                System.out.println("✅ Student updated.");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    private static void removeStudent(Scanner scanner) {
        System.out.print("Enter roll number to remove: ");
        String roll = scanner.nextLine().trim();

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getRollNumber().equalsIgnoreCase(roll)) {
                iterator.remove();
                System.out.println("✅ Student removed.");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter roll number to search: ");
        String roll = scanner.nextLine().trim();

        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(roll)) {
                System.out.println("🔍 Student Found:");
                System.out.println(s);
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("📭 No students to display.");
            return;
        }
        System.out.println("📋 List of All Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) in.readObject();
        } catch (Exception e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        }
    }
}
