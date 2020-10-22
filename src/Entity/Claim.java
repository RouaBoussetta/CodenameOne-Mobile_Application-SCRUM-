/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Lenovo
 */
public class Claim {
    
    private int id;
    private String name;
    private String lastname;
    private String mail;
    private String tel;
    private String available;
    private String other;
    private String reason;
    private String date;
    private int user;
    private int meeting;

    public Claim() {
    }

    public Claim(int id, String name, String lastname, String mail, String tel, String available, String other, String reason, String date, int user, int meeting) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.tel = tel;
        this.available = available;
        this.other = other;
        this.reason = reason;
        this.date = date;
        this.user = user;
        this.meeting = meeting;
    }
    public Claim(String name, String lastname, String mail, String tel, String available, String other, String reason, String date, int meeting,int user) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.tel = tel;
        this.available = available;
        this.other = other;
        this.reason = reason;
        this.date = date;
        this.user = user;
        this.meeting = meeting;
    }

    public Claim(String name, String lastname, String mail, String tel, String available, String other, String reason, String date) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.tel = tel;
        this.available = available;
        this.other = other;
        this.reason = reason;
        this.date = date;
   
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getMeeting() {
        return meeting;
    }

    public void setMeeting(int meeting) {
        this.meeting = meeting;
    }
    
    
    
   
    
}
