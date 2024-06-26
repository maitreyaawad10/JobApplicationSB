package com.maitreya.firstjobapp.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maitreya.firstjobapp.job.Job;
import com.maitreya.firstjobapp.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    // JsonIgnore annotation is going to remove those recursive call stack errors
    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Job> jobs;
    /* mappedBy tells compiler to not create a separate mapping table
     instead it is mapped by an attribute name company in Job table */

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Company() {}

    public Company(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
