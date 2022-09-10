import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DateHourLambda implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> payload, Context context) {
        logApplication(payload);

        final String region = payload.get("region");

        String response;
        if (region == null) {
            response = "404 - NOT FOUND";
            return response;
        }

        ZonedDateTime dateTime = getZonedDateTimeByRegion(region);
        String dateTimeFormatted = formatDateTime(dateTime);

        response = "RESPONSE: " + dateTimeFormatted + " (" + region + ")";
        return response;
    }

    private ZonedDateTime getZonedDateTimeByRegion(String region) {
        ZoneId zoneId = ZoneId.of(region);
        ZonedDateTime dateTime = ZonedDateTime.now(zoneId);
        return dateTime;
    }

    private String formatDateTime(ZonedDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    private void logApplication(Map<String, String> payload) {
        System.out.println("Handling lambda function.");
        System.out.println(payload);
    }
}
