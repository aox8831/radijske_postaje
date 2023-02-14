package com.example.music_projekt;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class Baza {

    public static Connection connectDb(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://ep-dawn-water-317665.eu-central-1.aws.neon.tech:5432/radijske_postaje", "aox8831", "TMJFVg8RQ1BG");
            return connection;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static void SignUp(String eposta, String username, String geslo, String vprasanje, String odgovor){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement check = null;
        ResultSet resultSet = null;
        Uporabnik user;

        try {

            connection = DriverManager.getConnection("jdbc:postgresql://ep-dawn-water-317665.eu-central-1.aws.neon.tech:5432/radijske_postaje","aox8831","TMJFVg8RQ1BG");

            check = connection.prepareStatement("SELECT * FROM get_uporabnik_by_username(?);");
            check.setString(1,username);
            resultSet = check.executeQuery();

            if (resultSet.isBeforeFirst()) { //Če uporabnik že obstaja
                System.out.println("Uporabnik že obstaja.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Ne moraš uporabiti tega uporabniškega gesla, ker že obstaja.");
                alert.show();
            }
            else {
                psInsert = connection.prepareStatement("SELECT vnesi_uporabnika(?,?,?,?,?);");
                psInsert.setString(1,username);
                psInsert.setString(2,eposta);
                psInsert.setString(3,geslo);
                psInsert.setString(4,vprasanje);
                psInsert.setString(5,odgovor);

                psInsert.execute();
                user = new Uporabnik(username, eposta);
                Main.changeScene("homepage.fxml",1100,600);
            }
        } catch (SQLException | IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if (resultSet != null)
            {
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (check != null)
            {
                try {
                    check.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null)
            {
                try {
                    psInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

    public static void LogIn(ActionEvent event, String username, String geslo)
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Uporabnik user;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ep-dawn-water-317665.eu-central-1.aws.neon.tech:5432/radijske_postaje","aox8831","TMJFVg8RQ1BG");
            stmt = connection.prepareStatement("SELECT * FROM get_uporabnik_by_username(?);");
            stmt.setString(1,username);
            resultSet = stmt.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                System.out.println("User not found!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Vnešeni podatki so napačni!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String pridobljeno_geslo = resultSet.getString("geslo");
                    //Še pridobiš vse ostale parametre...
                    //..
                    if (pridobljeno_geslo.equals(geslo)) {
                        String uporabnik = resultSet.getString("uporabnisko_ime");
                        String eposta = resultSet.getString("eposta");
                        user = new Uporabnik(uporabnik,eposta);
                        Main.changeScene("homepage.fxml",1100,600);
                        //Main.changeScene("dodaj-spremeni.fxml",377,684);
                    } else {
                        System.out.println("Geslo se ne ujema!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Vnešeni podatki so napačni!");
                        alert.show();
                    }
                }
            }
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
        finally {
            if (resultSet != null)
            {
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (stmt != null)
            {
                try {
                    stmt.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

    public static void radioDodaj(String ime, String frekvenca, String kraj)
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ep-dawn-water-317665.eu-central-1.aws.neon.tech:5432/radijske_postaje","aox8831","TMJFVg8RQ1BG");
            stmt = connection.prepareStatement("SELECT vnesi_radio(?,?,?);");
            stmt.setString(1, ime);
            stmt.setString(2, frekvenca);
            stmt.setString(3, kraj);
            resultSet = stmt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultSet != null)
            {
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (stmt != null)
            {
                try {
                    stmt.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

    public static void zaposleniDodaj(String ime, String priimek, String eposta, String polozaj, String radio)
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ep-dawn-water-317665.eu-central-1.aws.neon.tech:5432/radijske_postaje","aox8831","TMJFVg8RQ1BG");
            stmt = connection.prepareStatement("SELECT vnesi_zaposleni(?,?,?,?,?);");
            stmt.setString(1, ime);
            stmt.setString(2, priimek);
            stmt.setString(3, eposta);
            stmt.setString(4, polozaj);
            stmt.setString(5, radio);
            resultSet = stmt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (resultSet != null)
            {
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (stmt != null)
            {
                try {
                    stmt.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }



}
