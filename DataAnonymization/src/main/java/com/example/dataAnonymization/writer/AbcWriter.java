package com.example.dataAnonymization.writer;

import com.example.dataAnonymization.dto.AbcDto;
import com.example.dataAnonymization.dto.Report_Dto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class AbcWriter implements ItemWriter<AbcDto> {


    @Override
    public void write(Chunk<? extends AbcDto> chunk) throws Exception {
        for (AbcDto record : chunk.getItems()) {
            System.out.println(record);  // just print
        }
    }
}
