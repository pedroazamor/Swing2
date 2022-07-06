package swing2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Jdbc {
		public static void main(String[] args) {
			try {
				String url = "jdbc:mysql://localhost/pedroazamor"; 
				String user = "root";
				String password = ""; 
				Connection conn = DriverManager.getConnection(url, user, password);
				
				String sql = "select * from compra where num_carrinho=?, cod_planta=?, nome_planta=?";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				Scanner sc = new Scanner(System.in);
				System.out.print("Numero do carrinho = ");
				int carrinho = Integer.parseInt(sc.nextLine());
				System.out.print("Código da muda de planta = ");
				int cod_planta = Integer.parseInt(sc.nextLine());
				System.out.print("Nome da planta = ");
				String nome_planta = sc.nextLine();
				ps.setInt(1, carrinho);	
				ps.setInt(2, cod_planta);
				ps.setString(3, nome_planta);
				ResultSet rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) { 
					System.out.println("Nenhum registro foi encontrado.");
				}
				else
				{
					int totalRegistros = 0;
					while (rs.next()) {
					    int numero_carrinho = rs.getInt("num_carrinho");
					    int id_da_planta= rs.getInt("cod_planta");
					    String nome_da_planta = rs.getString("nome_planta");
					    System.out.println("Número do registro = " + rs.getRow());
					    System.out.println("Carrinho=" + numero_carrinho);
					    System.out.println("Planta=" + id_da_planta);
					    System.out.println("Nome do Produto=" + nome_da_planta);
					    totalRegistros++;
					}
					System.out.println("Número de registros = " + totalRegistros);
				}
				sc.close();
			} catch (Exception erro) {
				System.out.println("Erro: " + erro.toString());
			}
		}
}


