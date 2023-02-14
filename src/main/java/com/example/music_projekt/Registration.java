package com.example.music_projekt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class Registration implements Initializable {

    public Registration(){

    }
    @FXML
    private TextField text_username;
    @FXML
    private TextField text_email;
    @FXML
    private PasswordField text_geslo;
    @FXML
    private PasswordField text_ponovno_geslo;
    @FXML
    private Button gumb_registriraj;
    @FXML
    private Hyperlink gumb_login;
    @FXML
    private TextField vprasanje;
    @FXML
    private TextField odgovor;
    private boolean polno1 = false;
    private boolean polno2 = false;
    private boolean polno3 = false;
    private boolean polno4 = false;
    private boolean polno5 = false;
    private boolean polno6 = false;
    private String geslo;
    private String preveri;
    private String encryptpass = null;
    private String ecnryptpass1 = null;

    public void signup_click(ActionEvent event) throws NoSuchAlgorithmException {

        if(text_email.getText().isEmpty())
        {
            polno1 = false;
        }
        else {
            polno1 = true;
        }
        if(text_username.getText().isEmpty())
        {
            polno2 = false;
        }
        else {
            polno2 = true;
        }
        if(vprasanje.getText().isEmpty())
        {
            polno3=false;
        }
        else{
            polno3=true;
        }
        if(odgovor.getText().isEmpty())
        {
            polno4=false;
        }
        else{
            polno4=true;
        }
        if(text_geslo.getText().isEmpty())
        {
            polno5 = false;
        }
        else {
            geslo = text_geslo.getText();
            polno5 = true;

            /* MessageDigest instance for MD5. */
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(geslo.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptpass = s.toString();
        }
        if(text_ponovno_geslo.getText().isEmpty())
        {
            polno6 = false;
        }
        else {
            preveri = text_ponovno_geslo.getText();
            polno6 = true;
            /* MessageDigest instance for MD5. */
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(preveri.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            ecnryptpass1 = s.toString();
        }

        if(polno1 == true && polno2 == true && polno3 == true && polno4 == true && polno5 == true && polno6 == true)
        {
            if(encryptpass.equals(ecnryptpass1))
            {
                Baza.SignUp(text_email.getText(),text_username.getText(), encryptpass, vprasanje.getText(), odgovor.getText());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ERROR: Geslo se ne ujema!");
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR: Izpolnite vsa polja!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gumb_login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.changeScene("login.fxml",700,500);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
