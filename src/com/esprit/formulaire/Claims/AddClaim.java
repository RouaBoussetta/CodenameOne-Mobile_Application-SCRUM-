/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Claims;

import Entity.Claim;
import Entity.Meeting;
import Entity.Project;
import Entity.User;
import WebService.MeetingClaimService;
import WebService.MeetingService;
import WebService.ProjectService;
import WebService.UserService;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
public class AddClaim extends SideMenuBaseForm {

    public AddClaim(User u, Resources res) {
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
                                new Label("  New Meeting  ", "WelcomeBlue")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);

        TextField name = new TextField(u.getUserName());
        TextField lastname = new TextField(u.getLastname());
        TextField mail = new TextField(u.getUserMail());
        TextField tel = new TextField(u.getUserPhone());
        ComboBox available = new ComboBox();
        available.addItem("I'm sick");
        available.addItem("i will be late");
        available.addItem("i can't come");
        TextField other = new TextField(null, "Enter other availability here !", 20, TextArea.ANY);

        TextField reason = new TextField("", "Enter the meeting claim reason here !", 20, TextArea.ANY);

        Picker date = new Picker();
        ComboBox meeting = new ComboBox();

        for (Meeting m : MeetingService.getInstance().getAllMeetings()) {
            meeting.addItem(m.getTitle());
        }

        Button add = new Button("Add Claim");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((name.getText().length() == 0) || (lastname.getText().length() == 0) || (mail.getText().length() == 0) || (tel.getText().length() == 0) || (reason.getText().length() == 0) || (date.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else if (tel.getText().length() != 8) {
                    Dialog.show("Alert", "Please check your phone number", new Command("OK"));

                } else {
                    try {
                        String datestring = (new SimpleDateFormat("yyyy-MM-dd")).format(date.getDate());

                        Claim c = new Claim(name.getText(), lastname.getText(), mail.getText(), tel.getText(), available.getSelectedItem().toString(), other.getText(), reason.getText(), datestring, meeting.getSelectedIndex() + 1, u.getId());
                        if (MeetingClaimService.getInstance().AddClaim(c)) {
                            Dialog.show("Success", "Claim Added ", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }
            }
        });

        addAll(name, lastname, mail, tel, available, other, reason, meeting, date, add);

        setupSideMenu(u, res);

    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }

}
