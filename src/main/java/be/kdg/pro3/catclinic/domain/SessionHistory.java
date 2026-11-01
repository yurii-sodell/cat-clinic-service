package be.kdg.pro3.catclinic.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@SessionScope
public class SessionHistory{
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private List<String> history;

    public SessionHistory(){
        this.history = new ArrayList<>();
    }

    public void addLog(String eventInfo){
        history.add(formatter.format(Instant.now()) + " | " + eventInfo);
    }

    public List<String> getHistory(){
        return Collections.unmodifiableList(history);
    }
}
