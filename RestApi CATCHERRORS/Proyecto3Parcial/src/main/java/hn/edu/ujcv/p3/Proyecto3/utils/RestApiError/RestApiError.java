package hn.edu.ujcv.p3.Proyecto3.utils.RestApiError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@ToString
public class RestApiError {
    HttpStatus httpsStatus;

    String errorMessage;
    String errorDetails;

}
