package org.jmx.gateway.service.com.entity;

public class CpuInfo {
    private Double percentage;

    public CpuInfo(Double percentage) {
        this.percentage = percentage;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }



}
