package com.epam.ems.dto.lists;

import com.epam.ems.dto.Certificate;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class CertificateList extends RepresentationModel<CertificateList> {
    private List<Certificate> certificates;

    public CertificateList(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }
}
