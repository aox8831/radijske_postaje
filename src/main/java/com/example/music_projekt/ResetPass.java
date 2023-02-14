package com.example.music_projekt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPass implements Initializable {

    @FXML
    private Button gumb_isci_mail;

    @FXML
    private Button gumb_preveri_odg;

    @FXML
    private Hyperlink gumb_registracija;

    @FXML
    private TextField text_username_reset;

    @FXML
    private PasswordField text_novo_geslo;

    @FXML
    private TextField text_odgovor_s;

    @FXML
    private TextField text_email_s;

    @FXML
    private TextField text_vprasanje_s;

    public void isci() throws SQLException {
        Alert alert;
        String usename = text_username_reset.getText();
        if(usename==null)
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setHeaderText(null);
            alert.setContentText("Vpišite uporabniško ime!");
            alert.showAndWait();
        }
        else
        {
            String check = "SELECT * FROM get_uporabnik_by_username('"+ usename +"');";
            System.out.print(check);
            Connection connect= Baza.connectDb();
            PreparedStatement prepare = connect.prepareStatement(check);
            ResultSet result = prepare.executeQuery();
            if(result.next()) {
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    System.out.print(result.getString(i) + "\t");
                }
                System.out.println();
            }
            if(result.next()){
                text_email_s.setDisable(false);
                text_vprasanje_s.setDisable(false);
                text_odgovor_s.setDisable(false);
                gumb_preveri_odg.setDisable(false);
                text_username_reset.setDisable(true);
                gumb_isci_mail.setDisable(true);
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText(null);
                alert.setContentText("To uporabniško ime ne obstaja!");
                alert.showAndWait();
            }
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
