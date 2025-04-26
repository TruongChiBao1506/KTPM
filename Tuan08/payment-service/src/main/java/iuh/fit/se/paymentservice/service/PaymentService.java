package iuh.fit.se.paymentservice.service;

import iuh.fit.se.paymentservice.dto.PaymentRequest;
import iuh.fit.se.paymentservice.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    public PaymentResponse processPayment(PaymentRequest paymentRequest);
    public PaymentResponse getPaymentByOrderId(String orderId);
    public PaymentResponse refundPayment(String orderId);
    public List<PaymentResponse> getAllPayments();
}
