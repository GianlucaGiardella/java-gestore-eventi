package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean esci = false;

        while (!esci) {
            System.out.println("\n- Vuoi aggiungere un nuovo evento ? y | n");
            esci = scanner.nextLine().equals("n");

            if (!esci) {
                System.out.println("\n- Nuovo evento");

                try {
                    System.out.println("Inserire il titolo dell'evento: ");
                    String titolo = scanner.nextLine();

                    System.out.println("Inserire la data: ");
                    LocalDate data = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    System.out.println("Inserire il numero di posti totali: ");
                    int postiTotali = Integer.parseInt(scanner.nextLine());

                    Evento evento = new Evento(titolo, data, postiTotali);

                    System.out.println("\n- Evento creato con successo !");
                    System.out.println(evento);

                    boolean operazioni = false;

                    while (!operazioni) {
                        try {
                            System.out.println("\n- Vuoi aggiungere o disdire delle prenotazioni ? y | n");
                            operazioni = scanner.nextLine().equals("n");

                            if (!operazioni) {
                                System.out.println("\nInserire il numero dell'operazione da eseguire: " +
                                        "\n1 per prenotare" +
                                        "\n2 per disdire");
                                int numeroOperazione = Integer.parseInt(scanner.nextLine());

                                switch (numeroOperazione) {
                                    case 1:
                                        System.out.println("\n- Aggiungi Prenotazioni");

                                        System.out.println("Inserire il numero di prenotazioni: ");
                                        evento.prenota(Integer.parseInt(scanner.nextLine()));

                                        System.out.println("\n- Prenotazioni aggiunte con successo !");

                                        break;
                                    case 2:
                                        System.out.println("\n- Disdici Prenotazioni");

                                        System.out.println("Inserire il numero di prenotazioni da disdire: ");
                                        evento.disdici(Integer.parseInt(scanner.nextLine()));

                                        System.out.println("\n- Prenotazioni disdette con successo !");

                                        break;
                                    default:
                                        throw new NumberFormatException("\n- Errore: numero non valido");
                                }

                                System.out.println("Posti prenotati: " + evento.getPostiPrenotati() + ", " +
                                        "posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\n- Errore: Numero non valido");
                        } catch (IllegalArgumentException e) {
                            System.out.println("\n- Errore: " + e.getMessage());
                        }
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("\n- Errore: " + e.getMessage());
                }
            } else {
                System.out.println("\n- Buona giornata");
            }
        }

        scanner.close();
    }
}
