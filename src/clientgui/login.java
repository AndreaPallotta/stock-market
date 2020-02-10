package clientgui;

import javafx.scene.web.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.effect.*;
import com.jfoenix.controls.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.lang.*;
import javafx.scene.layout.*;

public abstract class login extends AnchorPane {

    protected final ImageView IWimage;
    protected final DropShadow dropShadow;
    protected final AnchorPane APlogin;
    protected final JFXTextField user;
    protected final Reflection reflection;
    protected final JFXPasswordField password;
    protected final Reflection reflection0;
    protected final Label LabelSignIn;
    protected final DropShadow dropShadow0;
    protected final JFXButton ButtonSignIn;
    protected final DropShadow dropShadow1;

    public login() {

        IWimage = new ImageView();
        dropShadow = new DropShadow();
        APlogin = new AnchorPane();
        user = new JFXTextField();
        reflection = new Reflection();
        password = new JFXPasswordField();
        reflection0 = new Reflection();
        LabelSignIn = new Label();
        dropShadow0 = new DropShadow();
        ButtonSignIn = new JFXButton();
        dropShadow1 = new DropShadow();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(342.0);
        setPrefWidth(700.0);
        setStyle("-fx-background-color: #20120F; -fx-border-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        IWimage.setFitHeight(308.0);
        IWimage.setFitWidth(357.0);
        IWimage.setLayoutX(23.0);
        IWimage.setLayoutY(37.0);
        IWimage.setPickOnBounds(true);
        IWimage.setPreserveRatio(true);
        IWimage.setImage(new Image(getClass().getResource("logo.jpg").toExternalForm()));

        dropShadow.setColor(javafx.scene.paint.Color.valueOf("#f8f6f6"));
        IWimage.setEffect(dropShadow);

        APlogin.setLayoutX(420.0);
        APlogin.setLayoutY(37.0);
        APlogin.setPrefHeight(268.0);
        APlogin.setPrefWidth(248.0);
        APlogin.setStyle("-fx-background-color: #9C2827;");

        user.setLayoutX(54.0);
        user.setLayoutY(100.0);
        user.setFont(new Font("Lucida Sans Demibold", 13.0));

        user.setEffect(reflection);

        password.setLayoutX(54.0);
        password.setLayoutY(149.0);
        password.setFont(new Font("Lucida Sans Demibold", 13.0));

        password.setEffect(reflection0);

        LabelSignIn.setAlignment(javafx.geometry.Pos.CENTER);
        LabelSignIn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        LabelSignIn.setLayoutX(54.0);
        LabelSignIn.setLayoutY(37.0);
        LabelSignIn.setPrefHeight(41.0);
        LabelSignIn.setPrefWidth(141.0);
        LabelSignIn.setStyle("-fx-background-color: brown;");
        LabelSignIn.setText("Sign In");
        LabelSignIn.setTextFill(javafx.scene.paint.Color.valueOf("#dacbcb"));
        LabelSignIn.setFont(new Font("Lucida Sans Demibold", 31.0));

        dropShadow0.setColor(javafx.scene.paint.Color.valueOf("#8d8d8d"));
        dropShadow0.setHeight(63.5);
        dropShadow0.setOffsetY(3.0);
        dropShadow0.setRadius(28.9725);
        dropShadow0.setSpread(0.2);
        dropShadow0.setWidth(54.39);
        LabelSignIn.setEffect(dropShadow0);

        ButtonSignIn.setLayoutX(84.0);
        ButtonSignIn.setLayoutY(213.0);
        ButtonSignIn.setStyle("-fx-background-color: brown; -fx-border-color: darkred;");
        ButtonSignIn.setFont(new Font("Lucida Sans Typewriter Bold", 13.0));

        dropShadow1.setColor(javafx.scene.paint.Color.WHITE);
        APlogin.setEffect(dropShadow1);

        getChildren().add(IWimage);
        APlogin.getChildren().add(user);
        APlogin.getChildren().add(password);
        APlogin.getChildren().add(LabelSignIn);
        APlogin.getChildren().add(ButtonSignIn);
        getChildren().add(APlogin);

    }
}
