

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PhySol_Initial_Window extends PhySol_Main{

	private JFrame frame;
	private JTextField textfield_Input;
	String textpane_data = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhySol_Initial_Window window = new PhySol_Initial_Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PhySol_Initial_Window() {
		initialize();
		Initialize_RNN();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Image imgMainBackground = new ImageIcon(this.getClass().getResource("/welcome_background.png")).getImage().getScaledInstance(850, 495, Image.SCALE_DEFAULT);
		Image imgPlainBackground = new ImageIcon(this.getClass().getResource("/plain_background.png")).getImage().getScaledInstance(850, 495, Image.SCALE_DEFAULT);
		ImageIcon imgLogo = new ImageIcon(this.getClass().getResource("/logo.png"));

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 859, 534);
		frame.setIconImage(imgLogo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_solve = new JPanel();
		panel_solve.setBounds(0, 0, 843, 495);
		frame.getContentPane().add(panel_solve);
		panel_solve.setLayout(null);
		panel_solve.show(false);
		panel_solve.enable(false);		
		
		textfield_Input = new JTextField();
		textfield_Input.setBounds(10, 11, 730, 30);
		panel_solve.add(textfield_Input);
		textfield_Input.setText("");
		textfield_Input.setColumns(10);
		
		JButton btn_Solve = new JButton("SOLVE");
		btn_Solve.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btn_Solve.setBackground(new Color(201, 201, 201));
		btn_Solve.setForeground(new Color(38, 69, 94));
		btn_Solve.setBounds(744, 11, 89, 30);
		panel_solve.add(btn_Solve);
		
		JButton btn_Reset = new JButton("Reset");
		btn_Reset.setFont(new Font("Rockwell", Font.PLAIN, 13));
		btn_Reset.setBackground(new Color(201, 201, 201));
		btn_Reset.setForeground(new Color(38, 69, 94));
		btn_Reset.setBounds(744, 454, 89, 30);
		panel_solve.add(btn_Reset);
		btn_Reset.setEnabled(false);
		
		JTextPane textpane_Output = new JTextPane();
		textpane_Output.setEditable(false);
		textpane_Output.setBounds(10, 52, 730, 432);
		panel_solve.add(textpane_Output);
		
		JLabel lbl_plainBackgroundImg = new JLabel("SOLVE");
		lbl_plainBackgroundImg.setBounds(0, 0, 843, 495);
		lbl_plainBackgroundImg.setIcon(new ImageIcon(imgPlainBackground));
		panel_solve.add(lbl_plainBackgroundImg);
		
		JPanel panel_main = new JPanel();
		panel_main.setBounds(0, 0, 843, 495);
		frame.getContentPane().add(panel_main);
		panel_main.setLayout(null);
		
		JButton btn_Start = new JButton("START");
		btn_Start.setForeground(new Color(255, 255, 255));
		btn_Start.setFont(new Font("Rockwell", Font.PLAIN, 17));
		btn_Start.setBackground(new Color(38, 69, 94));
		//btnNewButton.setForeground(new Color(38, 69, 94));
		btn_Start.setBounds(371, 408, 100, 30);
		panel_main.add(btn_Start);
		
		JLabel lbl_mainBackgroundImg = new JLabel("");
		lbl_mainBackgroundImg.setBounds(0, 0, 843, 495);
		lbl_mainBackgroundImg.setIcon(new ImageIcon(imgMainBackground));
		panel_main.add(lbl_mainBackgroundImg);

		btn_Start.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				panel_main.show(false);
				panel_main.enable(false);		
				panel_solve.show(true);			
				panel_solve.enable(true);		
				}
		});
		
		btn_Solve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					inputProblem = textfield_Input.getText();			
			        try {
			        InitializeInput();
			        textpane_data = "\n\tGIVEN :";
			        for(String param : givenEntailment){
			        	textpane_data += "\n\t\t " + param.split(":")[1] + " " + param.split(":")[2];
			        }
			        textpane_data += "\n\n\n\tREQUIRED :  ";
			        textpane_data += requiredEntailment.split(":")[0] + "'s " + requiredEntailment.split(":")[1];
			        textpane_data += "\n\n\n\tSOLUTION :  ";	
				        for(String der : ParameterHandling.derivation){
				        	textpane_data += "\n\t"+der;
						}
			        }
			        catch (Exception w){
			        	textpane_Output.setText("\n\n\t\tSorry. My knowledge is not enough to answer your Question right now. I'll try to learn it for future inquiry.");
			        }
		        
			        textpane_Output.setText(textpane_data);
			        btn_Reset.setEnabled(true);
			        btn_Solve.setEnabled(false);
				
		        
				}
		});
		
		btn_Reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				inputProblem = null;
				//ParameterHandling.givenParameter.
				ParameterHandling.derivation = new String[0];
				givenEntailment.clear();
				givenVariable = "obj";
				givenValue.clear();
				givenUnit.clear();
				requiredEntailment = null;
				textpane_Output.setText("");
				textfield_Input.setText("");
				btn_Solve.setEnabled(true);
				btn_Reset.setEnabled(false);
				}
		});
	}
}
