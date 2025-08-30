package com.example.dataAnonymization.mapper;

import com.example.dataAnonymization.dto.Report_Dto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report_Mapper implements RowMapper<Report_Dto> {
    @Override
    public Report_Dto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report_Dto record = new Report_Dto();

        record.setExternalNumber(rs.getInt("external_number"));
        record.setInternalNumber(rs.getInt("internal_number"));
        record.setSystem(rs.getString("system"));
        record.setIsabc(rs.getString("isabc"));
        record.setIsxyz(rs.getString("isxyz"));
        record.setIsdef(rs.getString("isdef"));
        record.setStatus(rs.getString("status"));
        return record;
    }
}
