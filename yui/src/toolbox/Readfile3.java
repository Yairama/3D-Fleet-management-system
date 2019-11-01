package toolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Entity;
import entities.Punto;

public class Readfile3 {
	private Scanner x;

	
	public void openFile() {
	try {
		x=new Scanner(new File("input3.txt"));
	}
	catch(Exception e) {
		System.out.println("could not find file");
	}

}
	public ArrayList<Punto> GetList() {
		ArrayList<Punto> Pointlist = new ArrayList<Punto>();
		while(x.hasNext()) {
			String b =x.next();
			String a =x.next();
			String c =x.next();
			Punto punto=new Punto(Double.parseDouble(a),Double.parseDouble(b));
			//System.out.println(a);
			Pointlist.add(punto);
		}
		return Pointlist;
	}

	public void closeFile() {
		x.close();
		
	}
}