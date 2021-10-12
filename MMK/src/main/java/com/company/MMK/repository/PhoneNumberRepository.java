package com.company.MMK.repository;

import com.company.MMK.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    PhoneNumber findByNumberAndAccount_Id(String number, Long accountId);
}
