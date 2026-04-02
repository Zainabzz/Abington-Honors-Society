package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.flywaydb.core.Flyway;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * @author aramc
 */
public class DB
{
    private static final String DB_URL = "jdbc:derby:HonorsDB;create=true;user=HonorsDB;password=psuAbington";
    private final Connection mConnection;
    private static final String MIGRATION_DIR = "db/migrations";
    private static DB instance = null;
    
    //initializer migrates the DB and sets the connection
    private DB() throws SQLException
    {
        migrateDB();
        mConnection = DriverManager.getConnection(DB_URL);
        
    }
    
    //migrates the DB
    private void migrateDB()
    {
        Flyway flyway = Flyway.configure()
                //set the database
                .dataSource(DB_URL, "HonorsDB", "psuAbington")
                //set the location of the migration director
                .locations(MIGRATION_DIR)
                .load();
        //migrates the DB
        flyway.migrate();
    }
    
    //returns the instance of the DB
    public static DB getInstance()
    {
        if(instance == null)
        {
            try
            {
                instance = new DB();
            }
            catch(SQLException exception)
            {
                System.err.println("Database Error: " + exception.toString());
                System.exit(1);
            }
        }   
        return instance;
    }
    
    /** executes a SQL command to insert,delete,or update 
     * @param sql
     * @return
     * @throws SQLException 
     */
    public int executeUpdate(String sql) throws SQLException
    {
        return mConnection.createStatement().executeUpdate(sql);
    }
    
    /** executes a query to the DB
     * @param sql
     * @return
     * @throws SQLException 
     */
    public ResultSet executeQuery(String sql) throws SQLException
    {
        return mConnection.createStatement().executeQuery(sql);
    }
    
    /** gets a prepared statement
     * @param sql
     * @return
     * @throws SQLException 
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException
    {
        return mConnection.prepareStatement(sql);
    }
}