package com.jumbo.neareststores;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class TestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final int MONGO_PORT = 27017;

    static MongoDBContainer mongo = new MongoDBContainer(DockerImageName.parse("mongo:5.0.3"))
            .withExposedPorts(MONGO_PORT)
            .withReuse(true);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        mongo.start();
        String mongoContainerIP =  "MONGODB_URL=" + mongo.getReplicaSetUrl();
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, mongoContainerIP);
    }
}
