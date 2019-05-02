package br.com.alan.cliente.window;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.alan.cliente.comunicacao.Comunicador;
import br.com.alan.cliente.log.Log;

public class App {

	private JFrame frame;
	private Comunicador comunicador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		comunicador = new Comunicador();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 761, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tarefas - Cliente");
		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("jdialog window closed event received");
				super.windowClosed(e);
			}

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				close();
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Comandos", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));

		JTextPane textPane = new JTextPane();
		Log.setJTextPane(textPane);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(null);

		JButton btnC1 = new JButton("c1");
		btnC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comunicador.enviandoDadosParaServidor("c1");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnC1.setBounds(10, 21, 169, 169);
		panel.add(btnC1);

		JButton btnC2 = new JButton("c2");
		btnC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comunicador.enviandoDadosParaServidor("c2");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnC2.setBounds(189, 21, 169, 169);
		panel.add(btnC2);

		JButton btnC3 = new JButton("c3");
		btnC3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comunicador.enviandoDadosParaServidor("c3");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnC3.setBounds(368, 21, 169, 169);
		panel.add(btnC3);

		JButton btnTerminarServidor = new JButton("terminar servidor");
		btnTerminarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnTerminarServidor.setBounds(547, 21, 169, 169);
		panel.add(btnTerminarServidor);

		frame.getContentPane().setLayout(groupLayout);
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(frame, "Quer Realmente fechar essa bagaça???", "Fechar aplicação?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			comunicador.fechar();
			System.exit(0);
		}

	}

}
