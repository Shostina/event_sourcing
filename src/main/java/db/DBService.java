package db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "create";
    public static final SessionFactory SESSIONFACTORY = createSessionFactory(getH2Configuration());

    private static Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Events.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
