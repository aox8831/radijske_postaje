package com.example.music_projekt;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Homepage  implements Initializable {

    @FXML
    private AnchorPane akcija_r;

    @FXML
    private TextField email_z;

    @FXML
    private Button gumb_dodaj_z;

    @FXML
    private Button gumb_izbrisi_z;

    @FXML
    private Button izpis_btn;

    @FXML
    private Button gumb_pocisti_z;

    @FXML
    private Button gumb_posodobi_z;

    @FXML
    private TextField ime_z;

    @FXML
    private TextField id_z;

    @FXML
    private TextField iskanje_z;

    @FXML
    private ComboBox<String> polozaj_z;

    @FXML
    private TextField priimek_z;

    @FXML
    private ComboBox<String> radio_z;

    @FXML
    private TableColumn<zaposleniData, String> tab_email_z;
    @FXML
    private TableColumn<zaposleniData, Integer> tab_id_z;

    @FXML
    private TableColumn<zaposleniData, String> tab_ime_z;

    @FXML
    private TableColumn<zaposleniData, String> tab_polozaj_z;

    @FXML
    private TableColumn<zaposleniData, String> tab_priimek_z;

    @FXML
    private TableColumn<zaposleniData, String> tab_radio_z;

    @FXML
    private TableView<zaposleniData> table_view_z;

    @FXML
    private Button zaposleni_btn;

    @FXML
    private AnchorPane zaposleni_tab;


    @FXML
    private Button dodaj_r;

    @FXML
    private TextField frekvenca_r;

    @FXML
    private TextField id_r;

    @FXML
    private TextField ime_r;

    @FXML
    private TextField isci_r;

    @FXML
    private Button izbrisi_r;

    @FXML
    private ComboBox<String> kraj_r;

    @FXML
    private Button logout_btn;

    @FXML
    private Button pocisti_r;

    @FXML
    private Button posodobi_r;

    @FXML
    private Button radijske_postaje_btn;


    @FXML
    private AnchorPane radijske_postaje_tab;

    @FXML
    private AnchorPane zaposleni_form;

    @FXML
    private TableColumn<radioData, Integer> tab_id_r;

    @FXML
    private TableColumn<radioData, String> tab_frekvenca_r;

    @FXML
    private TableColumn<radioData, String> tab_ime_r;

    @FXML
    private TableColumn<radioData, String> tab_kraj_r;

    @FXML
    private TableColumn<radioData, Integer> tab_zaposleni_r;

    @FXML
    private TableColumn<radioData, Integer> tab_zvrsti_r;

    @FXML
    private AnchorPane tabela_r;

    @FXML
    private TableView<radioData> table_view_r;

    @FXML
    private Label username;


    public void dodaj_click(ActionEvent event) throws NoSuchAlgorithmException, SQLException {

        String ime = ime_r.getText();
        String frekvenca = frekvenca_r.getText();
        String kraj = (String)kraj_r.getSelectionModel().getSelectedItem();

        Alert alert;

        if(ime==null || frekvenca==null || kraj==null)
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setHeaderText(null);
            alert.setContentText("Izpolnite vsa polja!");
            alert.showAndWait();
        }
        else
        {
            String check = "SELECT * FROM ime_radio('"+ ime +"');";
            System.out.print(check);
            Connection connect= Baza.connectDb();
            PreparedStatement prepare = connect.prepareStatement(check);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    System.out.print(result.getString(i) + "\t");
                }
                System.out.println();
            }
            if(result.next()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText(null);
                alert.setContentText("Ta radio že obstaja!");
                alert.showAndWait();
            }else {

                Baza.radioDodaj(ime, frekvenca, kraj);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacija");
                alert.setHeaderText(null);
                alert.setContentText("Radio je vnešen!");
                alert.showAndWait();
                radioPrikazi();
                pocistiRadio();
            }
        }

    }

    public void dodaj_z_click(ActionEvent event) throws NoSuchAlgorithmException, SQLException {

        String ime = ime_z.getText();
        String priimek = priimek_z.getText();
        String eposta = email_z.getText();
        String polozaj = (String)polozaj_z.getSelectionModel().getSelectedItem();
        String radio = (String)radio_z.getSelectionModel().getSelectedItem();

        Alert alert;

        if(ime==null || priimek==null || eposta==null || polozaj==null || radio==null)
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Napaka");
            alert.setHeaderText(null);
            alert.setContentText("Izpolnite vsa polja!");
            alert.showAndWait();
        }
        else
        {
            Baza.zaposleniDodaj(ime, priimek, eposta, polozaj, radio);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Zaposleni je vnešen!");
            alert.showAndWait();
            zaposleniPrikazi();
            pocistiZaposleni();
        }

    }

    public void getKraji()
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ep-dawn-water-317665.eu-central-1.aws.neon.tech:5432/radijske_postaje","aox8831","TMJFVg8RQ1BG");
            stmt = connection.prepareStatement("SELECT get_kraji();");
            resultSet = stmt.executeQuery();
            ArrayList <String[]> result = new ArrayList<String[]>();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while(resultSet.next())
            {
                String[] row = new String[columnCount];
                for (int i=0; i <columnCount ; i++)
                {
                    row[i] = resultSet.getString(i + 1);
                }
                result.add(row);
            }
            List<String> listData = new ArrayList<>();
            for(String[] data: result){
                listData.add(Arrays.toString(data).replace("[","").replace("]",""));
            }
            ObservableList list = FXCollections.observableArrayList(listData);
            kraj_r.setItems(list);
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

    public void getRadios()
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://ep-dawn-water-317665.eu-central-1.aws.neon.tech:5432/radijske_postaje","aox8831","TMJFVg8RQ1BG");
            stmt = connection.prepareStatement("SELECT get_radios();");
            resultSet = stmt.executeQuery();
            ArrayList <String[]> result = new ArrayList<String[]>();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while(resultSet.next())
            {
                String[] row = new String[columnCount];
                for (int i=0; i <columnCount ; i++)
                {
                    row[i] = resultSet.getString(i + 1);
                }
                result.add(row);
            }
            List<String> listData = new ArrayList<>();
            for(String[] data: result){
                listData.add(Arrays.toString(data).replace("[","").replace("]",""));
            }
            ObservableList list = FXCollections.observableArrayList(listData);
            radio_z.setItems(list);
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

    private String status[] = {"voditelj", "komentator", "urednik", "direktor", "tajnik", "vremenar"};
    public void getPolozaj(){
        List<String> listData = new ArrayList<>();
        for(String data: status){
            listData.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listData);
        polozaj_z.setItems(list);
    }

    public void pocistiRadio()
    {
        id_r.setText("");
        ime_r.setText("");
        frekvenca_r.setText("");
        kraj_r.getSelectionModel().clearSelection();
    }
    public void pocistiZaposleni()
    {
        id_z.setText("");
        ime_z.setText("");
        priimek_z.setText("");
        email_z.setText("");
        polozaj_z.getSelectionModel().clearSelection();
        radio_z.getSelectionModel().clearSelection();
    }

    public ObservableList<radioData> radioListData(){
        ObservableList<radioData> listData = FXCollections.observableArrayList();
        String sql= "SELECT * FROM get_radio();";
        Connection connect;
        PreparedStatement prepare;
        Statement statement;
        ResultSet result;
        connect = Baza.connectDb();

        try{
            radioData radio;
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()){
                radio = new radioData(result.getInt("id")
                        , result.getString("ime")
                        , result.getString("frekvenca")
                        , result.getString("kraj")
                        , result.getInt("st_zaposlenih")
                        , result.getInt("st_razlicnih_zvrsti"));

                listData.add(radio);
            }
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<radioData> radioDataList;
    public void radioPrikazi(){
        radioDataList = radioListData();
        tab_id_r.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_ime_r.setCellValueFactory(new PropertyValueFactory<>("ime"));
        tab_frekvenca_r.setCellValueFactory(new PropertyValueFactory<>("frekvenca"));
        tab_kraj_r.setCellValueFactory(new PropertyValueFactory<>("kraj"));
        tab_zaposleni_r.setCellValueFactory(new PropertyValueFactory<>("stZaposlenih"));
        tab_zvrsti_r.setCellValueFactory(new PropertyValueFactory<>("stZvrsti"));

        table_view_r.setItems(radioDataList);
    }

    public void logout(){
        try {
            Main.changeScene("login.fxml",700,500);
        }catch (Exception e){e.printStackTrace();}
    }

    public void radioIzbrano(){
        radioData radio = table_view_r.getSelectionModel().getSelectedItem();
        int num = table_view_r.getSelectionModel().getSelectedIndex();

        if((num-1) < -1){
            return;
        }
        id_r.setText(String.valueOf(radio.getId()));
        ime_r.setText(String.valueOf(radio.getIme()));
        frekvenca_r.setText(String.valueOf(radio.getFrekvenca()));
        kraj_r.setValue(radio.getKraj());
    }
    public void zaposleniIzbrano(){
        zaposleniData zaposlen = table_view_z.getSelectionModel().getSelectedItem();
        int num = table_view_z.getSelectionModel().getSelectedIndex();

        if((num-1) < -1){
            return;
        }
        id_z.setText(String.valueOf(zaposlen.getIdz()));
        ime_z.setText(String.valueOf(zaposlen.getImez()));
        priimek_z.setText(String.valueOf(zaposlen.getPriimekz()));
        email_z.setText(String.valueOf(zaposlen.getEpostaz()));
        polozaj_z.setValue(zaposlen.getPolozajz());
        radio_z.setValue(zaposlen.getRadioz());
    }

    public void radioUpdate(){
        Integer id = Integer.valueOf(id_r.getText());
        String rad = ime_r.getText();
        String frek = frekvenca_r.getText();
        String kr = (String)kraj_r.getSelectionModel().getSelectedItem();

        String sql= "SELECT posodobi_radio("+ id +", '"+ rad +"','"+ frek +"', '"+ kr +"');";
        Connection connect = Baza.connectDb();
        try {
            Alert alert;
            if(rad==null || frek==null || kr==null)
            {
                alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText(null);
                alert.setContentText("Izpolnite vsa polja!");
                alert.showAndWait();
            }
            else
            {
                PreparedStatement prepare = connect.prepareStatement(sql);
                prepare.execute();
                alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacija");
                alert.setHeaderText(null);
                alert.setContentText("Posodobljeno!");
                alert.showAndWait();
                radioPrikazi();
                pocistiRadio();
            }
        }catch (Exception e){e.printStackTrace();}

    }

    public void zaposleniUpdate(){
        Integer id = Integer.valueOf(id_z.getText());
        String ime = ime_z.getText();
        String priimek = priimek_z.getText();
        String eposta = email_z.getText();
        String polozaj = (String)polozaj_z.getSelectionModel().getSelectedItem();
        String radio = (String)radio_z.getSelectionModel().getSelectedItem();

        String sql= "SELECT posodobi_zaposleni("+ id +", '"+ ime +"','"+ priimek +"', '"+ eposta +"', '"+ polozaj +"', '"+ radio +"');";
        Connection connect = Baza.connectDb();
        try {
            Alert alert;
            if(ime==null || priimek==null || eposta==null || polozaj==null || radio==null)
            {
                alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Napaka");
                alert.setHeaderText(null);
                alert.setContentText("Izpolnite vsa polja!");
                alert.showAndWait();
            }
            else
            {
                PreparedStatement prepare = connect.prepareStatement(sql);
                prepare.execute();
                alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacija");
                alert.setHeaderText(null);
                alert.setContentText("Posodobljeno!");
                alert.showAndWait();
                zaposleniPrikazi();
                pocistiZaposleni();
            }
        }catch (Exception e){e.printStackTrace();}

    }

    public void radioDelete(){
        Integer id = Integer.valueOf(id_r.getText());
        String rad = ime_r.getText();
        String frek = frekvenca_r.getText();
        String kr = (String)kraj_r.getSelectionModel().getSelectedItem();

        String delete= "SELECT delete_radio("+ id +");";
        Connection connect = Baza.connectDb();
        Alert alert;
        try {
            if(rad==null || frek==null || kr==null)
            {
                alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Napaka");
                alert.setHeaderText(null);
                alert.setContentText("Najprej izpolnite vsa polja!");
                alert.showAndWait();
            }
            else
            {
                alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potrditev");
                alert.setHeaderText(null);
                alert.setContentText("Ali res želite izbrisati radio?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement stmt =connect.createStatement();
                    stmt.execute(delete);
                    alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspešno izbrisano!");
                    alert.showAndWait();
                    radioPrikazi();
                    pocistiRadio();


                }else{
                    return;
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void zaposleniDelete(){
        Integer id = Integer.valueOf(id_z.getText());
        String ime = ime_z.getText();
        String priimek = priimek_z.getText();
        String eposta = email_z.getText();
        String polozaj = (String)polozaj_z.getSelectionModel().getSelectedItem();
        String radio = (String)radio_z.getSelectionModel().getSelectedItem();

        String delete= "SELECT delete_zaposleni("+ id +");";
        Connection connect = Baza.connectDb();
        Alert alert;
        try {
            if(ime==null || priimek==null || eposta==null || polozaj==null || radio==null)
            {
                alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Napaka");
                alert.setHeaderText(null);
                alert.setContentText("Najprej izpolnite vsa polja!");
                alert.showAndWait();
            }
            else
            {
                alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potrditev");
                alert.setHeaderText(null);
                alert.setContentText("Ali res želite izbrisati zaposlenega?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement stmt =connect.createStatement();
                    stmt.execute(delete);
                    alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspešno izbrisano!");
                    alert.showAndWait();
                    zaposleniPrikazi();
                    pocistiZaposleni();


                }else{
                    return;
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void radioIsci(){
        FilteredList<radioData> filter = new FilteredList<>(radioDataList, e -> true);
        isci_r.textProperty().addListener((Observable, oldValue, newValue)->{
            filter.setPredicate(predicateRadioData ->{

                if(newValue==null && newValue.isEmpty()){
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if(predicateRadioData.getIme().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateRadioData.getFrekvenca().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateRadioData.getKraj().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateRadioData.getStZaposlenih().toString().contains(searchKey)){
                    return true;
                }else if(predicateRadioData.getStZvrsti().toString().contains(searchKey)){
                    return true;
                }else if(predicateRadioData.getId().toString().contains(searchKey)){
                    return true;
                }else return false;
            });
        });

        SortedList<radioData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(table_view_r.comparatorProperty());
        table_view_r.setItems(sortList);
    }

    public void spremeniStran(ActionEvent event){
        if(event.getSource() == radijske_postaje_btn){
            radijske_postaje_tab.setVisible(true);
            zaposleni_form.setVisible(false);
            radijske_postaje_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #5068c9, #bc59e4)");
            zaposleni_btn.setStyle("-fx-background-color:transparent");
            radioPrikazi();
        }else if(event.getSource() == zaposleni_btn){
            radijske_postaje_tab.setVisible(false);
            zaposleni_form.setVisible(true);
            zaposleni_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #5068c9, #bc59e4)");
            radijske_postaje_btn.setStyle("-fx-background-color:transparent");
            zaposleniPrikazi();
        }
    }
    public ObservableList<zaposleniData> zaposleniListData(){
        ObservableList<zaposleniData> listData = FXCollections.observableArrayList();
        String sql= "SELECT * FROM get_zaposleni();";
        Connection connect;
        PreparedStatement prepare;
        Statement statement;
        ResultSet result;
        connect = Baza.connectDb();

        try{
            zaposleniData zaposleni;
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()){
                zaposleni = new zaposleniData(result.getInt("id")
                        , result.getString("ime")
                        , result.getString("priimek")
                        , result.getString("eposta")
                        , result.getString("polozaj")
                        , result.getString("radio"));

                listData.add(zaposleni);
            }
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<zaposleniData> zaposleniDataList;
    public void zaposleniPrikazi(){
        zaposleniDataList = zaposleniListData();
        tab_id_z.setCellValueFactory(new PropertyValueFactory<>("idz"));
        tab_ime_z.setCellValueFactory(new PropertyValueFactory<>("imez"));
        tab_priimek_z.setCellValueFactory(new PropertyValueFactory<>("priimekz"));
        tab_email_z.setCellValueFactory(new PropertyValueFactory<>("epostaz"));
        tab_polozaj_z.setCellValueFactory(new PropertyValueFactory<>("polozajz"));
        tab_radio_z.setCellValueFactory(new PropertyValueFactory<>("radioz"));

        table_view_z.setItems(zaposleniDataList);
    }

    public void zaposleniIsci(){
        FilteredList<zaposleniData> filter = new FilteredList<>(zaposleniDataList, e -> true);
        iskanje_z.textProperty().addListener((Observable, oldValue, newValue)->{
            filter.setPredicate(predicateZaposleniData ->{

                if(newValue==null && newValue.isEmpty()){
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if(predicateZaposleniData.getImez().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateZaposleniData.getPriimekz().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateZaposleniData.getEpostaz().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateZaposleniData.getPolozajz().contains(searchKey)){
                    return true;
                }else if(predicateZaposleniData.getRadioz().contains(searchKey)){
                    return true;
                }else if(predicateZaposleniData.getIdz().toString().contains(searchKey)){
                    return true;
                }else return false;
            });
        });

        SortedList<zaposleniData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(table_view_z.comparatorProperty());
        table_view_z.setItems(sortList);
    }

    public void radioExcel() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("izpis");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table_view_r.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table_view_r.getColumns().get(j).getText());
        }

        for (int i = 0; i < table_view_r.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table_view_r.getColumns().size(); j++) {
                if(table_view_r.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(table_view_r.getColumns().get(j).getCellData(i).toString());
                }
                else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("radijske_postaje.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Alert alert;
        alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText(null);
        alert.setContentText("Tabela je uspešno izvožena!");
        alert.showAndWait();
    }

    public void zaposleniExcel() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("izpis");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table_view_z.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table_view_z.getColumns().get(j).getText());
        }

        for (int i = 0; i < table_view_z.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table_view_z.getColumns().size(); j++) {
                if(table_view_z.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(table_view_z.getColumns().get(j).getCellData(i).toString());
                }
                else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("zaposleni.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Alert alert;
        alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText(null);
        alert.setContentText("Tabela je uspešno izvožena!");
        alert.showAndWait();
    }

    public void navigation(){
        zaposleni_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #5068c9, #bc59e4)");
    }

    public void displayUsername(){
        username.setText(getData.username);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getKraji();
        radioPrikazi();
        radioIsci();
        zaposleniPrikazi();
        zaposleniIsci();
        getRadios();
        getPolozaj();
        navigation();
        displayUsername();

    }
}
