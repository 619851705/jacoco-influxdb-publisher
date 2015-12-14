package co.leantechniques.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InfluxDbPublisherTest {

    private InfluxDB mockDb;
    private InfluxDbPublisher publisher;
    private JacocoParser mockJacocoParser;

    @Before
    public void setUp() throws Exception {
        mockDb = mock(InfluxDB.class);
        mockJacocoParser = mock(JacocoParser.class);
        publisher = new InfluxDbPublisher(mockDb, "SomeDatabase", mockJacocoParser);
    }

    @Test
    public void createNeedsConnection() throws Exception {
        InfluxDbConnectionInfo connection = new InfluxDbConnectionInfo();
        connection.setUrl(URI.create("http://google.com"));
        connection.setDatabase("someDatabase");
        connection.setUsername("admin");
        connection.setPassword("admin");

        InfluxDbPublisher publisher = InfluxDbPublisher.create(connection);

        assertThat(publisher, notNullValue());
    }

    @Test
    public void publishTakesAFile() throws Exception {
        publisher.publish(new File("someJacocoFile.exec"));

        verify(mockDb).write(any(BatchPoints.class));
    }

    @Test
    public void publishUsesAJacocoParser() throws Exception {
        File jacocoFile = new File("someFile.exec");

        publisher.publish(jacocoFile);

        verify(mockJacocoParser).getCountersForFile(jacocoFile);
    }
}
