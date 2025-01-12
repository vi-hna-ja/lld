package problems.logger.destinations;

import problems.logger.models.Log;

public class FileAppender implements LogAppender {
    @Override
    public void appendMessage(Log log) {
        System.out.printf("%s [%s] [FILE] - %s\n", log.getTimestamp(), log.getLevel(), log.getMessage());
    }
}
