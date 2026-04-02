package entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author 
 */
public class ResourcesDAO implements DAO<Resources>
{
    public ResourcesDAO(){}
    List<Resources> resources;
    
    /**
     * gets a resource entity as a resource object
     * @param id
     * @return 
     */
    @Override
    public Optional<Resources> get(int id)
    {
        //gets the db nd initializes the resultset
        DB db = DB.getInstance();
        ResultSet result = null;
        //try block to catch SQL exceptions
        try
        {
            //creates the required SQL query and generates a prepared statement
            String sql = "SELECT * FROM Resources WHERE fileID = ?";
            PreparedStatement statement = db.getPreparedStatement(sql);
            
            //sets the id in the preparedstatement and executes
            statement.setInt(1, id);
            result = statement.executeQuery();
            
            //gets the data from the result
            Resources resource = null;
            while(result.next())
            {
                resource = new Resources(result.getInt("fileID"), result.getString("fileName"),
                        result.getBytes("fileData"), result.getInt("viewer"));
            }
            //returns the data using ofNullable to handle the case where no data is returned by the query
            return Optional.ofNullable(resource);
        }
        //catches any exceptions
        catch(SQLException exception)
        {
            System.err.println("Error getting resource: " + exception.toString());
            return null;
        }
    }
    
    /**
     * gets all resources as a arraylist
     * @return 
     */
    @Override
    public List<Resources> getAll()
    {
        //gets the db nd initializes the resultset
        DB db = DB.getInstance();
        ResultSet result = null;
        
        //initializes the arraylist of all resources
        resources = new ArrayList<>();
        
        //try block to catch SQL exceptions
        try
        {
            //creates the SQL query and executes
            String sql = "SELECT * FROM Resources";
            result = db.executeQuery(sql);
            
            //gets the data from the result
            Resources resource = null;
            while(result.next())
            {
                resource = new Resources(result.getInt("fileID"), result.getString("fileName"),
                        result.getBytes("fileData"), result.getInt("viewer"));
                resources.add(resource);
            }
            //returns the arraylist of resources
            return resources;
        }
        //catches any exceptions
        catch(SQLException exception)
        {
            System.err.println("Error getting resource: " + exception.toString());
            return null;
        }
    }
    
    /**
     * inserts a new resource into the table
     * @param resources 
     */
    @Override
    public void insert(Resources resources)
    {
        //gets the db
        DB db = DB.getInstance();
        
        //try block to catch SQL exceptions
        try
        {
            //generates the sql query and prepared statement
            String sql = "INSERT INTO Resources(fileID, fileName, fileData, viewer) Values (?, ?, ?, ?)";
            PreparedStatement statement = db.getPreparedStatement(sql);

            //sets values in the statement
            statement.setInt(1, resources.getFileID());
            statement.setString(2, resources.getFileName());
            statement.setBytes(3, resources.getFileData());
            statement.setInt(4, resources.getViewer());

            //inserts the row
            statement.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error inserting resource: " + exception.toString());
        }
        
        
    }
    
    /**
     * updates an existing resource in the db 
     * @param resources 
     */
    @Override
    public void update(Resources resources)
    {
        //gets the db
        DB db = DB.getInstance();
        
        //try block to catch SQL exceptions
        try
        {
            //generates the sql query and prepared statement
            String sql = "UPDATE Resources SET fileName=?, fileData=?, viewer=? WHERE fileID=?";
            PreparedStatement statement = db.getPreparedStatement(sql);
            
            //sets values in the statement
            statement.setString(1, resources.getFileName());
            statement.setBytes(2, resources.getFileData());
            statement.setInt(3, resources.getViewer());
            statement.setInt(4, resources.getFileID());
            
            //updates the row
            statement.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error updating resource: " + exception.toString());
        }
    }
    
    /**
     * deletes a resource from the db
     * @param resources 
     */
    @Override
    public void delete(Resources resources)
    {
        //gets the db
        DB db = DB.getInstance();
        
        //try block to catch SQL exceptions
        try
        {
        //generates the sql query and prepared statement
        String sql = "DELETE FROM Resources WHERE fileID=?";
        PreparedStatement statement = db.getPreparedStatement(sql);
        
        //sets the values in the statement and deletes
        statement.setInt(1, resources.getFileID());
        statement.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error deleting resource: " + exception.toString());
        }
    }
    
    /**
     * get all column names in a arraylist
     * @return 
     */
    @Override
    public List<String> getColumnNames()
    {
        //gets the db nd initializes the resultset
        DB db = DB.getInstance();
        ResultSet result = null;
        
        //initializes headers arraylist
        List<String> headers = new ArrayList<>();
        
        //try block to catch SQL exceptions
        try
        {
            //get column headers
            String sql = "SELECT * FROM Resources WHERE fileID=-1";
            result = db.executeQuery(sql);
            ResultSetMetaData resultMetaData = result.getMetaData();
            
            //add headers to a arraylist
            int columnNum = resultMetaData.getColumnCount();
            for(int i = 1; i <= columnNum; i++)
            {
                headers.add(resultMetaData.getColumnLabel(i));
            }
            return headers;
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting columns: " + exception.toString());
            return null;
        }
    }
}
