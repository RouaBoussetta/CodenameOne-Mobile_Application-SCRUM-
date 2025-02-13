/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Meeting;

import com.esprit.formulaire.Meeting.Meetings;
import Entity.Affect;
import Entity.Meeting;
import Entity.User;
import WebService.MeetingService;
import WebService.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class EditMeeting extends SideMenuBaseForm {

    public EditMeeting(Meeting m, User u,Resources res) {
        
          super( BoxLayout.y());
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
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label(m.getTitle(), "WelcomeBlue")
//                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
    
        
        
         ImageViewer img = new ImageViewer(res.getImage("calendarr.png"));
        Label title = new Label("Meeting title :");
        Label goal = new Label("Goal:");
        Label issues = new Label("Issues:");
        Label type = new Label("Type:");
//        Label project = new Label("Project:");
//        Label date = new Label("Date:");
//        Label time = new Label("Time :");
        Label duration = new Label("Duration :");
        Label location = new Label("Location :");

      
        TextField titleField = new TextField();
        
        titleField.setUIID("TextFieldF");

        titleField.setText(m.getTitle());

        TextField goalField = new TextField();
        goalField.setUIID("TextFieldF");
        goalField.setText(m.getGoal());
        TextField issuesField = new TextField();
        issuesField.setUIID("TextFieldF");
        issuesField.setText(m.getIssues());
        TextField typeField = new TextField();
        typeField.setUIID("TextFieldF");
        typeField.setText(m.getType());

        TextField durationField = new TextField();
        durationField.setUIID("TextFieldF");
        durationField.setText(m.getDuration());
        TextField locationField = new TextField();
        locationField.setUIID("TextFieldF");
        locationField.setText(m.getLocation());

        Container Container = new Container(BoxLayout.y());
        Container.addAll(title, titleField, goal, goalField, issues, issuesField, type, typeField, duration, durationField, location, locationField);
        add(img);
        add(Container);

        Container ButtonContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Edit = new Button("Edit");
        ButtonContainer.add(Edit);

        add(ButtonContainer);
        revalidate();
        Edit.addActionListener(ev -> {
            
            m.setTitle(titleField.getText());
            m.setGoal(goalField.getText());
            m.setIssues(issuesField.getText());
            m.setType(typeField.getText());
//        
            m.setDuration(durationField.getText());
            m.setLocation(locationField.getText());
    
            if (MeetingService.getInstance().EditEvenement(m)) {
                Dialog.show("Success", "Meeting Edited Successfully", new Command("OK"));
                
                new Meetings(u,res).showBack();
                  Message msg = new Message(m.getTitle()+"was edited now ");

Display.getInstance().sendMessage(new String[] {u.getUserMail()}, "Meeting edit ", msg);
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });
        
          
        
        
        setupSideMenu(u,res);

       
        
    }

    
    
    
    @Override
    protected void showOtherForm(User u, Resources res) {
    }
    
}
