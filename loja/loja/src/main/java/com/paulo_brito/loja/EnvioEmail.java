package com.paulo_brito.loja;

import org.springframework.stereotype.Service;

@Service

public class EnvioEmail {
    public void enviarEmail(String para, String subject, String corpo) {
        System.out.println("Para: " + para);
        System.out.println("Assunto: "+ subject);
        System.out.println("Corpo: " + corpo);
        System.out.println("Atenciosamente, Paulo Phones.");
    }
}
