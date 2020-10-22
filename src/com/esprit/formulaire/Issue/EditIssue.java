/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Issue;

import Entity.Issue;
import Entity.User;
import WebService.IssueService;
import WebService.MeetingService;
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
public class EditIssue extends SideMenuBaseForm {

    public EditIssue(Issue i, User u, Resources res) {

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

        ImageViewer img = new ImageViewer(res.getImage("issue.png"));
        Label type = new Label("Type :");
        Label description = new Label("Description:");
        Label summary = new Label("Summary :");
        Label priority = new Label("Priority :");

        Label status = new Label("Status :");

        TextField typee = new TextField();

        typee.setUIID("TextFieldF");

        typee.setText(i.getType());

        TextField descriptionn = new TextField();
        descriptionn.setUIID("TextFieldF");
        descriptionn.setText(i.getDescription());
        TextField summaryy = new TextField();
        summaryy.setUIID("TextFieldF");
        summaryy.setText(i.getSummary());
        
        TextField priorityy = new TextField();
        priorityy.setUIID("TextFieldF");
        priorityy.setText(i.getPriority());

        TextField statuss = new TextField();
        statuss.setUIID("TextFieldF");
        statuss.setText(i.getStatus());
        
        
       

        Container Container = new Container(BoxLayout.y());
        Container.addAll(type, typee, description, descriptionn, summary, summaryy, priority, priorityy, status, statuss);
        add(img);
        add(Container);

        Container ButtonContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Edit = new Button("Edit");
        ButtonContainer.add(Edit);

        add(ButtonContainer);
        revalidate();
        Edit.addActionListener(ev -> {

            i.setType(typee.getText());
            i.setDescription(descriptionn.getText());
            i.setSummary(summaryy.getText());
            i.setPriority(priorityy.getText());
            i.setStatus(statuss.getText());

            if (IssueService.getInstance().EditIssue(i)) {
                Dialog.show("Success", "Issue Edited Successfully", new Command("OK"));

                

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
