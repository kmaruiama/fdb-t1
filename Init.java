import Credentials.Credentials;

import java.sql.*;

public class Init {
    static String url = Credentials.getUrl();
    static String user = Credentials.getUser();
    static String password = Credentials.getPassword();
    public static void createSchema() throws Exception {

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            statement.execute("""
                        CREATE TABLE IF NOT EXISTS ResponsibleDepartment (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL
                        )
                    """);

            statement.execute("""
                        CREATE TABLE IF NOT EXISTS Campus (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL
                        )
                    """);

            statement.execute("""
                        CREATE TABLE IF NOT EXISTS Supplier (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL
                        )
                    """);

            statement.execute("""
                        CREATE TABLE IF NOT EXISTS Location (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            sala VARCHAR(255) NOT NULL
                        )
                    """);

            statement.execute("""
                        CREATE TABLE IF NOT EXISTS Document (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            numeroNotaFiscal VARCHAR(255) NOT NULL
                        )
                    """);

            statement.execute("""
                        CREATE TABLE IF NOT EXISTS Asset (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            status VARCHAR(255) NOT NULL,
                            descricao VARCHAR(255),
                            valorAquisicao DECIMAL(8, 2),
                            valorDepreciado DECIMAL(8, 2),
                            estadoConservacao VARCHAR(255),
                            dataEntrada DATE NOT NULL,
                            dataCarga DATE NOT NULL,
                            departamentoResponsavel_id BIGINT,
                            campus_id BIGINT,
                            fornecedor_id BIGINT,
                            sala_id BIGINT,
                            notaFiscal_id BIGINT,
                            CONSTRAINT ce_departamento FOREIGN KEY (departamentoResponsavel_id) REFERENCES ResponsibleDepartment(id) ON DELETE RESTRICT,
                            CONSTRAINT ce_campus FOREIGN KEY (campus_id) REFERENCES Campus(id) ON DELETE RESTRICT,
                            CONSTRAINT ce_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES Supplier(id) ON DELETE RESTRICT,
                            CONSTRAINT ce_sala FOREIGN KEY (sala_id) REFERENCES Location(id) ON DELETE RESTRICT,
                            CONSTRAINT ce_document FOREIGN KEY (notaFiscal_id) REFERENCES Document(id) ON DELETE RESTRICT
                        )
                    """);

            statement.execute("""
                        CREATE TABLE IF NOT EXISTS PrejuizoTotal (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            data_calculo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            prejuizoTotal DECIMAL(15, 2),
                            asset_id BIGINT,
                            CONSTRAINT ce_asset FOREIGN KEY (asset_id) REFERENCES Asset(id) ON DELETE CASCADE
                        )
                    """);

            checkAndCreateTrigger(statement, "calcula_prejuizo_insert", """
                        CREATE TRIGGER calcula_prejuizo_insert
                        AFTER INSERT ON Asset
                        FOR EACH ROW
                        BEGIN
                            DECLARE prejuizo DECIMAL(10, 2);
                            SET prejuizo = NEW.valorAquisicao - NEW.valorDepreciado;
                            INSERT INTO PrejuizoTotal (prejuizoTotal, asset_id)
                            VALUES (prejuizo, NEW.id);
                        END;
                    """);

            checkAndCreateTrigger(statement, "calcula_prejuizo_update", """
                        CREATE TRIGGER calcula_prejuizo_update
                        AFTER UPDATE ON Asset
                        FOR EACH ROW
                        BEGIN
                            DECLARE prejuizo DECIMAL(10, 2);
                            SET prejuizo = NEW.valorAquisicao - NEW.valorDepreciado;
                            UPDATE PrejuizoTotal
                            SET prejuizoTotal = prejuizo
                            WHERE asset_id = NEW.id;
                        END;
                    """);

            checkAndCreateTrigger(statement, "calcula_prejuizo_delete", """
                        CREATE TRIGGER calcula_prejuizo_delete
                        AFTER DELETE ON Asset
                        FOR EACH ROW
                        BEGIN
                            DELETE FROM PrejuizoTotal WHERE asset_id = OLD.id;
                        END;
                    """);

            checkAndCreateView(statement, "AssetSummary", """
                        CREATE VIEW AssetSummary AS
                        SELECT
                            a.id AS AssetNumber,
                            a.descricao AS Description,
                            a.valorAquisicao AS AcquisitionValue,
                            d.nome AS Department,
                            c.nome AS Campus
                        FROM Asset a
                        LEFT JOIN ResponsibleDepartment d ON a.departamentoResponsavel_id = d.id
                        LEFT JOIN Campus c ON a.campus_id = c.id;
                    """);

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    //QUE DELICIA DE IMPROVISO!!!!!
    private static void checkAndCreateTrigger(Statement statement, String triggerName, String createTriggerSQL) throws SQLException {
        String checkTrigger = "SELECT COUNT(*) FROM information_schema.triggers WHERE trigger_name = ?";

        try (PreparedStatement ps = statement.getConnection().prepareStatement(checkTrigger)) {
            ps.setString(1, triggerName);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                statement.execute(createTriggerSQL);
            }
        }
    }

    //QUE DELICIA DE IMPROVISO!!!!!
    private static void checkAndCreateView(Statement statement, String viewName, String createViewSQL) throws SQLException {
        String checkView = "SELECT COUNT(*) FROM information_schema.views WHERE table_name = ?";

        try (PreparedStatement ps = statement.getConnection().prepareStatement(checkView)) {
            ps.setString(1, viewName);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                statement.execute(createViewSQL);
            }
        }
    }
}
