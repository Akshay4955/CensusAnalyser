package censusanalyser;

public class StateCodeAnalyserException extends Exception {
    enum ExceptionType {
        UNABLE_TO_PARSE, STATE_CODE_FILE_HEADER_PROBLEM, STATE_CODE_FILE_PROBLEM
    }

    StateCodeAnalyserException.ExceptionType type;

    public StateCodeAnalyserException(String message, StateCodeAnalyserException.ExceptionType type) {
        super(message);
        this.type = type;
    }

    public StateCodeAnalyserException(String message, StateCodeAnalyserException.ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
