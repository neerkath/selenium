package com.example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public class Addcontroller {
	
	public static void main(String[] args) {
		
		//request for BDD style
		@Test
		public void getallemployees() {
			RestAssured.given()
			.baseUri("http://localhost:3000")
			.header("content-Type","application/json")
			.body("{\r\n"
					+\"firstname\":\Raja\",\r\n"
					+"\r\n"
					+"}")
			.when()
			.post("/employees").prettyPrint();
		}
		
		
		//Using without passing value with external file
		
		@Test
		public void getallemployeesfromjson() {
			
			File jsonfile=new File("postdata.json");//this is the file name
			 given()
			.baseUri("http://localhost:3000")
			.header("content-Type","application/json")
			.body(jsonfile)
			.when()
			.post("/employees").prettyPrint();
		}
		
		
		//using seralization in json format from map creating the pojo class getter and setter and constructor and object mapper
		Map<String,Object> jsondata=new HashMap<String,Object>(); //object can hold anything
		
		List<String> skills=new ArrayList<String>();
		skills.add("java");
		skills.add("selenium");
		//Skills.setSkill(Arrays.asList("java","selenium"));
		
		jsondata.put("name","nkl");
		jsondata.put("email", "nkl@gmail.com");
		jsondata.put("skill", skills);
		System.out.println(jsondata);
		
		//adding jackson-databind library jar files
		 given()
		.baseUri("http://localhost:3000")
		.header("content-Type","application/json")
		.body(jsondata)
		.log()
		.all()
		.when()
		.post("/employees")
		.then.log().all();
		
      ObjectMapper mapper=new ObjectMapper();
      String employeejson =mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper);     
	  System.out.println(employeejson);
	  
	  
	  /* json schema---(filename :schema.json){
  "$schema": "http://json-schema.org/draft-07/schema#",//meta data definition
  "type": "object",
  "properties": {
    "name": {
      "type": "string"
    },
    "age": {
      "type": "integer"
    },
   
    "courses": {
      "type": "array",
      "items": {
        "type": "string"
      }
    },
    "address": {
      "type": "object",
      "properties": {
        "city": {
          "type": "string"
        },
        "zipCode": {
          "type": "string"
        }
      },
      "required": ["city", "zipCode"]
    }
  },
  "required": ["name", "age", "isStudent", "courses", "address"]
}*/
	  
	  
	  
	  //jsonschema validation the responses
	  public void validationjsonschemaInclasspath() {
		  File jsonfile=new File("src/test/resources/schema.json");
		     given()
			.baseUri("http://localhost:3000")
			.header("content-Type","application/json")
			.body(jsonfile)
			.when()
			.post("/employees")
			.then()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));//this line represents the above jsonschema format and same as filename
		  //.body(JsonSchemaValidator.matchesJsonSchema("schema.json"));without the classpath in validation		     
	  }
	  
	  
	//jsonpath using java in filepath--jayway jsonpath
		public static void readJson() {
			File file =new File("src/test/resources/bookstore.json");//file name in bookstore.json--json values
			List<Object> priceList=JsonPath.read(file,"S..price");
			for(Object price:priceList) {
				System.out.println(price);
			}
		}
	 
		//multiple times read the json file using parsejson,parse function accepts the file input stream
		public static void readJson1() {
			InputStream file =new FileInputStream("src/test/resources/bookstore.json");//file name in bookstore.json--json values
			
			Configuration.defaultConfiguration().jsonProvider().parse(file.readAllBytes());
			List<Object> priceList=JsonPath.read(file,"S..price");
			for(Object price:priceList){
				System.out.println(price);
			}
			
			List<Object> categoryList=JsonPath.read(file,"S..category");
			for(Object category:categoryList){
				System.out.println(category);
			}
		}
		
		
		
	  
	  
	}
}
