import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class DateHourLambda implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> payload, Context context) {
        String response = "200 OK";
        System.out.println("Handling lambda function.");
        System.out.println(payload);
        return response;
    }
}
