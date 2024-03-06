package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);


        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708550000L, "Asep Acikiwir");
        Order order2 = new Order("b5a9d9d4-57f5-4d49-a1a0-1d6cfcc1b51d",
                this.products, 1708560000L, "Rusdi Ngawi");
        this.orders = new ArrayList<Order>();
        this.orders.add(order1);
        this.orders.add(order2);
    }

    @Test
    void testCreatePaymentVoucherCodeSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "VOUCHER_CODE", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testCreatePaymentVoucherCodeRejectedNot16Characters(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP789XYZ123456");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentVoucherCodeRejectedNotStartedWithESHOP(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "HELLO12345678XYZ");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentVoucherCodeRejectedNotContain8NumericalCharacters(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234567XYZZ");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "VOUCHER_CODE", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }


    @Test
    void testSetStatusPaymentVoucherCodeSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "VOUCHER_CODE", paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }
    @Test
    void testSetStatusPaymentVoucherCodeRejected(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "VOUCHER_CODE", paymentData);

        payment.setStatus("REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusPaymentVoucherInvalid() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "VOUCHER_CODE", paymentData);

        assertThrows (IllegalArgumentException.class, () -> {
            payment.setStatus("BLABLABLA");
        });
    }

    //COD
    @Test
    void testCreatePaymentCODSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Jalan");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "CASH_ON_DELIVERY", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testCreatePaymentCODRejectedMissingAddress(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentCODRejectedMissingDeliveryFee(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Pagi");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusPaymentCODSucess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Sore");
        paymentData.put("deliveryFee", "15000");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "CASH_ON_DELIVERY", paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }
    @Test
    void testSetStatusPaymentCODRejected(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Aja Dulu");
        paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                this.orders.getFirst(), "CASH_ON_DELIVERY", paymentData);

        payment.setStatus("REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }


    // Invalid payment method
    @Test
    void testCreatePaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");

        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("8e4b50d3-f58f-4b97-af2b-9fba86d6ebe7",
                    this.orders.getFirst(), "KASBON", paymentData);
        });
    }
}