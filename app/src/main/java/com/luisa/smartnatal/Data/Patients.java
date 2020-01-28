package com.luisa.smartnatal.Data;

public class Patients {

    String age;
    String blood_group;
    String hemoglobin_levels;
    String hiv_status;
    String id_number;
    String name;
    String rhesus;
    int ID;

    public Patients() {

    }

    public Patients(String Name, String Age){

        this.age = Age;
        //this.blood_group = Bloodgrp;
        //this.hemoglobin_levels = Hemaglobin;
       // this.hiv_status = HivTest;
       // this.id_number = IDNumber;
        this.name = Name;
       // this.rhesus = Rhesus;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getHemoglobin_levels() {
        return hemoglobin_levels;
    }

    public void setHemoglobin_levels(String hemoglobin_levels) {
        this.hemoglobin_levels = hemoglobin_levels;
    }

    public String getHiv_status() {
        return hiv_status;
    }

    public void setHiv_status(String hiv_status) {
        this.hiv_status = hiv_status;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRhesus() {
        return rhesus;
    }

    public void setRhesus(String rhesus) {
        this.rhesus = rhesus;
    }
}
