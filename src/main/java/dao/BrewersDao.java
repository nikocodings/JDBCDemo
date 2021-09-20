package dao;

import model.Beer;
import model.Brewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrewersDao {

    public Brewer getBrewerById(int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("SELECT * FROM Brewers WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Brewer brewer = new Brewer();
        if (resultSet.next()) {

            brewer.setId(resultSet.getInt("id"));
            brewer.setName(resultSet.getString("name"));
            brewer.setAddress(resultSet.getString("address"));
            brewer.setZipCode(resultSet.getInt("zipCode"));
            brewer.setCity(resultSet.getString("city"));
            brewer.setTurnover(resultSet.getDouble("turnover"));

        }
        return brewer;
    }
    public List<Brewer> getAllBrewers() throws SQLException {
        Statement statement = ConnectionClass.getConnection().createStatement();
        System.out.println("Connection successful");
        String select = "SELECT * FROM Brewers";
        ResultSet resultSet = statement.executeQuery(select);
        List<Brewer> brewers = new ArrayList<>();
        while (resultSet.next()){
            Brewer brewer = new Brewer();
            brewer.setId(resultSet.getInt("id"));
            brewer.setName(resultSet.getString("name"));
            brewer.setAddress(resultSet.getString("address"));
            brewer.setZipCode(resultSet.getInt("zipCode"));
            brewer.setCity(resultSet.getString("city"));
            brewer.setTurnover(resultSet.getDouble("turnover"));
            brewers.add(brewer);

        }
        return brewers;
    }
    public void createBrewer(Brewer brewer) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("INSERT INTO Brewers(Id, Name, Address, ZipCode, City, Turnover) VALUES (?,?,?,?,?,?)");
        System.out.println("Connection successful");
        preparedStatement.setInt(1,brewer.getId());
        preparedStatement.setString(2,brewer.getName());
        preparedStatement.setString(3,brewer.getAddress());
        preparedStatement.setInt(4,brewer.getZipCode());
        preparedStatement.setString(5,brewer.getCity());
        preparedStatement.setDouble(6,brewer.getTurnover());
        preparedStatement.execute();

    }
    public void updateBrewer(Brewer brewer, int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("UPDATE Brewers SET id = ?, name = ?, address = ?, zipCode = ?, city = ?, turnover = ? WHERE id = ?");
        System.out.println("Connection successful");
        preparedStatement.setInt(1,brewer.getId());
        preparedStatement.setString(2,brewer.getName());
        preparedStatement.setString(3,brewer.getAddress());
        preparedStatement.setInt(4,brewer.getZipCode());
        preparedStatement.setString(5,brewer.getCity());
        preparedStatement.setDouble(6,brewer.getTurnover());
        preparedStatement.setInt(7,id);
        preparedStatement.executeUpdate();
    }
    public void deleteBrewer(int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("DELETE FROM Brewers WHERE id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
    private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33062/nikolaas", "nikolaas", "nikolaas123");
    }
}
