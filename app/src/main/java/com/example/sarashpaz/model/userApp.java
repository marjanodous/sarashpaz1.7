package com.example.sarashpaz.model;

public class userApp {
    private String userName;
    private String passWord;
    private Boolean first_time_run= true;

    public userApp() {
    }

    public userApp(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public Boolean getFirst_time_run() {
        return first_time_run;
    }

    public void setFirst_time_run(Boolean first_time_run) {
        this.first_time_run = first_time_run;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
