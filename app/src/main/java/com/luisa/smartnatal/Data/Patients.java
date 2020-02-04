package com.luisa.smartnatal.Data;

import java.util.Random;

public class Patients {

    String age;
    String blood_group;
    String hemoglobin_levels;
    String hiv_status;
    String id_number;
    String name;
    String rhesus;
    String uid;
    String card_num;
    String phoneNumber;
    String residence;
    String weight;
    String height;
    String bloodpressure;


    public Patients(String Name, String Age,String IDNumber, String Bloodgrp, String Hemaglobin, String Rhesus, String HivTest,
                    String CardNumber,String PhoneNumber,String Residence,String Weight,String Height,String BloodPressure){

        this.age = Age;
        this.blood_group = Bloodgrp;
        this.hemoglobin_levels = Hemaglobin;
        this.hiv_status = HivTest;
        this.id_number = IDNumber;
        this.name = Name;
        this.rhesus = Rhesus;
        this.card_num = CardNumber;
        this.phoneNumber = PhoneNumber;
        this.residence = Residence;
        this.weight = Weight;
        this.height = Height;
        this.bloodpressure = BloodPressure;

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

    public String getCard_num() {

        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBloodpressure() {
        return bloodpressure;
    }

    public void setBloodpressure(String bloodpressure) {
        this.bloodpressure = bloodpressure;
    }
}
