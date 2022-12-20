package it.step.DB;

import it.step.Model.Gender;
import it.step.Model.Person;
import it.step.Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbUser {


        public Connection getConnection() throws SQLException {
            String URL = "jdbc:mysql://localhost:3306/emp_manager";
            String user = "root";
            String password = "1984";
            return DriverManager.getConnection(URL, user, password);
        }

//        public int getCount() {
//            int id = 0;
//            String sql = "SELECT id, name, surname, gender, birthdate from employee order by id asc";
//
//            try(Connection connection = getConnection();
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(sql)) {
//                while(resultSet.next()) {
//                    id++;
//                }
//
//            } catch (SQLException ex) {
//                System.out.println("Nu am putut face selectul!");
//            }


//            return id;
//        }

        public List<User> read() {
            try {
                List<User> users = new ArrayList<>();
                String sql = "select id, firstName, lastName, username, password, active from user where id > ? order by id asc";

                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, 0);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    boolean active = resultSet.getBoolean("active");

                    users.add(new User(firstName, lastName, username, password, active));
                }
                preparedStatement.close();
                connection.close();
                return users;
            } catch (SQLException ex) {
                System.out.printf("Error. Could not read employees. Reason: %s ", ex.getMessage());
                return new ArrayList<>();
            }
        }

        public void create(User user) {
            try {
                String sql = "INSERT into user(firstName, lastName, username, password, active)  values(?, ?, ?,?, ?)";
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getUsername());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setBoolean(5, user.getActive());
                int rows = preparedStatement.executeUpdate();
                String message = rows == 0 ? "ERROR: Numar de rinduri neasteptat" : " Informatia  sa adaugat cu succes!";
                System.out.println(message);
            }catch (SQLException ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
        }

//        public void updatePerson(Person updatePerson) {
//            try {
//                String sql = "UPDATE employee SET name = ?, surname = ?, gender = ?, birthdate = ? where id = ?";
//                Connection connection = getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setString(1, updatePerson.getName());
//                preparedStatement.setString(2, updatePerson.getSurname());
//                preparedStatement.setString(3, updatePerson.getGender().toString());
//                preparedStatement.setDate(4, Date.valueOf(updatePerson.getBirthdate()));
//                preparedStatement.setInt(5, updatePerson.getId());
//                int rows = preparedStatement.executeUpdate();
//                String message = rows == 0 ? "Numar de rinduri neasteptat" : " Informatia sa updatat cu succes!";
//                System.out.println(message);
//            }catch (SQLException ex) {
//                System.out.println("ERROR: " + ex.getMessage());
//            }
//        }

//        public void delete(Person modifiedPerson) {
//            try {
//                String sql = "DELETE from employee where id  = ?";
//                Connection connection = getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setInt(1, modifiedPerson.getId());
//                int rows = preparedStatement.executeUpdate();
//                String message = rows == 0 ? " Numar de rinduri neasteptat " : " Informatia sa sters cu succes ";
//                System.out.println(message);
//            }catch(SQLException ex) {
//                System.out.println("ERROR: " + ex.getMessage());
//            }
//        }

}
