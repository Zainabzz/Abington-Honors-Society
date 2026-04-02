package entity;

/**
 * @author aramc
 */
public class Students
{
    private int psuID;
    private String firstName;
    private String lastName;
    private String psuEmail;
    private String studentPassword;
    private float honorsCredits;
    private String advisorName;
    private String advisorEmail;
    
    //first constructor requires all attributes except the credits
    public Students(int psuID, String firstName, String lastName, String psuEmail, String studentPassword, String advisorName, String advisorEmail)
    {
        this.psuID = psuID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.psuEmail = psuEmail;
        this.studentPassword = studentPassword;
        this.advisorName = advisorName;
        this.advisorEmail = advisorEmail;
    }
    //second constructor that also takes credits
    public Students(int psuID, String firstName, String lastName, String psuEmail, String studentPassword, String advisorName, String advisorEmail, float honorsCredits)
    {
        this.psuID = psuID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.psuEmail = psuEmail;
        this.studentPassword = studentPassword;
        this.advisorName = advisorName;
        this.advisorEmail = advisorEmail;
        this.honorsCredits = honorsCredits;
    }
    
    //third constructor only requires the ID
    //(can be used for row deletion)
    public Students(int psuID)
    {
        this.psuID = psuID;
    }
        
    //methods to set advisorname, advisoremail, and credits.
    public void setAdvisorName(String advisorName)
    {
        this.advisorName = advisorName;
    }

    public void setAdvisorEmail(String advisorEmail)
    {
        this.advisorEmail = advisorEmail;
    }
    
    public void setHonorsCredits(float honorsCredits)
    {
        this.honorsCredits = honorsCredits;
    }
    
    //methods to get all attributes
    public int getPsuID()
    {
        return psuID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPsuEmail()
    {
        return psuEmail;
    }
    
    public String getstudentPassword()
    {
        return studentPassword;
    }

    public float getHonorsCredits()
    {
        return honorsCredits;
    }

    public String getAdvisorName() 
    {
        return advisorName;
    }

    public String getAdvisorEmail() 
    {
        return advisorEmail;
    }
}
