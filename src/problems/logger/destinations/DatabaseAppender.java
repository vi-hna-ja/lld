package problems.logger.destinations;

import problems.logger.models.Log;

public class DatabaseAppender implements LogAppender {
    @Override
    public void appendMessage(Log log) {
        System.out.printf("%s [%s] [DATABASE] - %s", log.getTimestamp(), log.getLevel(), log.getMessage());
    }
}

