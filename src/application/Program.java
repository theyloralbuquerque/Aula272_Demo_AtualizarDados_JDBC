package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;              // Criação da variável conn do tipo Connection.
		PreparedStatement st = null;         // Criação da variável st do tipo PreparedStatement.
		try {
			conn = DB.getConnection();       // .getConnection() conecta com o BD.

			st = conn.prepareStatement(      // Pega o comando sql dentro dos parêntes e retorna o resultado para um objeto PreparedStatement.
					"UPDATE seller "         // Atualizar a tabela seller
					+ "SET BaseSalary = BaseSalary + ? " // definir a coluna BaseSalary = BaseSalary + ?
					+ "WHERE "               // Onde
					+ "(DepartmentId = ?)"); // DepartmentId = ?

			//.set+tipoDoCampo (coluna) chamado a partir de um objeto PreparedStatement permite inserir/atualizar dados no BD.
			st.setDouble(1, 200.0); 		 // O primeiro placeholder recebe o valor de 200.00.
			st.setInt(2, 2);				 // O segundo placeholder recebe o valor de 2.

			int rowsAffected = st.executeUpdate(); // .executeUpdate() executa o comando sql armazenado em st e retorna o n° de linhas afetadas.

			System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);           // chamada do método que fecha o PreparedStatement.
			DB.closeConnection();			 // chamada do método que fecha o Connection.
		}
	}
}