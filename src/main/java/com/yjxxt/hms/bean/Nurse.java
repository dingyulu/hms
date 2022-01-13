package com.yjxxt.hms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Nurse {
    private Integer id;

    private String userName;

    private String userPwd;

    private String trueName;

    private String email;

    private String phone;

    private Integer isValid;

    @Override
    public String toString() {
        return "Nurse{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", trueName='" + trueName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isValid=" + isValid +
                ", info='" + info + '\'' +
                ", admittedDate=" + admittedDate +
                ", designOne=" + designOne +
                ", designTwo=" + designTwo +
                ", idNumber='" + idNumber + '\'' +
                ", state=" + state +
                ", pname='" + pname + '\'' +
                '}';
    }

    private String info;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date admittedDate;

    private Integer designOne;

    private Integer designTwo;

    private String idNumber;
    private  Integer state=0;
     private  String pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getAdmittedDate() {
        return admittedDate;
    }

    public void setAdmittedDate(Date admittedDate) {
        this.admittedDate = admittedDate;
    }

    public Integer getDesignOne() {
        return designOne;
    }

    public void setDesignOne(Integer designOne) {
        this.designOne = designOne;
    }

    public Integer getDesignTwo() {
        return designTwo;
    }

    public void setDesignTwo(Integer designTwo) {
        this.designTwo = designTwo;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }
}