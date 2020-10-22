/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;


/**
 *
 * @author Deku
 */
public class DailyScrum extends Document {
    
    private int id_daily;

    private String title;
    private String yesterdaywork;
    private String todayplan;
    private String blockers;
    
    private int hrsbrunt;
    private int hrscompleted;

    private String date_creation;
    private String time_creation;
    private String date_modification;
    private String time_modification;
            
    private int createdby;
    
        public DailyScrum() {
    }
    
  
    

    
    public DailyScrum (String title, String yesterdaywork, String todayplan, String blockers, int hrsbrunt, int hrscompleted, String date_creation, String time_creation, String date_modification, String time_modification ) {
        this.title = title;
        this.yesterdaywork = yesterdaywork;
        this.todayplan = todayplan;
        this.blockers = blockers;
        this.hrsbrunt = hrsbrunt;
        this.hrscompleted = hrscompleted;
        this.date_creation = date_creation;
        this.time_creation = time_creation;
        this.date_modification = date_modification;
        this.time_modification = time_modification;
        


    }

    public DailyScrum(String title, String yesterdaywork, String todayplan, String blockers, int hrsbrunt, int hrscompleted, String date_modification, String time_modification) {
        this.title = title;
        this.yesterdaywork = yesterdaywork;
        this.todayplan = todayplan;
        this.blockers = blockers;
        this.hrsbrunt = hrsbrunt;
        this.hrscompleted = hrscompleted;
        this.date_modification = date_modification;
        this.time_modification = time_modification;
    }


    
    
    public int getId_daily() {
        return id_daily;
    }

    public void setId_daily(int id_daily) {
        this.id_daily = id_daily;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYesterdaywork() {
        return yesterdaywork;
    }

    public void setYesterdaywork(String yesterdaywork) {
        this.yesterdaywork = yesterdaywork;
    }

    public String getTodayplan() {
        return todayplan;
    }

    public void setTodayplan(String todayplan) {
        this.todayplan = todayplan;
    }

    public String getBlockers() {
        return blockers;
    }

    public void setBlockers(String blockers) {
        this.blockers = blockers;
    }

    public int getHrsbrunt() {
        return hrsbrunt;
    }

    public void setHrsbrunt(int hrsbrunt) {
        this.hrsbrunt = hrsbrunt;
    }

    public int getHrscompleted() {
        return hrscompleted;
    }

    public void setHrscompleted(int hrscompleted) {
        this.hrscompleted = hrscompleted;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getTime_creation() {
        return time_creation;
    }

    public void setTime_creation(String time_creation) {
        this.time_creation = time_creation;
    }

    public String getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(String date_modification) {
        this.date_modification = date_modification;
    }

    public String getTime_modification() {
        return time_modification;
    }

    public void setTime_modification(String time_modification) {
        this.time_modification = time_modification;
    }

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id_daily;
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.yesterdaywork);
        hash = 17 * hash + Objects.hashCode(this.todayplan);
        hash = 17 * hash + Objects.hashCode(this.blockers);
        hash = 17 * hash + this.hrsbrunt;
        hash = 17 * hash + this.hrscompleted;
        hash = 17 * hash + Objects.hashCode(this.date_creation);
        hash = 17 * hash + Objects.hashCode(this.time_creation);
        hash = 17 * hash + Objects.hashCode(this.date_modification);
        hash = 17 * hash + Objects.hashCode(this.time_modification);
        hash = 17 * hash + Objects.hashCode(this.createdby);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DailyScrum other = (DailyScrum) obj;
        if (this.id_daily != other.id_daily) {
            return false;
        }
        if (this.hrsbrunt != other.hrsbrunt) {
            return false;
        }
        if (this.hrscompleted != other.hrscompleted) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.yesterdaywork, other.yesterdaywork)) {
            return false;
        }
        if (!Objects.equals(this.todayplan, other.todayplan)) {
            return false;
        }
        if (!Objects.equals(this.blockers, other.blockers)) {
            return false;
        }
        if (!Objects.equals(this.date_creation, other.date_creation)) {
            return false;
        }
        if (!Objects.equals(this.time_creation, other.time_creation)) {
            return false;
        }
        if (!Objects.equals(this.date_modification, other.date_modification)) {
            return false;
        }
        if (!Objects.equals(this.time_modification, other.time_modification)) {
            return false;
        }
        if (!Objects.equals(this.createdby, other.createdby)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DailyScrum{" + "id_daily=" + id_daily + ", title=" + title + ", yesterdaywork=" + yesterdaywork + ", todayplan=" + todayplan + ", blockers=" + blockers + ", hrsbrunt=" + hrsbrunt + ", hrscompleted=" + hrscompleted + ", date_creation=" + date_creation + ", time_creation=" + time_creation + ", date_modification=" + date_modification + ", time_modification=" + time_modification + ", createdby=" + createdby + '}';
    }



    
}

