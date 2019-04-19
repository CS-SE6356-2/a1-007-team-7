import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Canvas;

public class testGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testGui window = new testGui();
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
	public testGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 0));
		
		JButton btnStart = new JButton("Start the game!");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStart.setHorizontalAlignment(SwingConstants.LEADING);
		btnStart.setForeground(new Color(255, 20, 147));
		btnStart.setBackground(new Color(255, 255, 240));
		frame.getContentPane().add(btnStart, BorderLayout.EAST);
		frame.setBounds(100, 100, 571, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
