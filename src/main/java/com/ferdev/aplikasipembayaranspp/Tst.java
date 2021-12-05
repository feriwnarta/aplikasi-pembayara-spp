package com.ferdev.aplikasipembayaranspp;

import com.ferdev.aplikasipembayaranspp.entity.PaymentSpp;
import com.ferdev.aplikasipembayaranspp.service.impl.PaymentSppServiceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Tst {
    public static void main(String[] args) {

        PaymentSpp spp2 = new PaymentSpp("pembayaran spp tahun 2021", new BigDecimal(3900000));
        PaymentSpp buku2 = new PaymentSpp("pembayaran buku tahun 2021", new BigDecimal(900000));

        method(spp2, buku2);


    }

    static void method(PaymentSpp... paymentSpps) {
        List<PaymentSpp> pym = Arrays.asList(paymentSpps);
        for(PaymentSpp val : pym) {
            val.setIdPayment(generateKodeTagihan("12V", "20123"));
        }

        System.out.println(pym.toString());
    }

    public static String generateKodeTagihan(String kelas, String nik){
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
