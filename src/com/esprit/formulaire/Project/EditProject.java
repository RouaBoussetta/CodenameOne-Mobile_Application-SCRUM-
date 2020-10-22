/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Project;

import Entity.Project;
import Entity.User;
import WebService.MeetingService;
import WebService.ProjectService;
import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.Meeting.Meetings;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class EditProject extends SideMenuBaseForm {

    public EditProject(Project p, User u, Resources res) {

        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("bleu.png");
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

        tb.getUnselectedStyle().setBgImage(tintedImage);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH,
                        FlowLayout.encloseIn(
                                new Label(p.getTitle(), "WelcomeBlue")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);

        ImageViewer img = new ImageViewer(res.getImage("calendarr.png"));
        Label title = new Label("Projet title :");
        Label description = new Label("Description :");
        Label deadline = new Label("Deadline :");
        Label category = new Label("Category:");
        Label version = new Label("Version :");

        TextField titleField = new TextField();
        titleField.setUIID("TextFieldF");
        titleField.setText(p.getTitle());

        TextField descriptionn = new TextField();
        descriptionn.setUIID("TextFieldF");
        descriptionn.setText(p.getDescription());
        
        TextField deadlinee = new TextField();
        deadlinee.setUIID("TextFieldF");
        deadlinee.setText(p.getDeadline());
        
        
        
        TextField categoryy = new TextField();
        categoryy.setUIID("TextFieldF");
        categoryy.setText(p.getCategory());

        TextField versionn = new TextField();
        versionn.setUIID("TextFieldF");
        versionn.setText(p.getVersion());
        
       

        Container Container = new Container(BoxLayout.y());
        Container.addAll(title, titleField, description, descriptionn, deadline, deadlinee, category, categoryy, version, versionn);
        add(img);
        add(Container);

        Container ButtonContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Edit = new Button("Edit");
        ButtonContainer.add(Edit);

        add(ButtonContainer);
        revalidate();
        Edit.addActionListener(ev -> {

            p.setTitle(titleField.getText());
            p.setDescription(descriptionn.getText());
            p.setDeadline(deadlinee.getText());
            p.setCategory(categoryy.getText());
            p.setVersion(versionn.getText());

            if (ProjectService.getInstance().EditProject(p)) {
                Dialog.show("Success", "Project Edited Successfully", new Command("OK"));

                new Meetings(u, res).showBack();

              
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });

    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }

}
