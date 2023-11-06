package com.cev.sircapcev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.cev.sircapcev.entity.PersonasEntity;
import com.cev.sircapcev.repositories.PersonasRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayService {

    private static final Logger logger = LoggerFactory.getLogger(BirthdayService.class);

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    //@Scheduled(cron = "0 45 21 * * ?")
    @Scheduled(fixedRate = 300000)
    public void sendBirthdayGreetings() {
        logger.info("Iniciando el proceso de envío de saludos de cumpleaños...");
        LocalDate today = LocalDate.now();
        List<PersonasEntity> birthdayPersons = personasRepository.findByFechaDeNacimiento(today);
        for (PersonasEntity person : birthdayPersons) {
            sendEmail(person);
        }
    }
    
    private void sendEmail(PersonasEntity person) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(person.getCorreoElectronicoPersonal());
        msg.setSubject("Feliz Cumpleaños");
        msg.setText("Feliz cumpleaños, " + person.getNombres() + "!");
        
        try {
            javaMailSender.send(msg);
            logger.info("Correo enviado exitosamente a {}", person.getCorreoElectronicoPersonal());
        } catch (Exception e) {
            logger.error("Error al enviar correo: {}", e.getMessage());
        }
    }
}
