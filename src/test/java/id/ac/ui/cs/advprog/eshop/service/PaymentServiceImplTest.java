
package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;
    List<Product> products;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
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
        paymentDataCOD = new HashMap<>();
        paymentDataCOD.put("address", "Jalan Jalan");
        paymentDataCOD.put("deliveryFee", "10000");
        Payment payment2 = new Payment("a613f7b0-3d26-4b88-a68b-65d26fe0e764", orders.getFirst(), "CASH_ON_DELIVERY", paymentDataCOD);
        payments.add(payment2);
    }

    @Test
    void testAddingPayments() {
        Payment payment1 = payments.getFirst();
        doReturn(payment1).when(paymentRepository).save(any(Payment.class));
        payment1 = paymentService.addPayment(payment1.getOrder(), "VOUCHER_CODE", payment1.getPaymentData());

        Payment payment2 = payments.get(1);
        doReturn(payment2).when(paymentRepository).save(any(Payment.class));
        payment2 = paymentService.addPayment(payment2.getOrder(), "CASH_ON_DELIVERY", payment2.getPaymentData());

        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment retrievedInitialPayment = paymentService.getPayment(payment1.getId());

        assertEquals(payment1.getId(), retrievedInitialPayment.getId());
        assertEquals(payment1.getMethod(), retrievedInitialPayment.getMethod());
        assertEquals(payment1.getStatus(), retrievedInitialPayment.getStatus());

        doReturn(payment2).when(paymentRepository).findById(payment2.getId());
        Payment retrievedAnotherPayment = paymentService.getPayment(payment2.getId());

        assertEquals(payment2.getId(), retrievedAnotherPayment.getId());
        assertEquals(payment2.getMethod(), retrievedAnotherPayment.getMethod());
        assertEquals(payment2.getStatus(), retrievedAnotherPayment.getStatus());
    }

    @Test
    void testSettingPaymentStatusSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("e3a75c5a-0b38-45d4-8428-1c4cbf0c4d3b", orders.getFirst(), "VOUCHER_CODE", paymentData, PaymentStatus.REJECTED.getValue());

        paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSettingPaymentStatusRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("e3a75c5a-0b38-45d4-8428-1c4cbf0c4d3b", orders.getFirst(), "VOUCHER_CODE", paymentData, PaymentStatus.SUCCESS.getValue());

        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSettingStatusFailed() {
        assertThrows(IllegalArgumentException.class, ()->
                paymentService.setStatus(payments.getFirst(), "INVALID_STATUS")
        );
    }

    @Test
    void testGettingPaymentWhenFound() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment paymentFound = paymentService.getPayment(payment.getId());

        assertEquals(payment.getId(), paymentFound.getId());
        assertEquals("VOUCHER_CODE", paymentFound.getMethod());
        assertEquals(payment.getStatus(), paymentFound.getStatus());
    }

    @Test
    void testGettingPaymentWhenNotFound() {
        doReturn(null).when(paymentRepository).findById("RANDOM_ID");
        Payment payment = paymentService.getPayment("RANDOM_ID");
        assertNull(payment);
    }

    @Test
    void testGettingAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> resultPayments = paymentService.getAllPayments();
        assertNotNull(resultPayments);
        assertEquals(payments.size(), resultPayments.size());
        assertTrue(resultPayments.containsAll(payments));
    }

}