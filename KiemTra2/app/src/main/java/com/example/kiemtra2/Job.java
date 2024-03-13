package com.example.kiemtra2;

public class Job {
    private String name;
    private String description;
    private Boolean isMale;
    private Long time;

    public Job(String name, String description, Boolean isMale, Long time) {
        this.name = name;
        this.description = description;
        this.isMale = isMale;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}

