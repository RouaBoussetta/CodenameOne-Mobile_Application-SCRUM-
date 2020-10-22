/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire;

import Entity.User;
import WebService.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Lenovo
 */
public class Register extends Form {

    Form current;
    private static User User;

    public Register(Form previous, Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Sign Up | ", "WelcomeWhite"),
                new Label("TITAN", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        TextField name = new TextField(null, "Enter your name", 20, TextField.ANY);
        TextField lastname = new TextField(null, "Enter your lastname", 20, TextField.ANY);
        TextField pseudoname = new TextField(null, "Enter your pseudoname ", 20, TextField.ANY);
        TextField mail = new TextField(null, "Enter your mail", 20, TextField.ANY);

        TextField password = new TextField(null, "Enter tour password", 20, TextField.PASSWORD);
        TextField confpass = new TextField(null, "Confirm your password ", 20, TextField.ANY);
        TextField phone = new TextField(null, "Enter your phone number ", 20, TextField.NUMERIC);
        TextField address = new TextField(null, "Enter your address", 20, TextField.ANY);
        TextField cin = new TextField(null, "Enter your cin ", 20, TextField.NUMERIC);
        Picker date = new Picker();
        TextField nationality = new TextField(null, "Enter your nationality ", 20, TextField.ANY);
        TextField Speciality = new TextField(null, "Enter your speciality", 20, TextField.ANY);
        TextField bio = new TextField(null, "Enter your bio ", 20, TextField.ANY);

        ComboBox role = new ComboBox();
        role.addItem("MASTER");
        role.addItem("DEVELOPER");
        role.addItem("PRODUCT_OWNER");

        name.getAllStyles().setMargin(LEFT, 0);
        lastname.getAllStyles().setMargin(LEFT, 0);
        pseudoname.getAllStyles().setMargin(LEFT, 0);
        mail.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        confpass.getAllStyles().setMargin(LEFT, 0);
        phone.getAllStyles().setMargin(LEFT, 0);
        address.getAllStyles().setMargin(LEFT, 0);
        cin.getAllStyles().setMargin(LEFT, 0);
        date.getAllStyles().setMargin(LEFT, 0);
        nationality.getAllStyles().setMargin(LEFT, 0);
        Speciality.getAllStyles().setMargin(LEFT, 0);
        bio.getAllStyles().setMargin(LEFT, 0);
        role.getAllStyles().setMargin(LEFT, 0);

        Button RegisterButton = new Button("Register");
        RegisterButton.setUIID("LoginButton");

        Button login = new Button("Sign In");
        login.setUIID("CreateNewAccountButton");

        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                new Login(current, res).show();

            }
        });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container logoC = BoxLayout.encloseY(
                new ImageViewer(res.getImage("blancTitan.png").scaled(500, 100))
        );
        add(BorderLayout.OVERLAY, logoC);
        Container by = BoxLayout.encloseY(
                spaceLabel,
                welcome,
                BorderLayout.center(name),
                BorderLayout.center(lastname),
                BorderLayout.center(pseudoname),
                BorderLayout.center(mail),
                BorderLayout.center(phone),
                BorderLayout.center(password),
                BorderLayout.center(confpass),
                BorderLayout.center(address),
                BorderLayout.center(cin),
                BorderLayout.center(date),
                BorderLayout.center(nationality),
                BorderLayout.center(Speciality),
                BorderLayout.center(bio),
                BorderLayout.center(role),
                RegisterButton,
                login
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }

}
