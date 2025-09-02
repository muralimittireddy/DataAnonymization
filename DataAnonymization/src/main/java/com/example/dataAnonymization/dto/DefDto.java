package com.example.dataAnonymization.dto;

public class DefDto {
    private Integer externalNumber;
    private Integer internalNumber;
    private String isDef;
    private String status;

    // Constructors
    public DefDto() {}

    public DefDto(Integer externalNumber, Integer internalNumber, String isDef, String status) {
        this.externalNumber = externalNumber;
        this.internalNumber = internalNumber;
        this.isDef = isDef;
        this.status = status;
    }

    // Getters and Setters
    public Integer getExternalNumber() { return externalNumber; }
    public void setExternalNumber(Integer externalNumber) { this.externalNumber = externalNumber; }

    public Integer getInternalNumber() { return internalNumber; }
    public void setInternalNumber(Integer internalNumber) { this.internalNumber = internalNumber; }

    public String getIsDef() { return isDef; }
    public void setIsDef(String isDef) { this.isDef = isDef; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
