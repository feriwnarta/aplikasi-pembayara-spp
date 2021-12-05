package com.ferdev.aplikasipembayaranspp.service.impl;

import com.ferdev.aplikasipembayaranspp.entity.Account;
import com.ferdev.aplikasipembayaranspp.entity.Bank;
import com.ferdev.aplikasipembayaranspp.entity.PaymentSpp;
import com.ferdev.aplikasipembayaranspp.repository.AccountRepository;
import com.ferdev.aplikasipembayaranspp.repository.PaymentSppRepository;
import com.ferdev.aplikasipembayaranspp.service.PaymentSppService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentSppServiceImpl implements PaymentSppService {
    @Autowired
    private PaymentSppRepository sppRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void save(Account account, PaymentSpp... paymentSpp) {

        // isi kode va dengan fitur generate
        Account change = account;
        List<String> currentVa = new ArrayList<>();
        List<Bank> bnk = change.getBank();
        for(Bank val : bnk) {
            val.getVirtualAccount().setKodeVa(generateKodeVa(val.getNamaBank(), change.getNik()));
        }

        account = change;
        // simpan data awal account terlebih dahulu
        accountRepository.save(account);
        // simpan data payment spp dulu


        // isi id tagihan / id payment

        List<PaymentSpp> pym = Arrays.asList(paymentSpp);
        for(PaymentSpp val : pym) {
            val.setIdPayment(generateKodeTagihan(change.getKelas(), change.getNik()));
        }
        sppRepository.saveAll(pym);

        // isi set many dari account dengan payment spp
        account.getPaymentMany().addAll(pym);
        // simpan kembali account
        accountRepository.save(account);
    }

    @Override
    public void delete(Account account, PaymentSpp paymentSpp) {
        sppRepository.delete(paymentSpp);
        accountRepository.delete(account);
    }

    public String generateKodeVa(String namaBank, String nik) {
        String prefixBca = "0218";
        String prefixBri = "0219";
        String prefixCimbNiaga = "02130";

        String result = "";

        switch (namaBank) {
            case "BCA" :
                result = prefixBca + nik;
                break;
            case "BRI" :
                result = prefixBri + nik;
                break;
            case "CIMB NIAGA" :
                result = prefixCimbNiaga + nik;
                break;
            default :
                System.out.println("salah bank");
        }
            return result;
    }

    public String generateKodeTagihan(String kelas, String nik){
        // format P - KELAS - Full Kalender - NIK -
        // P12A2021120520201031

        // ambil format tanggal pas digenerate
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] date = format.format(new Date()).split("-");
        String tgl = "";
        for(String val : date) {
            tgl += val;
        }

        String kodeTagihan = "P" + kelas + tgl + nik;
        return kodeTagihan;
    }
}
