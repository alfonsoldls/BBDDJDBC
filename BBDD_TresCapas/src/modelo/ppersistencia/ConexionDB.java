package modelo.ppersistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConexionDB {
    private static final String PROPERTIES_FILE = "database.properties";

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}