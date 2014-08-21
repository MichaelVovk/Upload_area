import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private TextArea a;
	private JCheckBox checkbox;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test() ;
		

	}

	public Test() throws HeadlessException{
		super();
		
		setTitle("Automation script runner");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.BLUE);

		setPreferredSize(new Dimension(500,400));
		setLocationRelativeTo(null);
		//setSize(500,400);
	    addComponents(getContentPane());
		pack();
		
		setVisible(true);
		
	}

	private void addComponents(Container contentPane) {
		contentPane.setLayout(new BorderLayout());
		contentPane.add(new JPanel(),BorderLayout.NORTH);
		JButton b = new JButton("Run");
		b.addActionListener(new ActionListener() {
			

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//a.setText(textField.getText());
				checkbox.getItemListeners();
				if(checkbox.isSelected()){
					System.out.println("Perform selected script");
					System.out.println(checkbox.getLabel());
					a.setText(checkbox.getLabel()+" was selected");	
				}}
		});
		
		contentPane.add(b,BorderLayout.EAST);
		textField = new JTextField("Select Test scripts and press run");
		contentPane.add(textField,BorderLayout.WEST);
		
		a = new TextArea("blabla");
		contentPane.add(a,BorderLayout.SOUTH);
		checkbox = new JCheckBox("TC name");
		checkbox.setSelected(false);
		checkbox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent o) {
				a.setText(o.getStateChange()==1?"checked":"unchecked");

				
			}
		});

			

		contentPane.add(checkbox,BorderLayout.CENTER);
		
	}
	
}
