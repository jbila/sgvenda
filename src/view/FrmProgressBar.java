package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class FrmProgressBar {

	private JFrame FrmProgressBar;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProgressBar window = new FrmProgressBar();
					window.FrmProgressBar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ---------------------------------------------------------------
	public class Temporizador extends Thread {
		public void run() {
			FrmLogin f=new FrmLogin();
			f.utilizadorDao.count();
			while (progressBar.getValue() < 1000) {
				try {
					sleep(100);
					
					progressBar.setValue(progressBar.getValue() + 10);
					
					//System.out.println(f);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, e);
					// e.printStackTrace();
				}
			}

			FrmProgressBar.dispose();
			FrmLogin.main(null);
		}
	}

	// -----------------------------------------------------------------
	/**
	 * Create the application.
	 */
	public FrmProgressBar() {
		initialize();
		new Temporizador().start();
		FrmProgressBar.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FrmProgressBar = new JFrame();
		FrmProgressBar.setBackground(new Color(0, 0, 255));
		FrmProgressBar.setBounds(100, 100, 450, 294);
		FrmProgressBar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrmProgressBar.setUndecorated(true);
		FrmProgressBar.setLocationRelativeTo(null);
		FrmProgressBar.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 255));
		panel.setForeground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 450, 294);
		FrmProgressBar.getContentPane().add(panel);
		panel.setLayout(null);

		// -----------------------------------------
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(0, 0, 0));
		progressBar.setStringPainted(true);// escreve a percentagem no Jprogressbar
		// progressBar.setValue(50);// mostra o valor de inicio
		progressBar.setString("Loading...");
		progressBar.setMaximum(1000);
		progressBar.setForeground(new Color(255, 255, 255));
		progressBar.setBounds(0, 263, 450, 31);
		panel.add(progressBar);

		JLabel lblSgfSistemaDe = new JLabel("SISTEMA DE FACTURACAO E VENDA");
		lblSgfSistemaDe.setForeground(new Color(255, 255, 255));
		lblSgfSistemaDe.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSgfSistemaDe.setBackground(Color.BLACK);
		lblSgfSistemaDe.setBounds(10, 71, 440, 99);
		panel.add(lblSgfSistemaDe);
	}
}
