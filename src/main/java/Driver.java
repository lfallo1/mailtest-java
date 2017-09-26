import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static java.lang.System.exit;

class Driver {

    /*


    Properties props = new Properties();
		props.put("mail.smtp.host", appConfig.getPalasEmailHost());
		props.put("mail.smtp.user", appConfig.getPalasEmailUserName());
//		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", appConfig.getPalasEmailSmtpPort());
//		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "false");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(appConfig.getPalasEmailUserName(), appConfig.getPalasEmailPassword());
			}
		});
	}

     */

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.ssl.trust", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("lfallon@sbgtv.com", "Raven$3431");
                    }
                });

//        props.put("mail.smtp.auth", "false");
//        props.put("mail.smtp.user", "dummy@dummy.com");
//        props.put("mail.smtp.starttls.enable", "false");
//        props.put("mail.smtp.host", "smtp.sbgnet.int");
//        props.put("mail.smtp.port", "25");
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("", "");
//                    }
//                });


        MimeBodyPart attachment1BodyPart = null;
        try {
            attachment1BodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource("C:\\Users\\lfallon\\Desktop\\D88521GC10_sg.pdf");
            attachment1BodyPart.setDataHandler(new DataHandler(source));
            attachment1BodyPart.setFileName("OpemMe.pdf"); // ex : "test.pdf"
        } catch (MessagingException e) {
            e.printStackTrace();
            exit(0);
            return;
        }

//        for (int i = 0; i < 1000; i++) {
//            String body = i % 2 == 0 ? "Don't touch the potato" : "You can touch the potato";
        try {

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("lfallon@sbgtv.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("palastwo@gmail.com"));
            msg.setSubject("Email Ingest Stress Test");
            Multipart multipart = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();

            textBodyPart.setText("this is a test");

            multipart.addBodyPart(attachment1BodyPart);
            multipart.addBodyPart(textBodyPart);

            msg.setContent(multipart);
            Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        }
    }
}