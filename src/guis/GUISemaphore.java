
package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The GUI for assignment 3
 */
public class GUISemaphore 
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;				// The Main window
	private JProgressBar bufferStatus;	// The progressbar, showing content in buffer
	private JTextArea lstTruck;			// The truck cargo list
	private JLabel lblLimWeight;		// Label showing weight limit
	private JLabel lblLimVolume;		// Label showing volume limit
	private JLabel lblLimNrs;			// Label showing max nr of food
	private JLabel lblTruckStatus;		// Label showing truck waiting or loading
	private JButton btnDeliver;			// Button for starting deliverance
	private JLabel lblDeliver;			// Label showing one truck done, hides when next truck arrives
	private JLabel lblBStatus;			// Label showing factory B status: Hidden, working, waiting or stopped
	private JLabel lblAStatus;			// Label showing factory A status: Hidden, working, waiting or stopped
	private JButton btnStartB;			// Button start factory B
	private JButton btnStartA;			// Button start factory A
	private JButton btnStopB;			// Button stop factory B
	private JButton btnStopA;			// Button stop factory A
	
	private Controller controller;

	/**
	 * Constructor
	 */
	public GUISemaphore()
	{
	}
	
	/**
	 * Starts the application
	 */
	public void Start()
	{
		controller = new Controller(this);
		frame = new JFrame();
		frame.setBounds(0, 0, 652, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("ICA Item Delivery");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI()
	{
		// First create the four panels
		JPanel pnlBuffer = new JPanel();
		pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Storage"));
		pnlBuffer.setBounds(27, 25, 298, 71);
		pnlBuffer.setLayout(null);
		frame.add(pnlBuffer);
		JPanel pnlICA = new JPanel();
		pnlICA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Delivery to ICA"));
		pnlICA.setBounds(27, 115, 298, 281);
		pnlICA.setLayout(null);
		frame.add(pnlICA);
		JPanel pnlFoodb = new JPanel();
		pnlFoodb.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Food Factory B"));
		pnlFoodb.setBounds(381, 25, 246, 158);
		pnlFoodb.setLayout(null);
		frame.add(pnlFoodb);
		JPanel pnlFooda = new JPanel();
		pnlFooda.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Food Factory A"));
		pnlFooda.setBounds(381, 238, 246, 158);
		pnlFooda.setLayout(null);
		frame.add(pnlFooda);
		
		// Then create the progressbar, only component in buffer panel
		bufferStatus = new JProgressBar();
		bufferStatus.setBounds(17, 31, 262, 23);
		bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		bufferStatus.setForeground(Color.GREEN);
		pnlBuffer.add(bufferStatus);
		
		// Next set up components for the ICA Delivery frame, first static fields		
		JLabel lab1 = new JLabel("Truck Cargo:");
		lab1.setBounds(75, 24, 79, 13);
		pnlICA.add(lab1);
		JLabel lab2 = new JLabel("Truck Limits:");
		lab2.setBounds(17, 54, 77, 13);
		pnlICA.add(lab2);
		
		// Then variables used in program, add code/listeners
		lstTruck = new JTextArea();
		lstTruck.setEditable(false);
		JScrollPane spane = new JScrollPane(lstTruck);		
		spane.setBounds(159, 19, 120, 147);
		spane.setBorder(BorderFactory.createLineBorder(Color.black));		
		pnlICA.add(spane);
		lblLimWeight = new JLabel("Weight limit goes here");
		lblLimWeight.setBounds(20, 83, 200, 13);
		pnlICA.add(lblLimWeight);
		lblLimVolume = new JLabel("Volume limit goes here");
		lblLimVolume.setBounds(20, 96, 200, 13);
		pnlICA.add(lblLimVolume);
		lblLimNrs = new JLabel("Max items goes here");		
		lblLimNrs.setBounds(20, 109, 200, 13);
		pnlICA.add(lblLimNrs);
		lblTruckStatus = new JLabel("New Truck: No Items...");
		lblTruckStatus.setFont(new Font("Dejavu Sans", Font.PLAIN, 20));
		lblTruckStatus.setBounds(20, 199, 200, 20);
		pnlICA.add(lblTruckStatus);
		btnDeliver = new JButton("Start Deliver");
		btnDeliver.setBounds(17, 239, 109, 23);
		pnlICA.add(btnDeliver);
		lblDeliver = new JLabel("Truck Delivering");
		lblDeliver.setFont(new Font("Dejavu Sans", Font.BOLD, 20));
		lblDeliver.setForeground(Color.RED);
		lblDeliver.setBounds(133, 237, 200, 25);
		pnlICA.add(lblDeliver);
		
		// Static text in Food Factory B
		JLabel lab3 = new JLabel("Status:");
		lab3.setBounds(35, 31, 60, 13);
		pnlFoodb.add(lab3);
		
		// and variables used in program, add code/listeners
		lblBStatus = new JLabel("Producer Status here");
		lblBStatus.setFont(new Font("Dejavu Sans", Font.PLAIN, 20));
		lblBStatus.setBounds(38, 65, 200, 25);
		pnlFoodb.add(lblBStatus);
		btnStartB = new JButton("Start Working");
		btnStartB.setBounds(31, 114, 116, 23);
		pnlFoodb.add(btnStartB);
		btnStopB = new JButton("Stop");
		btnStopB.setBounds(156, 114, 60, 23);
		pnlFoodb.add(btnStopB);

		// Static text in Food Factory A
		JLabel lab4 = new JLabel("Status:");
		lab4.setBounds(35, 31, 60, 13);
		pnlFooda.add(lab4);
		
		// and variables used in program, add code/listeners
		lblAStatus = new JLabel("Producer Status here");
		lblAStatus.setFont(new Font("Dejavu Sans", Font.PLAIN, 20));
		lblAStatus.setBounds(38, 65, 200, 25);
		pnlFooda.add(lblAStatus);
		btnStartA = new JButton("Start Working");
		btnStartA.setBounds(31, 114, 116, 23);
		pnlFooda.add(btnStartA);
		btnStopA = new JButton("Stop");
		btnStopA.setBounds(156, 114, 60, 23);
		pnlFooda.add(btnStopA);
		
		//add the buttonlistener
		ButtonListener bL = new ButtonListener();
		btnStartA.addActionListener(bL);
		btnStopA.addActionListener(bL);
		btnStartB.addActionListener(bL);
		btnStopB.addActionListener(bL);
		btnDeliver.addActionListener(bL);
	}
	
	public void updateTruckCargo(String name) {
		if(name == ""){
			lstTruck.setText("");			
		}else{
			lstTruck.append(name);
		}
		
	}
	
	public void updateStorageProgress(int percentage) {
		bufferStatus.setValue(percentage);
	}
	
	public void updateFactoryStatus(int factoryNbr, String status) {
		if(factoryNbr == 0){
			lblAStatus.setText(status);
		}else{
			lblBStatus.setText(status);
		}
	}
	
	public void setTruckLimits(int maxW, int maxV, int maxItems) {
		lblLimWeight.setText("" + maxV);
		lblLimVolume.setText("" + maxW);
		if(maxItems == 0){
			lblLimNrs.setText("No limit");	
		}else{		  
			lblLimNrs.setText("" + maxV);	
		}
	}
	
	public void updateTruckStatus(String status) {
		lblTruckStatus.setText(status);
	}
	
	public void updateCargoList(String s){
		if(s == ""){
			lstTruck.setText("");
		}else{
			lstTruck.append(s);
		}
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnStartA){
				controller.startFactory(0);
			}else if(e.getSource() == btnStopA){
				controller.stopFactory(0);
			}else if(e.getSource() == btnStartB){
				controller.startFactory(1);
			}else if(e.getSource() == btnStopB){
				controller.stopFactory(1);
			}else if(e.getSource() == btnDeliver){
				controller.startDelivering();
			}
		}
	}

}
