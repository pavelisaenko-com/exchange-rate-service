package com.pavelisaenko.exchangerateservice;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Slf4j
@Service
public class ServiceScheduler {

//    private static final String CRON = "0 * * * * *";

    private final Parser parser;

    private final Exporter exporter;

    public ServiceScheduler(){
        this.parser = new RSHBParser();
        this.exporter = new CSVExporter();
    }


    @Scheduled(fixedRate = 21600000)    // cron = CRON
    public void parseAndExport(){
        exporter.exportItems(parser.parse());
        System.out.println("Hello");
    }

}
