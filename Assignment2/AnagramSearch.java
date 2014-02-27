//Assignment-02
import java.lang.*;
import java.util.*;
import java.io.*;
//=================================================

class String_sort{
	String sort(String s){
		char[] c=s.toCharArray();
		java.util.Arrays.sort(c);
		String x=new String(c);
		return x;
	}
}
//=====================================


//================
class array_prep{
	LinkedList[] array_prep1(){

		LinkedList[] arr=new LinkedList[20011];

		for (int i=0;i<20011;i++){
			arr[i]=new LinkedList<String>();
		}
		return arr;
	} }

//====================================
class hashTable{	
	array_prep prep=new array_prep();
	LinkedList[] a=prep.array_prep1();
	String_sort sort1=new String_sort();

	void insert(String s1){
		int i,k,j;
		String s2=sort1.sort(s1);
		//sort st and then cal. hashcode
		i=s2.hashCode();
		k=Math.abs(i%20011);
		a[k].addFirst(s1);
	}

	void anagram(String s){int k,i;
	String s3=sort1.sort(s);
	i=s3.hashCode();
	k=Math.abs(i%20011);
	int j=a[k].size();
	boolean d=false;
	System.out.print("[");
	int i1;
	Object o;
	String s_new;
	for(int m=0;m<j;m++){o=(a[k].get(m));
	s_new=o.toString();
	i1=(sort1.sort(s_new)).hashCode();				
	if (i==i1){System.out.print(a[k].get(m)+", ");
	d=true;}							 
	}
	System.out.println("]");			 
	if(d==false){System.out.println("No Such ANAGRAM Exist");}                   

	}
}




//=================================================
class AnagramSearch{
	public static void main(String args[]) throws IOException{
		hashTable h1=new hashTable();

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

		String line;
		line= reader.readLine();

		while(line!=null) {
			h1.insert(line);
			line = reader.readLine();
		}

		BufferedReader b1=new BufferedReader(new InputStreamReader(System.in));
		String s1;
		System.out.println("enter '1' to quit");
		System.out.println("enter your search word below");
		s1=b1.readLine();
		while(!s1.equals("1")){
			long start=System.nanoTime();
			h1.anagram(s1);
			long end=System.nanoTime();
			System.out.println("Time taken for this search is "+(end-start)+" ns. ");
			System.out.println();
			s1=b1.readLine();
		}

	}}