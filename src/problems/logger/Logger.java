package problems.logger;

import problems.logger.destinations.LogAppender;
import problems.logger.models.Level;

public class Logger {

    private static Logger loggerInstance;
    private LoggerManager manager;

    private Logger() {
        this.manager = new LoggerManager();
    }

    public static Logger getLoggerInstance() {
        if (loggerInstance == null) {
            synchronized (Logger.class) {
                if (loggerInstance == null) {
                    loggerInstance = new Logger();
                }
            }
        }
        return loggerInstance;
    }

    public void setLevel(Level level) {
        manager.setLevel(level);
    }

    public void setLogAppender(LogAppender logAppender) {
        manager.setLogAppender(logAppender);
    }

    public void log(String message) {
        manager.logMessage(message);
    }
}
