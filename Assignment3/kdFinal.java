import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


//defining a data type which contain two doubles in a pair
class pair{//x and y are values of the coordinates and z tells whether cutting plane is w.r.t x or y-axis
	double x,y;
	boolean z;//true implies about x-axis and false implies about y-axis
	void print(){
		System.out.println("["+x+","+y+"]");
	}

}

//defining k-d tree
class kdTree{
	pair node=new pair();
	kdTree left;
	kdTree right;

	kdTree(){
		left=null;
		right=null;}

	void printTree(){ if(left==null && right==null){
		System.out.print("Node("+"["+node.x+","+node.y+"],"+" Leaf, Leaf)");
	}
	else if(left!=null && right==null){
		System.out.print("Node("+"["+node.x+","+node.y+"], ");
		System.out.println();
		left.printTree();
		System.out.print(", Leaf)");
	}							  
	else if(left==null && right!=null){
		System.out.print("Node("+"["+node.x+","+node.y+"], "+"Leaf, ");
		right.printTree();
		System.out.print(")");		 
	}
	else{
		System.out.print("Node("+"["+node.x+","+node.y+"], ");
		System.out.println();
		left.printTree();
		System.out.print(", ");
		System.out.println();
		right.printTree();
		System.out.print(")");
	}								  

	}}

// defining a class which contains method which can sort array on the basis of x or y coordinate
class bubblesort{

	pair[] sortX(pair[] a,int e,int f){
		int max=e;
		pair p=new pair();
		while(f>e){
			for(int i=e;i<f;i++){
				if((a[max].x)>(a[max+1].x)){p=a[max];
				a[max]=a[max+1];
				a[max+1]=p;
				max++;
				}
				else if((a[max].x)==(a[max+1].x)){if((a[max].y)>=(a[max+1].y)){p=a[max];
				a[max]=a[max+1];
				a[max+1]=p;
				max++;

				}
				else{max++;};							 

				}							
				else{max++;}
			}
			max=e;
			f--;


		}

		return a;					
	}

	pair[] sortY(pair[] a,int e,int f){
		int max=e;
		pair p=new pair();
		while(f>e){
			for(int i=e;i<f;i++){
				if((a[max].y)>(a[max+1].y)){p=a[max];
				a[max]=a[max+1];
				a[max+1]=p;
				max++;
				}

				else if((a[max].y)==(a[max+1].y)){if((a[max].x)>=(a[max+1].x)){p=a[max];
				a[max]=a[max+1];
				a[max+1]=p;
				max++;

				}
				else{max++;};							 

				}								
				else{max++;}
			}
			max=e;
			f--;


		}

		return a;					
	}
}

//formation of the kd tree
class makeKDTree{
	bubblesort b=new bubblesort();
	kdTree TreeX(pair[] arr,int e,int f){kdTree t1=new kdTree();// makes kdTree of a pair[] from pos-e to pos-f
	arr=b.sortX(arr,e,f);	//sorted the array about x coordinates			         
	int l=(e+f+1)/2;
	if((f-e+1)==0){t1=null;
	}
	else if((f-e+1)==1){t1.node=arr[e];
	t1.node.z=true;}
	else{t1.node=arr[l];
	t1.node.z=true;
	t1.left=TreeY(arr,e,(l-1));
	t1.right=TreeY(arr,(l+1),f);

	}
	return t1;
	}
	kdTree TreeY(pair[] arr,int e,int f){kdTree t1=new kdTree();
	arr=b.sortY(arr,e,f);
	int l=(e+f+1)/2;
	if((f-e+1)==0){t1=null;
	}
	else  if((f-e+1)==1){t1.node=arr[e];
	t1.node.z=false;}
	else{		 t1.node=arr[l];
	t1.node.z=false;  
	t1.left=TreeX(arr,e,(l-1));
	t1.right=TreeX(arr,(l+1),f);

	}
	return t1;
	}


}
//===========**checked and found to be perfect upto here**==============
class Search{
	pair centre=new pair();
	pair nearest=new pair();
	pair xRange=new pair();
	pair yRange=new pair();
	double radius;
	int count=0;
	void setCentre(double x2,double y2){
		centre.x=x2;
		centre.y=y2;
	}
	void setVariables(pair pair4){
		nearest=pair4;
		radius=dist(nearest);
		xRange.x=(centre.x-radius);
		xRange.y=(centre.x+radius);
		yRange.x=(centre.y-radius);
		yRange.y=(centre.y+radius);
	}		 

	String isIntersect(pair pair1){//this method tells position of a given pair w.r.t. circle
		String s;
		if(pair1.z==true){
			if(pair1.x>=xRange.x && pair1.x<=xRange.y){ s="mid";
			return s;
			}
			else if(pair1.x<xRange.x){s="right";
			return s;
			}
			else{s="left";
			return s;
			}												  
		}
		else{
			if(pair1.y>=yRange.x && pair1.y<=yRange.y){ s="mid";
			return s;}
			else if(pair1.y<yRange.x){s="right";
			return s;
			}                                                 
			else{s="left";
			return s;
			}												  
		}				  
	}

	double dist(pair p1){//gives the distance of pair w.r.t. centre of circle
		double dx=(p1.x-centre.x);
		double dy=(p1.y-centre.y);
		count++;
		return java.lang.Math.sqrt((dx*dx)+(dy*dy));
	}

	void reachNearerX(kdTree t1){if(t1==null){nearest=null;}
	else if(centre.x<t1.node.x){if(t1.left==null){setVariables(t1.node);}
	else{reachNearerY(t1.left); }
	}
	else if(centre.x==t1.node.x){
		if(centre.y<t1.node.y){if(t1.left==null){setVariables(t1.node);}
		else{reachNearerY(t1.left); }
		}
		else if(centre.y==t1.node.y){setVariables(t1.node);}
		else{
			if(t1.right==null){setVariables(t1.node);}
			else{reachNearerY(t1.right); }
		}														
	}
	else{if(t1.right==null){setVariables(t1.node);}
	else{reachNearerY(t1.right) ;}
	}							
	}			 
	void reachNearerY(kdTree t1){if(t1==null){nearest=null;}
	else if(centre.y<t1.node.y){if(t1.left==null){setVariables(t1.node);}
	else{reachNearerX(t1.left) ;}
	}
	else if(centre.y==t1.node.y){
		if(centre.x<t1.node.x){if(t1.left==null){setVariables(t1.node);}
		else{reachNearerX(t1.left) ;}
		}
		else if(centre.x==t1.node.x){setVariables(t1.node);}
		else{if(t1.right==null){setVariables(t1.node);}
		else{reachNearerX(t1.right); }
		}														
	}
	else{if(t1.right==null){setVariables(t1.node);}
	else{reachNearerX(t1.right) ;}
	}							
	}
	pair bestOfTwo(pair p1,pair p2){
		if(dist(p1)<=dist(p2)){return p1;}
		else{return p2;}
	}

	pair goDown(kdTree t1){ if(nearest!=null){if(radius!=0.0){if(isIntersect(t1.node)=="mid"){
		if(dist(t1.node)<radius){setVariables(t1.node);}
		if(t1.left==null && t1.right!=null){goDown(t1.right);}
		else if(t1.left==null && t1.right==null){}
		else if(t1.left!=null && t1.right==null){goDown(t1.left);}
		else{goDown(t1.left);
		goDown(t1.right);}
	}
	else if(isIntersect(t1.node)=="left"){if(t1.left==null){}
	else{goDown(t1.left);}
	}
	else{if(t1.right==null){return nearest;}
	else{goDown(t1.right);}
	}	
	return nearest;											
	}
	else{return nearest;}
	}
	else{return null;}
	}
}
//================================
class kdFinal{

	public static void main(String args[]) throws IOException{
		makeKDTree m1=new makeKDTree();
		Search s1=new Search();
		BufferedReader reader ;
		try{
			reader= new BufferedReader( new FileReader(args[0]) );;
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
			return;}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("show file");
			return;}
		String line=null;
		line= reader.readLine();
		try{
			line=line.replaceAll(","," ");
			line=line.replaceAll("\\(","");
			line=line.replaceAll("\\)","");
		}
		catch(NullPointerException e){System.out.println("null input");
		return;}
		double x1,y1;
		Scanner scan=new Scanner(line);
		int len=scan.nextInt();
		pair[] a1=new pair[len];
		for(int j=0;j<len;j++){
			a1[j]=new pair();
			a1[j].x=scan.nextDouble();
			a1[j].y=scan.nextDouble();
		}
		kdTree k1=m1.TreeX(a1,0,(len-1));
		k1.printTree();
		System.out.println();
		System.out.println(".........................");
		//============================================================================
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the value of x-coordinate");
		x1=sc.nextDouble();
		System.out.println("Enter the value of y-coordinate");
		y1=sc.nextDouble();
		long start=System.nanoTime();
		s1.setCentre(x1,y1);
		s1.reachNearerX(k1);
		System.out.print("nearest node is = ");
		s1.goDown(k1).print();
		long end=System.nanoTime();
		// System.out.println();
		System.out.println("Time taken for this search is "+(end-start)+" ns. ");
		System.out.println(s1.count);
	}
}	
