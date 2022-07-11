package stragan.stragan;

import java.math.BigDecimal;

public class ItemTransactionModel {
    private String name;
    private String shortName;
    private BigDecimal amount;
    private BigDecimal totalPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemTransactionModel(){
        this("", "", BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public ItemTransactionModel(String name, String shortName, BigDecimal amount, BigDecimal totalPrice) {
        this.name = name;
        this.shortName = shortName;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
