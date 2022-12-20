package it.step.DB;

import it.step.Model.Gender;
import it.step.Model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbEmployee {

    public Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/emp_manager";
        String user = "root";
        String password = "1984";
        return DriverManager.getConnection(URL, user, password);
    }

    public int getCount() {
        int id = 0;
        String sql = "SELECT id, name, surname, gender, birthdate from employee order by id asc";

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()) {
                id++;
            }

        } catch (SQLException ex) {
            System.out.println("Nu am putut face selectul!");
        }


        return id;
    }

    public List<Person> read() {
        try {
            List<Person> employees = new ArrayList<>();
            String sql = "select id, name, surname,gender,birthdate from employee where id > ? order by id asc";

            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String gender = resultSet.getString("gender");
                LocalDate birthdate = LocalDate.parse(resultSet.getString("birthdate"));
                employees.add(new Person(id, name, surname, Gender.getGender(gender), birthdate));
            }
            preparedStatement.close();
            connection.close();
            return employees;
        } catch (SQLException ex) {
            System.out.printf("Error. Could not read employees. Reason: %s ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public void create(Person emp) {
        try {
            String sql = "INSERT into employee(name, surname, gender, birthdate)  values(?, ?, ?, ?)";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getSurname());
            preparedStatement.setString(3, emp.getGender().toString());
            preparedStatement.setDate(4, Date.valueOf(emp.getBirthdate()));
            int rows = preparedStatement.executeUpdate();
            String message = rows == 0 ? "ERROR: Numar de rinduri neasteptat" : " Informatia  sa adaugat cu succes!";
            System.out.println(message);
        }catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void updatePerson(Person updatePerson) {
        try {
            String sql = "UPDATE employee SET name = ?, surname = ?, gender = ?, birthdate = ? where id = ?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setString(2, updatePerson.getSurname());
            preparedStatement.setString(3, updatePerson.getGender().toString());
            preparedStatement.setDate(4, Date.valueOf(updatePerson.getBirthdate()));
            preparedStatement.setInt(5, updatePerson.getId());
            int rows = preparedStatement.executeUpdate();
            String message = rows == 0 ? "Numar de rinduri neasteptat" : " Informatia sa updatat cu succes!";
            System.out.println(message);
        }catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void delete(Person modifiedPerson) {
        try {
            String sql = "DELETE from employee where id  = ?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, modifiedPerson.getId());
            int rows = preparedStatement.executeUpdate();
            String message = rows == 0 ? " Numar de rinduri neasteptat " : " Informatia sa sters cu succes ";
            System.out.println(message);
        }catch(SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}




































