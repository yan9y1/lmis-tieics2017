package cn.edu.tju.tiei.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cn.edu.tju.tiei.logistics.model.Usr;
import cn.edu.tju.tiei.logistics.service.IUsrService;


@RestController
public class UsrRestController {

	@Autowired
	private IUsrService usrService;
	
	/**
	 * Retrieve all usrs
	 * @return
	 */
    @RequestMapping(value = "/usrs", method = RequestMethod.GET)
    public ResponseEntity<List<Usr>> listAll(){
        List<Usr> usrs = usrService.findAll();
        if (usrs.isEmpty()) {
            return new ResponseEntity<List<Usr>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usr>>(usrs, HttpStatus.OK);
    }
    
    /**
     * Retrieve a single usr
     * @param id
     * @return
     */
    @RequestMapping(value = "/usrs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usr> get(@PathVariable("id") String id) {
    	Usr usr = usrService.findById(id);
        if (usr == null) {
            return new ResponseEntity<Usr>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usr>(usr, HttpStatus.OK);
    }
    
    /**
     * Create a usr
     * @param usr
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/signUp/usrs", method = RequestMethod.POST)
    public ResponseEntity<Void> create(Usr usr, UriComponentsBuilder ucBuilder) {
        if (usrService.isExist(usr)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        usrService.create(usr);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/usr/{id}").buildAndExpand(usr.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    /**
     * Update a usr
     * @param id
     * @param usr
     * @return
     */
    @RequestMapping(value = "/usrs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usr> update(@PathVariable("id") String id, Usr usr) {
        Usr oldUsr = usrService.findById(id);
        if (oldUsr==null) {
            return new ResponseEntity<Usr>(HttpStatus.NOT_FOUND);
        }
        oldUsr.setName(usr.getName());
        usrService.update(oldUsr);
        return new ResponseEntity<Usr>(oldUsr, HttpStatus.OK);
    }
    
    /**
     * Delete a usr
     * @param id
     * @return
     */
    @RequestMapping(value = "/usrs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Usr> delete(@PathVariable("id") String id) {
        Usr usr = usrService.findById(id);
        if (usr == null) {
             return new ResponseEntity<Usr>(HttpStatus.NOT_FOUND);
        }
        usrService.deleteById(id);
        return new ResponseEntity<Usr>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Delete all usrs
     * @return
     */
    @RequestMapping(value = "/usrs", method = RequestMethod.DELETE)
    public ResponseEntity<Usr> deleteAll() {
        usrService.deleteAll();
        return new ResponseEntity<Usr>(HttpStatus.NO_CONTENT);
    }

}
