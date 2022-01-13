package com.yjxxt.hms.query;

import com.yjxxt.hms.base.BaseQuery;

public class NurseQuery extends BaseQuery{

    private String userName;

    private String idNumber;

    public NurseQuery() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId_number() {
        return idNumber;
    }

    public void setId_number(String id_number) {
        this.idNumber = id_number;
    }

    @Override
    public String toString() {
        return "NurseQuery{" +
                "userName='" + userName + '\'' +
                ", id_number='" + idNumber + '\'' +
                '}';
    }
}
