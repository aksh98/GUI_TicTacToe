
class Point{
	int x, y;
	Point(int x,int y){
		this.x = x;
		this.y = y;
	}
}
public class AIAlgo {
	private int[][] arr;
	public AIAlgo() {
	arr = new int[3][3];	
	}
//anyone wins || xwins->2 || owins->1 || tie->3 ||any empty slot -> 0;
	public int WhoWins(){
		int flag = 0;
		for (int i=0 ; i<3 ; i++){
			if((arr[i][1] == arr[i][2])&&(arr[i][0] == arr[i][1])&&(arr[i][0]!= '-')){
				if(arr[i][1]== 'X'){
					return 2;}
				else{ return 1;}
			}
			if((arr[0][i] == arr[1][i])&&(arr[0][i] == arr[2][i])&&(arr[2][i]!= '-')){
				if(arr[1][i]== 'X'){
					return 2;}
				else{return 1;}
			}
		}
		if((arr[0][0] == arr[1][1])&&(arr[0][0] == arr[2][2])&&(arr[0][0]!= '-')){
			if(arr[1][1]== 'X'){return 2;}
				else{ return 1;}	
		}
		else if((arr[0][2] == arr[1][1])&&(arr[2][0]!= '-')&&(arr[0][2] == arr[2][0])){
			if(arr[1][1]== 'X'){return 2;}
				else{ return 1;}
		}
		for(int i=0;i<3;i++){
			for (int j=0 ;j<3 ;j++){
				if(arr[i][j] == '-'){
					flag = 1;}
			}
		}
		if(flag != 1){return 3;}
		return 0;
	}
//-----------------------------------------------------------
	public int getValueAt(int i,int j){
		return arr[i][j];
	}
//----------------------------------------------/
	public boolean put(int x,int y,int val){
		if(x>2 || x<0 || y>2 || y<0){
			return false;
			}
		if(arr[x][y] != 0){
			return false;
		}
		arr[x][y] = val;
		return true; 
		}
///---------------------------------------------
	public boolean random_put(int x,int y,int val){
		if(x>2 || x<0 || y>2 || y<0){
			return false;
			}
		if(arr[x][y] != 0){
			//System.out.println("Invalid coordinates! Enter again");
			return false;
		}
		arr[x][y] = val;
		return true; 
	}
//-------------------------------------------
	
	public int[][] get(){
		return arr;
		}
//---------------------------------------------
///-------------------------------------------
	public Point WinningMove(int player){
		//int[] FreePoints;
	//	Point AIve;
		boolean canwin ;
		for(int i=0;i<3;i++){
			for(int j =0;j<3;j++){
				if(arr[i][j]==0){
					arr[i][j]=player;
					canwin = CanWin(player);
					arr[i][j]=0;
					if(canwin==true)
						//Aive = Point(i,j);
						return new Point(i,j); 
				}}
		}
		return null;
	}
//check @player(-1)/ opponent (+1) wins ? else 0;
	
	public boolean CanWin(int player){
		final int DI[]={-1,0,1,1};
  		final int DJ[]={1,1,1,0};
 	 	for(int i=0;i<3;i++)
   			for(int j=0;j<3;j++) {

	    		if(getValueAt(i, j)!= player) 
   		 			continue;

		   		for(int k=0;k<4;k++) {
		   		  	int  ctr= 0;
	   			  	while(getValueAt(i+DI[k]*ctr, j+DJ[k]*ctr)==player) 
	   			    	ctr++;
					if(ctr==3) 
			  			return true;
	   		}
 		}
 		return false;
	}
	public Point nextMove(int player){
		Point bestMove = WinningMove(player);
		  int opponent;
		if(getValueAt(1,1)==0)
			return new Point(1,1);
		if(bestMove!=null)
			return bestMove;
		if(player==1){ opponent = 2;}
		else{opponent = 1;}
		for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getValueAt(i, j)==0)
                {
                    arr[i][j] = player;
                 if(player == 1){opponent = 2;}
                 else{opponent = 1;}
              	boolean ok = WinningMove(opponent) == null;
                    arr[i][j] = 0;
                    if(ok) return new Point(i,j);
                }
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getValueAt(i, j)==0)
                    return new Point(i,j);

        // no move possible
        return null;
	}
}

/*public int score(char player){
if(player=='X'){
	if(WhoWins()==1){return -10;}//owins
	else if(WhoWins()==2){return 10;}
}
else{
	if(WhoWins()==2){return -10;}//xwins
	else if(WhoWins()==1){return 10;}
}
if(WhoWins()==3){return 0;}
return -1;
}*/
