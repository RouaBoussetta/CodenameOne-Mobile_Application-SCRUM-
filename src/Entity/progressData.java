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
public class progressData {
    
    int totaldone;
    int todo;

    public progressData() {
    }

    public progressData(int totaldone, int todo) {
        this.totaldone = totaldone;
        this.todo = todo;
    }

    public int getTotaldone() {
        return totaldone;
    }

    public void setTotaldone(int totaldone) {
        this.totaldone = totaldone;
    }

    public int getTodo() {
        return todo;
    }

    public void setTodo(int todo) {
        this.todo = todo;
    }
    
}
