package toolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Entity;
import entities.Punto;

public class Readfile {
	private Scanner x;
	private String filename;
	private ArrayList<Punto> Pointlist=new ArrayList<Punto>();
	public Readfile(String filename) {
		
		this.filename=filename;
		openFile();
		GetList();
		closeFile();
		
	}
	public ArrayList<Punto> getPointlist(){
		
		return Pointlist;
		
	}
	
	public void openFile() {
	try {
		x=new Scanner(new File(filename));
	}
	catch(Exception e) {
		System.out.println("could not find file");
	}

}
	public ArrayList<Punto> GetList() {
		while(x.hasNext()) {
			String a =x.next();
			String b =x.next();
			Punto punto=new Punto(Double.parseDouble(a),Double.parseDouble(b));
			Pointlist.add(punto);
		}
		return Pointlist;
	}

	public void closeFile() {
		x.close();
		
	}
}