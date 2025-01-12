package problems.logger;


import problems.logger.destinations.LogAppender;
import problems.logger.models.Level;
import problems.logger.models.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LoggerManager {
    private Level currentLevel;
    private Set<LogAppender> currentLogAppenders;
    private ConcurrentLinkedQueue<Log> logQueue;

    public LoggerManager() {
        this.currentLevel = Level.INFO;
        this.currentLogAppenders = new HashSet<>();
        this.logQueue = new ConcurrentLinkedQueue<>();
    }

    public synchronized void setLevel(Level level) {
        this.currentLevel = level;
    }

    public synchronized void setLogAppender(LogAppender logAppender) {
        currentLogAppenders.add(logAppender);
    }

    public synchronized void logMessage(String message) {
        Log log = new Log(message, this.currentLevel);
        logQueue.add(log);
        System.out.printf("Appending log %s with message %s\n", log.getId(), message);
        currentLogAppenders.forEach(appender -> appender.appendMessage(log));
    }
}
