package org.example;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentController {
    private static String url = "jdbc:mysql://192.168.1.15:3306/testdb1";
    private static String username = "testuser";
    private static String password = "root";
    private static Map<Integer, Student> studentMap = new ConcurrentHashMap<Integer, Student>();
    private static ResultSet resultSet;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    private StudentController() {
    }

    private static void mapPut(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(Integer.parseInt(resultSet.getString("ID")));
                student.setFirst_Name(resultSet.getString("First_Name"));
                student.setLast_Name(resultSet.getString("Last_Name"));
                student.setAddress(resultSet.getString("Address"));
                student.setId(Integer.parseInt(resultSet.getString("Group_ID")));
                studentMap.put(Integer.parseInt(resultSet.getString("ID")), student);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map getAll() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection1 = DriverManager.getConnection(url,username, password);
            statement = connection1.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Student; ");
            mapPut(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }finally {
            try {
                resultSet.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return studentMap;
        }}


    public static void addStudent(Student student) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection1 = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO testdb1.Student (First_Name, Last_Name, Address, Group_ID) VALUES (?, ?, ?, ?)";
            preparedStatement = connection1.prepareStatement(query);
            // preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(1, student.getFirst_Name());
            preparedStatement.setString(2, student.getLast_Name());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setInt(4, student.getGroup_ID());
            preparedStatement.execute();
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

   public static void delete(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

       try {
           Connection connection1 =DriverManager.getConnection(url, username, password);
           String query = " DELETE FROM `testdb1`.`Student` WHERE `id`=?";
           preparedStatement = connection1.prepareStatement(query);
           preparedStatement.setInt(1, id);
           preparedStatement.execute();
       } catch (
               SQLException e) {
           throw new IllegalStateException("Cannot connect the database!", e);
       }
   }

    public static void update(int id, String First_Name, String Last_Name, String Address, int Group_ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection1 = DriverManager.getConnection(url,username, password);
            String query = " UPDATE `testdb1`.`Student` SET `First_Name`=?, `Last_Name`=?, `Address`=?, `Group_ID`=? WHERE `id`=?;";
            preparedStatement = connection1.prepareStatement(query);
            preparedStatement.setString(1, First_Name);
            preparedStatement.setString(2, Last_Name);
            preparedStatement.setString(3, Address);
            preparedStatement.setInt(4, Group_ID);
            preparedStatement.setInt(5, id);

            preparedStatement.execute();
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static Map search(int ID) {
        studentMap = null;
        studentMap = new ConcurrentHashMap<Integer, Student>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection1 = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM `testdb1`.`Student` where id = ?;";
            preparedStatement = connection1.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            mapPut(resultSet);
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return studentMap;
    }
}
