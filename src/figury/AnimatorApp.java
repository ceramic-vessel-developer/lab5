package figury;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class AnimatorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Random rand = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AnimatorApp frame = new AnimatorApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param delay
	 */
	public AnimatorApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int ww = 1500, wh = 900;
		setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		AnimPanel kanwa = new AnimPanel();
		kanwa.setBounds(10, 11, 1456, 780);
		contentPane.add(kanwa);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				kanwa.initialize();
			}
		});

		JButton btnAdd = new JButton("Add Neutron");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.addFig(rand.nextInt(50),rand.nextInt(730),Figures.NEUTRON);
			}
		});
		btnAdd.setBounds(10, 810, 80, 23);
		contentPane.add(btnAdd);
		
		JButton btnAnimate = new JButton("Animate");
		btnAnimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.animate();
			}
		});
		btnAnimate.setBounds(100, 810, 80, 23);
		contentPane.add(btnAnimate);

		JSpinner spinnerQuantity = new JSpinner(new SpinnerNumberModel(10,1,200,1));
		spinnerQuantity.setBounds(400, 810, 80, 23);
		contentPane.add(spinnerQuantity);

		JButton btnQuantity = new JButton("Add Uran");
		btnQuantity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int value = (int) spinnerQuantity.getValue();
				for (int i = 0; i < value; i++) {
					kanwa.addFig(rand.nextInt(1400),rand.nextInt(730),Figures.URAN_UNSTABLE);
				}
				btnQuantity.setEnabled(false);
			}
		});
		btnQuantity.setBounds(500, 810, 80, 23);
		contentPane.add(btnQuantity);
		
	}

}
