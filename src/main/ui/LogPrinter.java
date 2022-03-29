package ui;


import model.Event;
import model.EventLog;

/**
 * Defines behaviours that event log printers must support.
 */
public class LogPrinter {
    /**
     * Prints the log
     *
     * @param el the event log to be printed
     */
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }

    }
}

