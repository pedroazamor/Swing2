package swing2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Swing {
	public static void main(String[] args) {
		
		JFrame janela = new JFrame("Gerenciamento de plantas"); 
		janela.setResizable(false); 
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setSize(400, 300); 
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);
		
		JLabel label_nome_planta = new JLabel("Nome Planta: ");
		JLabel label_cod_planta = new JLabel("Código da planta: ");
		JLabel label_num_carrinho = new JLabel("Carrinho: ");
		
		label_nome_planta.setBounds(50, 40, 100, 20); 
		label_cod_planta.setBounds(50, 80, 150, 20); 
		label_num_carrinho.setBounds(50, 120, 100, 20); 
		
		JTextField jText_nome_planta= new JTextField();
		JTextField jText_cod_planta = new JTextField();
		JTextField jText_num_carrinho = new JTextField();
		
		
		jText_nome_planta.setEnabled(true);
		jText_cod_planta.setEnabled(true);
		jText_num_carrinho.setEnabled(true);
		
		jText_nome_planta.setBounds(180, 40, 150, 20);
		jText_cod_planta.setBounds(180, 80, 50, 20);
		jText_num_carrinho.setBounds(180, 120, 50, 20);
		
		janela.add(label_nome_planta);
		janela.add(label_cod_planta);
		janela.add(label_num_carrinho);
		janela.add(jText_nome_planta);
		janela.add(jText_cod_planta);
		janela.add(jText_num_carrinho);
		
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 80, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		janela.add(botaoGravar);
		JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(155, 200, 100, 20);
        janela.add(botaoExcluir);
        botaoExcluir.setEnabled(false);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janela.add(botaoLimpar);
		
		
		Plantas planta = new Plantas();
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num_Carrinho = Integer.parseInt(jText_num_carrinho.getText());
					int id_Planta = Integer.parseInt(jText_cod_planta.getText());
					botaoGravar.setEnabled(false);
					String nome_Planta;
					if (!planta.consultarPlantas(num_Carrinho,id_Planta))
						nome_Planta = "";
					else
						nome_Planta = planta.getNome_planta();
						jText_nome_planta.setText(nome_Planta);
						jText_num_carrinho.setEnabled(false);
						jText_cod_planta.setEnabled(false);
						botaoConsultar.setEnabled(false);
						jText_nome_planta.setEnabled(true);
						jText_nome_planta.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,"ERRO: Preencha os campos corretamente!");
				}
			}
		});
		botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int num_Carrinho = Integer.parseInt(jText_num_carrinho.getText());
                int id_Planta= Integer.parseInt(jText_cod_planta.getText());
                planta.excluirPedido(num_Carrinho, id_Planta);
                jText_num_carrinho.setText("");
                jText_cod_planta.setText("");
                jText_nome_planta.setText("");
            }
        });
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num_Carrinho= Integer.parseInt(jText_num_carrinho.getText());
				int id_Planta= Integer.parseInt(jText_cod_planta.getText());
				String nome_Planta = jText_nome_planta.getText().trim(); 
				if(nome_Planta == "") {
					botaoExcluir.setEnabled(false);
				}
				if (nome_Planta.length()==0) {
					JOptionPane.showMessageDialog(janela, "ERRO: Preencha o campo nome da planta!");
					jText_nome_planta.requestFocus();
				}
				else {
					if (!planta.consultarPlantas(num_Carrinho, id_Planta)) {
						if (!planta.cadastrarPlanta(num_Carrinho, nome_Planta, id_Planta))
							JOptionPane.showMessageDialog(janela, "ERRO: Não foi possível incluir a planta.");
						else
							JOptionPane.showMessageDialog(janela, "Inclusão realizada com sucesso!");
					} else {
						if (!planta.atualizarPlanta(num_Carrinho, id_Planta, nome_Planta))
							JOptionPane.showMessageDialog(janela, "ERRO: Não foi possível incluir a atualização da planta!");
						else
							JOptionPane.showMessageDialog(janela, "Alteração realizada com sucesso!");
					}

				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jText_num_carrinho.setText("");
				jText_cod_planta.setText(""); 
				jText_nome_planta.setText(""); 
			}
		});
		janela.setVisible(true); 
	}
}
