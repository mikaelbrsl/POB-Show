/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
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
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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

import modelo.Aluno;
import modelo.Telefone;
import requisito.FachadaAluno;
import requisito.FachadaPessoa;
import requisito.FachadaTelefone;

public class TelaAluno {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button_3;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JButton button_1;
	private JButton button_2;
	private JButton button_4;
	private JLabel label_8;
	private JPanel panel;
	private JLabel label_1;
	private JButton button_5;
	private JButton button_6;

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JLabel label_5;
	private BufferedImage buffer; // armazena a foto na memória durante a edicao
	private JLabel label_6;
	private JTextField textField_4;
	private JButton button;
	private JLabel label_7;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// TelaReuniao window = new TelaReuniao();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public TelaAluno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setModal(true);
		frame.setTitle("Aluno");
		frame.setBounds(100, 100, 813, 438);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		table = new JTable() { // herança de JTable
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false; // desabilita edicao de celulas
			}
		};
		// click numa linha da tabela
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					label.setText("");
					if (table.getSelectedRow() >= 0) {
						// copiar a pessoa selecionada para formulario de edicao
						String nome = (String) table.getValueAt(table.getSelectedRow(), 1);
						Aluno p = FachadaAluno.localizarAluno(nome);
						textField_1.setText(nome);
						textField_2.setText(p.getDtNascimento());
						textField_3.setText(String.join(",", p.getApelidos()));
						textField_5.setText("");
						textField_6.setText(String.valueOf(p.getNota()));
						String telefones;
						if (p.getTelefones().size() == 0)
							telefones = "sem telefone";
						else {
							telefones = "";
							for (Telefone t : p.getTelefones())
								telefones = telefones + " " + t.getNumero();
						}
						textField_4.setText(telefones);

						// carregar foto
						if (p.getFoto() != null) {
							// converte byte[] para BufferedImage do icon do label
							InputStream in = new ByteArrayInputStream(p.getFoto());
							buffer = ImageIO.read(in);
							ImageIcon icon = new ImageIcon(buffer.getScaledInstance(buffer.getWidth(),
									buffer.getHeight(), Image.SCALE_DEFAULT));
							icon.setImage(
									icon.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1));
							label_1.setIcon(icon);
						} else {
							buffer = null;
							label_1.setText("sem foto"); // limpa a imagem
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

		label_2 = new JLabel("selecione uma pessoa para editar");
		label_2.setBounds(21, 187, 394, 14);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("nome:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(21, 216, 62, 14);
		frame.getContentPane().add(label_3);

		label_4 = new JLabel("nascimento:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(356, 216, 73, 14);
		frame.getContentPane().add(label_4);

		label_8 = new JLabel("novo numero:");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(21, 300, 74, 14);
		frame.getContentPane().add(label_8);

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
		button_1.setToolTipText("cadastrar nova pessoa");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					criarAluno();
			}
		});
		button_1.setBounds(548, 327, 95, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Atualizar");
		button_2.setToolTipText("atualizar pessoa ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					atualizarAlunoSelecionado();
			}
		});
		button_2.setBounds(284, 327, 95, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Apagar");
		button_3.setToolTipText("apagar pessoa e seus dados");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					apagarAlunoSelecionada();
			}
		});
		button_3.setBounds(415, 327, 95, 23);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Limpar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				button_6.doClick(); // limpa a foto
			}
		});
		button_4.setBounds(147, 327, 95, 23);
		frame.getContentPane().add(button_4);

		button_5 = new JButton("Buscar foto");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_2.getText().isEmpty()) {
					label.setText("selecione uma pessoa");
					return;
				}
				File file = selecionarArquivoFoto();
				if (file == null)
					return; // arquivo nao foi selecionado

				try {
					buffer = ImageIO.read(file); // ler imagem do arquivo
					ImageIcon icon = new ImageIcon(
							buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(), Image.SCALE_DEFAULT));
					icon.setImage(icon.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1));
					label_1.setIcon(icon);
					label.setText("Precisa atualizar/criar pessoa para salvar a foto");
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
				label.setText("Precisa atualizar/criar pessoa para salvar a foto");

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

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(439, 212, 87, 20);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(93, 238, 253, 20);
		frame.getContentPane().add(textField_3);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(93, 296, 86, 20);
		frame.getContentPane().add(textField_5);

		label_5 = new JLabel("apelidos:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(21, 241, 62, 14);
		frame.getContentPane().add(label_5);

		label_6 = new JLabel("telefones:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(21, 270, 62, 14);
		frame.getContentPane().add(label_6);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(93, 266, 433, 20);
		frame.getContentPane().add(textField_4);

		button = new JButton("ver Telefones");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().isEmpty()) {
						label.setText("selecione uma pessoa");
						return;
					}

					String nome = textField_1.getText();
					Aluno p = FachadaAluno.localizarAluno(nome);

					String telefones;
					if (p.getTelefones().size() == 0)
						telefones = "sem telefone";
					else {
						telefones = "";
						for (Telefone t : p.getTelefones())
							telefones = telefones + "\n" + t.getNumero();
					}
					JOptionPane.showMessageDialog(frame, telefones, "Telefones", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button.setBounds(233, 296, 158, 23);
		frame.getContentPane().add(button);

		label_7 = new JLabel("nota:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(356, 242, 73, 14);
		frame.getContentPane().add(label_7);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_6.setColumns(10);
		textField_6.setBounds(439, 238, 43, 20);
		frame.getContentPane().add(textField_6);

		frame.setVisible(true);
	}

	public void listagem() {
		try {
			// objeto model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			// adicionar as colunas (0,1,2) do grid
			model.addColumn("Id");
			model.addColumn("Nome");
			model.addColumn("Nascimento");
			model.addColumn("Nota");
			model.addColumn("Idade");

			// adicionar as linhas do grid
			List<Aluno> lista = FachadaAluno.listarAlunos();
			for (Aluno p : lista)
				model.addRow(new Object[] { p.getId(), p.getNome(), p.getDtNascimento(), p.getNota(), p.getIdade() });

			label_2.setText("resultados: " + lista.size() + " alunos   - selecione uma linha para editar");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void apagarAlunoSelecionada() {
		try {
			label.setText("");
			textField_5.setText("");
			String nome = textField_1.getText();

			Object[] options = { "Confirmar", "Cancelar" };
			int escolha = JOptionPane.showOptionDialog(null, "Esta operação apagará a pessoa " + nome, "Alerta",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (escolha == 0) {
				FachadaPessoa.apagarPessoa(nome);
				label.setText("aluno excluido");
				listagem();
			} else
				label.setText("exclusão cancelada");

		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void criarAluno() {
		try {
			label.setText("");
			String nome = textField_1.getText().trim();
			String nascimento = textField_2.getText().trim();
			double nota = Double.parseDouble(textField_6.getText().trim());
			List<String> apelidos = new ArrayList<>(Arrays.asList(textField_3.getText().trim().split(",")));

			FachadaAluno.criarAluno(nome, nascimento, apelidos, nota);

			String numero = textField_5.getText();
			if (!numero.isEmpty())
				FachadaTelefone.criarTelefone(numero, nome);
			label.setText("aluno criado");
			listagem();
		} catch (NumberFormatException ex) {
			label.setText("nota inválida");
		} catch (Exception ex) {
			label.setText(ex.getMessage());
		}
	}

	public void atualizarAlunoSelecionado() {
		try {
			label.setText("");
			String nome = textField_1.getText();
			String nascimento = textField_2.getText();
			double nota = Double.parseDouble(textField_6.getText().trim());
			List<String> apelidos = new ArrayList<>(Arrays.asList(textField_3.getText().trim().split(",")));

			FachadaAluno.alterarAluno(nome, nascimento, apelidos, nota);

			byte[] bytesfoto = null;
			if (buffer != null)
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(buffer, "jpg", baos);
					bytesfoto = baos.toByteArray();
					baos.close();
				} catch (IOException ex1) {
					label.setText("problema na conversão da imagem em bytes");
				}
			FachadaPessoa.alterarFoto(nome, bytesfoto);

			String numero = textField_5.getText();
			if (!numero.isEmpty())
				FachadaTelefone.criarTelefone(nome, numero);

			label.setText("aluno atualizado");
			listagem();
		} catch (Exception ex2) {
			label.setText(ex2.getMessage());
		}
	}

	public File selecionarArquivoFoto() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "gif");
		chooser.setFileFilter(filter);
		try {
			// exibir pasta externa no Windows
			// chooser.setCurrentDirectory(new File("c:\\"));
			// exibir pasta interna \fotos
			chooser.setCurrentDirectory(new File((new File(".").getCanonicalPath() + "\\src\\fotos")));
		} catch (IOException e) {
		}
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		return file;
	}
}
