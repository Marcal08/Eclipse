package dao;

import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO extends DAO {	
	public ProdutoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Produto produto) {
		boolean status = false;
		try {
			String sql = "INSERT INTO produto (nome, salario, idade, dataingresso, dataaposentadoria) "
		               + "VALUES ('" + produto.getnome() + "', "
		               + produto.getsalario() + ", " + produto.getidade() + ", ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(produto.getdataingresso()));
			st.setDate(2, Date.valueOf(produto.getdataaposentadoria()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Produto get(int id) {
		Produto produto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 produto = new Produto(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("salario"), 
	                				   rs.getInt("idade"), 
	        			               rs.getTimestamp("dataingresso").toLocalDateTime(),
	        			               rs.getDate("dataaposentadoria").toLocalDate());
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produto;
	}
	
	
	public List<Produto> get() {
		return get("");
	}

	
	public List<Produto> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Produto> getOrderBynome() {
		return get("nome");		
	}
	
	
	public List<Produto> getOrderBysalario() {
		return get("salario");		
	}
	
	
	private List<Produto> get(String orderBy) {
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Produto p = new Produto(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("salario"), 
	        			                rs.getInt("idade"),
	        			                rs.getTimestamp("dataingresso").toLocalDateTime(),
	        			                rs.getDate("dataaposentadoria").toLocalDate());
	            produtos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}
	
	
	public boolean update(Produto produto) {
		boolean status = false;
		try {  
			String sql = "UPDATE produto SET nome = '" + produto.getnome() + "', "
					   + "salario = " + produto.getsalario() + ", " 
					   + "idade = " + produto.getidade() + ","
					   + "dataingresso = ?, " 
					   + "dataaposentadoria = ? WHERE id = " + produto.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(produto.getdataingresso()));
			st.setDate(2, Date.valueOf(produto.getdataaposentadoria()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}