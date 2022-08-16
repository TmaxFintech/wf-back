package tmaxfintech.wf.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import tmaxfintech.wf.util.response.DefaultResponse;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @Value("${responseMessage.NOT_FOUND_USER}")
    private String NOT_FOUND_USER;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handelAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handelUserNotFoundException(Exception ex, WebRequest request){

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(DefaultResponse.response(HttpStatus.UNAUTHORIZED.value(), NOT_FOUND_USER), HttpStatus.NOT_FOUND);
    }

}
