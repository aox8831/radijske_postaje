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

public class Login implements Initializable {
    public Login(){

    }
    @FXML
    private TextField text_username;
    @FXML
    private PasswordField text_geslo;
    @FXML
    private Button gumb_vpisi_se;

    @FXML
    private Hyperlink gumb_pozabljeno_geslo;

    @FXML
    private Hyperlink gumb_registracija;
    private String enkriptanogeslo=null;
    private boolean polno = false;

    public void login_click(ActionEvent event) throws NoSuchAlgorithmException {

        if(text_username.getText().isEmpty())
        {
            polno = false;
        }
        else
        {
            polno = true;
        }

        if(text_geslo.getText().isEmpty())
        {
            polno = false;
        }
        else
        {
            polno = true;
        }
        if(polno == true)
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(text_geslo.getText().getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            enkriptanogeslo = s.toString();
            getData.username=text_username.getText();
            Baza.LogIn(event,text_username.getText(),enkriptanogeslo);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("V polja vnesite vse podatke!");
            alert.show();
        }
    }

    public void pozabljeno() throws IOException {
        Main.changeScene("reset_pass.fxml",700,500);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gumb_registracija.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.changeScene("registration.fxml",700,500);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
