package com.example.dataAnonymization.enums;

public enum SqlEnum {




    PENDING_REPORTS(
        "SELECT external_number, internal_number, system, isabc, isxyz, isdef, status",
                "report_table",
                "status = 'PENDING'"
    ),

    ABC_Reader(
        "SELECT external_number, internal_number, isabc, status",
                "report_table",
                "status = 'PENDING' and isabc='Y'"
    ),

    DEF_Reader(
            "SELECT external_number, internal_number, isdef, status",
            "report_table",
            "status = 'PENDING' and isdef='Y'"
    ),

    XYZ_READER(
            "SELECT external_number, internal_number,  isxyz, status",
            "report_table",
            "status = 'PENDING' and isxyz='Y'"
    );


    private final String selectClause;
    private final String fromClause;
    private final String whereClause;

    SqlEnum(String selectClause, String fromClause, String whereClause) {
        this.selectClause = selectClause;
        this.fromClause = fromClause;
        this.whereClause = whereClause;
    }

    public String getSelectClause() { return selectClause; }
    public String getFromClause() { return fromClause; }
    public String getWhereClause() { return whereClause; }
}
