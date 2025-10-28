package flightsearch;
import java.lang.reflect.Array;
import java.util.*;
public class flightsearch {

	public static void main(String[] args) {
		int arr[]= {402,688};
		int p=0;
		int x=arr.length;
		Scanner sc=new Scanner(System.in);
		int flight_search=sc.nextInt();
		for (int i=0;i<x;i++) {
			if(flight_search==arr[i]) {
				p=1;
				break;
				
			}
		}
				if(p==1) {
			System.out.println("Flight detected");
		}
				else {
					System.out.println("No Flight detected");
				}
	}

}
