/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Project;

import Entity.Claim;
import Entity.Meeting;
import Entity.Project;
import Entity.User;
import WebService.MeetingClaimService;
import WebService.MeetingService;
import WebService.ProjectService;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class AddProject extends SideMenuBaseForm {

    public AddProject(User u, Resources res) {

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
                                new Label("  New Project  ", "WelcomeBlue")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);

        TextField title = new TextField(null, "Enter title here !", 20, TextArea.ANY);
              title.setUIID("style");
        TextField description = new TextField(null, "Enter description here !", 20, TextArea.ANY);
              description.setUIID("style");
        Picker deadline = new Picker();
                      deadline.setUIID("style");

        TextField category = new TextField(null, "Enter category here !", 20, TextArea.ANY);
        
              category.setUIID("style");

        TextField version = new TextField(null, "Enter version here !", 20, TextArea.ANY);
              version.setUIID("style");

        Picker dateCreation = new Picker();
                      dateCreation.setUIID("style");

        Picker timeCreation = new Picker();

        Button add = new Button("Add Project");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((title.getText().length() == 0) || (description.getText().length() == 0) || (deadline.getText().length() == 0) || (category.getText().length() == 0) || (version.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        String datestring1 = (new SimpleDateFormat("yyyy-MM-dd")).format(deadline.getDate());
                        String datestring2 = (new SimpleDateFormat("yyyy-MM-dd")).format(dateCreation.getDate());

                        Project p = new Project(title.getText(), description.getText(), datestring1, category.getText(), version.getText(), datestring2);
                        if (ProjectService.getInstance().AddProject(p)) {
                            Dialog.show("Success", "Project Added ", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }
            }
        });

        addAll(title, description, deadline, category, version, dateCreation, timeCreation, add);
        setupSideMenu(u, res);

    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }

}
