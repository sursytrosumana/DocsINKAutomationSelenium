package com.orangehrm.utils;

import jakarta.mail.*;
import jakarta.mail.search.FlagTerm;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailOTPReader {

    public static String fetchOTP(String host, String user, String password) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");

            store.connect(host, user, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            for (int i = messages.length - 1; i >= 0; i--) {
                String content = messages[i].getContent().toString();

                // Regex for 6-digit OTP
                Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
                Matcher matcher = pattern.matcher(content);

                if (matcher.find()) {
                    messages[i].setFlag(Flags.Flag.SEEN, true);  // mark as read
                    return matcher.group();
                }
            }
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
