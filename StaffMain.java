package com.gl.caseStudy1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffMain {

	 public static Double totalCalculation (Applicant applicant) {
		 if(applicant.getSubject1Mark() < 50 || applicant.getSubjec2Mark() < 50 || applicant.getSubject3Mark() < 50)
			 return (double)0;
		 else {
			 Double total = applicant.getSubject1Mark() + applicant.getSubjec2Mark() + applicant.getSubject3Mark();
			 return total;
		 }
	 }
	 
	 public static Double percentageCalculation (Double total) {
		 DecimalFormat df = new DecimalFormat("0.00");
		 String cal = String.valueOf(df.format((total/300)*100));
		 return Double.parseDouble(cal);
	 }

	public static void main(String[] args) {
		List<Applicant> applicantList = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of applicant detail to enter");
		int num = Integer.parseInt(sc.nextLine());
		
		try {
		Applicant app = null;
		for(int i=0; i<num; i++) {
			System.out.println("Enter applicant details");
			String details = sc.nextLine();
			String split[] = details.split(",");
			
			String name = split[0];
			Double subject1 = Double.parseDouble(split[1]);
			Double subject2 = Double.parseDouble(split[2]);
			Double subject3 = Double.parseDouble(split[3]);
			
			if((subject1<0 && subject1>100) || (subject2<0 && subject2>100) || (subject3<0 && subject3>100))
				throw new MarksException("one of the subject marks is less then 50");
			

			
			app = new Applicant(name, subject1, subject2, subject3, null, null);
			Double total = totalCalculation(app);
			Double percentage =percentageCalculation(total);
			
			app = new Applicant(name, subject1, subject2, subject3, total, percentage);
			if(app.getPercentage() > 70)
				applicantList.add(app);
		}
		}
		
		catch(MarksException me) {
			System.out.println(me.getMessage());
		}
		
		applicantList.forEach(a -> System.out.println(a));
	}

}
