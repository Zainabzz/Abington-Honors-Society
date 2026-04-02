
package entity;

/**
 * @author aramc
 */
public class Resources
{
    private int fileID;
    private String fileName;
    private String fileData;
    private int viewer;
    
    //constructor requires all attribtes at creation
    public Resources(int fileID, String fileName, String fileData, int viewer)
    {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileData = fileData;
        this.viewer = viewer;
    } 
    
    //constructor for only ID
    public Resources(int fileID)
    {
        this.fileID = fileID;
    } 
    
    //methods to get all attributes
    public int getFileID()
    {
        return fileID;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public String getFileData()
    {
        return fileData;
    }

    public int getViewer()
    {
        return viewer;
    }  
}
