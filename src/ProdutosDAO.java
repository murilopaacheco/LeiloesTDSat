/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) {

    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    conn = new conectaDAO().connectDB();

    if (conn == null) {
    JOptionPane.showMessageDialog(
            null,
            "Não foi possível conectar ao banco de dados."
    );
    return;
}
    
    try {

        prep = conn.prepareStatement(sql);

        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null,
                "Produto cadastrado com sucesso!");

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(null,
                "Erro ao cadastrar produto:\n" + erro.getMessage());

    }

}
    
public ArrayList<ProdutosDTO> listarProdutos() {

    String sql = "SELECT * FROM produtos";

    conn = new conectaDAO().connectDB();

    if (conn == null) {
        JOptionPane.showMessageDialog(
                null,
                "Não foi possível conectar ao banco de dados."
        );

        return listagem;
    }

    try {

        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {

            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                null,
                "Erro ao listar produtos:\n" + erro.getMessage()
        );

    }

    return listagem;
}
    
 public void venderProduto(int id) {

    String sql = "UPDATE produtos SET status = ? WHERE id = ?";

    conn = new conectaDAO().connectDB();

    if (conn == null) {
        JOptionPane.showMessageDialog(
                null,
                "Não foi possível conectar ao banco de dados."
        );
        return;
    }

    try {

        prep = conn.prepareStatement(sql);

        prep.setString(1, "Vendido");
        prep.setInt(2, id);

        int linhasAlteradas = prep.executeUpdate();

        if (linhasAlteradas > 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Produto vendido com sucesso!"
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Produto não encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                null,
                "Erro ao vender produto:\n" + erro.getMessage()
        );
    }
}   
    
        
}

