
public class searchnode 
{
	private eightnode current;
	private searchnode parent;
	private double gcost;
	private double hcost;
	private double fcost;
	private int iteration;
	//private int location;
	
	
	public searchnode(eightnode s)
	{
		current =s;
		parent= null;
		gcost=0;
		hcost=getHcost();
		fcost=getFcost();
	}
	public searchnode (searchnode prev, eightnode s, double g, double h, int ite)
	{
		parent=prev;
		current =s;
		gcost=g;
		hcost=h;
		fcost=gcost+hcost;
		iteration=ite;
		//l=location;
		
	}
    public searchnode getParent()
    {
    	return parent;
    }
    public eightnode getCurrent()
    {
    	return current;
    }
    public double getFcost()
	{
		return fcost;
	}
    public double getHcost()
	{
		return hcost;
	}
    public double getGcost()
	{
		return gcost;
	}
   /* public int getLocation() {
		return location;
	}*/
	public int getIteration() {
		return iteration;
	}
	
}

