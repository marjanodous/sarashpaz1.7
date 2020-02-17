package com.example.sarashpaz.model;

public class LikedFoods {

    /*1.model fields*/
    private int id;
    private String title;
    private boolean like;
    private String img;
    private String mavad;
    private String tahaieh;

    /*2. constructors */
    public LikedFoods() {
    }
    public LikedFoods(String title) {
        this.title = title;
    }

    public LikedFoods(int id, String title, boolean like, String img, String mavad, String tahaieh) {
        this.id = id;
        this.title = title;
        this.like = like;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
