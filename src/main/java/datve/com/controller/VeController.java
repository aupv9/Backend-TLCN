package datve.com.controller;


import datve.com.model.Ve;
import datve.com.service.ve.VeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class VeController {

    @Autowired
    @Qualifier("veService")
    VeServiceImpl veService;

    @RequestMapping(value = "/api/ve",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<Ve> addVe(@RequestBody Ve ve){
        HttpHeaders headers=new HttpHeaders();
        if(veService.addVe(ve)){
            return  new ResponseEntity<Ve>(ve,headers, HttpStatus.OK);
        }
        return new ResponseEntity<Ve>(ve,headers, HttpStatus.NOT_EXTENDED);
    }

    @RequestMapping(value = "/api/ve-cancel",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<Ve> cancelVe(@RequestBody Ve ve){
        HttpHeaders headers=new HttpHeaders();

        if(veService.cancelVe(ve)){
            headers.add("token-mess","OK");
            return new ResponseEntity<Ve>(ve,headers, HttpStatus.OK);
        }
        return new ResponseEntity<Ve>(ve,headers, HttpStatus.NOT_MODIFIED);
    }

}
