package dao;

import model.Brewer;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao {

    public Category getCategoryById(int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("SELECT * FROM Categories WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Category category = new Category();
        if (resultSet.next()) {
            category.setId(resultSet.getInt("id"));
            category.setCategory(resultSet.getString("category"));
        }
        return category;
    }
    public List<Category> getAllCategories() throws SQLException {
        Statement statement = ConnectionClass.getConnection().createStatement();
        System.out.println("Connection successful");
        String select = "SELECT * FROM Categories";
        ResultSet resultSet = statement.executeQuery(select);
        List<Category> categories = new ArrayList<>();
        while (resultSet.next()){
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setCategory(resultSet.getString("category"));
            categories.add(category);

        }
        return categories;

    }
    public void createCategory(Category category) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("INSERT INTO Categories(Id, Category) VALUES (?,?)");
        System.out.println("Connection successful");
        preparedStatement.setInt(1,category.getId());
        preparedStatement.setString(2,category.getCategory());
        preparedStatement.execute();

    }
    public void updateCategory(Category category, int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("UPDATE Categories SET id = ?, Category = ? WHERE id = ?");
        System.out.println("Connection successful");
        preparedStatement.setInt(1,category.getId());
        preparedStatement.setString(2,category.getCategory());
        preparedStatement.setInt(3,id);
        preparedStatement.executeUpdate();
    }
    public void deleteCategory(int id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionClass.getConnection().prepareStatement("DELETE FROM Categories WHERE id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33062/nikolaas", "nikolaas", "nikolaas123");

    }
}
