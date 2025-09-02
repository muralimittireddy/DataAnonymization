package com.example.dataAnonymization.writer;

import com.example.dataAnonymization.dto.DefDto;
import com.example.dataAnonymization.dto.Report_Dto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class DefWriter implements ItemWriter<DefDto> {
    @Override
    public void write(Chunk<? extends DefDto> chunk) throws Exception {
        for (DefDto record : chunk.getItems()) {
            System.out.println(record);  // just print
        }
    }
}
