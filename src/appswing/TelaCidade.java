package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Cidade;
import modelo.Show;
import requisito.FachadaCidade;

public class TelaCidade {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button_3;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JButton button_1;
	private JButton button_2;
	private JButton button_4;

	private JTextField textField_1;
	private JLabel label_6;
	private JTextField textField_4;
	private JButton button_5;

	public TelaCidade() {
		initialize();
	}

	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setModal(true);
		frame.setTitle("Cidade");
		frame.setBounds(100, 100, 813, 438);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 39, 751, 147);
		frame.getContentPane().add(scrollPane);

		table = new JTable() { 
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					label.setText("");
					if (table.getSelectedRow() >= 0) {
						String nome = (String) table.getValueAt(table.getSelectedRow(), 1);
						Cidade c = FachadaCidade.localizarCidade(nome);
						
						textField_1.setText(c.getNome());
						
						String showsIds;
						if (c.getListaDeShow() == null || c.getListaDeShow().isEmpty()) {
							showsIds = "sem shows";
						} else {
							List<String> ids = new ArrayList<>();
							for (Show s : c.getListaDeShow()) {
								ids.add(String.valueOf(s.getId()));
							}
							showsIds = String.join(", ", ids);
						}
						textField_4.setText(showsIds);
					}
				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});

		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 374, 735, 14);
		frame.getContentPane().add(label);

		label_2 = new JLabel("selecione uma cidade para editar");
		label_2.setBounds(21, 187, 394, 14);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("nome:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(21, 216, 62, 14);
		frame.getContentPane().add(label_3);

		button_1 = new JButton("Criar");
		button_1.setToolTipText("cadastrar nova cidade");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					criarCidade();
			}
		});
		button_1.setBounds(548, 327, 95, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Atualizar");
		button_2.setToolTipText("atualizar cidade");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					atualizarCidadeSelecionada();
			}
		});
		button_2.setBounds(284, 327, 95, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Apagar");
		button_3.setToolTipText("apagar cidade e seus dados");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					apagarCidadeSelecionada();
			}
		});
		button_3.setBounds(415, 327, 95, 23);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Limpar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_4.setText("");
			}
		});
		button_4.setBounds(147, 327, 95, 23);
		frame.getContentPane().add(button_4);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(93, 213, 253, 20);
		frame.getContentPane().add(textField_1);
		
		label_6 = new JLabel("id shows:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(21, 245, 62, 14);
		frame.getContentPane().add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(93, 241, 433, 20);
		frame.getContentPane().add(textField_4);

		button_5 = new JButton("Ver Shows");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = table.getSelectedRow();
					if (linhaSelecionada < 0) {
						label.setText("selecione uma cidade");
						return;
					}
					String nome = (String) table.getValueAt(linhaSelecionada, 1);
					Cidade c = FachadaCidade.localizarCidade(nome);
					
					if (c.getListaDeShow() == null || c.getListaDeShow().isEmpty()) {
						label.setText("essa cidade não possui shows");
						return;
					}

					JDialog telaShows = new JDialog(frame, "Shows em " + c.getNome(), true);
					telaShows.setBounds(150, 150, 500, 300);
					telaShows.getContentPane().setLayout(null);

					JScrollPane scrollShows = new JScrollPane();
					scrollShows.setBounds(20, 20, 440, 200);
					telaShows.getContentPane().add(scrollShows);

					JTable tabelaShows = new JTable() {
						public boolean isCellEditable(int r, int c) {
							return false;
						}
					};
					
					DefaultTableModel modeloShows = new DefaultTableModel();
					modeloShows.addColumn("ID Show");
					modeloShows.addColumn("Data");
					modeloShows.addColumn("Artista");

					for (Show s : c.getListaDeShow()) {
						String nomeArtista = (s.getArtista() != null) ? s.getArtista().getNomeArtistico() : "N/A";
						modeloShows.addRow(new Object[]{ s.getId(), s.getData(), nomeArtista });
					}

					tabelaShows.setModel(modeloShows);
					scrollShows.setViewportView(tabelaShows);
					telaShows.setVisible(true);

				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_5.setBounds(548, 240, 110, 23);
		frame.getContentPane().add(button_5);

		frame.setVisible(true);
	}

	public void listagem() {
		try {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			
			model.addColumn("Id");
			model.addColumn("Nome");
			model.addColumn("Quantidade de Shows");
			
			List<Cidade> lista = FachadaCidade.listarCidades();
			for (Cidade c : lista) {
				int qtdShows = (c.getListaDeShow() != null) ? c.getListaDeShow().size() : 0;
				model.addRow(new Object[] { c.getId(), c.getNome(), qtdShows });
			}
		
			label_2.setText("resultados: " + lista.size() + " cidades - selecione uma linha para editar");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void apagarCidadeSelecionada() {
		try {
			label.setText("");
			String nome = textField_1.getText();

			Object[] options = { "Confirmar", "Cancelar" };
			int escolha = JOptionPane.showOptionDialog(null,
					"Esta operação apagará a cidade " + nome, "Alerta",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (escolha == 0) {
				FachadaCidade.apagarCidade(nome);
				label.setText("cidade excluida");
				listagem(); 
			} else {
				label.setText("exclusão cancelada");
			}
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void criarCidade() {
		try {
			label.setText("");
			String nome = textField_1.getText().trim();

			FachadaCidade.criarCidade(nome);
			
			label.setText("cidade criada");
			listagem();
		} catch (Exception ex) {
			label.setText(ex.getMessage());
		}
	}

	public void atualizarCidadeSelecionada() {
		try {
			label.setText("");
			int linhaSelecionada = table.getSelectedRow();
			if (linhaSelecionada < 0) {
				label.setText("Selecione uma cidade na tabela primeiro");
				return;
			}
			
			String nomeOriginal = (String) table.getValueAt(linhaSelecionada, 1);
			String novoNome = textField_1.getText().trim();

			if (!nomeOriginal.equals(novoNome)) {
				FachadaCidade.alterarCidade(nomeOriginal, novoNome);
			}
			
			label.setText("cidade atualizada");
			listagem();
		} catch (Exception ex2) {
			label.setText(ex2.getMessage());
		}
	}
}