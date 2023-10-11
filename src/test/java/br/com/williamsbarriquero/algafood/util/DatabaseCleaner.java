package br.com.williamsbarriquero.algafood.util;

// Baseado em: https://brightinventions.pl/blog/clear-database-in-spring-boot-tests/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleaner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DataSource dataSource;

    private Connection connection;

    public DatabaseCleaner(final DataSource aDataSource) {
        this.dataSource = aDataSource;
    }

    public void clearTables() {
        try (final Connection connection = dataSource.getConnection()) {
            this.connection = connection;

            checkTestDatabase();
            tryToClearTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.connection = null;
        }
    }

    private void checkTestDatabase() throws SQLException {
        final var catalog = connection.getCatalog();

        if (catalog == null || !catalog.endsWith("test")) {
            throw new RuntimeException(
                    "Cannot clear database tables because '" + catalog +
                            "' is not a test database (suffix 'test' not found).");
        }
    }

    private void tryToClearTables() throws SQLException {
        final var tableNames = getTableNames();
        clear(tableNames);
    }

    private List<String> getTableNames() throws SQLException {
        final List<String> tableNames = new ArrayList<>();

        final var metaData = connection.getMetaData();
        final var rs =
                metaData.getTables(
                        connection.getCatalog(), null, null, new String[]{"TABLE"}
                );

        while (rs.next()) {
            tableNames.add(rs.getString("TABLE_NAME"));
        }

        tableNames.remove("flyway_schema_history");

        return tableNames;
    }

    private void clear(final List<String> aTableNames) throws SQLException {
        final var statement = buildSqlStatement(aTableNames);

        logger.debug("Executing SQL");
        statement.executeBatch();
    }

    private Statement buildSqlStatement(final List<String> aTableNames) throws SQLException {
        final var statement = connection.createStatement();

        statement.addBatch(sql("SET FOREIGN_KEY_CHECKS = 0"));
        addTruncateSatements(aTableNames, statement);
        statement.addBatch(sql("SET FOREIGN_KEY_CHECKS = 1"));

        return statement;
    }

    private void addTruncateSatements(final List<String> aTableNames, final Statement aStatement) {
        aTableNames.forEach(tableName -> {
            try {
                aStatement.addBatch(sql("TRUNCATE TABLE " + tableName));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String sql(final String aSql) {
        logger.debug("Adding SQL: {}", aSql);
        return aSql;
    }

}
