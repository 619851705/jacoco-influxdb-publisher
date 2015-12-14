package co.leantechniques.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;

import java.io.File;
import java.util.List;

public class InfluxDbPublisher {

    private final InfluxDB db;
    private final String database;
    private final JacocoParser jacocoParser;

    public InfluxDbPublisher(InfluxDB db, String database, JacocoParser jacocoParser) {
        this.db = db;
        this.database = database;
        this.jacocoParser = jacocoParser;
    }

    public static InfluxDbPublisher create(InfluxDbConnectionInfo connection) {
        InfluxDB db = InfluxDBFactory.connect(connection.getConnectionUrl(), connection.getUsername(), connection.getPassword());
        return new InfluxDbPublisher(db, connection.getDatabase(), new JacocoParser());
    }

    public void publish(File file) {
        List<String> counters = jacocoParser.getCountersForFile(file);

        BatchPoints points = BatchPoints.database(database).build();
        db.write(points);
    }
}
