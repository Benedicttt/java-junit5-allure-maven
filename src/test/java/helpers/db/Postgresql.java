package helpers.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import helpers.loader.vault.DataFromVault;

import java.sql.Connection;
import java.sql.SQLException;

public enum Postgresql {
    INSTANCE;

    private final HikariConfig config = new HikariConfig();
    private final HikariDataSource ds;
    private final DataFromVault data;

    Postgresql() {
        data = DataFromVault.INSTANCE;

        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl( data.vault.db.services_platform.host );
        config.setUsername( data.vault.db.services_platform.username );
        config.setPassword( data.vault.db.services_platform.password );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}