package com.ferdev.aplikasipembayaranspp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor
public class Bank {
    @Id
    private String idBank;
    private String namaBank;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    @OneToOne(mappedBy = "bank", cascade = CascadeType.ALL)
    private VirtualAccount virtualAccount;

    public Bank(String idBank, String namaBank, VirtualAccount virtualAccount) {
        this.idBank = idBank;
        this.namaBank = namaBank;
        this.virtualAccount = virtualAccount;
        this.virtualAccount.setBank(this);
    }
}
