package proy.fing.core.context;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages= {"proy.fing.core.dao.mongo"})
public class MongoRepositoryContext {
	
	@Value("${mongodb.host}")
	private String mongoHost;
	
	@Value("${mongodb.user}")
	private String mongoUser;
	
	@Value("${mongodb.password}")
	private String mongoPassword;
	
	@Value("${mongodb.database}")
	private String mongoDatabase;
	
	@Value("${mongodb.port}")
	private int mongoPort;
	
	@Bean
	public SimpleMongoDbFactory getSimpleMongoDbFactory() throws UnknownHostException{
		
		// Set credentials      
	    MongoCredential credential = MongoCredential.createCredential(this.mongoUser, this.mongoDatabase, this.mongoPassword.toCharArray());
	    ServerAddress serverAddress = new ServerAddress(this.mongoHost, this.mongoPort);

	    // Mongo Client
	    MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential)); 

	    // Mongo DB Factory
	    SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
	            mongoClient, this.mongoDatabase);

	    return simpleMongoDbFactory;
		
	}
	
	@Bean(name = "mongoTemplate")
	public MongoTemplate getMongoTemplate() throws UnknownHostException{
		
		return new MongoTemplate(getSimpleMongoDbFactory());
		
	}

}
