/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.Vector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrea Pallotta
 * @version 20190503
 * description controller for the mainGUI Scene
 * 
 */
public class MainGUIController implements Initializable {
    
    @FXML
    AnchorPane AnchorPane;
    
    @FXML
    RadioButton radioButtonBuy;
    
    @FXML
    RadioButton radioButtonSell;
    
    @FXML
    Label labelBalance;
    
    @FXML 
    TableView<ObjectClass.Securities> myStockTable;
    
    @FXML
    TableColumn nameSecurityColumn;
    
    @FXML
    TableColumn amountSecurityColumn;
    
    @FXML 
    TableView<ObjectClass.Company> companyTable;
    
    @FXML
    TableColumn NameCompanyColumn;
    
    @FXML
    TableColumn ShareCompanyColumn;
    
    @FXML
    LineChart<String,Number> chart;
    
    @FXML
    JFXTextField symbolField;
    
    @FXML
    JFXTextField quantityField;
    
    @FXML
    Label priceField;
    
    @FXML
    Label costLabel;
    
    @FXML
    JFXTextArea yourInvestmentsArea;
    
    @FXML
    Button submitButton;
    
    @FXML
    Button resetButton;
    
    @FXML
    JFXTextField chatTextField;
    
    @FXML
    Button chatButton;
    
    @FXML
    JFXTextArea chatArea;
    
    @FXML
    TableView<ObjectClass.User> usersTable;
    
    @FXML
    TableColumn usernameColumn;
    
    @FXML 
    TabPane ChatPane;
    
    @FXML 
    Tab publicTab;
    
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    Vector<Integer> list = new Vector();
    String symbol;
    int quantity; 
    int securities;
    double price, costNotFormatted;
    String costFormatted;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    ToggleGroup tg;
    FXMLLoader loader;
    LoginController lc;
    boolean checkTab = true;
    boolean checkCompany = false;
    Timer timer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        symbolField.setDisable(true);
        quantityField.setDisable(true);

    }    

    /**
     * method that handles the logout button
     * @param event ActionEvent
     */
    @FXML
    public void Logout(ActionEvent event) {
        try {
                loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("login.fxml"));
                Parent parentView = loader.load();
                Scene tableViewScene = new Scene(parentView);
                

                //get the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();

            }

            catch (IOException ioe) {
                ioe.getMessage();
            }
    }
    
    /**
     * method that populates the TableView for the companies
     * @param list type ObservableList
     */
    @FXML
    public void PopulateCompanyTable(ObservableList list) {  
        NameCompanyColumn = new TableColumn<ObjectClass.Company, String>("Ticker");
        ShareCompanyColumn = new TableColumn<ObjectClass.Company, Double>("Value");
        
        NameCompanyColumn.setCellValueFactory(
                new PropertyValueFactory<ObjectClass.Company, String>("ticker"));
        
        ShareCompanyColumn.setCellValueFactory(
                new PropertyValueFactory<ObjectClass.Company, Double>("value"));
        
        NameCompanyColumn.prefWidthProperty().bind(companyTable.widthProperty().divide(2));
        ShareCompanyColumn.prefWidthProperty().bind(companyTable.widthProperty().divide(2));
        
        companyTable.setItems(list);
        companyTable.getColumns().addAll(NameCompanyColumn, ShareCompanyColumn);
        
    }
    
    /**
     * method that populates the TableView for the securities
     * @param list type ObjservableList
     */
    @FXML
    public void PopulateSecurityTable(ObservableList list) {  
        nameSecurityColumn = new TableColumn<ObjectClass.Company, String>("Ticker");
        amountSecurityColumn = new TableColumn<ObjectClass.Company, Double>("Amount");
        
        nameSecurityColumn.setCellValueFactory(
                new PropertyValueFactory<ObjectClass.Company, String>("ticker"));
        
        amountSecurityColumn.setCellValueFactory(
                new PropertyValueFactory<ObjectClass.Company, Double>("numberOfShares"));
        
        nameSecurityColumn.prefWidthProperty().bind(myStockTable.widthProperty().divide(2));
        amountSecurityColumn.prefWidthProperty().bind(myStockTable.widthProperty().divide(2));
        
        myStockTable.setItems(list);
        myStockTable.getColumns().addAll(nameSecurityColumn, amountSecurityColumn);
        
    }

   
    /**
     * method to create the chart
     */
    @FXML
    public void MakeChart() {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("login.fxml"));
        
        try {
            Parent root = loader1.load();
        } 
        
        catch (IOException ex) {
            ex.printStackTrace();
        }
        lc = loader1.getController();
        
        chart.getData().clear();
        ObjectClass.Company companyRow = companyTable.getSelectionModel().getSelectedItem();
        System.out.println(companyRow);
        
        if (companyRow != null) {
            String ticker = companyRow.getTicker();
            System.out.println(ticker); 
            xAxis.setLabel("Minutes");
            chart.setTitle("Stock Chart");

            XYChart.Series series = new XYChart.Series();
            series.setName("Stock for " + ticker);
              // ---- OFFLINE ---------
            Random rand = new Random();

            for (int i = 0; i < 10; i++) {
                list.add(rand.nextInt(10));
            }

            for (int i = 0; i < list.size(); i++) {
                series.getData().add(new XYChart.Data(i + "'", list.get(i)));
            }
            
              // -------- ONLINE ------
//            for (double d : lc.listCompanyValues) {
//                for (int i = 0; i < lc.listCompanyValues.size(); i++) {
//                    series.getData().add(new XYChart.Data(i + "'", d));
//                }
//            }

            chart.getData().add(series);
            list.clear();
            
        }
    }
    
    /**
     * method that checks what radiobutton is selected
     * @return String value for BUY or SELL
     */
    @FXML
    public String RadioButtonValue() {
        if (radioButtonBuy.isSelected()) {
            return "<BUY>";
        }
        
        else if (radioButtonSell.isSelected()) {
            return "<SELL>";
        }
        
        else {
            return "";
        }
    }

    /**
     * method that enables the textfields if the radiobutton is selected
     */
    @FXML
    public void CheckRadioButtonSelection() {
        
        tg = new ToggleGroup();
        radioButtonBuy.setToggleGroup(tg);
        radioButtonSell.setToggleGroup(tg);
        
        if (radioButtonBuy.isSelected() || radioButtonSell.isSelected()) {
            symbolField.setDisable(false);
            quantityField.setDisable(false);
 
        }
        
    }
    
    /**
     * method that makes the ticker text all uppercased
     */
    @FXML
    public void FormatTicker() {
        
            symbolField.setTextFormatter(new TextFormatter<>((change) -> {
                change.setText(change.getText().toUpperCase());
                
                return change;
            }));
            
    }
    
    /**
     * Method that iterates through the list of companies and find the price of the ticker input in the transaction
     * @return price
     */
    @FXML
    public double setPrice() {
        
        symbol = symbolField.getText().trim();
        quantity = Integer.parseInt(quantityField.getText().trim());
        
        for (ObjectClass.Company companySelected : companyTable.getItems()) {
            String ticker = companySelected.getTicker();
            if (ticker.equalsIgnoreCase(symbol)) {
                price = companySelected.getValue();
                System.out.println(price);
            }
        }
        
        return price;
        
    }
    
    /**
     * Method that return the amount of securities for a particular ticker
     * @return securities
     */
    @FXML
    public int GetQuantityOwned() {
        symbol = symbolField.getText().trim();
        quantity = Integer.parseInt(quantityField.getText().trim());
        
        for (ObjectClass.Securities securitySelected : myStockTable.getItems()) {
            String ticker = securitySelected.getTicker();
            if (ticker.equalsIgnoreCase(symbol)) {
                securities = securitySelected.getNumberOfShares();
                System.out.println(securities);
            }
        }
        
        return securities;
        
    }

    /**
     * Methods that uploads the objects in the tableView according to the amount of shares of the transaction
     * @param ticker type String
     * @param sec type integer
     */
    @FXML
    public void UpdateMyStockTable(String ticker, int sec) {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("login.fxml"));
        
        try {
            Parent root = loader1.load();
        } 
        
        catch (IOException ex) {
            ex.printStackTrace();
        }
        lc = loader1.getController();
        lc.securityInfo.add(new ObjectClass.Securities(ticker, sec));
        
        if (RadioButtonValue().equals("<BUY>")) {
            
            if (lc.securityInfo.isEmpty()) {
                lc.securityInfo.add(new ObjectClass.Securities(ticker, sec));
            }
            
            else {
                
                for (Iterator<ObjectClass.Securities> it = lc.securityInfo.iterator(); it.hasNext();) {
                    
                    ObjectClass.Securities objSec = it.next();
                    String tickerInList = objSec.getTicker();
                    
                    if (ticker.equalsIgnoreCase(tickerInList)) {
                        
                        int securityInTable = objSec.getNumberOfShares();

                        if (securityInTable != 0) {
                            
                            int newSecurity = securityInTable + sec;
                            lc.securityInfo.remove(objSec);
                            lc.securityInfo.add(new ObjectClass.Securities(ticker, newSecurity));
                        }

                        else {
                            
                            lc.securityInfo.remove(objSec);
                            lc.securityInfo.add(new ObjectClass.Securities(ticker, sec));
                        }
                        
                    }
                }
            }
        }
        
        else if (RadioButtonValue().equals("<SELL>")) {
            
            if (lc.securityInfo.isEmpty()) {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("You do not have any security");
                alert.setContentText("You can't sell " + quantity + " shares from " + symbol);
                alert.showAndWait();
            }
            
            else {
               
                for (Iterator<ObjectClass.Securities> it = lc.securityInfo.iterator(); it.hasNext();) {
                    
                    ObjectClass.Securities objSec = it.next();
                    String tickerInList = objSec.getTicker();
                    
                    if (ticker.equalsIgnoreCase(tickerInList)) {
                        int securityInTable = objSec.getNumberOfShares();

                        if (securityInTable == sec) {
                            
                            lc.securityInfo.remove(objSec);
                        }

                        else {
                            
                            lc.securityInfo.remove(objSec);
                            lc.securityInfo.add(new ObjectClass.Securities(ticker, sec));
                        }
                        
                    }
                }
            }
        }
        
        myStockTable.setItems(lc.securityInfo);
        
    }
    
    @FXML 
    public void resetFields() {
        
    }

    
    /**
     * Event for the button that handles the transactions 
     * @param event ActionEvent
     */
    @FXML
    public void TransactionButton(ActionEvent event) {

        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("login.fxml"));
        
        try {
            Parent root = loader1.load();
        } 
        
        catch (IOException ex) {
            ex.printStackTrace();
        }
        lc = loader1.getController();
        
        // if the radiobutton BUY is selected
        if (radioButtonBuy.isSelected()) {
            
            try {  
                
                symbol = symbolField.getText().trim();
                quantity = Integer.parseInt(quantityField.getText().trim());
                price = setPrice();
                priceField.setText("$" + price);
                costNotFormatted = price * quantity;
                costFormatted = String.format("%,.2f", costNotFormatted);
                costLabel.setText("$" + costFormatted);
                
                if (costNotFormatted <= lc.balance) {
                    
                    UpdateMyStockTable(symbol, quantity/2);
                    String time = sdf.format(new Date());
                    yourInvestmentsArea.appendText(time + ": You bought " + quantity + " shares from " 
                                                        + symbol + " at the price of " + costFormatted + "\n");
                    double newBalance = lc.balance - costNotFormatted;
                    String balanceFormatted = String.format("%,.2f", newBalance);
                    labelBalance.setText("$" + balanceFormatted);
                }
                
                else if (costNotFormatted > lc.balance) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Your balance is too low");
                    alert.setContentText("You can't buy " + quantity + " shares from " + symbol);
                    alert.showAndWait();
                }
                
 
            }
            
            catch(Exception e) {
                
              if ((symbolField.getText().isEmpty() == true) || (quantityField.getText().isEmpty() == true)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("The fields are empty");
                    alert.setContentText("Please insert a ticker and a quantity");
                    alert.showAndWait();
                }
            }
        }

        // if the radiobutton SELL is selected
        else if (radioButtonSell.isSelected()) {
            
            try {  

                symbol = symbolField.getText().trim();
                quantity = Integer.parseInt(quantityField.getText().trim());
                price = setPrice();
                costNotFormatted = price * quantity;
                costFormatted = String.format("%,.2f", costNotFormatted);
                costLabel.setText("$" + costFormatted);
                
                if (quantity <= GetQuantityOwned()) {
                    String time = sdf.format(new Date());
                    yourInvestmentsArea.appendText(time + ": You sold " + quantity + " shares from " 
                                                    + symbol + " at the price of " + costFormatted + "\n");
                    int newSecurity = GetQuantityOwned() - quantity;
                    UpdateMyStockTable(symbol, newSecurity);
                    double newBalance = lc.balance + price;
                    String balanceFormatted = String.format("%,.2f", newBalance);
                    labelBalance.setText("$" + balanceFormatted);
                    symbolField.setText("");
                    quantityField.setText("");
                    priceField.setText("");
                    costLabel.setText("");
                            
                }
                
                else if (quantity > GetQuantityOwned()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Your shares for " + symbol + " are too low");
                    alert.setContentText("You can't sell " + quantity + " shares from " + symbol);
                    alert.showAndWait();
                    symbolField.setText("");
                    quantityField.setText("");
                    priceField.setText("");
                    costLabel.setText("");
                }
 
            }
            
            catch(Exception e) {
                
              if ((symbolField.getText().isEmpty() == true) || (quantityField.getText().isEmpty() == true)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("The fields are empty");
                    alert.setContentText("Please insert a ticker and a quantity");
                    alert.showAndWait();
                    symbolField.setText("");
                    quantityField.setText("");
                    priceField.setText("");
                    costLabel.setText("");
                }
            }
        }
        
        // if neither radiobutton is selected
        else if (!(radioButtonSell.isSelected()) && !(radioButtonBuy.isSelected())){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Select one of the radiobuttons");
                    alert.setContentText("Please select BUY or SELL");
                    alert.showAndWait();
        }
        
       
    }
    
    /**
     * Button listener that resents the fields in the transaction tab
     * @param event ActionEvent 
     */
    @FXML 
    public void ResetButton(ActionEvent event) {
        symbolField.setText("");
        quantityField.setText("");
        priceField.setText("");
        costLabel.setText("");
    }
    
    /**
     * method that populates the user table
     * @param list type ObservableList
     */
    @FXML
    public void PopulateUserTable (ObservableList<ObjectClass.User> list) {
        usernameColumn = new TableColumn<ObjectClass.Company, String>("Username");
        
        usernameColumn.setCellValueFactory(
                new PropertyValueFactory<ObjectClass.Company, String>("username"));
        
        usernameColumn.prefWidthProperty().bind(usersTable.widthProperty());
                
        usersTable.setItems(list);
        usersTable.getColumns().addAll(usernameColumn);
    
    }
    
    /**
     * method that handles the public and private chat
     * @return publicMessage or privateMessage
     */
    @FXML
    public ObjectClass.Message SendMessage() {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        
        try {
            Parent root = loader.load();
        } 
        
        catch (IOException ex) {
            ex.printStackTrace();
        }
        lc = loader.getController();
        
        String time = sdf.format(new Date());
        String messageNotFormatted = chatTextField.getText();
        String messageFormatted = time + ": " + lc.getUsername() + messageNotFormatted + "\n";
        chatArea.appendText(messageFormatted);
        chatTextField.setText("");
        
        if (publicTab.isSelected()) {
            ObjectClass.Message publicMessage = new ObjectClass.Message(messageFormatted, "public");
            return publicMessage;
        }
        
        else {
            ObjectClass.Message privateMessage = new ObjectClass.Message(messageFormatted, "private");
            return privateMessage;
        }
        
        
    } 
    
}
