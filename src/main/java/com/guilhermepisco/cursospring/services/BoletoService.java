package com.guilhermepisco.cursospring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.guilhermepisco.cursospring.domain.PaymentWithBoleto;

@Service
public class BoletoService {

	public void fillPaymentWithBoleto(PaymentWithBoleto payment, Date requestInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(requestInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setExpiratioDate(cal.getTime());
	}
}
