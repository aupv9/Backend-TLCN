package datve.com.controller;


import datve.com.model.Mail;
import datve.com.model.MailRequest;
import datve.com.model.Ve;
import datve.com.service.ve.VeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@RestController
@RequestMapping("/")
public class VeController {

    @Autowired
    @Qualifier("veService")
    VeServiceImpl veService;

    @Autowired
    public JavaMailSender emailSender;

    @RequestMapping(value = "/api/ve", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Ve> addVe(@RequestBody Ve ve) {
        HttpHeaders headers = new HttpHeaders();
        if (veService.addVe(ve)) {
            return new ResponseEntity<Ve>(ve, headers, HttpStatus.OK);
        }
        return new ResponseEntity<Ve>(ve, headers, HttpStatus.NOT_EXTENDED);
    }

    @RequestMapping(value = "/api/ve-cancel", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Ve> cancelVe(@RequestBody Ve ve) {
        HttpHeaders headers = new HttpHeaders();

        if (veService.cancelVe(ve)) {
            headers.add("token-mess", "OK");
            return new ResponseEntity<Ve>(ve, headers, HttpStatus.OK);
        }
        return new ResponseEntity<Ve>(ve, headers, HttpStatus.NOT_MODIFIED);
    }

    @ResponseBody
    @RequestMapping(value = "api/sendEmail", method = RequestMethod.POST, produces = "text/plain")
    public String sendSimpleEmail(@RequestBody MailRequest mailRequest) {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailRequest.getEmail());
        message.setSubject("Thông Báo Đặt Vé");
        message.setText("Mã vé của bạn đã đặt là" + " " + mailRequest.get_id());

        // Send Message
        this.emailSender.send(message);

        return "Email Sent!";
    }

    @RequestMapping(value = "/api/search-ve", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Ve> searchVe(@RequestBody Ve ve) {
        HttpHeaders headers = new HttpHeaders();
        Ve ticket = veService.searchVe(ve.get_id(), ve.getSdt());
        if (ticket != null) {
            return new ResponseEntity<Ve>(ticket, headers, HttpStatus.OK);
        }
        return new ResponseEntity<Ve>(null, headers, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/search-ve-day", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<Ve>> searchVeToDay(@RequestBody Ve ve) {
        HttpHeaders headers = new HttpHeaders();
        List<Ve> ticket = veService.listVeToDate(ve.getNgaydat());
        if (ticket != null) {
            return new ResponseEntity<List<Ve>>(ticket, headers, HttpStatus.OK);
        }
        return new ResponseEntity<List<Ve>>(null, headers, HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/api/get-ve", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Ve>> getVe() {
        HttpHeaders headers = new HttpHeaders();
        List<Ve> ticket = veService.getVe();
        if (ticket != null) {
            return new ResponseEntity<List<Ve>>(ticket, headers, HttpStatus.OK);
        }
        return new ResponseEntity<List<Ve>>(null, headers, HttpStatus.NOT_FOUND);
    }
}
