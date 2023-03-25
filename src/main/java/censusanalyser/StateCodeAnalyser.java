package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCodeAnalyser {
    public int loadIndianStateCodeData(String csvFilePath) throws StateCodeAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = csvToBean.iterator();
            Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCodeCSVIterator;
            int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfEntries;
        } catch (IOException e) {
            throw new StateCodeAnalyserException(e.getMessage(),
                    StateCodeAnalyserException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            throw new StateCodeAnalyserException(e.getMessage(),
                    StateCodeAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new StateCodeAnalyserException(e.getMessage(),
                    StateCodeAnalyserException.ExceptionType.STATE_CODE_FILE_HEADER_PROBLEM);
        }
    }
}
