package com.example.refactorings.humbleobject;

interface CompanyRepository {
    Company findById(long companyId);
    void save(Company company);
}
