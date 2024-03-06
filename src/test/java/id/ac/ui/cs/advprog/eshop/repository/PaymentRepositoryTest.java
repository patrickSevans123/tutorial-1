package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        List<Product> products = new ArrayList<>();
        payments = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708550000L, "Asep Kecamatan");
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb878", products, 1708560000L, "Doni Serpis AC");
        Order order3 = new Order("e334ef48-9eff-4da8-9487-8ee697ecbf1e", products, 1708570000L, "Rahmat Empang");

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        Map<String, String> paymentDataVoucher = new HashMap<String, String>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7", orders.getFirst(), "VOUCHER_CODE", paymentDataVoucher);
        payments.add(payment1);

        Map<String, String> paymentDataCOD = new HashMap<String, String>();
        paymentDataCOD.put("address", "Jalan Jalan");
        paymentDataCOD.put("deliveryFee", "10000");
        Payment payment2 = new Payment("a613f7b0-3d26-4b88-a68b-65d26fe0e764", orders.getFirst(), "CASH_ON_DELIVERY", paymentDataCOD);
        payments.add(payment2);
    }


    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertSame(payment.getOrder(), findResult.getOrder());
    }


    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), payment.getOrder(), payment.getMethod(), payments.get(0).getPaymentData(), payment.getStatus());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payments.getFirst().getPaymentData(), findResult.getPaymentData());
        assertSame(payment.getOrder(), findResult.getOrder());


    }
    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());

        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertSame(payments.get(1).getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdNotFound() {
        for (Payment payment: payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("randomId");
        assertNull(findResult);
    }

    @Test
    void testFindAll(){
        for (Payment payment: payments) {
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.getAllPayments();
        assertEquals(2, paymentList.size());
    }
}