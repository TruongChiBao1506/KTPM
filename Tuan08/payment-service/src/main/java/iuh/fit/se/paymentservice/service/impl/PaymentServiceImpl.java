package iuh.fit.se.paymentservice.service.impl;

import iuh.fit.se.paymentservice.dto.PaymentRequest;
import iuh.fit.se.paymentservice.dto.PaymentResponse;
import iuh.fit.se.paymentservice.entities.Payment;
import iuh.fit.se.paymentservice.repositories.PaymentRepository;
import iuh.fit.se.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        return circuitBreakerFactory.create("payment").run(
                () -> {
                    // Mô phỏng tích hợp với cổng thanh toán bên ngoài
                    try {
                        Thread.sleep(100); // Mô phỏng thời gian xử lý
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    Payment payment = new Payment();
                    payment.setOrderId(paymentRequest.getOrderId());
                    payment.setAmount(paymentRequest.getAmount());
                    payment.setPaymentMethod(paymentRequest.getPaymentMethod());
                    payment.setStatus(Payment.PaymentStatus.COMPLETED);
                    payment.setTransactionId(UUID.randomUUID().toString());

                    Payment savedPayment = paymentRepository.save(payment);
                    return PaymentResponse.fromEntity(savedPayment);
                },
                throwable -> {
                    // Logic xử lý khi có lỗi
                    PaymentResponse response = new PaymentResponse();
                    response.setStatus(Payment.PaymentStatus.FAILED);
                    return response;
                }
        );
    }

    @Override
    public PaymentResponse getPaymentByOrderId(String orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thanh toán cho đơn hàng: " + orderId));
        return PaymentResponse.fromEntity(payment);
    }

    @Override
    public PaymentResponse refundPayment(String orderId) {
        return circuitBreakerFactory.create("payment-refund").run(
                () -> {
                    Payment payment = paymentRepository.findByOrderId(orderId)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy thanh toán cho đơn hàng: " + orderId));

                    if (payment.getStatus() == Payment.PaymentStatus.COMPLETED) {
                        payment.setStatus(Payment.PaymentStatus.REFUNDED);
                        Payment savedPayment = paymentRepository.save(payment);
                        return PaymentResponse.fromEntity(savedPayment);
                    } else {
                        throw new RuntimeException("Không thể hoàn tiền cho thanh toán với trạng thái: " + payment.getStatus());
                    }
                },
                throwable -> {
                    // Logic xử lý khi có lỗi
                    PaymentResponse response = new PaymentResponse();
                    response.setStatus(Payment.PaymentStatus.FAILED);
                    return response;
                }
        );
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
