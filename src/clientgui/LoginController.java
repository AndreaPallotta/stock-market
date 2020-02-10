
package clientgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.*;
import javafx.scene.control.Alert.AlertType;
import java.net.*;
import java.sql.Timestamp;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Andrea Pallotta
 * @author Sergio Zygmunt
 * @version 20190503
 * description multi-threaded controller for the login Scene
 * 
 */
public class LoginController extends ObjectClass implements Initializable {

    @FXML
    private AnchorPane APimage;
    
    @FXML
    private ImageView IWimage;
    
    @FXML
    private AnchorPane APlogin;
    
    @FXML
    private JFXTextField userField;
    
    @FXML
    private JFXPasswordField passwordField;
    
    @FXML
    private Label LabelSignIn;
    
    @FXML
    private JFXButton ButtonSignIn;
    
    @FXML 
    private JFXTextField ipAddress;
    
    FXMLLoader loader;
    MainGUIController mgc;
    
    boolean checkLogin = true; // true only for debugging //
    boolean received;
    int quantity;
    
    // attributes for the user Object
    String username;
    String password;
    double balance = 1000; // value assigned only for offline
    Timestamp tsUser;
    ObservableList<User> listUsers = FXCollections.observableArrayList();
    Vector<String> listUsernames = new Vector();
    
    // attributes for the company Object
    int numberOfSharesCompany;
    String companyTicker;
    String companyName;
    double companyValue;
    Timestamp tsCompany;
    Vector<Company> listCompanies = new Vector();
    Vector<String> listCompanyNames = new Vector();
    Vector<Double> listCompanyValues = new Vector();
    ObservableList<Company> companyInfo = FXCollections.observableArrayList();
    
    // attributes for the securities Object
    String securityUsername;
    String securityTicker;
    int numberOfShares;
    Timestamp tsSecurities;
    Vector<Securities> listSecurities = new Vector();
    Vector<String> listSecurityUsernames = new Vector();
    ObservableList<Securities> securityInfo = FXCollections.observableArrayList();
    
    // attributes for the trade Object
    String tradeUsername;
    int transactionID;
    String tradeTicker;
    int tradedShares;
    double valuePerShare;
    String transactionType;
    Vector<Trades> listTrade = new Vector();
    Vector<Integer> listTradedShare = new Vector();
    ObservableList<Trades> tradeInfo = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        received = false;

    } 
 
    // OFFLINE DEBUGGING
    public void PopulateCompanyList() {
        companyInfo.add(new Company("AAPL", 50.12));
        companyInfo.add(new Company("MSFT", 130.32));
        companyInfo.add(new Company("INTC", 84.19));
        companyInfo.add(new Company("FB", 1102.76));
        companyInfo.add(new Company("BRK", 84.19));
        companyInfo.add(new Company("BABA", 1102.76));
        companyInfo.add(new Company("JNJ", 84.19));
        companyInfo.add(new Company("JPM", 1102.76));
        companyInfo.add(new Company("XOM", 84.19));
        companyInfo.add(new Company("BAC", 1102.76));
    }
    
    
    // OFFLINE DEBUGGING
    public void PopulateUserList() {
        listUsers.add(new User("USER1"));
        listUsers.add(new User("USER2"));
        listUsers.add(new User("USER3"));
        listUsers.add(new User("USER4"));
        listUsers.add(new User("USER5"));
        listUsers.add(new User("USER6"));
    }
    


    /**
     * method that starts the thread and connection with the server 
     *        and handles the events for the login button
     * @param event ActionEvent
     */
    @FXML
    public void loginButtonChangeScene(ActionEvent event) {
        // ONLINE VERSION THREAD
//        ClientThread ct = new ClientThread(ipAddress.getText(), 5000);
//        Thread th = new Thread(ct);
//        th.start();       
//        try {
//            Thread.sleep(2000);
//        } 
//        
//        catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
        
        
        System.out.println("checklogin" + checkLogin);
        
        // if username and passwords are correct
        if (checkLogin) {
            try {
                
                 loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("mainGUI.fxml"));
                 Parent tableViewParent = loader.load();
                 
                 // create mainGUIController Object
                 mgc = loader.getController();
                
                Scene tableViewScene = new Scene(tableViewParent);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
                
                PopulateCompanyList();
                mgc.PopulateCompanyTable(companyInfo);
                mgc.PopulateSecurityTable(securityInfo);
                PopulateUserList();
                mgc.PopulateUserTable(listUsers);
                mgc.labelBalance.setText(("$" + balance));
                
                }

            catch (IOException ioe) {
                ioe.getMessage();
            }
        }
        
        // if fields are empty 
        else if(getUsername().isEmpty() || getPassword().isEmpty()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Username or Password are empty");
                alert.setContentText("Please enter you credentials before submitting.");
                alert.showAndWait();
        }
        
        // if username and password are wrong
        else if (!checkLogin) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Username or Password are wrong");
                alert.setContentText("Your username or password do not match the one stored in the database. Try again.");
                alert.showAndWait();
        }
        
        // --------- DEBUGGING. DO NOT UNCOMMENT ----------- //
    /* public void LoadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    */
    } 

    /**
     * getter for the username
     * @return text inside userField
     */
    public String getUsername() {
        return userField.getText();
    }

    /**
     * getter for the password
     * @return text inside userField
     */
    public String getPassword() {
        return passwordField.getText();
    }


    // class containing the thread
    class ClientThread implements Runnable {
        private String ip = "";
        private int port = 0;
        private Socket s = null;
        private ObjectOutputStream oos;
        private ObjectInputStream ois;
        private Object obj;
        
        /**
         * Constructor
         * @param ip
         * @param port 
         */
        public ClientThread(String ip, int port) {
            this.ip = ip;
            this.port = port;
            
            try {
                
                s = new Socket(ip, port);
                ois = new ObjectInputStream(s.getInputStream());
                oos = new ObjectOutputStream(s.getOutputStream());
                
            } 
            
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        
       
        
        /**
         * method that receives an object and finds out what type of object it is
         */
        public void receiveFromServer() {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainGUI.fxml"));
        
            try {
                Parent root = loader.load();
            } 

            catch (IOException ex) {
                ex.printStackTrace();
            }

            mgc = loader.getController();
            
            // create the stream and reads the object
            try {
                
                obj = ois.readObject();
                System.out.println("Object Received: " + obj.getClass());
                System.out.println("Data has been received");
                
                if (obj != null) {
                
                    // if the object is User object
                    if (obj instanceof User) {
                       System.out.println("Received user object");
                       User user = (User)obj;
                       System.out.println(user.toString());
                       listUsers.add(user);
                       username = user.getUsername();
                       listUsernames.add(username);
                       password = user.getPassword();
                       balance = user.getBalance();
                       mgc.labelBalance.setText(balance + " $");
                       System.out.println("Balance updated");
                       tsUser = user.getTimestamp();
                       
                       
                    }

                    // if the object is Company object
                    else if (obj instanceof Company) {
                        
                        System.out.println("Received company object");
                        Company company = (Company)obj;
                        System.out.println(company.toString());
                        listCompanies.add(company);
                        numberOfSharesCompany = company.getNumberOfShares();
                        companyTicker = company.getTicker();
                        companyValue = company.getValue();
                        listCompanyValues.add(companyValue);

                        companyInfo.add(new Company(companyTicker, companyValue));
                        mgc.companyTable.setItems(companyInfo);

                        companyName = company.getName();
                        listCompanyNames.add(companyName);
                        tsCompany = company.getTimestamp();

                        // make the charty
                        mgc.chart.setTitle("Stock Monitoring " + tsCompany);
                        XYChart.Series series = new XYChart.Series();
                        series.setName("Stock for " + companyName);

                        for (int i = 0; i < listCompanyValues.size(); i++) {
                            series.getData().add(new XYChart.Data(i + "", listCompanyValues.get(i)));
                        }

                        mgc.chart.getData().add(series);


                    }

                    // if the object is Securities object
                    else if (obj instanceof Securities) {
                        
                        System.out.println("Received securities object");
                        Securities securities = (Securities)obj;
                        listSecurities.add(securities);
                        securityUsername = securities.getUsername();
                        listSecurityUsernames.add(securityUsername);
                        securityTicker = securities.getTicker();
                        numberOfShares = securities.getNumberOfShares();
                        tsSecurities = securities.getTimestamp();
                        securityInfo.add(new Securities(securityTicker, numberOfShares));
                        mgc.myStockTable.setItems(securityInfo);


                    }

                    // if the object is Trades object
                    else if (obj instanceof Trades) {
                        
                        System.out.println("Received trade object");
                        Trades trade = (Trades)obj;
                        listTrade.add(trade);
                        tradeUsername = trade.getUsername();
                        tradedShares = trade.getTradedShares();
                        listTradedShare.add(tradedShares);
                        valuePerShare = trade.getValuePerShare();
                        tradeTicker = trade.getTicker();
                        tradeInfo.add(new Trades(tradeTicker, valuePerShare));

                    }

                    // if the object is a Boolean Object
//                    else if (obj instanceof Boolean) {
//                        System.out.println("Received boolean object");
//                        System.out.println("Login result received");
//                        received = true;
//
//                        checkLogin = (Boolean)obj;
//                        System.out.println("Value received" + checkLogin);
//
//                    }
                }
                
                else {
                    System.out.println("Object is null");
                }
            } 
            
            catch (IOException ioe) {
                ioe.printStackTrace();
            } 
            
            catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        }
            
        
        /**
         * Method to send the objects to the server
         */
        public void sendToServer() {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainGUI.fxml"));
        
            try {
                Parent root = loader.load();
            } 

            catch (IOException ex) {
                ex.printStackTrace();
            }

            mgc = loader.getController();
            
            try {
                
                if (tradeInfo != null) {
                    
                    if (mgc.quantityField.getText().trim().equals("")) {
                        quantity = 0;
                    }
                    
                    else {
                        quantity = Integer.parseInt(mgc.quantityField.getText().trim());
                    }
                
                    Trades tradeInfo = new Trades(userField.getText().trim(), mgc.symbolField.getText().trim(),
                    quantity, mgc.RadioButtonValue());

                    oos.writeObject(tradeInfo);
                    oos.writeObject(mgc.chatTextField.getText());
                    oos.flush();
                    oos.reset();
                }
               
            }
            
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
                
        /**
         * run method that creates the socket and calls the two methods above
         * 
         */
        @Override
        public void run() {
            try {
                oos.writeObject(userField.getText().trim());
                oos.writeObject(passwordField.getText().trim());
                oos.flush();
                oos.reset();
                try {
                    obj = ois.readObject();
                    checkLogin = (Boolean)obj;
                } 
                
                catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                
                while(true) {
                    
                  sendToServer();
                  receiveFromServer();
                    
                }
            } 
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
           
        } // end of run
    } // end of ClientThread class
} // end of LoginController class
