package com.BillingApp.DTO;


public class ChartDataDTO {
    private String label; // e.g. Jan, Feb
    private double value; // amount

    public ChartDataDTO() {
    }

    public ChartDataDTO(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
