package com.pavelisaenko.exchangerateservice;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Slf4j
@Service
public class ServiceScheduler {

//    private static final String CRON = "0 * * * * *";


    @Scheduled(fixedRate = 5160)    // cron = CRON  21600000
    public void parseAndExport(){
        Parser parser = new RSHBParser();
        Exporter exporter = new CSVExporter();
        exporter.exportItems(parser.parse());
        System.out.println("Hello");
    }

}
