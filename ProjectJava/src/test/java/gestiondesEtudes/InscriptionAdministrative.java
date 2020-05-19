package gestiondesEtudes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import Excel.ExcelUtils; 

public class InscriptionAdministrative {
	protected Ecole ecole;
	protected Student e = new Student();
	SimpleDateFormat s= new SimpleDateFormat("dd/MM/yyyy");
	protected Date dN = new Date();
	protected Filiere f;
	
	protected static int nbrFemme = 0;
	protected static int nbrHomme = 0;
	protected static int i = 0;
	
	Scanner sc = new Scanner(System.in);
	
	public InscriptionAdministrative(Ecole ecole) throws ParseException, IOException {
		i++;
		this.ecole=ecole;
		setNom();
		setPrenom();
		setdN();
		setCNE();
		setFili�re();
		setMail();
		setTelephone();
		setFormation();
		setSexe();
		setPaiement();
		setPwd();
		confirmer();
	}

	public String getCNE() {
		return e.CNE;
	}

	public void setCNE() throws IOException {
		System.out.println("\nVeuillez entrer votre CNE");
		e.CNE = sc.next();
		ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 2, e.CNE);
	}

	public String getNom() {
		return e.lastnamePers+ " " + e.firstnamePers;
	}

	public void setNom() throws IOException {
		System.out.println("\nVeuillez entrer votre nom");
		e.lastnamePers = sc.next();
		ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 0, e.lastnamePers);
	
	}
	
	public void setPrenom() throws IOException {
	
		System.out.println("\nVeuillez entrer votre pr�nom");
		e.firstnamePers = sc.next();
		ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 1, e.firstnamePers);
	}
	
	public void setPwd() throws IOException {
		System.out.println("\nEntrez votre mot de passe :");
		//sc.hasNextLine();
		e.pwd=sc.next();
		ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 10, e.pwd);
	}
	public String getPwd() {
		return e.pwd;
	}


	@SuppressWarnings("deprecation")
	public String getdN() {
		String s = "";
		s = dN.getDate() + "-" + dN.getMonth() + "-" + dN.getYear();
		return s;
	}
	@SuppressWarnings("deprecation")
	public void setdN() throws IOException {
		String s = "";
		int i=0;
		while(i==0) {//year
			try {
		System.out.println("\nVeuillez entrer votre ann�e de naissance :");
		dN.setYear(sc.nextInt());
		sc.nextLine();
		i=1;
			}catch(InputMismatchException er) {//catch 1
				System.out.println();
				System.err.println("Ce choix est invalide");
				sc.nextLine();
				
			}//catch 1
		}//year
			while(i==1) {//mo
				try {
					System.out.println("\nVeuillez entrer votre mois de naissance :");
					dN.setMonth(sc.nextInt());
					sc.nextLine();
					i=2;
				}catch(InputMismatchException er) {//catch 1
					System.out.println();
					System.err.println("Ce choix est invalide");
					sc.nextLine();
			}//catch 1
			}//mo
				while(i==2) {//d
					try {
						System.out.println("\nVeuillez entrer votre jour de naissance :");
						dN.setDate(sc.nextInt());
						sc.nextLine();
						e.date=dN;
						s = dN.getDate() + "-" + dN.getMonth() + "-" + dN.getYear();
						ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",InscriptionAdministrative.i, 5, s);
						break;
					}catch(InputMismatchException er) {//catch 1
						System.out.println();
						System.err.println("Ce choix est invalide");
						sc.nextLine();
				}//catch 1
				}//d

		
	}

	public String getMail() {
		return e.mail;
	}

	public void setMail() throws IOException {
		
		System.out.println("\nVeuillez entrer votre adresse mail :");
		e.mail = sc.next();
		ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 3, e.mail);
		
	}

	public String getTelephone() {
		return e.telephone;
	}

	public void setTelephone() throws IOException {
		
		System.out.println("\nVeuillez entrer votre num�ro de t�l�phone :");
		e.telephone = sc.next();
		ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 4, e.telephone);
		
	}

	public String getFormation() {
		return e.formation;
	}

	public void setFormation() throws IOException {
		int i=0;
		while(i==0) {
		System.out.println("\nVotre formation est :\n 1) CPGE\n 2) Autre");
		try {
			
			i = sc.nextInt();
			if (i!=1 && i!=2) {
				throw  new InputMismatchException("Ce choix est invalide");
				
			}
			break;
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Ressayer");
			}
		}
			
			if (i == 1) {
				System.out.println("\nVeuillez pr�ciser votre fili�re en CPGE :");
				String s = sc.next();
				e.formation = "CPGE fili�re : " + s;
				ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",InscriptionAdministrative.i, 6, e.formation);
			}
			else if (i == 2){
				System.out.println("\nVeuillez pr�ciser votre formation :");
				String s = sc.next();
				e.formation = s;
				ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",InscriptionAdministrative.i, 6, e.formation);
			}

	}

	public String isPaiement() {
		if (e.paiement == true) return "Paiement valid�";
		else return "Paiement non valid�";
	}

	public void setPaiement() throws IOException {
	
		System.out.println("\nEst-ce que vous avez effectu� le paiement :\n 1) Oui\n 2) Non");
		int z = sc.nextInt();
		
		if (z == 1) {
			e.paiement = true;
			ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 8, "Paiement valid�");
		}
		else if (z == 2) {
			e.paiement = false;
			ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 8, "Paiement non valid�");
		} 
		else {
			System.out.println("Ressayer");
			setPaiement();
		}
		
	}

	public String getSexe() {
		return e.sexe;
	}

	public void setSexe() throws IOException {
		int I;
		System.out.println("\nVous �tes : \n 1) Femme\n 2) Homme");
		I = sc.nextInt();
		if (I == 1) {
			e.sexe = "Femme";
			ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 7, e.sexe);
			nbrFemme++;
		}
		else if (I == 2) {
			e.sexe = "Homme";
			ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",i, 7, e.sexe);
			nbrHomme++;
		}
		else {
			System.out.println("R�essayez\n");
			setSexe();
		}
	}

	@Override
	public String toString() {
		return "Nom complet : " + e.lastnamePers + " " + e.firstnamePers + "\nSexe : " + e.sexe + "\nDate de naissance : " +s.format(e.date)
				+ "\nT�l�phone : " +e.telephone + "\nAdresse �lectronique : " + e.mail + "\nFormation initiale : " + e.formation + "\nEtat de  paiement : " + isPaiement() ;
	}
	
	public void r�capitulatifInscription() {
		System.out.println(toString());
	}
	
	
	public void confirmer() throws ParseException, IOException {
		System.out.println("\nV�rifiez les informations que vous avez saisies : \n");
		r�capitulatifInscription();
		System.out.println("\nVous �tes s�r(e) de ces informations ?\n 1) Confirmer\n 2) Il y a une erreur quelque part");
		int i = sc.nextInt();
		//sc.nextLine();
		if (i == 1) {
			System.out.println("\nInsciption enregist�e avec succ�s\nBienvenue ");
			e.afficherStudent();
			//quitter();
		}
		else if (i == 2) {
			sc.nextLine();
			System.out.println("\nVous voulez modifier : \n 1) Votre nom\n 2) Votre pr�nom\n 3) Votre date de naissance\n 4) Votre sexe\n 5) Votre CNE\n 6) Votre num�ro de t�l�phone\n 7) Votre adresse �lectronique\n 8) Votre formation\n 9)Changer votre mot de passe 10) L'�tat du paiement");
			int j = sc.nextInt();
			switch (j) {
			case 1 : setNom(); confirmer(); break;
			case 2 : setPrenom(); confirmer(); break;
			case 3 : setdN(); confirmer(); break;
			case 4 : setSexe(); confirmer(); break;
			case 5 : setCNE(); confirmer(); break;
			case 6 : setTelephone(); confirmer(); break;
			case 7 : setMail(); confirmer(); break;
			case 8 : setFormation(); confirmer(); break;
			case 9 : setPwd(); confirmer();break;
			case 10 : setPaiement(); confirmer(); break;
			}
		}
		else {
			System.out.println("R�essayer");
			confirmer();
		}
	}
	
	public void statInscription() {
		
	}
	
	public void quitter() {
		sc.close();
	}

	public String getFili�re() {
		return e.fili�re.nomFili�re;
	}

	public void setFili�re() throws IOException {
		int i=0;
		while(i==0){
		System.out.println("choisir votre fili�re :");
		ecole.affichefiliereEcole();
		try {
			int fili�rechoisie=sc.nextInt();
			sc.nextLine();
			if(0>fili�rechoisie|| fili�rechoisie>ecole.filiereEcole.size()) {
				throw new InputMismatchException("Ce choix est invalide.");
			}
			e.fili�re=ecole.choisirFiliere(fili�rechoisie);
			f=e.fili�re;
			ExcelUtils.setCellStringValue("./data/ListeStudentInscription.xlsx",InscriptionAdministrative.i, 9, f.nomFili�re);
			break;
		}catch(InputMismatchException e) {
			System.out.println();
			System.err.println("Ce choix est invalide");
			sc.nextLine();
			i=0;
			
		}
		catch(NullPointerException e) {
			System.out.println();
			System.err.println("Pas de choix actuellement.");
			sc.nextLine();
			i=0;
		}
		}
	}
	
	public void statInscriptionSexe() {
		if(nbrFemme+nbrHomme != 0) {
			float pg = nbrHomme*100/(nbrFemme+nbrHomme);
			float pf =nbrFemme*100/(nbrFemme+nbrHomme);
			System.out.println("Pourcentage des gar�ons parmi les nouveaux inscrits est : " + pg + "%");
			System.out.println("Pourcentage des filles parmi les nouveaux inscrits est : " + pf + "%");
		} else {
			System.out.println("Pas d'inscription pour le moment.");
		}
	}
	
}

