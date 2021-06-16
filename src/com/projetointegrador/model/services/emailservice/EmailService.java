package com.projetointegrador.model.services.emailservice;

//import javax.mail.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    
    private Transport transport;
    private final String host = "smtp.gmail.com";
    private final String login = "softbibliotecadsb@gmail.com";
    private final String senha = "softbiblioteca.";
    private final String assunto;
    private final String texto;
    private final String destinatario;
    //private final List<String> listEmails = new ArrayList<>();
    
    
    public EmailService(String assunto, String texto, String destinatario){
        this.assunto = assunto;
        this.texto = texto;
        this.destinatario = destinatario;
        //this.listEmails.addAll(listEmails);
    }
    
    public Boolean enviaEmail(){
    try{
        Properties props = System.getProperties();
//        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", login);
        props.put("mail.smtp.password", senha);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(login));
        message.setSubject(assunto);
        message.setText(texto);

//        List<InternetAddress> setEnderecos = new ArrayList<>();
//        for(String email: listEmails){
//            setEnderecos.add(new InternetAddress(email));
//        }
//        for(InternetAddress endereco: setEnderecos){
//            message.addRecipient(Message.RecipientType.TO, endereco);
//        }
        InternetAddress endereco = new InternetAddress(destinatario);
        message.addRecipient(Message.RecipientType.TO, endereco);
        
        transport = session.getTransport("smtps");
        transport.connect(host, login, senha);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("Emails enviados com sucesso!");
        return true;
        }catch(MessagingException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
