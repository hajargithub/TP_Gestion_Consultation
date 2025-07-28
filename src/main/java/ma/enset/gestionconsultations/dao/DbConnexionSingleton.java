package ma.enset.gestionconsultations.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnexionSingleton {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }
/*
    Block Static block qui sera executer une seule fois lors du chargement de l'App
    dans la memoire
    */

    static{

        // URL de la base de données, utilisateur et mot de passe
        String url = "jdbc:mysql://localhost:3306/db_cabinet";
        String username = "root";
        String password = "";
        // Charger le driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Établir la connexion
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
