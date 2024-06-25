package com.maitreya.firstjobapp.company.impl;

import com.maitreya.firstjobapp.company.Company;
import com.maitreya.firstjobapp.company.CompanyRepository;
import com.maitreya.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompanyById(Long id, Company updatedCompany) {
        Optional<Company> oldCompany = companyRepository.findById(id);
        if (oldCompany.isPresent()) {
            Company companyToUpdate = oldCompany.get();
            companyToUpdate.setName(updatedCompany.getName());
            companyToUpdate.setDescription(updatedCompany.getDescription());
            companyToUpdate.setJobs(updatedCompany.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
