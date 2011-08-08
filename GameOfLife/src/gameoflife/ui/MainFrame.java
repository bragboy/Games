package gameoflife.ui;

import gameoflife.core.Globals;
import gameoflife.core.PlayGround;
import gameoflife.core.SampleStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private boolean[][] theState;
	
	private boolean lock;
	
	JPanel[][] panelArray = new JPanel[Globals.MAX_X][Globals.MAX_Y];
	
	//MAIN PANEL
	JPanel mainPanel = new JPanel();
	
	//CENTER PANEL
	JPanel centerPanel = new JPanel();

	//TOP PANEL ITEMS
	JButton btnBlock = new JButton("Block");
	JButton btnBoat = new JButton("Boat");
	JButton btnBlinker = new JButton("Blinker");
	JButton btnToad = new JButton("Toad");
	JButton btnCustom = new JButton("Custom");
	JPanel topPanel = new JPanel();
	
	//BOTTOM PANEL ITEMS
	JButton btnTick = new JButton("Tick");
	JLabel lblInstruction = new JLabel("Set a pattern and click -->");
	JButton btnDone = new JButton("Done");
	JPanel bottomPanel = new JPanel();
	
	boolean locked = false;
	
	private TopButtonsListener topBtnActionListener = new TopButtonsListener();
	private TickButtonListener tickBtnListener = new TickButtonListener();
	private CustomButtonListener customListener = new CustomButtonListener();
	
	public MainFrame(){
		this.setTitle("Game of Life!!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.add(getMainPanel());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        refreshCenterWithState();
	}
	
	public JPanel getMainPanel(){
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.add(topPanel(), BorderLayout.NORTH);
		mainPanel.add(centerPanel(), BorderLayout.CENTER);
		mainPanel.add(bottomPanel(), BorderLayout.SOUTH);
		return mainPanel;
	}
	
	private JPanel topPanel(){
		topPanel.add(btnBlock);
		topPanel.add(btnBoat);
		topPanel.add(btnBlinker);
		topPanel.add(btnToad);
		topPanel.add(btnCustom);
		btnBlock.addActionListener(topBtnActionListener);
		btnBoat.addActionListener(topBtnActionListener);
		btnBlinker.addActionListener(topBtnActionListener);
		btnToad.addActionListener(topBtnActionListener);
		btnCustom.addActionListener(customListener);
		return topPanel;
	}
	
	private JPanel centerPanel(){
		GridLayout matrixLayout = new GridLayout(Globals.MAX_X, Globals.MAX_Y);
		matrixLayout.setHgap(2);
		matrixLayout.setVgap(2);
		centerPanel.setLayout(matrixLayout);
        for(int i=0;i<Globals.MAX_X;i++){
            panelArray[i] = new JPanel[Globals.MAX_X];
            for(int j=0;j<Globals.MAX_Y;j++){
                panelArray[i][j] = new JPanel();
                panelArray[i][j].setLayout(new GridLayout(1,1));
                panelArray[i][j].addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent e){
                		if(lock){
                			JPanel clikedPanel = (JPanel)e.getSource();
                    		if(clikedPanel.getBackground() == Color.white){
                    			clikedPanel.setBackground(Color.black);
                    		}else{
                    			clikedPanel.setBackground(Color.white);
                    		}
                		}
                	}
				});
                centerPanel.add(panelArray[i][j]);
            }
        }
		return centerPanel;
	}
	
	private JPanel bottomPanel(){
		bottomPanel.add(btnTick);
		lblInstruction.setForeground(Color.red);
		bottomPanel.add(lblInstruction);
		bottomPanel.add(btnDone);
		btnDone.setVisible(false);
		lblInstruction.setVisible(false);
		btnTick.addActionListener(tickBtnListener);
		btnDone.addActionListener(customListener);
		return bottomPanel;
	}
	
	private void refreshCenterWithState(){
		for(int i=0;i<Globals.MAX_X;i++)
			for(int j=0;j<Globals.MAX_Y;j++)
				if(theState!=null && theState[i][j])
					panelArray[i][j].setBackground(Color.black);
				else
					panelArray[i][j].setBackground(Color.white);
		this.repaint();
	}
	
	private void lock(boolean state){
		lock = state;
		btnDone.setVisible(state);
		lblInstruction.setVisible(state);
		btnTick.setVisible(!state);
		btnBlock.setEnabled(!state);
		btnBoat.setEnabled(!state);
		btnToad.setEnabled(!state);
		btnBlinker.setEnabled(!state);
		btnCustom.setEnabled(!state);
	}
	
	public static void main(String args[]){
		new MainFrame();
	}
	
	class TopButtonsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			theState = SampleStates.getFreshState();
			if(e.getSource() == btnBlock)
				theState = SampleStates.blockPattern();
			if(e.getSource() == btnBlinker)
				theState = SampleStates.blinkerPattern();
			if(e.getSource() == btnBoat)
				theState = SampleStates.boatPattern();
			if(e.getSource() == btnToad)
				theState = SampleStates.toadPattern();
			refreshCenterWithState();
		}
	}
	
	class TickButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnTick){
				if(theState==null){
					theState = SampleStates.getFreshState();
				}
				PlayGround playGround = new PlayGround(theState);
				playGround.progress();
				theState = playGround.coreState;
			}
			refreshCenterWithState();
		}
	}
	
	class CustomButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCustom){
				lock(true);
				theState = SampleStates.getFreshState();
			}
			if(e.getSource() == btnDone){
				theState = SampleStates.getFreshState();
				for(int i=0;i<Globals.MAX_X;i++){
					for(int j=0;j<Globals.MAX_Y;j++){
						theState[i][j] = panelArray[i][j].getBackground() == Color.black;
					}
				}
				lock(false);
			}
			refreshCenterWithState();
		}
	}
}