package com.lee.bean;

import java.io.Serializable;

/**
 * 班级
 */
public class Clazz implements Serializable {
    // 班级信息
    private Integer id;
    private String name;
    private String number;
    private String introduction;
    // 班主任信息
    private String coordinator;
    private String email;
    private String telephone;
    // 所属年级
    private String gradeName;

    public Clazz() {}

    public Clazz(String name, String gradeName) {
        this.name = name;
        this.gradeName = gradeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", introduction='" + introduction + '\'' +
                ", coordinator='" + coordinator + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
