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
 * @author AnthonyW 
 */
public class AdminDAO implements DAO<Admin>
{
    public AdminDAO(){}
    List<Admin> admin;

    @Override
    public Optional<Admin> get(int id)
    {
        // gets one admin by ID
        String sql = "SELECT * FROM Admin WHERE adminID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // if found, build Admin object
            if(rs.next())
            {
                Admin foundAdmin = new Admin(
                    rs.getInt("adminID"),
                    rs.getString("adminPassword"),
                    rs.getString("adminName")
                );

                return Optional.of(foundAdmin);
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting admin: " + exception.toString());
        }

        return Optional.empty();
    }

    @Override
    public List<Admin> getAll()
    {
        // gets all admins
        List<Admin> adminList = new ArrayList<>();
        String sql = "SELECT * FROM Admin";

        try
        {
            ResultSet rs = DB.getInstance().executeQuery(sql);

            // loop through rows
            while(rs.next())
            {
                adminList.add(new Admin(
                    rs.getInt("adminID"),
                    rs.getString("adminPassword"),
                    rs.getString("adminName")
                ));
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting all admins: " + exception.toString());
        }

        return adminList;
    }

    @Override
    public void insert(Admin admin)
    {
        // inserts new admin
        String sql = "INSERT INTO Admin (adminID, adminPassword, adminName) VALUES (?, ?, ?)";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, admin.getAdminID());
            stmt.setString(2, admin.getAdminPassword());
            stmt.setString(3, admin.getAdminName());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error inserting admin: " + exception.toString());
        }
    }

    @Override
    public void update(Admin admin)
    {
        // updates admin by ID
        String sql = "UPDATE Admin SET adminPassword = ?, adminName = ? WHERE adminID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setString(1, admin.getAdminPassword());
            stmt.setString(2, admin.getAdminName());
            stmt.setInt(3, admin.getAdminID());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error updating admin: " + exception.toString());
        }
    }

    @Override
    public void delete(Admin admin)
    {
        // deletes admin by ID
        String sql = "DELETE FROM Admin WHERE adminID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, admin.getAdminID());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error deleting admin: " + exception.toString());
        }
    }

    @Override
    public List<String> getColumnNames()
    {
        // gets column names
        List<String> columnNames = new ArrayList<>();
        String sql = "SELECT * FROM Admin";

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
}
