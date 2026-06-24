package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Artista;
import modelo.Show;
import requisito.FachadaShow;

public class TelaConsulta {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel label;
	private JLabel label_4;

	private JComboBox<String> comboBox;

	public TelaConsulta() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Consultas do Sistema de Shows");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};

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
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		button = new JButton("Consultar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index < 0) {
					label_4.setText("consulta nao selecionada");
				} else {
					label_4.setText("");
					label.setText("");
					try {
						switch(index) {
						case 0: 
							String dataTexto = JOptionPane.showInputDialog("Digite a data (dd/MM/yyyy):");
							if (dataTexto != null && !dataTexto.isEmpty()) {
								LocalDate dataBusca = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
								List<Show> resultado1 = FachadaShow.consultarShowsNaData(dataBusca);
								listagemShow(resultado1);
							}
							break;
						case 1: 
							String cidade = JOptionPane.showInputDialog("Digite o nome da cidade:");
							if (cidade != null && !cidade.isEmpty()) {
								List<Artista> resultado2 = FachadaShow.consultarArtistasNaCidade(cidade);
								listagemArtista(resultado2);
							}	
							break;
						case 2: 
							String cid = JOptionPane.showInputDialog("Digite a cidade:");
							String qtdStr = JOptionPane.showInputDialog("Digite a quantidade limite de shows:");
							if (cid != null && !cid.isEmpty() && qtdStr != null && !qtdStr.isEmpty()) {
								int quantidade = Integer.parseInt(qtdStr);
								List<Artista> resultado3 = FachadaShow.consultarArtistasComMaisDeNShowsNaCidade(cid, quantidade);
								listagemArtista(resultado3);
							}
							break;
						}
					} catch (Exception erro) {
						label.setText("Erro ao executar consulta: " + erro.getMessage());
					}
				}
			}
		});
		button.setBounds(606, 10, 89, 23);
		frame.getContentPane().add(button);

		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("selecione a consulta");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
			"quais os shows na data X", 
			"quais os artistas que vao se apresentar na cidade X", 
			"quais os artistas que tem mais de N shows na cidade X"
		}));
		comboBox.setBounds(21, 10, 513, 22);
		frame.getContentPane().add(comboBox);
	}

	public void listagemShow(List<Show> lista) {
		try {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			model.addColumn("ID Show");
			model.addColumn("Data");
			model.addColumn("Artista");
			model.addColumn("Cidade");

			for (Show s : lista) {
				String artista = (s.getArtista() != null) ? s.getArtista().getNomeArtistico() : "N/A";
				String cidade = (s.getCidade() != null) ? s.getCidade().getNome() : "N/A";
				model.addRow(new Object[] { s.getId(), s.getData(), artista, cidade });
			}
			
			label_4.setText("resultados: " + lista.size() + " shows encontrados.");
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void listagemArtista(List<Artista> lista) {
		try {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			model.addColumn("ID Artista");
			model.addColumn("Nome Artístico");
			model.addColumn("Qtd Total de Shows");

			for (Artista a : lista) {
				int qtd = (a.getListaDeShow() != null) ? a.getListaDeShow().size() : 0;
				model.addRow(new Object[] { a.getId(), a.getNomeArtistico(), qtd });
			}

			label_4.setText("resultados: " + lista.size() + " artistas encontrados.");
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}
}