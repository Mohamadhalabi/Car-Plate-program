package packet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQliteAccess {
    private Connection connect = null;
    private Statement statement = null;
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("org.sqlite.JDBC");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\Yusufcan\\Desktop\\Proje\\base.db");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from base");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into  base values (?,?,?,?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "Yusuf Can Sert");
            preparedStatement.setString(3, "Mercedes");
            preparedStatement.setInt(4,123);
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("SELECT ID,Full_Name,Car_Model,Car_Plate from base");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);




            resultSet = statement.executeQuery("select * from base");
            writeMetaData(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String ID = resultSet.getString("ID");
            String user = resultSet.getString("Full_Name");
            String CarModel = resultSet.getString("Car_Model");
            String CarPlate = resultSet.getString("Car_Plate");
            System.out.println("ID:" + ID);
            System.out.println("User: " + user);
            System.out.println("CarModel: " + CarModel);
            System.out.println("CarPlate: " + CarPlate);

        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
