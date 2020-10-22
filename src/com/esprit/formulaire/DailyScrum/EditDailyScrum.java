/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.DailyScrum;

import Entity.DailyScrum;
import Entity.User;
import WebService.DailyScrumService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SignatureComponent;
import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Deku
 */
public class EditDailyScrum  extends SideMenuBaseForm {
    
        public EditDailyScrum(DailyScrum d, User u,Resources res) {
        
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
                                new Label(d.getTitle(), "WelcomeBlue")
//                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
    
        
        
     ImageViewer img = new ImageViewer(res.getImage("file.png").scaled(500, 500));
        Label title = new Label("Daily File name :");
        Label work = new Label("Yesterday work:");
        Label today = new Label("Today plan:");
        Label blocker = new Label("Blockers:");
        Label brunt = new Label("Brunt-hours:");
        Label completed = new Label("Completed-Hours:");
        Label ctime = new Label("Created Time :");
        Label cdate=new Label("Created Date :");
        Label mtime = new Label("Modification Time :");
        Label mdate = new Label("Modification Date :");
        Label createdby = new Label("Created By :");

      
        
        TextField titleField = new TextField();
        
        titleField.setUIID("TextFieldF");

        titleField.setText(d.getTitle());

        TextField workField = new TextField();
        workField.setUIID("TextFieldF");
        workField.setText(d.getYesterdaywork());
        TextField todayField = new TextField();
        todayField.setUIID("TextFieldF");
        todayField.setText(d.getTodayplan());
        TextField blockerField = new TextField();
        blockerField.setUIID("TextFieldF");
        blockerField.setText(d.getBlockers());

        TextField bruntField = new TextField();
        bruntField.setUIID("TextFieldF");
        bruntField.setText(String.valueOf(d.getHrsbrunt()));
        TextField completField = new TextField();
        completField.setUIID("TextFieldF");
        completField.setText(String.valueOf(d.getHrscompleted()));
        
         Picker date = new Picker();
        Picker time = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
        time.setType(Display.PICKER_TYPE_TIME);
        
   

        Container Container = new Container(BoxLayout.y());
        Container.addAll(title, titleField, work, workField, today, todayField, blocker,blockerField, brunt, bruntField, completed, completField, mtime, time, mdate, date);
        add(img);
        add(Container);

        Container ButtonContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Edit = new Button("Edit");
        ButtonContainer.add(Edit);

        add(ButtonContainer);
        revalidate();
        Edit.addActionListener(ev -> {
            
            ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Pleas wait to send email");
            status.setShowProgressIndicator(true);
            status.show();
           //...  Some time later you must clear the status
           // status.clear();
            
            String datestring = (new SimpleDateFormat("yyyy-MM-dd")).format(date.getDate());
            
            d.setTitle(titleField.getText());
            d.setYesterdaywork(workField.getText());
            d.setTodayplan(todayField.getText());
            d.setBlockers(blockerField.getText());
            d.setHrsbrunt(Integer.parseInt(bruntField.getText()));
            d.setHrscompleted(Integer.parseInt(completField.getText()));
            
    
            d.setDate_modification(datestring);
            d.setTime_modification(time.getText());
    
            if (DailyScrumService.getInstance().EditDaily(d)) {
                

                Dialog.show("Success", "Daily Scrum file Edited Successfully", new Command("OK"));
                
                new Dailys(u,res).showBack();
                  Message msg = new Message(d.getTitle()+"was edited now ");

Display.getInstance().sendMessage(new String[] {u.getUserMail()}, "Daily File edit ", msg);
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
