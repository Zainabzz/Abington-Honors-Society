package entity;

/**
 * @author aramc
 */
public class Admin
{
    private int adminID;
    private String adminEmail;
    private String adminPassword;
    private String adminName;
    
    //constructor requires all attributes at creation
    public Admin(int adminID, String adminEmail, String adminPassword, String adminName)
    {
        this.adminID = adminID;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
    }
    
    //constructor that only requires ID
    public Admin(int adminID)
    {
        this.adminID = adminID;
    }
    
    //methods to get admin attributes
    public int getAdminID()
    {
        return adminID;
    }

    public String getAdminEmail()
    {
        return adminEmail;
    }

    public String getAdminPassword()
    {
        return adminPassword;
    }

    public String getAdminName()
    {
        return adminName;
    } 
    
}
