package com.davidhernandezvilaltagmail.projecte1.recyclerview;

/**
 * Created by David Hdez on 07/07/2017.
 */

public class Contract {
    private int icon;
    private String name;
    private String phone;

    Contract(int icon, String name, String phone){
        this.icon = icon;
        this.name = name;
        this.phone = phone;

    }
    Contract(){

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
