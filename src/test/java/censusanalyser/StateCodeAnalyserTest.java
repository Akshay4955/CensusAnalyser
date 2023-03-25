package censusanalyser;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class StateCodeAnalyserTest {
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String CSV_FILE_WRONG_EXTENSION = "./src/test/resources/IndiaStateCode.ext";
    private static final String CSV_FILE_WRONG_DELIMITER = "./src/test/resources,IndiaStateCode.csv";
    private static final String CSV_FILE_WRONG_HEADER = "./src/test/resources/IndiaStateCodeHeader.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            int numOfRecords = stateCodeAnalyser.loadIndianStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (StateCodeAnalyserException e) {

        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCodeAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCodeData(WRONG_CSV_FILE_PATH);
        } catch (StateCodeAnalyserException e) {
            Assert.assertEquals(StateCodeAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusCSVFile_WhenIncorrectType_ShouldThrowException() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCodeAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCodeData(CSV_FILE_WRONG_EXTENSION);
        } catch (StateCodeAnalyserException e) {
            Assert.assertEquals(StateCodeAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusCSVFile_WhenIncorrectDelimiter_ShouldThrowException() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCodeAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCodeData(CSV_FILE_WRONG_DELIMITER);
        } catch (StateCodeAnalyserException e) {
            Assert.assertEquals(StateCodeAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusCSVFile_WhenIncorrectHeader_ShouldThrowException() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(StateCodeAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCodeData(CSV_FILE_WRONG_HEADER);
        } catch (StateCodeAnalyserException e) {
            Assert.assertEquals(StateCodeAnalyserException.ExceptionType.STATE_CODE_FILE_HEADER_PROBLEM, e.type);
        }
    }
}
