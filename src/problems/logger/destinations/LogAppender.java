package problems.logger.destinations;

import problems.logger.models.Log;

public interface LogAppender {
    void appendMessage(Log log);
}
