package com.ferdev.aplikasipembayaranspp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor
public class VirtualAccount {
    @Id
    private String idVa;
    @Column(unique = true)
    private String kodeVa;
    @OneToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;

    public VirtualAccount(String idVa) {
        this.idVa = idVa;
    }
}
