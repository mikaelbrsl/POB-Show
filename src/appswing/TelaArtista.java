package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import modelo.Artista;
import modelo.Show;
import requisito.FachadaArtista;

public class TelaArtista {
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
	private JPanel panel;
	private JLabel label_1;
	private JButton button_5;
	private JButton button_6;

	private JTextField textField_1;
	private BufferedImage buffer;
	private JLabel label_6;
	private JTextField textField_4;
	private JButton button_7;

	public TelaArtista() {
		initialize();
	}

	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setModal(true);
		frame.setTitle("Artista");
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
						Artista a = FachadaArtista.localizarArtista(nome);
						
						textField_1.setText(a.getNomeArtistico());
						
						String showsIds;
						if (a.getListaDeShow() == null || a.getListaDeShow().isEmpty()) {
							showsIds = "sem shows";
						} else {
							List<String> ids = new ArrayList<>();
							for (Show s : a.getListaDeShow()) {
								ids.add(String.valueOf(s.getId()));
							}
							showsIds = String.join(", ", ids);
						}
						textField_4.setText(showsIds);
						
						if (a.getFoto() != null) {
							InputStream in = new ByteArrayInputStream(a.getFoto());
							buffer = ImageIO.read(in);
							ImageIcon icon = new ImageIcon(
									buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(), Image.SCALE_DEFAULT));
							icon.setImage(icon.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1));
							label_1.setIcon(icon);
						} else {
							buffer = null;
							label_1.setText("sem foto");
							label_1.setIcon(null);
						}
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

		label_2 = new JLabel("selecione um artista para editar");
		label_2.setBounds(21, 187, 394, 14);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("nome:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(21, 216, 62, 14);
		frame.getContentPane().add(label_3);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Foto",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(555, 197, 102, 105);
		frame.getContentPane().add(panel);

		label_1 = new JLabel("sem foto");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 21, 78, 73);
		panel.add(label_1);

		button_1 = new JButton("Criar");
		button_1.setToolTipText("cadastrar novo artista");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					criarArtista();
			}
		});
		button_1.setBounds(548, 327, 95, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Atualizar");
		button_2.setToolTipText("atualizar artista");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					atualizarArtistaSelecionado();
			}
		});
		button_2.setBounds(284, 327, 95, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Apagar");
		button_3.setToolTipText("apagar artista e seus dados");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					apagarArtistaSelecionado();
			}
		});
		button_3.setBounds(415, 327, 95, 23);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Limpar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_4.setText("");
				button_6.doClick();
			}
		});
		button_4.setBounds(147, 327, 95, 23);
		frame.getContentPane().add(button_4);

		button_5 = new JButton("Buscar foto");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty()) {
					label.setText("selecione um artista");
					return;
				}
				File file = selecionarArquivoFoto();
				if (file == null)
					return;

				try {
					buffer = ImageIO.read(file);
					ImageIcon icon = new ImageIcon(
							buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(), Image.SCALE_DEFAULT));
					icon.setImage(icon.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1));
					label_1.setIcon(icon);
					label.setText("Precisa atualizar/criar artista para salvar a foto");
				} catch (IOException ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_5.setBounds(664, 213, 108, 23);
		frame.getContentPane().add(button_5);

		button_6 = new JButton("Limpar foto");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buffer = null;
				label_1.setIcon(null);
				label_1.setText("sem foto");
				label.setText("");
				label.setText("Precisa atualizar/criar artista para salvar a foto");
			}
		});
		button_6.setBounds(667, 247, 105, 23);
		frame.getContentPane().add(button_6);

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
		textField_4.setBounds(93, 241, 320, 20);
		frame.getContentPane().add(textField_4);

		button_7 = new JButton("Ver Shows");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int linhaSelecionada = table.getSelectedRow();
					if (linhaSelecionada < 0) {
						label.setText("selecione um artista");
						return;
					}
					String nome = (String) table.getValueAt(linhaSelecionada, 1);
					Artista a = FachadaArtista.localizarArtista(nome);
					
					if (a.getListaDeShow() == null || a.getListaDeShow().isEmpty()) {
						label.setText("este artista não possui shows");
						return;
					}

					JDialog telaShows = new JDialog(frame, "Shows de " + a.getNomeArtistico(), true);
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
					modeloShows.addColumn("Cidade");

					for (Show s : a.getListaDeShow()) {
						String nomeCidade = (s.getCidade() != null) ? s.getCidade().getNome() : "N/A";
						modeloShows.addRow(new Object[]{ s.getId(), s.getData(), nomeCidade });
					}

					tabelaShows.setModel(modeloShows);
					scrollShows.setViewportView(tabelaShows);
					telaShows.setVisible(true);

				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_7.setBounds(425, 240, 101, 23);
		frame.getContentPane().add(button_7);

		frame.setVisible(true);
	}

	public void listagem() {
		try {
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			
			model.addColumn("Id");
			model.addColumn("Nome Artístico");
			model.addColumn("Qtd Shows");
			
			List<Artista> lista = FachadaArtista.listarArtistas();
			for (Artista a : lista) {
				int qtdShows = (a.getListaDeShow() != null) ? a.getListaDeShow().size() : 0;
				model.addRow(new Object[] { a.getId(), a.getNomeArtistico(), qtdShows });
			}
		
			label_2.setText("resultados: " + lista.size() + " artistas - selecione uma linha para editar");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void apagarArtistaSelecionado() {
		try {
			label.setText("");
			String nome = textField_1.getText();

			Object[] options = { "Confirmar", "Cancelar" };
			int escolha = JOptionPane.showOptionDialog(null,
					"Esta operação apagará o artista " + nome, "Alerta",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (escolha == 0) {
				FachadaArtista.apagarArtista(nome);
				label.setText("artista excluido");
				listagem(); 
			} else {
				label.setText("exclusão cancelada");
			}
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void criarArtista() {
		try {
			label.setText("");
			String nome = textField_1.getText().trim();

			FachadaArtista.criarArtista(nome);
			
			if (buffer != null) {
				salvarFotoDoBuffer(nome);
			}
			
			label.setText("artista criado");
			listagem();
		} catch (Exception ex) {
			label.setText(ex.getMessage());
		}
	}

	public void atualizarArtistaSelecionado() {
		try {
			label.setText("");
			int linhaSelecionada = table.getSelectedRow();
			if (linhaSelecionada < 0) {
				label.setText("Selecione um artista na tabela primeiro");
				return;
			}
			
			String nomeOriginal = (String) table.getValueAt(linhaSelecionada, 1);
			String novoNome = textField_1.getText().trim();

			if (!nomeOriginal.equals(novoNome)) {
				FachadaArtista.alterarArtista(nomeOriginal, novoNome);
			}
			
			salvarFotoDoBuffer(novoNome);

			label.setText("artista updated");
			listagem();
		} catch (Exception ex2) {
			label.setText(ex2.getMessage());
		}
	}

	private void salvarFotoDoBuffer(String nomeArtista) {
		byte[] bytesfoto = null;
		if (buffer != null) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(buffer, "jpg", baos);
				bytesfoto = baos.toByteArray();
				baos.close();
			} catch (IOException ex1) {
				label.setText("problema na conversão da imagem em bytes");
				return;
			}
		}
		FachadaArtista.alterarFoto(nomeArtista, bytesfoto);
	}

	public File selecionarArquivoFoto() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		try {
			chooser.setCurrentDirectory(new File((new File(".").getCanonicalPath() + "\\src\\fotos")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile();
	}
}