package com.ferdev.aplikasipembayaranspp.service;

import com.ferdev.aplikasipembayaranspp.entity.Account;
import com.ferdev.aplikasipembayaranspp.entity.PaymentSpp;

public interface PaymentSppService {
    void save(Account account, PaymentSpp... paymentSpp);
    void delete(Account account, PaymentSpp paymentSpp);
}
