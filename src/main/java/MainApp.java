import dao.BeersDao;
import dao.BrewersDao;
import dao.CategoriesDao;
import model.Beer;
import model.Brewer;
import model.Category;

import java.sql.*;

public class MainApp {
    public static void main(String[] args) {


        try {

            BeersDao beersDao = new BeersDao();
            Beer beer = new Beer(5000, "Nikolaas triple",0,0,5.5,100,12);
            beersDao.createBeer(beer);
            beer.setName("Nikolaas quadruple");
            beersDao.updateBeer(beer,5000);
            beersDao.getAllBeers().forEach(System.out::println);
            beersDao.deleteBeer(5000);

            BrewersDao brewersDao = new BrewersDao();
            Brewer brewer = new Brewer(5000,"Nikolaas brewer","hollywoodlane",12345,"LA", 1000000);
            brewersDao.createBrewer(brewer);
            brewer.setTurnover(5000000);
            brewersDao.updateBrewer(brewer,5000);
            brewersDao.getAllBrewers().forEach(System.out::println);
            brewersDao.deleteBrewer(5000);

            CategoriesDao categoriesDao = new CategoriesDao();
            Category category = new Category(100, "Spicy fruity");
            categoriesDao.createCategory(category);
            category.setCategory("frisky beerbeer");
            categoriesDao.updateCategory(category,100);
            categoriesDao.getAllCategories().forEach(System.out::println);
            categoriesDao.deleteCategory(100);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
