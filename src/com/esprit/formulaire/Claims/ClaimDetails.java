/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Claims;

import Entity.Affect;
import Entity.Claim;
import Entity.Meeting;
import Entity.User;
import WebService.MeetingClaimService;
import WebService.MeetingService;
import WebService.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.Meeting.EditMeeting;
import com.esprit.formulaire.Meeting.Meetings;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class ClaimDetails extends SideMenuBaseForm {

    public ClaimDetails(Claim c, User u, Resources res) {

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
                                new Label(c.getName() + "'s claiming", "WelcomeBlue")
                        //                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);

        ImageViewer img = new ImageViewer(res.getImage("claim1.png").scaled(500, 500));
        Label name = new Label("Username :");
        Label last = new Label("Lastname");
        Label mail = new Label("Mail address:");
        Label phone = new Label("Phone number :");
        Label availability = new Label("Availability");
        Label other = new Label("Other:");
        Label reason = new Label("Reason:");
        Label date = new Label("Date:");
        Label meeting = new Label("Meeting :");

        Label nameL = new Label(u.getUserName());
        Label LastL = new Label(u.getLastname());
        Label MailL = new Label(u.getUserMail());
        Label l1 = new Label(u.getUserPhone());
        Label l2 = new Label(c.getAvailable());
        Label l3 = new Label(c.getOther());
        Label l4 = new Label(c.getReason());
        Label l5 = new Label(String.valueOf(c.getDate()));

        Container Container = new Container(BoxLayout.y());
        Container.addAll(name, nameL, last, LastL, mail, MailL, phone, l1, availability, l2, other, l3, reason, l4, date, l5);
        add(img);
        add(Container);

        Container ButtonsContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        add(ButtonsContainer);
        revalidate();
        Delete.addActionListener(ev -> {
            String result = MeetingClaimService.getInstance().DeleteClaim(c);
            if (!result.equals("Error")) {
                Dialog.show("Success", result, new Command("OK"));
                new Claims(u, res).showBack();

                Message msg = new Message("Claim Canceled by " + u.getUserName());

                Display.getInstance().sendMessage(new String[]{"boussettaroua@gmail.com"}, "Claiming Cancel ", msg);
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });
        Edit.addActionListener(ev -> {

            new EditClaim(c, u, res).show();

        });

        setupSideMenu(u, res);

        Button pdf = new Button("Show PDF");
        pdf.addActionListener(e -> {
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + "Claim.pdf";
            if (!fs.exists(fileName)) {
                Util.downloadUrlToFile("http://localhost/TitanWeb/web/app_dev.php/Claims", fileName, true);

            }
            Display.getInstance().execute(fileName);
        });

        add(pdf);

    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }

}
