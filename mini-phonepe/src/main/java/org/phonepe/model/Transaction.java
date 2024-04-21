package org.phonepe.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.phonepe.enums.PaymentMode;
import org.phonepe.enums.PaymentType;

@Data
@AllArgsConstructor
public class Transaction {
    String transactionId;
    String payerId;
    String payeeId;
    Double transactionAmount;
    PaymentMode paymentMode;
    PaymentType paymentType;
    int dateOfPayment;
    public Transaction(String payerId, String payeeId, Double transactionAmount, PaymentMode paymentMode,
                       PaymentType paymentType, int date) {
        this.transactionId = UUID.randomUUID().toString();;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.transactionAmount = transactionAmount;
        this.paymentMode = paymentMode;
        this.paymentType = paymentType;
        this.dateOfPayment = date;
    }
}
