package com.wbd.mongodb.crud.jbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * mongodb spring crud
 * 
 * @author jwh
 *
 */
@Service
public class SpringMongodbCRUD {

	
	private final static String COLLECTION_NAME = "scores";

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mongodb.xml");

		SpringMongodbCRUD sc = (SpringMongodbCRUD) context.getBean("springMongodbCRUD");
		StudentDTO student = new StudentDTO("张三", "数学", 49.89);
        // System.out.println(sc.mongoTemplate);
		
		//添加
		////sc.mongoTemplate.insert(student, COLLECTION_NAME);

		
		
		List<StudentDTO> list = sc.mongoTemplate.find(new Query(Criteria.where("studentId").is("张三")), StudentDTO.class,COLLECTION_NAME);
		
		System.out.println(list.size()+"====");
		for (StudentDTO studentDTO : list) {
			
			System.out.println(studentDTO.getCourse()+"课程");
		}

	}

}
