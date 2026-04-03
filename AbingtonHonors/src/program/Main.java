package program;

import entity.*;
import java.util.List;
import java.util.Optional;

/**
 * @author aramc
 * @author AnthonyW
 */
public class Main
{
    
    private static AdminDAO AdminDAO;
    private static CreditsEarnedDAO CreditsEarnedDAO;
    private static ResourcesDAO ResourcesDAO;
    private static StudentsDAO StudentsDAO;

    // NEW: init method so UI can call it
    public static void initDAOs()
    {
        AdminDAO = new AdminDAO();
        CreditsEarnedDAO = new CreditsEarnedDAO();
        ResourcesDAO = new ResourcesDAO();
        StudentsDAO = new StudentsDAO();
    }
    
    public static void main(String[] args)
    {
        initDAOs();

        // original demo (kept)
        removeStudent(123456789);
        removeCreditsEarned(1);
        removeCreditsEarned(2);
        
        addStudent(123456789, "John", "Doe", "jd1234@psu.edu", "12345", "Jane Doe", "jad123@psu.edu");
        printStudents();
        
        updateStudent(123456789, "John", "Doe", "jd1234@psu.edu", "12345", "addie visor", "av123@psu.edu");
        printStudents();
        
        addCreditsEarned(1,1,"placeholder",123456789);
        addCreditsEarned(2,1,"placeholder",123456789);
        
        updateCreditsEarned(1,1,"placeholder",123456789, true, "looks good");
        updateCreditsEarned(2,1,"placeholder",123456789, true, "looks good");
        
        printCreditsEarned();
        printStudents();
        
        //testing admin functions
        removeAdmin(1);
        addAdmin(1, "abc1234@psu.edu", "12345", "john");
        updateAdmin(1, "abc1234@psu.edu", "12345", "jane");
        System.out.print(getAdmin(1).getAdminEmail());
    }
    

// ================= DEMO FUNCTIONS =================

// reset demo data
public static void resetDemo()
{
    // remove credits first so foreign key does not block student deletion
    removeCreditsEarned(1);
    removeCreditsEarned(2);
    removeCreditsEarned(3);

    // then remove students
    removeStudent(111);
    removeStudent(222);
}

// add demo students
public static void demoAddStudents()
{
    addStudent(111, "Alice", "Smith", "alice@psu.edu", "pass", "Advisor A", "a@psu.edu");
    addStudent(222, "Bob", "Jones", "bob@psu.edu", "pass", "Advisor B", "b@psu.edu");
}

// update demo student
public static void demoUpdateStudent()
{
    updateStudent(111, "Alice", "Smith", "alice@psu.edu", "pass", "Updated Advisor", "new@psu.edu");
}

// add demo credits
public static void demoAddCredits()
{
    addCreditsEarned(1, 3, "Course", 111);
    addCreditsEarned(2, 2, "Seminar", 111);
    addCreditsEarned(3, 1, "Workshop", 222);
}

// review credits
public static void demoReviewCredits()
{
    updateCreditsEarned(1, 3, "Course", 111, true, "Approved");
    updateCreditsEarned(2, 2, "Seminar", 111, false, "Rejected");
    updateCreditsEarned(3, 1, "Workshop", 222, true, "Approved");
}

// delete demo records
public static void demoDelete()
{
    // remove Bob's remaining credits first
    removeCreditsEarned(2);
    removeCreditsEarned(3);

    // then remove Bob
    removeStudent(222);
}
// ================= STRING OUTPUT (FOR UI) =================

public static String studentsTableString()
{
    StringBuilder sb = new StringBuilder();
    List<Students> list = StudentsDAO.getAll();

    if (list.isEmpty())
    {
        return "(no students found)\n";
    }

    for (Students s : list)
    {
        sb.append("ID: ").append(s.getPsuID())
          .append(" | Name: ").append(s.getFirstName()).append(" ").append(s.getLastName())
          .append(" | Credits: ").append(s.getHonorsCredits())
          .append("\n");
    }

    return sb.toString();
}

public static String creditsTableString()
{
    StringBuilder sb = new StringBuilder();
    List<CreditsEarned> list = CreditsEarnedDAO.getAll();

    if (list.isEmpty())
    {
        return "(no credit records found)\n";
    }

    for (CreditsEarned c : list)
    {
        sb.append("ID: ").append(c.getCreditsEarnedID())
          .append(" | Credits: ").append(c.getCreditsEarned())
          .append(" | Student: ").append(c.getPsuID())
          .append(" | Approved: ").append(c.getValidity())
          .append("\n");
    }

    return sb.toString();
}


// ================= ORIGINAL FUNCTIONS (UNCHANGED) =================



//functions to add data
static void addAdmin(int adminID, String adminEmail, String adminPassword, String adminName)
{
    Admin admin;
    admin = new Admin(adminID, adminEmail, adminPassword, adminName);
    AdminDAO.insert(admin);
}

static void addCreditsEarned(int creditsEarnedID, float credits, String creditSource, int psuID)
{
    CreditsEarned creditsEarned;
    creditsEarned = new CreditsEarned(creditsEarnedID, credits, creditSource, psuID);
    CreditsEarnedDAO.insert(creditsEarned);
}

static void addResource(int fileID, String fileName, byte[] fileData, int viewer)
{
    Resources resources;
    resources = new Resources(fileID, fileName, fileData, viewer);
    ResourcesDAO.insert(resources);
}

static void addStudent(int psuID, String firstName, String lastName, String psuEmail, String studentPassword, String advisorName, String advisorEmail)
{
    Students students;
    students = new Students(psuID, firstName, lastName, psuEmail, studentPassword, advisorName, advisorEmail);
    StudentsDAO.insert(students);
}

//functions to update rows
static void updateAdmin(int adminID, String adminEmail, String adminPassword, String adminName)
{
    Admin admin;
    admin = new Admin(adminID, adminEmail, adminPassword, adminName);
    AdminDAO.update(admin);
}

static void updateCreditsEarned(int creditsEarnedID, float credits, String creditSource, int psuID)
{
    CreditsEarned creditsEarned;
    creditsEarned = new CreditsEarned(creditsEarnedID, credits, creditSource, psuID);
    CreditsEarnedDAO.update(creditsEarned);
}

// main important update function
static void updateCreditsEarned(int creditsEarnedID, float credits, String creditSource, int psuID, boolean validity, String validityComment)
{
    CreditsEarned creditsEarned;
    creditsEarned = new CreditsEarned(creditsEarnedID, credits, creditSource, psuID, validity, validityComment);
    CreditsEarnedDAO.update(creditsEarned);
    
    StudentsDAO.updateCredit(psuID, CreditsEarnedDAO.getApprovedCredits(psuID));
}

static void updateResource(int fileID, String fileName, byte[] fileData, int viewer)
{
    Resources resources;
    resources = new Resources(fileID, fileName, fileData, viewer);
    ResourcesDAO.update(resources);
}

static void updateStudent(int psuID, String firstName, String lastName, String psuEmail, String studentPassword, String advisorName, String advisorEmail)
{
    // preserve the student's current honors credits when updating other fields
    float currentCredits = 0;

    Students existingStudent = getStudents(psuID);
    if (existingStudent.getPsuID() != -1)
    {
        currentCredits = existingStudent.getHonorsCredits();
    }

    Students students;
    students = new Students(psuID, firstName, lastName, psuEmail, studentPassword, advisorName, advisorEmail, currentCredits);
    StudentsDAO.update(students);
}

//functions to delete rows
static void removeAdmin(int adminID)
{
    Admin admin;
    admin = new Admin(adminID);
    AdminDAO.delete(admin);
}

static void removeCreditsEarned(int creditsEarnedID)
{
    CreditsEarned creditsEarned;
    creditsEarned = new CreditsEarned(creditsEarnedID);
    CreditsEarnedDAO.delete(creditsEarned);
}

static void removeResources(int resourcesID)
{
    Resources resources;
    resources = new Resources(resourcesID);
    ResourcesDAO.delete(resources);
}

static void removeStudent(int studentID)
{
    Students student;
    student = new Students(studentID);
    StudentsDAO.delete(student);
}

static Students getStudents(int psuID)
{
    Optional<Students> student = StudentsDAO.get(psuID);
    return student.orElse(new Students(-1, "", "", "", "", "", "", 0));
}

static Admin getAdmin(int adminID)
{
    Optional<Admin> admin = AdminDAO.get(adminID);
    return admin.orElse(new Admin(-1, "", "", ""));
}

static void printCreditsEarned()
{
    List<String> headers = CreditsEarnedDAO.getColumnNames();
    int numberCols = headers.size();

    for (int i = 0; i < numberCols; i++)
    {
        String header = headers.get(i);
        System.out.printf("%25s", header);
    }
    System.out.println();
    List<CreditsEarned> creditsEarned = CreditsEarnedDAO.getAll();
    int numberRows = creditsEarned.size();
    for (int i = 0; i < numberRows; i++) {
        System.out.printf("%25s%25s%25s%25s%25s%25s",
                creditsEarned.get(i).getCreditsEarnedID(),
                creditsEarned.get(i).getCreditsEarned(),
                creditsEarned.get(i).getCreditSource(),
                creditsEarned.get(i).getPsuID(),
                creditsEarned.get(i).getValidity(),
                creditsEarned.get(i).getValidityComment());
        System.out.println();
    }
}

static void printStudents()
{
    List<String> headers = StudentsDAO.getColumnNames();
    int numberCols = headers.size();

    for (int i = 0; i < numberCols; i++)
    {
        String header = headers.get(i);
        System.out.printf("%25s", header);
    }
    System.out.println();
    List<Students> students = StudentsDAO.getAll();
    int numberRows = students.size();
    for (int i = 0; i < numberRows; i++) {
        System.out.printf("%25s%25s%25s%25s%25s%25s%25s%25s",
                students.get(i).getPsuID(),
                students.get(i).getFirstName(),
                students.get(i).getLastName(),
                students.get(i).getPsuEmail(),
                students.get(i).getstudentPassword(),
                students.get(i).getHonorsCredits(),
                students.get(i).getAdvisorName(),
                students.get(i).getAdvisorEmail());
        System.out.println();
    }
}

}