package com.example.dataAnonymization.dto;

public class AbcDto {


    private Integer externalNumber;
    private Integer internalNumber;
    private String isAbc;
    private String status;

    // Constructors
    public AbcDto() {}

    public AbcDto(Integer externalNumber, Integer internalNumber, String isAbc, String status) {
        this.externalNumber = externalNumber;
        this.internalNumber = internalNumber;
        this.isAbc = isAbc;
        this.status = status;
    }

    // Getters and Setters
    public Integer getExternalNumber() { return externalNumber; }
    public void setExternalNumber(Integer externalNumber) { this.externalNumber = externalNumber; }

    public Integer getInternalNumber() { return internalNumber; }
    public void setInternalNumber(Integer internalNumber) { this.internalNumber = internalNumber; }

    public String getIsAbc() { return isAbc; }
    public void setIsAbc(String isAbc) { this.isAbc = isAbc; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
