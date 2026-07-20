package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String expediteur;

    @Override
    public void envoyerEmail(
            String destinataire,
            String sujet,
            String html)
    {

        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true,
                            "UTF-8"
                    );


            helper.setFrom(expediteur);

            helper.setTo(destinataire);

            helper.setSubject(sujet);

            helper.setText(html,true);


            mailSender.send(message);


        } catch (MessagingException e){

            throw new RuntimeException(
                    "Erreur lors de l'envoi du mail",
                    e
            );
        }
    }
}
