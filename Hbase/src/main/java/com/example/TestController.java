package com.example;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController // =@ResponseBody＋ @Controller
@RequestMapping("/testController")
public class TestController {

@Autowired
HbaseDao hbaseDao;
	   
		@RequestMapping("/createTable")
		String addUser(@FormParam("tablename")String tablename, @FormParam("columnFamily")String columnFamily) throws Exception {
			hbaseDao.createTable(tablename, columnFamily);
			return "createTable";
		}
		

		
}
