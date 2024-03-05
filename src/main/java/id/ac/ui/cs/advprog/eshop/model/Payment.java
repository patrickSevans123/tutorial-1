package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    Order order;
    String method;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        boolean valid = false;
        if (method.equals("VOUCHER_CODE")) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
                int numCounter = 0;
                for (char currentChar : voucherCode.toCharArray()) {
                    if (Character.isDigit(currentChar)) {
                        numCounter++;
                    }
                }
                if (numCounter == 8) {
                    valid = true;
                }
            }
        } else if (method.equals("CASH_ON_DELIVERY")) {
            String address = paymentData.get("address");
            String deliveryFee = paymentData.get("deliveryFee");

            if (address != null && !address.isBlank() && deliveryFee != null && !deliveryFee.isBlank()) {
                valid = true;
            }
        } else {
            throw new IllegalArgumentException();
        }

        if (valid){
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else{
            setStatus(PaymentStatus.REJECTED.getValue());
        }
    }
    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        setStatus(status);
    }
    public void setStatus(String status){
        if (PaymentStatus.contains(status)){
            this.status = status;
            if (status.equals(OrderStatus.SUCCESS.getValue())){
                order.setStatus(OrderStatus.SUCCESS.getValue());
            } else {
                order.setStatus(OrderStatus.FAILED.getValue());
            }
        } else{
            throw new IllegalArgumentException();
        }

    }
}