package com.ferdev.aplikasipembayaranspp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity @Data @NoArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    @Column(unique = true)
    private String nik;
    private String kelas;
    private String email;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "account"
    )
    private List<Bank> bank = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "payment_detail",
            joinColumns = {
                    @JoinColumn(name = "id_account", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_payment", referencedColumnName = "id", nullable = false, updatable = false)
            }

    )
    private Set<PaymentSpp> paymentMany = new HashSet<>();

    public Account(String nama, String nik, String kelas, String email, Bank... bank) {
        this.nama = nama;
        this.nik = nik;
        this.kelas = kelas;
        this.email = email;
        this.bank = Stream.of(bank).collect(Collectors.toList());
        this.bank.forEach(x -> x.setAccount(this));
    }
}
