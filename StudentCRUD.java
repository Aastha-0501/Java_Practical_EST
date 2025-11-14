import java.sql.*;
import java.util.Scanner;

public class StudentCRUD {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String pass = "Aastha@12345";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Database Connected Successfully!");

            while (true) {
                System.out.println("\n======= STUDENT CRUD MENU =======");
                System.out.println("1. Insert Student");
                System.out.println("2. View Student");
                System.out.println("3. Update Marks");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int ch = sc.nextInt();

                switch (ch) {

                    case 1:
                        System.out.print("Enter UID: ");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Section: ");
                        String section = sc.nextLine();

                        System.out.print("Enter Marks: ");
                        float marks = sc.nextFloat();

                        PreparedStatement ps1 = con.prepareStatement(
                            "INSERT INTO students VALUES (?,?,?,?)"
                        );
                        ps1.setInt(1, uid);
                        ps1.setString(2, name);
                        ps1.setString(3, section);
                        ps1.setFloat(4, marks);
                        ps1.executeUpdate();

                        System.out.println("Student Inserted!");
                        break;

                    case 2:
                        System.out.print("Enter UID to View: ");
                        int viewUid = sc.nextInt();

                        PreparedStatement ps2 = con.prepareStatement(
                            "SELECT * FROM students WHERE uid = ?"
                        );
                        ps2.setInt(1, viewUid);
                        ResultSet rs = ps2.executeQuery();

                        if (rs.next()) {
                            System.out.println("UID: " + rs.getInt(1));
                            System.out.println("Name: " + rs.getString(2));
                            System.out.println("Section: " + rs.getString(3));
                            System.out.println("Marks: " + rs.getFloat(4));
                        } else {
                            System.out.println("Student Not Found!");
                        }
                        break;

                    case 3:
                        System.out.print("Enter UID to Update Marks: ");
                        int updateUid = sc.nextInt();
                        System.out.print("Enter New Marks: ");
                        float newMarks = sc.nextFloat();

                        PreparedStatement ps3 = con.prepareStatement(
                            "UPDATE students SET marks=? WHERE uid=?"
                        );
                        ps3.setFloat(1, newMarks);
                        ps3.setInt(2, updateUid);
                        ps3.executeUpdate();

                        System.out.println("Marks Updated!");
                        break;

                    case 4:
                        System.out.print("Enter UID to Delete: ");
                        int deleteUid = sc.nextInt();

                        PreparedStatement ps4 = con.prepareStatement(
                            "DELETE FROM students WHERE uid=?"
                        );
                        ps4.setInt(1, deleteUid);
                        ps4.executeUpdate();

                        System.out.println("Student Deleted!");
                        break;

                    case 5:
                        System.out.println("Exiting Program...");
                        con.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
