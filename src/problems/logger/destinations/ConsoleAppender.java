package problems.logger.destinations;

import problems.logger.models.Log;

public class ConsoleAppender implements LogAppender {
    @Override
    public void appendMessage(Log log) {
        System.out.printf("%s [%s] [CONSOLE] - %s\n", log.getTimestamp(), log.getLevel(), log.getMessage());
    }
}

