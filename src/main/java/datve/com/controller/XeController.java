package datve.com.controller;

import datve.com.model.Xe;
import datve.com.service.xe.XeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")

public class XeController {

    @Autowired
    @Qualifier("xeService")
    XeServiceImpl xeService;

    /*
     * find các xe có đủ điều kiện guest cần
     * @param start mã nơi đi
     * @param end mã nơi đến
     * @param date ngày đi
     */
    @RequestMapping(value = "/api/Cars/start={start}&end={end}&date={date}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Xe>> getXes(@PathVariable int start, @PathVariable int end, @PathVariable String date) {

        HttpHeaders headers = new HttpHeaders();
        List<Xe> listXe = xeService.searchXe(start, end, date);

        if (listXe == null) {
            return new ResponseEntity<List<Xe>>(HttpStatus.NOT_FOUND);
        }
        headers.add("Numbers", String.valueOf(listXe.size()));

        return new ResponseEntity<List<Xe>>(listXe, headers, HttpStatus.OK);
    }

    /*
     * add xe
     * kèm theo x-user-token để xác thực
     * */
    @RequestMapping(value = "/api/Car", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Xe> addXe(@RequestBody Xe xe) {
        HttpHeaders headers = new HttpHeaders();

        if (xeService.addXe(xe)) {
            return new ResponseEntity<Xe>(xe, headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<Xe>(xe, headers, HttpStatus.NOT_EXTENDED);
    }

    @RequestMapping(value = "/api/Car/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Xe> updateXe(@RequestBody Xe xe) {

        HttpHeaders headers = new HttpHeaders();
        if (xeService.updateXe(xe)) {
            return new ResponseEntity<Xe>(xe, headers, HttpStatus.OK);
        }
        return new ResponseEntity<Xe>(xe, headers, HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(value = "/api/Car/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Xe> deleteXe(@RequestBody Xe xe) {

        HttpHeaders headers = new HttpHeaders();
        if (xeService.deleteXe(xe)) {
            return new ResponseEntity<Xe>(xe, headers, HttpStatus.OK);
        }
        return new ResponseEntity<Xe>(xe, headers, HttpStatus.NOT_MODIFIED);
    }
    /**/

        @RequestMapping(value = "/api/Cars", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<List<Xe>> getAll() {
            HttpHeaders headers = new HttpHeaders();
            List<Xe> listXe = xeService.getXes();

            if (listXe == null) {
                return new ResponseEntity<List<Xe>>(HttpStatus.NOT_FOUND);
            }
            headers.add("Numbers", String.valueOf(listXe.size()));

            return new ResponseEntity<List<Xe>>(listXe, headers, HttpStatus.OK);
        }


}
