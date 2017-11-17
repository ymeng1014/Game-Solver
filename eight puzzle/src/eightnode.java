import java.util.ArrayList;
import java.util.Arrays;
public class eightnode 
{
	
    private final int size=9;
    private int misplace=0;
    private int manha=0;
    private int combine=0;
    private final int[] goal= new int[] {1,2,3,8,0,4,7,6,5};
    private int [] currentnode;
    private int location;
    
    public eightnode(int[] node)
    {
    	currentnode= node;
    	setMisplace();
    	setManha();
    	setCombine();
    }
    
	public double findCost() 
	{
		return 1;
	}
    // first heuristic: how many number are misplace
    public void setMisplace() 
    {
		for (int i=0; i< currentnode.length;i++)
		{
		 if (currentnode[i]!= goal[i]&&currentnode[i]!=0){
			misplace++; 
		 }
		}
	}
	public int getMisplace() 
	{
		return misplace;
	}
	
	//second heuristic: Manhattan distance
	public void setManha() 
	{
		for(int j=0; j<size; j++){
			int value = currentnode[j];
			if (value !=0){
				int x1=j%3;
				int y1=j/3;
				int x2=0;
				int y2=0;
			for(int k=0; k<size; k++){
				if(value==goal[k]){
					x2 +=k%3;
					y2 +=k/3;
				}
			
			}
			manha += Math.abs(x1-x2) + Math.abs(y1-y2);	
			}
		
		}
	}
	public int getManha() 
	{
		return manha;
	}
	
	// third heuristic: add two search into one search
	public void setCombine() 
	{
		int h1= getMisplace();
		int h2= getManha();
		combine = h1+h2;
	}
	public int getCombine() 
	{
		return combine;
	}
	
	// find the location of blank
	public int getBlank()
	{
		int blank =0;
		for (int i=0;i<size;i++){
			if(currentnode[i]==0){
				blank =i;
			}
		}
		return blank;
	}
	
	// copy node sequence
	private int[] copy(int[] sequence)
	{
		
		int[] co = new int[size];
		for (int i=0; i<size; i++){
			co [i]=sequence[i];
		}
		return co;
	}
	
    // if the currentnode is the goal or not
	public boolean isGoal() 
	{
		if (Arrays.equals(currentnode,goal))
		{
			return true;
		}
		return false;
	}

	// expand the successor of current node
	public ArrayList<eightnode> Expand() 
	{
		 ArrayList<eightnode> successor = new ArrayList<eightnode>();
		 int blank = getBlank();
		 
		 //left
		 if(blank!=0 && blank!=3 && blank!= 6){
			 
			 change(blank -1, blank, successor);
			 location = 1;
			}
		 
		 //right
          if(blank!=2 && blank!=5 && blank!= 8){
			 
			 change(blank +1, blank, successor);
			 location = 2;
			}
          
          //up
          if(blank!=0 && blank!=1 && blank!= 2){
 			 
			 change(blank -3, blank, successor);
			 location = 3;
			}
          
          //down
          if(blank!=6 && blank!=7 && blank!= 8){
  			 
 			 change(blank +3, blank, successor);
 			location = 4;
 			}
		return successor;
	}

	

	public String printState() 
	{
		String printstate="{"+currentnode[0]+ "," + currentnode[1]+ ","+ currentnode[2]
				+ ","+currentnode[3] + "," + currentnode[4]+ ","+ currentnode[5]
						+"," + currentnode[6]+ "," + currentnode[7]+ ","+ currentnode[8]+"}";
		return printstate;
		//System.out.println(currentnode[3]+ " " + currentnode[4]+ " "+ currentnode[5]);
		//System.out.println(currentnode[6]+ " " + currentnode[7]+ " "+ currentnode[8]);
		
	}

	//compare two state
	public boolean EqualState(eightnode s) 
	{
		if(Arrays.equals(currentnode,((eightnode) s).getCurrentnode())){
			return true;
		}
		return false;
	}
	
	public int[] getCurrentnode() 
	{
		
		return currentnode;
	}
	
	// change the location of one node to another
	private void change( int one, int two, ArrayList<eightnode> s)
	{
		int[] cop=copy(currentnode);
		int yue=cop[one];
		cop[one]=currentnode[two];
		cop[two]=yue;
		s.add((new eightnode(cop)));
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
