package br.com.onpecas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.onpecas.helper.Alerta;
import br.com.onpecas.helper.MySqlConnect;

public class Permissao {

	private int Oid_permissao;
	private int acs_cms;

	public int getOid_permissao() {
		return Oid_permissao;
	}
	public void setOid_permissao(int oid_permissao) {
		Oid_permissao = oid_permissao;
	}
	public int getAcs_cms() {
		return acs_cms;
	}
	public void setAcs_cms(int acs_cms) {
		this.acs_cms = acs_cms;
	}

	public static void Insert(Permissao permissao){
		Connection con = MySqlConnect.ConectarDb();

		String sql ="insert into permissao (acs_cms) values(?);";

		PreparedStatement parametros;

		try {
			parametros = con.prepareStatement(sql);
			parametros.setInt(1, permissao.getAcs_cms());

			parametros.executeUpdate();
			con.close();

			Alerta.showInformation("sucesso", "Inserido com sucesso");
			//limpar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alerta.showError("Erro", "Ocorreu um erro, tente novamente.");
		}
	}

	public static void Update(Permissao permissao){
		Connection con = MySqlConnect.ConectarDb();

		String sql ="update permissao set acs_cms = ? where oid_permissao = ?;";
		PreparedStatement parametros;

		try {
			parametros = con.prepareStatement(sql);
			parametros.setInt(1, permissao.getAcs_cms());
			parametros.setInt(6, permissao.getOid_permissao());

			parametros.executeUpdate();
			con.close();

			Alerta.showInformation("sucesso", "Atualizado com sucesso");
			//limpar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alerta.showError("Erro", "Ocorreu um erro, tente novamente.");
		}

	}

	public static void Delete(Permissao permissao){
		Connection con = MySqlConnect.ConectarDb();

		String sql ="delete from permissao where oid_permissao = ?;";

		PreparedStatement parametros;

		try {
			parametros = con.prepareStatement(sql);
			parametros.setInt(1, permissao.getOid_permissao());

			parametros.executeUpdate();
			con.close();

			Alerta.showInformation("sucesso", "Deletado com sucesso");
			//limpar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alerta.showError("Erro", "Ocorreu um erro, tente novamente.");
		}
	}

	public static List<Permissao> Select(){
		Connection con = MySqlConnect.ConectarDb();

		List<Permissao> lstPermissao = new ArrayList<>();
		String sql = "select * from permissao;";

		try {
			ResultSet rs = con.createStatement().executeQuery(sql);

			while(rs.next()){

				Permissao permissao = new Permissao();

				permissao.setOid_permissao(rs.getInt("oid_permissao"));
				permissao.setAcs_cms(rs.getInt("acs_cms"));

				lstPermissao.add(permissao);
			}

			con.close();
			return lstPermissao;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
