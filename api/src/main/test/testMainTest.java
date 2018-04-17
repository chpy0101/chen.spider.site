

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * testMain Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 1, 2018</pre>
 */
@SpringBootApplication
@EnableJpaRepositories("chen.site.dao.mysql.idao")
@EntityScan
public class testMainTest {

    @Autowired
    Connection connection;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: save()
     */
    @Test
    public void testSave() throws Exception {

    }


} 
