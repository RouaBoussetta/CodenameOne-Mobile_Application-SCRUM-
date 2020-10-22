/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire;

import WebService.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
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
import com.codename1.ui.util.Resources;

/**
 *
 * @author Lenovo
 */
public class Started extends Form {

    Form current;

    public Started(Resources theme) {

        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome To ", "WelcomeWhite"),
                new Label("TITAN ", "WelcomeBlue"),
                new Label("Mobile App ", "WelcomeWhite")
        );

        Container l2 = FlowLayout.encloseCenter(
                new Label("Project management with SCRUM methodology. ")
        );

        getTitleArea().setUIID("Container");


        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container logoC = BoxLayout.encloseY(
                new ImageViewer(theme.getImage("blancTitan.png").scaled(500, 100))
        );
        add(BorderLayout.OVERLAY, logoC);

        ImageViewer img = new ImageViewer(theme.getImage("unnamed.png").scaled(500, 500));
        Button StartedButton = new Button("Get started ➡");
StartedButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new Login(current, theme).show();
            }
        });

        Container by = BoxLayout.encloseY(
                BorderLayout.center(img),
                welcome,
                spaceLabel,
                l2,
               
                 BorderLayout.north( StartedButton )
        );
        add(BorderLayout.CENTER, by);
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }

}
