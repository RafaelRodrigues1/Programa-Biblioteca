package com.projetointegrador.model.services.emailservice;

//import javax.mail.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService extends Thread {
    
    private Transport transport;
    private final String host = "smtp.gmail.com";
    private final String login = getProperties().getProperty("login");
    private final String senha = getProperties().getProperty("senha");
    private final String assunto;
    private final String texto;
    private final String destinatario;
    
    
    public EmailService(String assunto, String texto, String destinatario){
        this.assunto = assunto;
        this.texto = texto;
        this.destinatario = destinatario;
    }
    
    @Override
    public void run(){
        enviaEmail();
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

            InternetAddress endereco = new InternetAddress(destinatario);
            message.addRecipient(Message.RecipientType.TO, endereco);

            transport = session.getTransport("smtps");
            transport.connect(host, login, senha);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Email enviado com sucesso!" + destinatario);
            return true;
        }catch(MessagingException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    private Properties getProperties(){
        try(FileInputStream fis = new FileInputStream(new File("C:\\Users\\RAFAEL\\Documents\\NetBeansProjects\\AppProjetoBiblioteca\\src\\com\\projetointegrador\\model\\services\\emailservice\\emaildata.properties"))){
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
