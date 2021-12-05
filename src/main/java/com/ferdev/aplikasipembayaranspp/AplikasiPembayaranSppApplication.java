package com.ferdev.aplikasipembayaranspp;


import com.ferdev.aplikasipembayaranspp.entity.Account;
import com.ferdev.aplikasipembayaranspp.entity.Bank;
import com.ferdev.aplikasipembayaranspp.entity.PaymentSpp;
import com.ferdev.aplikasipembayaranspp.entity.VirtualAccount;
import com.ferdev.aplikasipembayaranspp.repository.AccountRepository;
import com.ferdev.aplikasipembayaranspp.repository.PaymentSppRepository;
import com.ferdev.aplikasipembayaranspp.service.impl.PaymentSppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AplikasiPembayaranSppApplication implements CommandLineRunner {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PaymentSppRepository paymentSppRepository;
	@Autowired
	private PaymentSppServiceImpl service;

	public static void main(String[] args) {
		SpringApplication.run(AplikasiPembayaranSppApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// akun 1
		Account account = new Account("feri", "20201031", "12C", "ferigntgn@gmail.com",
				new Bank("BNK01", "BCA", new VirtualAccount("VA01")),
				new Bank("BNK02", "BRI", new VirtualAccount("VA02")),
				new Bank("BNK03", "CIMB NIAGA", new VirtualAccount("VA03")));

		PaymentSpp spp = new PaymentSpp("pembayaran spp tahun 2021", new BigDecimal(2700000));
		PaymentSpp buku = new PaymentSpp("pembayaran buku tahun 2021", new BigDecimal(700000));

		// akun2 // next fitur id bank jadi primary key(1..2...3), dan tambahan kode bank yg akan sama (VA01...)
		Account account2 = new Account("joko", "20201039", "12F", "joko@gmail.com",
				new Bank("BNK04", "BCA", new VirtualAccount("VA04")),
				new Bank("BNK05s", "BRI", new VirtualAccount("VA05"))
				);

		PaymentSpp spp2 = new PaymentSpp("pembayaran spp tahun 2021", new BigDecimal(3900000));
		PaymentSpp buku2 = new PaymentSpp("pembayaran buku tahun 2021", new BigDecimal(900000));

		// jalankan method save dari services
		 service.save(account,spp, buku);
		 service.save(account2, spp2, buku2);



	}
}
