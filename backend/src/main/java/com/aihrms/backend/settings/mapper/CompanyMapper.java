package com.aihrms.backend.settings.mapper;

import com.aihrms.backend.settings.dto.CompanyRequestDTO;
import com.aihrms.backend.settings.dto.CompanyResponseDTO;
import com.aihrms.backend.settings.entity.Company;

public class CompanyMapper {

    private CompanyMapper() {
    }

    public static Company toEntity(CompanyRequestDTO dto) {

        Company company = new Company();

        company.setCompanyName(dto.getCompanyName());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setWebsite(dto.getWebsite());
        company.setAddress(dto.getAddress());
        company.setCity(dto.getCity());
        company.setState(dto.getState());
        company.setCountry(dto.getCountry());
        company.setPostalCode(dto.getPostalCode());
        company.setLogoUrl(dto.getLogoUrl());

        return company;
    }

    public static CompanyResponseDTO toDTO(Company company) {

        CompanyResponseDTO dto = new CompanyResponseDTO();

        dto.setId(company.getId());
        dto.setCompanyName(company.getCompanyName());
        dto.setEmail(company.getEmail());
        dto.setPhone(company.getPhone());
        dto.setWebsite(company.getWebsite());
        dto.setAddress(company.getAddress());
        dto.setCity(company.getCity());
        dto.setState(company.getState());
        dto.setCountry(company.getCountry());
        dto.setPostalCode(company.getPostalCode());
        dto.setLogoUrl(company.getLogoUrl());

        return dto;
    }

    public static void update(Company company, CompanyRequestDTO dto) {

        company.setCompanyName(dto.getCompanyName());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setWebsite(dto.getWebsite());
        company.setAddress(dto.getAddress());
        company.setCity(dto.getCity());
        company.setState(dto.getState());
        company.setCountry(dto.getCountry());
        company.setPostalCode(dto.getPostalCode());
        company.setLogoUrl(dto.getLogoUrl());
    }
}