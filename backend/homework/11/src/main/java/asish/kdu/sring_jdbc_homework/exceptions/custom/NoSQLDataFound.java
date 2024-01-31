package asish.kdu.sring_jdbc_homework.exceptions.custom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoSQLDataFound extends Exception {
    public NoSQLDataFound(String errorMessage, Throwable err) {
        super(errorMessage, err);
        log.error(errorMessage, err);
    }
}
