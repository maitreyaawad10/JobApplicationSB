package com.maitreya.firstjobapp.job;

import java.util.List;

public interface JobService {
    void createJob(Job job);

    List<Job> findAll();

    Job getJobById(Long id);

    boolean updateJobById(Long id, Job job);

    boolean deleteJobById(Long id);
}
