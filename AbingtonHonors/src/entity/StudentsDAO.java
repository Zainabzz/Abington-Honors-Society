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
public class StudentsDAO implements DAO<Students>
{
    public StudentsDAO(){}
    List<Students> students;

    @Override
    public Optional<Students> get(int id)
    {
        // gets one student by PSU ID
        String sql = "SELECT * FROM HonorStudent WHERE psuID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // if found, build student object
            if(rs.next())
            {
                Students student = new Students(
                    rs.getInt("psuID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("psuEmail"),
                    rs.getString("studentPassword"),
                    rs.getString("advisorName"),
                    rs.getString("advisorEmail")
                );

                // set remaining fields
                student.setHonorsCredits(rs.getFloat("honorsCredits"));

                return Optional.of(student);
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting student: " + exception.toString());
        }

        return Optional.empty();
    }

    @Override
    public List<Students> getAll()
    {
        // gets all students
        List<Students> studentList = new ArrayList<>();
        String sql = "SELECT * FROM HonorStudent";

        try
        {
            ResultSet rs = DB.getInstance().executeQuery(sql);

            while(rs.next())
            {
                Students student = new Students(
                    rs.getInt("psuID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("psuEmail"),
                    rs.getString("studentPassword"),
                    rs.getString("advisorName"),
                    rs.getString("advisorEmail")
                );

                student.setHonorsCredits(rs.getFloat("honorsCredits"));

                studentList.add(student);
            }
        }
        catch(SQLException exception)
        {
            System.err.println("Error getting all students: " + exception.toString());
        }

        return studentList;
    }

    @Override
    public void insert(Students student)
    {
        // inserts a student
        String sql = "INSERT INTO HonorStudent (psuID, firstName, lastName, psuEmail, studentPassword, honorsCredits, advisorName, advisorEmail) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, student.getPsuID());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getPsuEmail());
            stmt.setString(5, student.getstudentPassword());
            stmt.setFloat(6, student.getHonorsCredits());
            stmt.setString(7, student.getAdvisorName());
            stmt.setString(8, student.getAdvisorEmail());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error inserting student: " + exception.toString());
        }
    }

    @Override
    public void update(Students student)
    {
        // updates student by PSU ID
        String sql = "UPDATE HonorStudent SET firstName = ?, lastName = ?, psuEmail = ?, studentPassword = ?, honorsCredits = ?, advisorName = ?, advisorEmail = ? WHERE psuID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getPsuEmail());
            stmt.setString(4, student.getstudentPassword());
            stmt.setFloat(5, student.getHonorsCredits());
            stmt.setString(6, student.getAdvisorName());
            stmt.setString(7, student.getAdvisorEmail());
            stmt.setInt(8, student.getPsuID());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error updating student: " + exception.toString());
        }
    }
    

    public void updateCredit(int psuID, float credits)
    {
        // updates student by PSU ID
        String sql = "UPDATE HonorStudent SET honorsCredits = ? WHERE psuID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setFloat(1, credits);
            stmt.setInt(2, psuID);

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error updating student: " + exception.toString());
        }
    }
    


    @Override
    public void delete(Students student)
    {
        // deletes student by PSU ID
        String sql = "DELETE FROM HonorStudent WHERE psuID = ?";

        try
        {
            PreparedStatement stmt = DB.getInstance().getPreparedStatement(sql);
            stmt.setInt(1, student.getPsuID());

            stmt.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.err.println("Error deleting student: " + exception.toString());
        }
    }

    @Override
    public List<String> getColumnNames()
    {
        // gets column names
        List<String> columnNames = new ArrayList<>();
        String sql = "SELECT * FROM HonorStudent";

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
