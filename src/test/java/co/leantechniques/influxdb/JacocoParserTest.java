package co.leantechniques.influxdb;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class JacocoParserTest {

    @Test
    public void parseAFile() throws Exception {
        InputStream stream = this.getClass().getResourceAsStream("jacoco.exec");
        

    }
}