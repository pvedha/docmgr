package com.blog.trials;

//import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoCheck {
	 MongoClient mongo;
	MongoDatabase db;
	
	public MongoCheck() {
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDatabase("cmad");
	}

	public FindIterable<Document> findByUserId(String userId){
		MongoCollection<Document> collection = db.getCollection("users");
		return collection.find();//eq("userId",userId));
	}
	
	public FindIterable<Document> getUsers(){
		MongoCollection<Document> collection = db.getCollection("users");
		return collection.find();
	}
	
	public boolean incrementSalary(int salary) {
		System.out.println("Salary to increment " + salary);
		MongoCollection<Document> collection = db.getCollection("users");
//		collection.find().forEach((Document d) -> {
//			Object salObj = d.get("salary");
//			if(salObj == null){
//				//d.append("salary", salary);
//				//Document temp = d.replace(key, oldValue, newValue);
////				temp.remove("_id");
////				temp.put("salary", salary);
////				System.out.println("Added salary");
//////				collection.updateOne({"_Id" : d.get("_Id")},
//////						{ "sfds" : "SFDSF" });		
////				d.append("new", "value");
////				collection.updateOne(d, temp);
////				collection.updateOne(
////					      { "name" : "Pizza Rat's Pizzaria" },
////					      { $set: {"_id" : 4, "violations" : 7, "borough" : "Manhattan" } }
////					   );
//				
//				//collection.updateOne(d, d);
//				//collection.findon
//				Document temp = new Document();
//				temp.put("name", "via java");
//				temp.put("salary",salary);
//				//collection.insertOne(temp);
//				//collection.updateMany(d, temp);
//				
//			}else{
//				Integer currSalary = Integer.valueOf((String) salObj);
//				d.replace("salary", (currSalary + salary)+"");
//				d.replace("name", "got replaced");
//				d.put("new", "from java");
//				collection.deleteOne(d);
//				Document temp = d;
//				System.out.println("Total Salary " + (currSalary + salary));
//			}
//		});
		
		Document tmp = new Document();
		tmp.put("new", "from java");
		//collection.deleteOne(tmp);
		collection.insertOne(tmp);
		
	
	
		return true;
	}
	
		

}
