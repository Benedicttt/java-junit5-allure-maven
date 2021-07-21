package helpers.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum Postgresql {
    INSTANCE;
    private final HikariConfig config = new HikariConfig();
    private final HikariDataSource ds;


    Postgresql() {
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl( "jdbc:postgresql://10.203.96.205:6432/serviceplatform_1" );
        config.setUsername( "service_platform_1" );
        config.setPassword( "kK3kXTSmhk6ZNVDk" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}