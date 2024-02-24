package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSService {
    // Twilio credentials
    public static final String ACCOUNT_SID = "AC42e830f6807dd64c45614dc9a40b21e5";
    public static final String AUTH_TOKEN = "4b775a062aa7bdba85bbe9a5dbbb0dab";

    public static boolean sendSMS(String to, String message) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            // Send SMS message

            Message.creator(
                            new PhoneNumber(to), // Recipient's phone number
                            new PhoneNumber("+14152339647"), // Your Twilio phone number
                            message) // Message content
                    .create();

            return true; // SMS sent successfully
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Failed to send SMS
        }
    }
}

