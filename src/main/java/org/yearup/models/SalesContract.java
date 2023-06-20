package org.yearup.models;

import java.math.BigDecimal;

public class SalesContract
{
    private int salesId;
    private String vin;
    private String customerName;
    private String customerEmail;
    private BigDecimal salesPrice;
    private BigDecimal recordingFee;
    private BigDecimal processingFee;
    private BigDecimal salesTax;

    public int getSalesId()
    {
        return salesId;
    }

    public void setSalesId(int salesId)
    {
        this.salesId = salesId;
    }

    public String getVin()
    {
        return vin;
    }

    public void setVin(String vin)
    {
        this.vin = vin;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail)
    {
        this.customerEmail = customerEmail;
    }

    public BigDecimal getSalesPrice()
    {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice)
    {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getRecordingFee()
    {
        recordingFee = BigDecimal.valueOf(100);
        return recordingFee;
    }

    public void setRecordingFee(BigDecimal recordingFee)
    {
        this.recordingFee = recordingFee;
    }

    public BigDecimal getProcessingFee()
    {
        BigDecimal threshold = new BigDecimal("10000");
        BigDecimal lowerProcessingFee = new BigDecimal("295");
        BigDecimal higherProcessingFee = new BigDecimal("495");

        if (salesPrice.compareTo(threshold) < 0)
        {
            processingFee = lowerProcessingFee;
        }
        else
        {
            processingFee = higherProcessingFee;
        }

        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee)
    {
        this.processingFee = processingFee;
    }

    public BigDecimal getSalesTax()
    {
        salesTax = salesPrice.multiply(BigDecimal.valueOf(0.08));
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax)
    {
        this.salesTax = salesTax;
    }
}
