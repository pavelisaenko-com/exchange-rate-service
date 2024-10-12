package com.pavelisaenko.exchangerateservice;

// import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Slf4j
@Service
public class ServiceScheduler {

//    private static final String CRON = "0 * * * * *";


    @Scheduled(fixedRate = 10800000)    // cron = CRON
    public void parseAndExportRSHBToCSV(){
        Parser parser = new RSHBParser();
        Exporter exporter = new CSVExporter();
        exporter.exportItems(parser.parse());

        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("d.M.y H:m:s").toFormatter();
        String dateTime = LocalDateTime.now().format(dtf);
        System.out.printf("[%s] RSHB => CSV", dateTime);
    }

    @Scheduled(fixedRate = 1000)
    public void parseAndExportBPToCSV(){
        Parser parser = new BPParser();
        Exporter exporter = new CSVExporter();
        exporter.exportItems(parser.parse());

        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("d.M.y H:m:s").toFormatter();
        String dateTime = LocalDateTime.now().format(dtf);
        System.out.printf("[%s] BP => CSV", dateTime);
    }

}
