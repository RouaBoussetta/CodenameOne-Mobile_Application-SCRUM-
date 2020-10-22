/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Meeting;

import Entity.Meeting;
import Entity.Project;
import Entity.User;
import WebService.MeetingService;
import WebService.ProjectService;
import WebService.UserService;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
class AddMeeting extends SideMenuBaseForm {

    public AddMeeting(User u, Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("bleu.png").scaled(120, 90);
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
        TextField title = new TextField(null, "Enter the meeting title here !", 20, TextField.ANY);
        title.setUIID("style");
        TextField goal = new TextField(null, "Enter the goal of the meeting here !", 20, TextField.ANY);
        goal.setUIID("style");

        TextField issues = new TextField(null, "Enter the issues of the meeting here !", 20, TextField.ANY);
        issues.setUIID("style");

        Picker date = new Picker();
        date.setUIID("style");

        Picker time = new Picker();
        time.setType(Display.PICKER_TYPE_TIME);
        time.setUIID("style");
        TextField location = new TextField(null, "Enter the location here !", 20, TextField.ANY);
        location.setUIID("style");
        ComboBox proj = new ComboBox();

        for (Project p : ProjectService.getInstance().getAllProjects()) {
            proj.addItem(p.getTitle());
        }

        ComboBox type = new ComboBox();
        type.setHint("Choose meeting type");

        type.addItem("Review");
        type.addItem("Sprint");
        type.addItem("Retrospective");
        type.addItem("Daily");
        Button btnValider = new Button("Add");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
   String review="4Hours";
      String daily="15mn";
      String retrospective="1H50";
      String sprint="2Hours";
                 if ((title.getText().length() == 0) || (goal.getText().length() == 0) || (issues.getText().length() == 0) || (location.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        String datestring = (new SimpleDateFormat("yyyy-MM-dd")).format(date.getDate());
        if (type.getSelectedItem().equals("Review")){
              Meeting t = new Meeting(title.getText(), goal.getText(), issues.getText(), proj.getSelectedIndex() + 1, type.getSelectedItem().toString(), datestring, time.getText(), review, location.getText(), u.getUserName());
                        if (MeetingService.getInstance().AddMeeting(t)) {
                            Dialog.show("Success", "Meeting Added", new Command("OK"));

                            Message msg = new Message("New Meeting Added now by " + u.getUserName());

                            Display.getInstance().sendMessage(new String[]{"boussettaroua@gmail.com"}, "New Meeting Added ", msg);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
        }else     if (type.getSelectedItem().equals("Retrospective")){
              Meeting t = new Meeting(title.getText(), goal.getText(), issues.getText(), proj.getSelectedIndex() + 1, type.getSelectedItem().toString(), datestring, time.getText(), retrospective, location.getText(), u.getUserName());
                        if (MeetingService.getInstance().AddMeeting(t)) {
                            Dialog.show("Success", "Meeting Added", new Command("OK"));

                            Message msg = new Message("New Meeting Added now by " + u.getUserName());

                            Display.getInstance().sendMessage(new String[]{"boussettaroua@gmail.com"}, "New Meeting Added ", msg);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
        }else     if (type.getSelectedItem().equals("sprint")){
              Meeting t = new Meeting(title.getText(), goal.getText(), issues.getText(), proj.getSelectedIndex() + 1, type.getSelectedItem().toString(), datestring, time.getText(), sprint, location.getText(), u.getUserName());
                        if (MeetingService.getInstance().AddMeeting(t)) {
                            Dialog.show("Success", "Meeting Added", new Command("OK"));

                            Message msg = new Message("New Meeting Added now by " + u.getUserName());

                            Display.getInstance().sendMessage(new String[]{"boussettaroua@gmail.com"}, "New Meeting Added ", msg);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
        }else{
             Meeting t = new Meeting(title.getText(), goal.getText(), issues.getText(), proj.getSelectedIndex() + 1, type.getSelectedItem().toString(), datestring, time.getText(), daily, location.getText(), u.getUserName());
                        if (MeetingService.getInstance().AddMeeting(t)) {
                            Dialog.show("Success", "Meeting Added", new Command("OK"));

                            Message msg = new Message("New Meeting Added now by " + u.getUserName());

                            Display.getInstance().sendMessage(new String[]{"boussettaroua@gmail.com"}, "New Meeting Added ", msg);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
        }
                      
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

        });

        addAll(title, goal, issues, type, proj, date, time,  location, btnValider);
        setupSideMenu(u, res);

    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }

}
