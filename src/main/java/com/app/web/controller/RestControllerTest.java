package com.app.web.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/uday")
@CrossOrigin(origins= {"https://udaywebtestapplication.cfapps.io/"})
//@CrossOrigin(origins = {"http://192.168.21.12:9090","http://localhost:4200"}, maxAge = 3600)
public class RestControllerTest {
	private static final Logger logger = Logger.getLogger("RestControllerTest");
	RestTemplate rt=new RestTemplate();
	HttpHeaders headers=new HttpHeaders();
	
	@PostMapping("/user/login")
	public ResponseEntity<?> login(@RequestBody Object user) {
		ResponseEntity<?> message=null;
		try{
			headers.add("Content-Type","application/json");
			headers.add("Accept","application/json");
			HttpEntity<Object> entity=new HttpEntity<>(user,headers);
			message=rt.postForEntity(InterceptorClass.url+InterceptorClass.salt()+"/uday/user/login",entity,String.class);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			message=new ResponseEntity<>("{\"text\":\"User details not logged successfully\" }",HttpStatus.NO_CONTENT);
		}
		return message;
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Object test) {
		ResponseEntity<?> message=null;
		try {
			headers.add("Content-Type","application/json");
			headers.add("Accept","application/json");
			HttpEntity<Object> entity=new HttpEntity<>(test,headers);
			message=rt.postForEntity(InterceptorClass.url+InterceptorClass.salt()+"/uday/save",entity,String.class);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			message=new ResponseEntity<>("{\"text\":\"User details not saved successfully\" }",HttpStatus.NO_CONTENT);
		}
		return message;
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		ResponseEntity<?> message=null;
		try {
			message=rt.getForEntity(InterceptorClass.url+InterceptorClass.salt()+"/uday/getAll",List.class);	
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			message=new ResponseEntity<>("{\"text\":\"User details not saved successfully\" }",HttpStatus.NO_CONTENT);
		}
		return message;
	}
	@GetMapping("/getAll/{email}")
	public ResponseEntity<?> getData(@PathVariable("email")String userEmail){
		ResponseEntity<?> message=null;
		try {
			message=rt.getForEntity(InterceptorClass.url+InterceptorClass.salt()+"/uday/getAll/"+userEmail,List.class);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			message=new ResponseEntity<>("{\"text\":\"User details not saved successfully\" }",HttpStatus.NO_CONTENT);
		}
		return message;
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Integer id){
		ResponseEntity<?> message=null;
		try {
			rt.delete(InterceptorClass.url+InterceptorClass.salt()+"/uday/delete/"+id);
			message=new ResponseEntity<>("{\"text\":\"User deleted successfully\"}",HttpStatus.OK) ;
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			message=new ResponseEntity<>("{\"text\":\"Not deleted\"} ",HttpStatus.NO_CONTENT);
		}
		return message;
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable("id")Integer id){
		ResponseEntity<?> message=null;
		try {
			message=rt.getForEntity(InterceptorClass.url+InterceptorClass.salt()+"/uday/get/"+id,Object.class);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			message=new ResponseEntity<>("{\"text\":\"No data found for id '"+id+"' \"}",HttpStatus.NO_CONTENT);
		}
		return message;
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Object test) {
		ResponseEntity<?> message=null;
		try{
			headers.add("Content-Type","application/json");
			headers.add("Accept","application/json");
			HttpEntity<Object> entity=new HttpEntity<>(test,headers);
			rt.put(InterceptorClass.url+InterceptorClass.salt()+"/uday/update",entity,headers);
			message=new ResponseEntity<>("{\"text\":\"User updated successfully\"}",HttpStatus.OK) ;
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			message=new ResponseEntity<>("{\"text\":\"User details not updated successfully\" }",HttpStatus.NO_CONTENT);
		}
		return message;
	}
}
