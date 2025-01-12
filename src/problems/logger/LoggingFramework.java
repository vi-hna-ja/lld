package problems.logger;

import problems.logger.destinations.ConsoleAppender;
import problems.logger.destinations.DatabaseAppender;
import problems.logger.destinations.FileAppender;
import problems.logger.models.Level;

/*
Requirements:
    The logging framework should support different log levels, such as DEBUG, INFO, WARNING, ERROR, and FATAL.
    It should allow logging messages with a timestamp, log level, and message content.
    The framework should support multiple output destinations, such as console, file, and database.
    It should provide a configuration mechanism to set the log level and output destination.
    The logging framework should be thread-safe to handle concurrent logging from multiple threads.
    It should be extensible to accommodate new log levels and output destinations in the future.
 */

public class LoggingFramework {
    public void run() {
        Logger logger = Logger.getLoggerInstance();
        logger.setLevel(Level.DEBUG);
        logger.setLogAppender(new FileAppender());
        logger.log("This is a debug message");
        logger.setLogAppender(new DatabaseAppender());
        logger.log("This is a log");
        logger.setLevel(Level.ERROR);
        logger.setLogAppender(new ConsoleAppender());
        logger.log("Logging error message");
    }
}
