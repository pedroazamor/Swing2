package swing2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Sistema de gerenciamento de muda de planta.

public class Plantas {
	public int cod_planta;
	public String nome_planta;
	public int num_carrinho;
	public static int quantidade_planta;
	
	
	//Getters and setters
	public int getCod_planta() {
		return cod_planta;
	}

	public void setCod_planta(int cod_planta) {
		this.cod_planta = cod_planta;
	}

	public String getNome_planta() {
		return nome_planta;
	}

	public void setNome_planta(String nome_planta) {
		this.nome_planta = nome_planta;
	}

	public int getNum_carrinho() {
		return num_carrinho;
	}

	public void setNum_carrinho(int num_carrinho) {
		this.num_carrinho = num_carrinho;
	}

	
	
	
	//Método de cadastrar uma planta.
	public boolean cadastrarPlanta(int id_planta , String nome_planta, int num_carrinho ) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "insert into compra set cod_planta=?, nome_planta=?, num_carrinho=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id_planta); 
			ps.setString(2, nome_planta); 
			ps.setInt(3, num_carrinho);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Nenhum cadastro foi registrado.");
				return false;
			}
			System.out.println("Cadastro bem sucedido!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar planta: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	//Método de consultar plantas cadastradas.
	public boolean consultarPlantas(int cod_carrinho, int cod_planta) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "select * from compra where cod_planta=? and num_carrinho=? ";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, cod_planta); 
			ps.setInt(2, cod_carrinho);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				return false;
			} else {
				while (rs.next()) {
					this.num_carrinho = rs.getInt("num_carrinho");
					this.cod_planta = rs.getInt("cod_planta");
					this.nome_planta = rs.getString("nome_planta");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("ERRO: Não foi possível consultar plantas: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	//Método de atualizar plantas cadastradas.
	public boolean atualizarPlanta(int num_carrinho, int cod_planta, String nome_planta) {
		if (!consultarPlantas(num_carrinho, cod_planta))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "update compra set , cod_planta=?, nome_planta=? num_carrinho=? where cod_planta=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				ps.setInt(1, cod_planta);
				ps.setString(2, nome_planta);
				ps.setInt(3, num_carrinho);
				ps.setInt(4, cod_planta);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização.");
				else
					System.out.println("Atualização realizada.");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro na atualização do pedido: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
	
	//Método de excluir uma planta cadastrada
	public void excluirPedido(int cod_planta, int num_carrinho) {
		Connection conexao = Conexao.conectaBanco();
	    String sql = "delete from compra where cod_planta= ? and num_carrinho = ?";
	    try {
	    	PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, cod_planta);
	        ps.setInt(2, num_carrinho);
	        ps.execute();
	        System.out.println("Pedido excluído com sucesso");
	     } catch (SQLException e) {
	        System.out.println("Erro: Falha ao excluir pedido");
	     }
	}
}
