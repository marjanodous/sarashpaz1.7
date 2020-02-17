package com.example.sarashpaz.model;

public class ItemRecycler {
    /*1.model fields*/
    private int uAvatar;
    private String uName;
    private String mavad;
    private String tahaieh;

    /* 2.constructors */
    public ItemRecycler() {
    }

    public ItemRecycler(int uAvatar) {
        this.uAvatar = uAvatar;
    }

    public ItemRecycler(int uAvatar, String uName, String mavad, String tahaieh) {
        this.uAvatar = uAvatar;
        this.uName = uName;
        this.mavad = mavad;
        this.tahaieh = tahaieh;
    }
    /*3.get and set*/
    public String getMavad() {
        return mavad;
    }

    public void setMavad(String mavad) {
        this.mavad = mavad;
    }

    public String getTahaieh() {
        return tahaieh;
    }

    public void setTahaieh(String tahaieh) {
        this.tahaieh = tahaieh;
    }

    public int getuAvatar() {
        return uAvatar;
    }

    public void setuAvatar(int uAvatar) {
        this.uAvatar = uAvatar;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }


}
