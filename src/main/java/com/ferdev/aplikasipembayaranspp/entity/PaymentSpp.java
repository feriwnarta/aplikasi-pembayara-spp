package com.ferdev.aplikasipembayaranspp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @NoArgsConstructor
public class PaymentSpp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idPayment;
    private String namaPayment;
    private BigDecimal tagihan;

    @ManyToMany(
            mappedBy = "paymentMany",
            fetch = FetchType.LAZY
    )
    private Set<Account> accountMany = new HashSet<>();

    public PaymentSpp(String namaPayment, BigDecimal tagihan) {
        this.namaPayment = namaPayment;
        this.tagihan = tagihan;
    }
}
