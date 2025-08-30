package com.example.dataAnonymization.dto;

public class Report_Dto {

    private Integer externalNumber;
    private Integer internalNumber;
    private String system;
    private String isabc;
    private String isxyz;
    private String isdef;
    private String status;

    // Default constructor
    public Report_Dto() {
    }

    // All-args constructor
    public Report_Dto(Integer externalNumber, Integer internalNumber, String system,
                               String isabc, String isxyz, String isdef, String status) {
        this.externalNumber = externalNumber;
        this.internalNumber = internalNumber;
        this.system = system;
        this.isabc = isabc;
        this.isxyz = isxyz;
        this.isdef = isdef;
        this.status = status;
    }

    // Getters and Setters
    public Integer getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(Integer externalNumber) {
        this.externalNumber = externalNumber;
    }

    public Integer getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(Integer internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getIsabc() {
        return isabc;
    }

    public void setIsabc(String isabc) {
        this.isabc = isabc;
    }

    public String getIsxyz() {
        return isxyz;
    }

    public void setIsxyz(String isxyz) {
        this.isxyz = isxyz;
    }

    public String getIsdef() {
        return isdef;
    }

    public void setIsdef(String isdef) {
        this.isdef = isdef;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: toString for logging
    @Override
    public String toString() {
        return "AnonymizationRecord{" +
                "externalNumber=" + externalNumber +
                ", internalNumber=" + internalNumber +
                ", system='" + system + '\'' +
                ", isabc='" + isabc + '\'' +
                ", isxyz='" + isxyz + '\'' +
                ", isdef='" + isdef + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
