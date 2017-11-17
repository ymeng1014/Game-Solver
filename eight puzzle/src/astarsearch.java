import java.util.ArrayList;
import java.util.Stack;

public class astarsearch 
{
	public static ArrayList<String> search(int [] node, char heuristic)
	{
	ArrayList<String> p= new ArrayList<String>();
	searchnode initial = new searchnode(new eightnode(node));
    ArrayList<searchnode> OpenList= new ArrayList<searchnode>();
    ArrayList<searchnode> ClosedList= new ArrayList<searchnode>();
    //ArrayList<int> iteration= new ArrayList<int>();
    OpenList.add(initial);
    searchnode temp=initial;
    int count=0;
    
    //use A* search to find the goal
    //begin
    while(!temp.getCurrent().isGoal())
    {     count++;
    	//move the lowest one to ClosedList
    	for(int i=0; i<OpenList.size(); i++)
    	{   searchnode node1= OpenList.get(i);
    		if(temp.getCurrent().EqualState(node1.getCurrent()))
    		{
    			OpenList.remove(i);
    			ClosedList.add(temp);
    		}
    	}
    	
    	//start generate the successors and check whether it is duplicate
    	ArrayList<eightnode> gesuccessors=temp.getCurrent().Expand();
    	ArrayList<searchnode> successors=new ArrayList<searchnode>();
    	for(int i=0; i<gesuccessors.size(); i++)
    	{
			
			searchnode check;
			
			// generate different successor with different heuristic
			if(heuristic == 'p')
			{
				check= new searchnode(temp, gesuccessors.get(i), 
						temp.getGcost()+gesuccessors.get(i).findCost(),
						((eightnode) gesuccessors.get(i)).getMisplace(),count);
			}
			else if(heuristic=='m')
			{
				check= new searchnode(temp, gesuccessors.get(i), 
						temp.getGcost()+gesuccessors.get(i).findCost(),
						((eightnode) gesuccessors.get(i)).getManha(),count);
			}
			else
			{
				check= new searchnode(temp, gesuccessors.get(i), 
						temp.getGcost()+gesuccessors.get(i).findCost(),
						((eightnode) gesuccessors.get(i)).getCombine(),count);
			}
			 if (!checkrepeat(check))
			 {
    			 
	    			OpenList.add(check);
	    			successors.add(check);
	    			
	    		 }
    	}
    	
    	if(successors.size() == 0) continue;
    	
    	//find Lowest cost node
    	searchnode templow=OpenList.get(0);
    	//int fvalue=(int) templow.getFcost();
    	for(int i=0; i<OpenList.size(); i++)
    	{
    		if(OpenList.get(i).getFcost()<templow.getFcost())
    		{
    		   templow=OpenList.get(i);	
    		}
    	}
    	temp=templow;
  
    	
    count++;	
    }
    //end
    
    // find the goal then go back to find the path.
	Stack<searchnode> answer= new Stack<searchnode>();
	answer.push(temp).getParent();
	while(temp.getParent()!= null)
	{
		answer.push(temp);
		temp=temp.getParent();
	    
    }
    answer.push(temp);
    int s= answer.size()-1;
    // print the path
    for(int i=0; i<s; i++) 
    {
	   temp=answer.pop();
	   String prin= "	  "+temp.getCurrent().printState()+"	"+ Action(temp)+"	"
	   +"Hcost is "+ temp.getHcost()+"	"+" Gcost is "+ temp.getGcost()+ "	"+"Fcost is "+ temp.getFcost()+
	   "	"+"iteration is "+ temp.getIteration();
	   p.add(prin);
	   //String step = Action(temp);
	   //System.out.println(step);
	   //int location = temp.getLocation();
	  
	   //System.out.println("hcost = " + temp.getHcost());
	   //System.out.println("gcost = " + temp.getGcost());
	   //System.out.println("fcost = " + temp.getFcost());
	  
	   //System.out.println("");
	   }
    
    int so=OpenList.size();
    int sc=ClosedList.size();
    int total=so+sc;
    //System.out.println("total node"+total);
    //System.out.println("Number ofiteration "+ count);
    //System.out.println("Answer step"+s );
   p.add("	  Expand nodes are "+ total+"\n");
   
   return p;	
 }
	
	private static boolean checkrepeat(searchnode meng)
	{
		boolean rep=false;
		searchnode dup= meng;
		while (meng.getParent()!=null && !rep){
			if(meng.getParent().getCurrent().EqualState(dup.getCurrent())){
				rep=true;
			}
			meng=meng.getParent();
		}
       return rep;
	}
	
	private static String Action(searchnode meng)
	{
		int current1= meng.getCurrent().getBlank();
		String act=" ";
		int current2= 0;
		if(meng.getParent()!=null) 
		{
			current2 = meng.getParent().getCurrent().getBlank();
			int delta=current2-current1;
			
			if(delta==-3) {act="up";}
			else if(delta== +3) {act="down";}
			else if(delta == -1){ act ="right";}
			else if(delta == 1){ act ="left";}
			else {act="null";}
		}
		else{act="null";}
		
		return act;
	}
		
}
