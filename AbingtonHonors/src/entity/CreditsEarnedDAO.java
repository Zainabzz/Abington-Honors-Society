/* CREDITS EARNED DAO

*/
package entity;

import core.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles database operations for CreditsEarned
 */
public class CreditsEarnedDAO implements DAO<CreditsEarned>
{
    public CreditsEarnedDAO(){}
    List<CreditsEarned> credits;

    // Get one credit record by ID
    @Override
    public Optional<CreditsEarned> get(int id)
    {
        String sql = "SELECT * FROM CreditsEarned WHERE creditsEarnedID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                CreditsEarned credit = new CreditsEarned(
                    rs.getInt("creditsEarnedID"),
                    rs.getFloat("creditsEarned"),
                    rs.getString("creditSource"),
                    rs.getInt("psuID")
                );
                
                credit.setValidity(rs.getBoolean("validity"));
                credit.setValidityComment(rs.getString("validityComment"));
                
                return Optional.of(credit);
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting credit: " + exception.toString());
        }

        return Optional.empty();
    }

    // Get all credit records
    @Override
    public List<CreditsEarned> getAll()
    {
        List<CreditsEarned> creditList = new ArrayList<>();
        String sql = "SELECT * FROM CreditsEarned";

        try
        {
            ResultSet rs = DB.getInstance().executeQuery(sql);

            while(rs.next())
            {
                CreditsEarned credit = new CreditsEarned(
                    rs.getInt("creditsEarnedID"),
                    rs.getFloat("creditsEarned"),
                    rs.getString("creditSource"),
                    rs.getInt("psuID")
                );
                
                credit.setValidity(rs.getBoolean("validity"));
                credit.setValidityComment(rs.getString("validityComment"));
                
                creditList.add(credit);
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting all credits: " + exception.toString());
        }

        return creditList;
    }

    // Insert new credit (default = pending)
    @Override
    public void insert(CreditsEarned credit)
    {
        String sql = "INSERT INTO CreditsEarned (creditsEarnedID, creditsEarned, creditSource, psuID, validity, validityComment) VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);

            stmt.setInt(1, credit.getCreditsEarnedID());
            stmt.setFloat(2, credit.getCreditsEarned());
            stmt.setString(3, credit.getCreditSource());
            stmt.setInt(4, credit.getPsuID());
            stmt.setBoolean(5, false);
            stmt.setString(6, "Pending");

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error inserting credit: " + exception.toString());
        }
    }

    // Update credit (approve/deny)
    @Override
    public void update(CreditsEarned credit)
    {
        String sql = "UPDATE CreditsEarned SET creditsEarned = ?, creditSource = ?, psuID = ?, validity = ?, validityComment = ? WHERE creditsEarnedID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);

            stmt.setFloat(1, credit.getCreditsEarned());
            stmt.setString(2, credit.getCreditSource());
            stmt.setInt(3, credit.getPsuID());
            stmt.setBoolean(4, credit.getValidity());
            stmt.setString(5, credit.getValidityComment());
            stmt.setInt(6, credit.getCreditsEarnedID());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error updating credit: " + exception.toString());
        }
    }

    // Delete a credit record
    @Override
    public void delete(CreditsEarned credit)
    {
        String sql = "DELETE FROM CreditsEarned WHERE creditsEarnedID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, credit.getCreditsEarnedID());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error deleting credit: " + exception.toString());
        }
    }

    // Get column names
    @Override
    public List<String> getColumnNames()
    {
        List<String> columnNames = new ArrayList<>();
        String sql = "SELECT * FROM CreditsEarned";

        try
        {
            ResultSet rs = DB.getInstance().executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            for(int i = 1; i <= metaData.getColumnCount(); i++)
            {
                columnNames.add(metaData.getColumnName(i));
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting column names: " + exception.toString());
        }

        return columnNames;
    }

    // Get total APPROVED credits for a student
    public float getApprovedCredits(int psuID)
    {
        float total = 0;
        String sql = "SELECT SUM(creditsEarned) AS total FROM CreditsEarned WHERE psuID = ? AND validity = true";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, psuID);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                total = rs.getFloat("total");
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error calculating credits: " + exception.toString());
        }

        return total;
    }
}
