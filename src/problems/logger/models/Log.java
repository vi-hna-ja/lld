package problems.logger.models;

import java.util.Date;
import java.util.UUID;

public class Log {

    private final String id;
    private final String message;
    private final long timestamp;
    private final Level level;

    public Log(String message, Level level) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.level = level;
        this.timestamp = new Date().getTime();
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Level getLevel() {
        return level;
    }
}
