/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Release;

import com.esprit.formulaire.Issue.*;
import Entity.Issue;
import Entity.Release;
import Entity.User;
import WebService.IssueService;
import WebService.MeetingService;
import WebService.ReleaseService;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.Meeting.Meetings;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class EditRelease extends SideMenuBaseForm {

    public EditRelease(Release i, User u, Resources res) {

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
                                new Label("Issue " +i.getId(), "WelcomeBlue")
                        //                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);

        ImageViewer img = new ImageViewer(res.getImage("release.png"));
        Label name = new Label("Type :");
        Label description = new Label("Description:");
        Label release = new Label("Release :");

        
        TextField namee = new TextField();

        namee.setUIID("TextFieldF");

        namee.setText(i.getName());

        TextField descriptionn = new TextField();
        descriptionn.setUIID("TextFieldF");
        descriptionn.setText(i.getDescription());
        
        
        TextField releasee = new TextField();
        releasee.setUIID("TextFieldF");
        releasee.setText(i.getReleaseDate());
        
       
       

        Container Container = new Container(BoxLayout.y());
        Container.addAll(name, namee, description, descriptionn, release, releasee);
        add(img);
        add(Container);

        Container ButtonContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Edit = new Button("Edit");
        ButtonContainer.add(Edit);

        add(ButtonContainer);
        revalidate();
        Edit.addActionListener(ev -> {

            i.setName(namee.getText());
            i.setDescription(descriptionn.getText());
            i.setReleaseDate(releasee.getText());
          

            if (ReleaseService.getInstance().EditIssue(i)) {
                Dialog.show("Success", "Release Edited Successfully", new Command("OK"));

                

            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });

        setupSideMenu(u, res);

    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }

}
