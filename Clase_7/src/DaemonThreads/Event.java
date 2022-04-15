package DaemonThreads;

import java.util.Date;

/**
 * Clase Event: Almacena solo información de los eventos.
 * Tiene 2 atributos privados y métodos getter y setter.
 */
public class Event {
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
