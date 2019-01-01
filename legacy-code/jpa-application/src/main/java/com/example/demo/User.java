package com.example.demo;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * Created by Daniel on 2017. 7. 8..
 */

@Entity
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "이름은 최소 2글자에서 최대 10글자입니다.")
    @Length(min = 2, max = 10)
    private String name;

    private int age;

    @NotBlank(message = "주소는 필수 값입니다.")
    private String address;

    @NotBlank
    private String gender;

    @Min(value = 0, message = "최소 1회 이상입니다.")
    @Max(value= 1000, message = "최대 1000회 이하입니다.")
    private int countSex;

    private boolean isMoSol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCountSex() {
        return countSex;
    }

    public void setCountSex(int countSex) {
        this.countSex = countSex;
    }

    public boolean isMoSol() {
        return isMoSol;
    }

    public void setMoSol(boolean moSol) {
        isMoSol = moSol;
    }
}
