import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class Window1 extends JFrame {
	AIAlgo game = new AIAlgo();	
	private static final long serialVersionUID = 1L;
	private static String player1 = "X";
	private int currPlayer;
	private String player2 = "O";
	static String USER1;
	static String USER2;
	JButton b1 = new JButton("User1 vs User2");
	JButton b2 = new JButton("User vs CPU");
	JButton b3 = new JButton("CPU vs AIBot");
	JButton b4 = new JButton("AIBot vs User");
	JButton Quit = new JButton("Quit the game");
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panelgrid = new JPanel();
	JPanel panell = new JPanel();
	private JButton StartBtn = new JButton("Start Game");
	private JButton QuitBtn = new JButton("QUIT");
	private JLabel label = new JLabel("Tic-Tac-Toe");
	private JLabel statusLabel = new JLabel();
	int[][] arr = game.get();
	grid grid = new grid();
//CONSTRUCTOR=============================================	
	public Window1() {
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(150,200,150,200)));
		setTitle("Tic Tac Toe");
		setSize(200	,300);
		setResizable(false);
		label.setFont(label.getFont ().deriveFont (30.0f));
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		handleEvents eventHandler = new handleEvents();
		StartBtn.setForeground(Color.blue);
		StartBtn.addActionListener(eventHandler);
		StartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(StartBtn);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		QuitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		QuitBtn.setForeground(Color.blue);
		QuitBtn.addActionListener(eventHandler);
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(QuitBtn);
		panel.setVisible(true);
		add(panel);
		//////////////////////////////////////////////////////////////////		
		panell.setLayout(new BoxLayout(panell,BoxLayout.Y_AXIS));
		panell.setBorder(new EmptyBorder(new Insets(150,200,150,200)));
		setTitle("Game");
		setSize(200,300);
		setResizable(false);
		panelgrid.setLayout(new BoxLayout(panelgrid,BoxLayout.Y_AXIS));
		panelgrid.setBorder(new EmptyBorder(new Insets(50,0,50,0)));
		//	Font font = new Font("Arial",Font.BOLD, 32);
  	}
	private class handleEvents implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==QuitBtn){
				System.exit(JFrame.EXIT_ON_CLOSE);}
			if(e.getSource()==StartBtn){
				startGame();
				grid tictac = new grid();
				tictac.setLayout(new GridLayout(3,3));
				panelgrid.add(tictac);
				panelgrid.add(Box.createRigidArea(new Dimension(0, 40)));
				panel2.add(panell);
				panelgrid.add(statusLabel);
				panel2.add(panelgrid);
				setContentPane(panel2);
				revalidate();
			}
		}	
	}

	//---------------------------------------------------
		
//left panel !
  	public void startGame(){
  		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
  		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
  		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
  		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
  		panell.add(b1);
  		panell.add(Box.createRigidArea(new Dimension(0, 40)));
		panell.add(b2);
  		panell.add(Box.createRigidArea(new Dimension(0, 40)));
		panell.add(b3);
  		panell.add(Box.createRigidArea(new Dimension(0, 40)));
		panell.add(b4);
		panell.add(Box.createRigidArea(new Dimension(0, 40)));
		panell.add(QuitBtn);
		Quit.setForeground(Color.blue);
		LHandler eHandler = new LHandler();
		Quit.addActionListener(eHandler);
		b1.addActionListener(eHandler);
		b2.addActionListener(eHandler);
		b3.addActionListener(eHandler);
		b4.addActionListener(eHandler);
		statusLabel.setText("Status will be dispayed here");
  	}
//action performed for panel left !
  	private class LHandler implements ActionListener{
  		public void actionPerformed(ActionEvent e){
  			if(e.getSource()==Quit){
				System.exit(JFrame.EXIT_ON_CLOSE);}
			else{
				
				for(int i=0;i<3;i++){
		   			for(int j=0;j<3;j++){
		    			grid.button[i][j].setEnabled(true);
		    			grid.button[i][j].setText(" ");
		  	 		}
		  	 	}
				if(e.getSource()==b1){
					USER1 = JOptionPane.showInputDialog("User1");
					USER2 = JOptionPane.showInputDialog("User2");
				//	UserVsUser();
				}
				if(e.getSource()==b2){
					USER1 = JOptionPane.showInputDialog("User1");
				//	UserVsRandom();
				}
				if(e.getSource()==b3){
				//	AIvsRandom();
				}
				if(e.getSource()==b4){
					USER1 = JOptionPane.showInputDialog("User1");
				//	AIvsUser();
				}
			}
  		}
  	}
//
	//////////////////////////////////////////////////////

	private void setStatus(String s){
		statusLabel.setText(s);
	}


	static Point getCoord(int i,int j){
		return new Point(i,j);
	}

	public boolean isGameOver(){
		if(game.WhoWins()==1){
			setStatus("Player X wins ! ");
			return true;
		} 
		if(game.WhoWins()==2){
			setStatus("Player O wins !! "); return true;
		}
		if(game.WhoWins()==3){
			setStatus("Thats a Draw !"); return true;
		}
		return false;
	}
	void switchPlayer(){
		if(this.currPlayer==1)	this.currPlayer = 2;
		else this.currPlayer = 1;
	}
	
	public static void main(String[] args){
		Window1 MyWindow = new Window1();
		MyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyWindow.setSize(200,300);
		MyWindow.pack();
		MyWindow.setVisible(true);
	}

	void UserVsUser(){
		setStatus("User1 - your turn!");
		currPlayer = 1;
		while(true){
			if(isGameOver()) return;
			setStatus("User2 - it's your turn!");
			switchPlayer();
		}
	}
	void UserVsRandom(){
		currPlayer = whoPlayfirst();
		if(currPlayer==1){
			setStatus("CPU will play first");}
		else if(currPlayer==2){setStatus("User Plays First ! ");}
		if(currPlayer==1){
			while(!isGameOver()){
				setStatus("ComputerPlays");
				CPUplays(1);
				switchPlayer();
				setStatus("User - its your turn !");
				switchPlayer();}}
		else{
			while(!isGameOver()){
				setStatus("User-its your turn");
				switchPlayer();
				setStatus("ComputerPlays!");
				CPUplays(2);
				switchPlayer();
			}
		}
	}

	void AIvsUser(){
		
		AIplays(currPlayer);
	}
	void AIvsRandom(){
		setStatus("We'll let the computer play first !");

	}
	private void AIplays(int player){
		if(isGameOver()) return;
	//	Point point = new Point();
		Point point = game.nextMove(player);
		int i = point.x;
		int j = point.y;
		if(point!=null)
			game.put(i,j,player);
		if(player==1){
			grid.button[i][j].setText("X");
			grid.button[i][j].setEnabled(false);
		}
		else{grid.button[i][j].setText("O");
		grid.button[i][j].setEnabled(false); }
	}
	private void CPUplays(int player){
		if(isGameOver()) return;
		Random random = new Random();
		int i,j;
		while(true){
			 i =  random.nextInt(3);
			 j = random.nextInt(3);
			if(game.random_put(i,j,player)) break;
		}
		if(player==1){
			grid.button[i][j].setText("X");
			grid.button[i][j].setEnabled(false);
		}
		else{grid.button[i][j].setText("O");
		grid.button[i][j].setEnabled(false); }
	}

	private int whoPlayfirst(){
		Random random = new Random();
		int player =  random.nextInt(2);
		currPlayer = player+1;
		return currPlayer;
	}
	void click(int i,int j){
		if(grid.button[i][j].isEnabled()){
			if(currPlayer == 1){
		   		game.put(i,j,1);
		   		grid.button[i][j].setText(player1);
			}
 			else{
 				game.put(i,j,2); 
 				grid.button[i][j].setText(player2);
 			}
 			grid.button[i][j].setEnabled(false);
 			//game.put(i,j,currPlayer);
   			if(isGameOver())return;
   			switchPlayer();
		}
	}

}

class grid extends JPanel{
	private static final long serialVersionUID = 1L;
	static JButton[][] button = new JButton[3][3]; 
	public grid(){
  		button[0][0].setPreferredSize(new Dimension(70,70));
  		handleEvent eventHandler = new handleEvent();
  		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				//grid.button[i][j].setFont(font);
				add(button[i][j]);
				button[i][j].setBackground(Color.lightGray);
				button[i][j].addActionListener(eventHandler);
			}	
		}
		setButEnabled(false);
	}

		void setButEnabled(boolean enable) {
			for(int i=0;i<3;i++){
   			for(int j=0;j<3;j++){
    			button[i][j].setEnabled(enable);
    			if(enable==true) 
    				button[i][j].setText(" ");
  	 		}
  	 		}
		}
		//grid.button listener
		private class handleEvent implements ActionListener{
			public void actionPerformed(ActionEvent e){
			Window1 Window1 = new Window1();
			for(int i=0;i<3;i++)
		   	 	for(int j=0;j<3;j++)
		    		if(e.getSource()==button[i][j]){
		    			Window1.click(i,j);	
		    			Window1.getCoord(i,j); 	 
		    		}
		}
	}
}

