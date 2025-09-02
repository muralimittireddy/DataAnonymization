package com.example.dataAnonymization.reader;

import com.example.dataAnonymization.dto.AbcDto;
import com.example.dataAnonymization.dto.Report_Dto;
import com.example.dataAnonymization.enums.SqlEnum;
import com.example.dataAnonymization.mapper.AbcMapper;
import com.example.dataAnonymization.mapper.Report_Mapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;


@Component
@StepScope
public class AbcReader extends JdbcPagingItemReader<AbcDto> {

    SqlEnum query = SqlEnum.ABC_Reader;
    public AbcReader(DataSource dataSource) {

        setDataSource(dataSource);
        setPageSize(100); // batch size per fetch
        setRowMapper(new AbcMapper());

        // Query provider for paging (thread-safe)
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause(query.getSelectClause());
        queryProvider.setFromClause("FROM " + query.getFromClause());
        queryProvider.setWhereClause("WHERE " + query.getWhereClause());
        queryProvider.setSortKey("external_number");

        try {
            setQueryProvider(queryProvider.getObject());
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize query provider", e);
        }

    }
}
