

public class GestionEtudiantsV1{




     double calculerMoyenne(double[] notes) {
        double somme = 0;
        for (double note : notes) {
            somme += note;
        }
        return somme / notes.length;
    }


     String obtenirMention(double moyenne) {
        if (moyenne >= 16) return "Très Bien";
        else if (moyenne >= 14) return "Bien";
        else if (moyenne >= 12) return "Assez Bien";
        else if (moyenne >= 10) return "Passable";
        else return "Ajournement";
    }


     boolean estAdmis(double moyenne) {
        return moyenne >= 10;
    }


     static void afficherInfosEtudiant(String numero, String nom, int age, String email, String promo) {
        System.out.println("Numéro : " + numero);
        System.out.println("Nom : " + nom);
        System.out.println("Age : " + age );
        System.out.println("Email : " + email);
        System.out.println("Promotion : " + promo);
    }


     void afficherBulletin(String nom, String[] matieres, double[] notes) {
        double moyenne = calculerMoyenne(notes);
        String mention = obtenirMention(moyenne);
        boolean admis = estAdmis(moyenne);


        System.out.printf("BULLETIN DE :"+ nom);

        for (int j = 0; j < matieres.length; j++) {
            System.out.printf(" Dans la matiere "+matieres[j] +" votre note est "+notes[j]);
        }

        System.out.printf(" MOYENNE GÉNÉRALE : "+ moyenne);
        System.out.printf(" MENTION : "+ mention);
        System.out.printf("DÉCISION :", admis ? " ADMIS" : " No ADMIS");

    }


     double calculerMoyennePromo(double[][] notes) {
        double somme = 0;
        int total = 0;
        for (double[] etudiantNotes : notes) {
            for (double note : etudiantNotes) {
                somme += note;
                total++;
            }
        }
        return somme / total;
    }


    double trouverMeilleureNote(double[][] notes) {
        double max = notes[0][0];
        for (double[] etudiantNotes : notes) {
            for (double note : etudiantNotes) {
                if (note > max) max = note;
            }
        }
        return max;
    }

      double trouverPireNote(double[][] notes) {
        double min = notes[0][0];
        for (double[] etudiantNotes : notes) {
            for (double note : etudiantNotes) {
                if (note < min) min = note;
            }
        }
        return min;
    }


      int compterAdmis(double[][] notes) {
        int count = 0;
        for (double[] etudiantNotes : notes) {
            if (estAdmis(calculerMoyenne(etudiantNotes))) count++;
        }
        return count;
    }


     void repartitionMentions(double[][] notes) {
        int tresBien = 0;
           int     bien = 0;
             int    assezBien = 0;
              int   passable = 0;
              int  ajourne = 0;

        for (double[] etudiantNotes : notes) {
            String mention = obtenirMention(calculerMoyenne(etudiantNotes));
            switch (mention) {
                case "Très Bien" : tresBien++;
                case "Bien" :bien++;
                case "Assez Bien" : assezBien++;
                case "Passable" : passable++;
                default : ajourne++;
            }
        }

        System.out.println("RÉPARTITION DES MENTIONS :");
        System.out.println("Très Bien : " + tresBien + " étudiant(s)");
        System.out.println(" Bien : " + bien + " étudiant(s)");
        System.out.println(" Assez Bien : " + assezBien + " étudiant(s)");
        System.out.println("Passable : " + passable + " étudiant(s)");
        System.out.println("Ajournement : " + ajourne + " étudiant(s)");
    }


     int rechercherEtudiant(String[] noms, String nomRecherche) {
        for (int i = 0; i < noms.length; i++) {
            if (noms[i].equalsIgnoreCase(nomRecherche)) {
                return i;
            }
        }
        return -1;
    }


    public void main(String[] args) {


        String[] numeros = {"STU-2024-001", "STU-2024-002", "STU-2024-003"};
        String[] noms = {"Ahmed Bennani", "Fatima Alaoui", "Youssef Idrissi"};
        int[] ages = {20, 21, 19};
        String[] emails = {
                "ahmed.bennani@edutech.ma",
                "fatima.alaoui@edutech.ma",
                "youssef.idrissi@edutech.ma"
        };
        String[] promotions = {"3A", "3A", "3A"};


        String[] matieres = {"Java", "Base de données", "Réseaux", "Big Data", "Algorithmique"};


        double[][] notes = {
                {15.5, 14.0, 16.0, 13.5, 15.0},
                {12.0, 13.5, 11.0, 14.0, 12.5},
                {17.0, 16.5, 18.0, 17.5, 16.0}
        };


        System.out.println("=== LISTE DES ÉTUDIANTS ===");
        for (int i = 0; i < noms.length; i++) {
            afficherInfosEtudiant(numeros[i], noms[i], ages[i], emails[i], promotions[i]);
            System.out.println();
            afficherBulletin(noms[i], matieres, notes[i]);
        }


        System.out.println("═════════════════════════════════════════════");
        System.out.println(" STATISTIQUES DE LA PROMOTION ");
        System.out.println("══════════════════════════════════════════════");
        System.out.println(" Nombre total d'étudiants : "+ noms.length);
        System.out.println("Moyenne de la promotion : "+calculerMoyennePromo(notes));
        System.out.println("Note maximale : "+trouverMeilleureNote(notes));
        System.out.println("Note minimale : "+ trouverPireNote(notes));
        int nbAdmis = compterAdmis(notes);
        double tauxReussite = (nbAdmis * 100.0) / noms.length;
        System.out.println("Étudiants admis : "+ nbAdmis+"TAUX DE REUSSITE :"+ tauxReussite);
        System.out.printf("Étudiants ajournés : %d TAUX DE REUSSITE :%d", noms.length-nbAdmis, 100 - tauxReussite);
        System.out.println("══════════════════════════════════════════════");
        repartitionMentions(notes);
        System.out.println();


        double[] moyennes = new double[noms.length];
        for (int i = 0; i < noms.length; i++) {
            moyennes[i] = calculerMoyenne(notes[i]);
        }


        for (int i = 0; i < noms.length - 1; i++) {
            for (int j = i + 1; j < noms.length; j++) {
                if (moyennes[j] > moyennes[i]) {

                    double tempM = moyennes[i];
                    moyennes[i] = moyennes[j];
                    moyennes[j] = tempM;

                    String tempN = noms[i];
                    noms[i] = noms[j];
                    noms[j] = tempN;
                }
            }
        }

        System.out.println("═════════════════════════════════════════════");
        System.out.println(" CLASSEMENT DE LA PROMOTION ");
        System.out.println("════════════════════════════════════════════");
        for (int i = 0; i < noms.length; i++) {
            System.out.println(i +"e_"+ noms[i]+":" +moyennes[i]);
        }
        System.out.println("══════════════════════════════════════════════");
        System.out.printf(" MAJOR DE PROMOTION : "+ noms[0]+" SON MOYENNE :"+ moyennes[0]);
    }
}