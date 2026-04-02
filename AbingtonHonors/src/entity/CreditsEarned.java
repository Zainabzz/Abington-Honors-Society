package entity;

/**
 * @author aramc
 */
public class CreditsEarned
{
    private int creditsEarnedID;
    private float creditsEarned;
    private String creditSource;
    private int psuID;
    private boolean validity;
    private String validityComment;
    
    //constructor requires all attributes except validity and validitycomment
    public CreditsEarned(int creditsEarnedID, float creditsEarned, String creditSource, int psuID)
    {
        this.creditsEarnedID = creditsEarnedID;
        this.creditsEarned = creditsEarned;
        this.creditSource = creditSource;
        this.psuID = psuID;
    }
    
        //constructor requires all attributes except validity and validitycomment
    public CreditsEarned(int creditsEarnedID, float creditsEarned, String creditSource, int psuID, boolean validity, String validtyComment)
    {
        this.creditsEarnedID = creditsEarnedID;
        this.creditsEarned = creditsEarned;
        this.creditSource = creditSource;
        this.psuID = psuID;
        this.validity = validity;
        this.validityComment = validtyComment;
    }
    
    //constructor that only required ID
    public CreditsEarned(int creditsEarnedID)
    {
        this.creditsEarnedID = creditsEarnedID;
    }

    //setters for validity
    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public void setValidityComment(String validityComment) {
        this.validityComment = validityComment;
    }

    //methods to get all attributes
    public int getCreditsEarnedID() 
    {
        return creditsEarnedID;
    }

    public float getCreditsEarned()
    {
        return creditsEarned;
    }

    public String getCreditSource()
    {
        return creditSource;
    }

    public int getPsuID()
    {
        return psuID;
    }

    public boolean getValidity()
    {
        return validity;
    }

    public String getValidityComment()
    {
        return validityComment;
    }   
}
