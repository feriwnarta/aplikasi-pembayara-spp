package com.ferdev.aplikasipembayaranspp.repository;

import com.ferdev.aplikasipembayaranspp.entity.Account;
import com.ferdev.aplikasipembayaranspp.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
