import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String [] args) {
        System.out.println("quero tua xoxota suculenta");

            // URL de conexão com o banco de dados SQLite
        String url = "jdbc:sqlite:/Users/mariacarolinaboabaid/Downloads/estacio-3trimestre/MP4/Loja.db";

        // Declaração de objetos de conexão e declaração SQL
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            // Exemplo de consulta SQL
            String query = "SELECT * FROM Pessoa_Juridica";
            ResultSet resultSet = statement.executeQuery(query);

            // Processamento dos resultados
            while (resultSet.next()) {
                // Extrair dados do resultado, por exemplo:
                int id = resultSet.getInt("idPessoaJuridica");
                String cnpj = resultSet.getString("cnpj");
                // Processar os dados conforme necessário
                System.out.println("ID: " + id + ", Cnpj: " + cnpj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}