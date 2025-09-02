package com.example.dataAnonymization.enums;

public enum UpdateSqlEnum {

    ABC1_UPDATE("UPDATE abc_table1 SET name = '####' WHERE external_number = ? "),
    ABC2_UPDATE("UPDATE abc_table2 SET name = '####' WHERE external_number = ? "),
    ABC3_UPDATE("UPDATE abc_table3 SET name = '####' WHERE external_number = ? "),
    DEF1_UPDATE("UPDATE def_table1 SET email = '####' WHERE external_number = ? "),
    DEF2_UPDATE("UPDATE def_table2 SET email = '####' WHERE external_number = ? "),
    DEF3_UPDATE("UPDATE def_table3 SET email = '####' WHERE external_number = ? "),
    XYZ1_UPDATE("UPDATE xyz_table1 SET phone = '####' WHERE external_number = ? "),
    XYZ2_UPDATE("UPDATE xyz_table2 SET phone = '####' WHERE external_number = ? "),
    XYZ3_UPDATE("UPDATE xyz_table3 SET phone = '####' WHERE external_number = ? "),
    XYZ4_UPDATE("UPDATE xyz_table4 SET phone = '####' WHERE external_number = ? ");

    private final String query;

    UpdateSqlEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
