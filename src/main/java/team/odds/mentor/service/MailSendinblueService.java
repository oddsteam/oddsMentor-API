package team.odds.mentor.service;

import org.springframework.beans.factory.annotation.Value;
import team.odds.mentor.model.Booking;
import org.springframework.stereotype.Service;
import sibApi.TransactionalEmailsApi;

import java.util.*;

import sendinblue.*;
import sendinblue.auth.*;
import sibModel.*;

@Service
public class MailSendinblueService {

    @Value("${sendinblue.token}")
    private String sendinblueToken;

    public boolean mailToUser(Booking booking) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(sendinblueToken);

        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail("odds.molamola@gmail.com");
        sender.setName("Odds Mentor");

        var sendTo = new SendSmtpEmailTo();
        sendTo.setEmail(booking.getUserEmail());
        sendTo.setName(booking.getUserFullName());

        var replyTo = new SendSmtpEmailReplyTo();
        replyTo.setEmail("odds.molamola@gmail.com");
        replyTo.setName("Odds Booking");

        var templateCtx = new Properties();
        templateCtx.setProperty("fullNameEN", booking.getMentorFullName());
        templateCtx.setProperty("reason", booking.getReason());
        templateCtx.setProperty("sessionDate", booking.getSessionDate().toString());
        templateCtx.setProperty("duration", booking.getSessionDuration().toString());

        var mailCompose = new SendSmtpEmail();
        mailCompose.sender(sender);
        mailCompose.replyTo(replyTo);
        mailCompose.to(List.of(sendTo));
        mailCompose.setParams(templateCtx);
        mailCompose.templateId(10L);

        var api = new TransactionalEmailsApi();
        var errorMessage = "";
        try {
            api.sendTransacEmail(mailCompose);
        } catch (ApiException e) {
            errorMessage = e.getMessage();
        }
        return errorMessage.length() != 0;
    }
}
