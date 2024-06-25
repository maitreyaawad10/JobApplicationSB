package com.maitreya.firstjobapp.job.impl;

import com.maitreya.firstjobapp.job.Job;
import com.maitreya.firstjobapp.job.JobRepository;
import com.maitreya.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void addJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateJobById(Long id, Job updatedjob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job jobToUpdate = optionalJob.get();
            jobToUpdate.setTitle(updatedjob.getTitle());
            jobToUpdate.setDescription(updatedjob.getDescription());
            jobToUpdate.setMinSalary(updatedjob.getMinSalary());
            jobToUpdate.setMaxSalary(updatedjob.getMaxSalary());
            jobToUpdate.setLocation(updatedjob.getLocation());
            jobRepository.save(jobToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteJobById(Long id) {
        if(jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
