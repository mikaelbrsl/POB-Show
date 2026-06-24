package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import modelo.Show;
import requisito.FachadaShow;

public class TelaShow {
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
	private JLabel label_7;
	private JTextField textField_5;

	public TelaShow() {
		initialize();
	}

	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setModal(true);
		frame.setTitle("Show");
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
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);
						Show s = FachadaShow.localizarShow(id);
						
						textField_1.setText(s.getData());
						textField_4.setText(s.getArtista() != null ? s.getArtista().getNomeArtistico() : "");
						textField_5.setText(s.getCidade() != null ? s.getCidade().getNome() : "");
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

		label_2 = new JLabel("selecione um show para editar");
		label_2.setBounds(21, 187, 394, 14);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("data:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(21, 216, 62, 14);
		frame.getContentPane().add(label_3);

		button_1 = new JButton("Criar");
		button_1.setToolTipText("cadastrar novo show");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty() || textField_4.getText().isEmpty() || textField_5.getText().isEmpty())
					label.setText("campos vazios");
				else
					criarShow();
			}
		});
		button_1.setBounds(548, 327, 95, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Atualizar");
		button_2.setToolTipText("atualizar show");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty() || textField_4.getText().isEmpty() || textField_5.getText().isEmpty())
					label.setText("campos vazios");
				else
					atualizarShowSelecionado();
			}
		});
		button_2.setBounds(284, 327, 95, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Apagar");
		button_3.setToolTipText("apagar show e seus dados");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0)
					label.setText("selecione uma linha");
				else
					apagarShowSelecionado();
			}
		});
		button_3.setBounds(415, 327, 95, 23);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Limpar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_4.setText("");
				textField_5.setText("");
			}
		});
		button_4.setBounds(147, 327, 95, 23);
		frame.getContentPane().add(button_4);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(93, 213, 150, 20);
		frame.getContentPane().add(textField_1);
		
		label_6 = new JLabel("artista:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(21, 245, 62, 14);
		frame.getContentPane().add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(93, 241, 253, 20);
		frame.getContentPane().add(textField_4);

		label_7 = new JLabel("cidade:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(21, 274, 62, 14);
		frame.getContentPane().add(label_7);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(93, 270, 253, 20);
		frame.getContentPane().add(textField_5);

		frame.setVisible(true);
	}

	public void listagem() {
		try {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			
			model.addColumn("Id");
			model.addColumn("Data");
			model.addColumn("Artista");
			model.addColumn("Cidade");
			
			List<Show> lista = FachadaShow.listarShows();
			for (Show s : lista) {
				String nomeArtista = (s.getArtista() != null) ? s.getArtista().getNomeArtistico() : "N/A";
				String nomeCidade = (s.getCidade() != null) ? s.getCidade().getNome() : "N/A";
				model.addRow(new Object[] { s.getId(), s.getData(), nomeArtista, nomeCidade });
			}
		
			label_2.setText("resultados: " + lista.size() + " shows - selecione uma linha para editar");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void apagarShowSelecionado() {
		try {
			label.setText("");
			int linhaSelecionada = table.getSelectedRow();
			int id = (int) table.getValueAt(linhaSelecionada, 0);

			Object[] options = { "Confirmar", "Cancelar" };
			int escolha = JOptionPane.showOptionDialog(null,
					"Esta operação apagará o show ID " + id, "Alerta",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (escolha == 0) {
				FachadaShow.apagarShow(id);
				label.setText("show excluido");
				listagem(); 
			} else {
				label.setText("exclusão cancelada");
			}
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void criarShow() {
		try {
			label.setText("");
			String dataTexto = textField_1.getText().trim();
			String nomeArtista = textField_4.getText().trim();
			String nomeCidade = textField_5.getText().trim();

			LocalDate dataParsed = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			FachadaShow.criarShow(dataParsed, nomeCidade, nomeArtista);
			
			label.setText("show criado");
			listagem();
		} catch (Exception ex) {
			label.setText(ex.getMessage());
		}
	}

	public void atualizarShowSelecionado() {
		try {
			label.setText("");
			int linhaSelecionada = table.getSelectedRow();
			if (linhaSelecionada < 0) {
				label.setText("Selecione um show na tabela primeiro");
				return;
			}
			
			int id = (int) table.getValueAt(linhaSelecionada, 0);
			String dataTexto = textField_1.getText().trim();
			String nomeArtista = textField_4.getText().trim();
			String nomeCidade = textField_5.getText().trim();

			LocalDate dataParsed = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			FachadaShow.alterarShow(id, dataParsed, nomeCidade, nomeArtista);

			label.setText("show atualizado");
			listagem();
		} catch (Exception ex2) {
			label.setText(ex2.getMessage());
		}
	}
}