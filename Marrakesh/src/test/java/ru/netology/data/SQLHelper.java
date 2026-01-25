//package ru.netology.data;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import lombok.SneakyThrows;
//import lombok.Value;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.ScalarHandler;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
//public class SQLHelper {
//
//    public enum DatabaseType {
//        MYSQL,
//        POSTGRESQL
//    }
//
//    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
//    private static DatabaseType currentDbType;
//
//    private SQLHelper() {}
//
//    /**
//     * для результатов платежа
//     */
//    @Value
//    public static class PaymentResult {
//        private String id;
//        private boolean approved;
//        private String paymentMethod;
//        private int amount;
//    }
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Database drivers not found", e);
//        }
//    }
//
//    /**
//     * Устанавливает тип используемой базы данных
//     */
//    public static void setDatabaseType(DatabaseType dbType) {
//        currentDbType = dbType;
//    }
//
//    /**
//     * Сохраняет информацию о результате платежа без сохранения данных карты
//     */
//    @SneakyThrows
//    public static void savePaymentResult(String transactionId, boolean approved,
//                                         String paymentMethod, int amount) {
//        String sql;
//
//        if (currentDbType == DatabaseType.MYSQL) {
//            sql = "INSERT INTO payment_results (approved, payment_method, amount) " +
//                    "VALUES (?, ?, ?)";
//        } else {
//            sql = "INSERT INTO payment_results (approved, payment_method, amount, created) " +
//                    "VALUES (?, ?, ?, NOW())";
//        }
//
//        try (var conn = getConnection()) {
//            QUERY_RUNNER.execute(conn, sql, transactionId, approved, paymentMethod, amount);
//        }
//    }
//
//    /**
//     * Получает информацию о платеже по transaction_id
//     */
//    @SneakyThrows
//    public static PaymentResult getPaymentResult(String transactionId) {
//        String sql = "SELECT id, transaction_id as transactionId, approved, " +
//                "payment_method as paymentMethod, created, amount " +
//                "FROM payment_results WHERE transaction_id = ?";
//
//        try (var conn = getConnection()) {
//            return QUERY_RUNNER.query(conn, sql,
//                    new BeanHandler<>(PaymentResult.class), transactionId);
//        }
//    }
//
//    /**
//     * Проверяет, была ли одобрена транзакция
//     */
//    @SneakyThrows
//    public static boolean isTransactionApproved(String transactionId) {
//        String sql = "SELECT approved FROM payment_results WHERE transaction_id = ?";
//
//        try (var conn = getConnection()) {
//            Boolean result = QUERY_RUNNER.query(conn, sql,
//                    new ScalarHandler<Boolean>(), transactionId);
//            return result != null && result;
//        }
//    }
//
//    /**
//     * Создаёт подключение к БД в зависимости от выбранного типа
//     */
//    private static Connection getConnection() throws SQLException {
//        Dotenv dotenv = Dotenv.load();
//
//        String url, user, password;
//
//        if (currentDbType == null) {
//            throw new IllegalStateException("Database type not set. Call setDatabaseType() first.");
//        }
//
//        if (currentDbType == DatabaseType.MYSQL) {
//            url = dotenv.get("MYSQL_DB_URL");
//            user = dotenv.get("MYSQL_DB_USER");
//            password = dotenv.get("MYSQL_DB_PASSWORD");
//        } else {
//            url = dotenv.get("POSTGRESQL_DB_URL");
//            user = dotenv.get("POSTGRESQL_DB_USER");
//            password = dotenv.get("POSTGRESQL_DB_PASSWORD");
//        }
//
//        if (url == null || user == null) {
//            throw new SQLException("Database connection parameters not found in .env file");
//        }
//
//        return DriverManager.getConnection(url, user, password);
//    }
//
//    /**
//     * Инициализирует тестовые таблицы в БД (для первого запуска)
//     */
//    @SneakyThrows
//    public static void initializeTestTables() {
//        try (var conn = getConnection()) {
//            if (currentDbType == DatabaseType.MYSQL) {
//                // Создание таблиц для MySQL
//                QUERY_RUNNER.execute(conn,
//                        "CREATE TABLE IF NOT EXISTS payment_results (" +
//                                "id INT AUTO_INCREMENT PRIMARY KEY," +
//                                "transaction_id VARCHAR(50) NOT NULL UNIQUE," +
//                                "approved BOOLEAN NOT NULL," +
//                                "payment_method VARCHAR(20) NOT NULL," +
//                                "amount INT NOT NULL," +
//                                "created TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
//                                "INDEX idx_transaction (transaction_id)" +
//                                ")");
//                QUERY_RUNNER.execute(conn,
//                        "CREATE TABLE IF NOT EXISTS order_statuses (" +
//                                "id INT AUTO_INCREMENT PRIMARY KEY," +
//                                "payment_id VARCHAR(50) NOT NULL," +
//                                "transaction_id VARCHAR(50)," +
//                                "status VARCHAR(50) NOT NULL," +
//                                "updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
//                                "INDEX idx_payment (payment_id)" +
//                                ")");
//            } else {
//                // Создание таблиц для PostgreSQL
//                QUERY_RUNNER.execute(conn,
//                        "CREATE TABLE IF NOT EXISTS payment_results (" +
//                                "id SERIAL PRIMARY KEY," +
//                                "transaction_id VARCHAR(50) NOT NULL UNIQUE," +
//                                "approved BOOLEAN NOT NULL," +
//                                "payment_method VARCHAR(20) NOT NULL," +
//                                "amount INTEGER NOT NULL," +
//                                "created TIMESTAMP DEFAULT NOW()" +
//                                ")");
//                QUERY_RUNNER.execute(conn,
//                        "CREATE INDEX IF NOT EXISTS idx_payment_transaction ON payment_results(transaction_id)");
//
//                QUERY_RUNNER.execute(conn,
//                        "CREATE TABLE IF NOT EXISTS order_statuses (" +
//                                "id SERIAL PRIMARY KEY," +
//                                "payment_id VARCHAR(50) NOT NULL," +
//                                "transaction_id VARCHAR(50)," +
//                                "status VARCHAR(50) NOT NULL," +
//                                "updated TIMESTAMP DEFAULT NOW()" +
//                                ")");
//
//                QUERY_RUNNER.execute(conn,
//                        "CREATE INDEX IF NOT EXISTS idx_order_payment ON order_statuses(payment_id)");
//            }
//        }
//    }
//}
