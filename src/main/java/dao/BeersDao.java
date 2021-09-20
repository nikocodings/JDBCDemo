package dao;

import model.Beer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeersDao {

    public Beer getBeerById(int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("SELECT * FROM Beers WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Beer beer = new Beer();
        if (resultSet.next()) {

            beer.setId(resultSet.getInt("id"));
            beer.setName(resultSet.getString("name"));
            beer.setAlcohol(resultSet.getInt("alcohol"));
            beer.setBrewerId(resultSet.getInt("BrewerId"));
            beer.setCategoryId(resultSet.getInt("CategoryId"));
            beer.setPrice(resultSet.getDouble("price"));
            beer.setStock(resultSet.getInt("stock"));

        }
        return beer;
    }
    public List<Beer> getAllBeers() throws SQLException {
        Statement statement = ConnectionClass.getConnection().createStatement();
        System.out.println("Connection successful");
        String select = "SELECT * FROM Beers";
        ResultSet resultSet = statement.executeQuery(select);
        List<Beer> beers = new ArrayList<>();
        while (resultSet.next()){
            Beer beer = new Beer();
            beer.setId(resultSet.getInt("id"));
            beer.setName(resultSet.getString("name"));
            beer.setAlcohol(resultSet.getInt("alcohol"));
            beer.setBrewerId(resultSet.getInt("BrewerId"));
            beer.setCategoryId(resultSet.getInt("CategoryId"));
            beer.setPrice(resultSet.getDouble("price"));
            beer.setStock(resultSet.getInt("stock"));
            beers.add(beer);

        }
        return beers;
    }
    //statement: executeQuery -> SELECT ; UpdateQuery -> INSERT, UPDATE, DELETE
    //preparedstatement: executeQuery -> SELECT ; execute -> INSERT, UPDATE, DELETE
    public void createBeer(Beer beer) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("INSERT INTO Beers(Id, Name, price, stock, alcohol) VALUES (?,?,?,?,?)");
        System.out.println("Connection successful");
        preparedStatement.setInt(1,beer.getId());
        preparedStatement.setString(2,beer.getName());
        preparedStatement.setDouble(3,beer.getPrice());
        preparedStatement.setInt(4,beer.getStock());
        preparedStatement.setDouble(5,beer.getAlcohol());
        preparedStatement.execute();

    }
    public void updateBeer(Beer beer, int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("UPDATE Beers SET id = ?, name = ?, price = ?, stock = ?, alcohol = ? WHERE id = ?");
        System.out.println("Connection successful");
        preparedStatement.setInt(1,beer.getId());
        preparedStatement.setString(2,beer.getName());
        preparedStatement.setDouble(3,beer.getPrice());
        preparedStatement.setInt(4,beer.getStock());
        preparedStatement.setDouble(5,beer.getAlcohol());
        preparedStatement.setInt(6,id);
        preparedStatement.executeUpdate();
    }
    public void deleteBeer(int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("DELETE FROM Beers WHERE id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33062/nikolaas", "nikolaas", "nikolaas123");
    }

}
