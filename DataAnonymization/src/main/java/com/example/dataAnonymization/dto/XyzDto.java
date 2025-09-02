package com.example.dataAnonymization.dto;

public class XyzDto {
    private Integer externalNumber;
    private Integer internalNumber;
    private String isXyz;
    private String status;

    // Constructors
    public XyzDto() {}

    public XyzDto(Integer externalNumber, Integer internalNumber, String isXyz, String status) {
        this.externalNumber = externalNumber;
        this.internalNumber = internalNumber;
        this.isXyz = isXyz;
        this.status = status;
    }

    // Getters and Setters
    public Integer getExternalNumber() { return externalNumber; }
    public void setExternalNumber(Integer externalNumber) { this.externalNumber = externalNumber; }

    public Integer getInternalNumber() { return internalNumber; }
    public void setInternalNumber(Integer internalNumber) { this.internalNumber = internalNumber; }

    public String getIsXyz() { return isXyz; }
    public void setIsXyz(String isXyz) { this.isXyz = isXyz; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
