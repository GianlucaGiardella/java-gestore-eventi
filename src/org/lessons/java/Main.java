package org.lessons.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prova della classe ProgrammEventi

        System.out.println("\n- Nuovo programma");

        try {

            ProgrammEventi programmEventi = new ProgrammEventi("Programma di prova");

            programmEventi.aggiungiEvento(new Evento(
                    "Evento1",
                    LocalDate.of(3000, 10, 30),
                    100));

            programmEventi.aggiungiEvento(new Evento(
                    "Evento2",
                    LocalDate.of(2027, 11, 1),
                    200));

            programmEventi.aggiungiEvento(new Evento(
                    "Evento3",
                    LocalDate.of(2030, 10, 4),
                    300));

            programmEventi.aggiungiEvento(new Evento(
                    "Evento4",
                    LocalDate.of(2023, 12, 31),
                    400));

            programmEventi.aggiungiEvento(new Evento(
                    "Evento5",
                    LocalDate.of(2023, 12, 31),
                    500));

            System.out.println("\nProgramma eventi:" + "\n"
                    + programmEventi.toString());

            System.out.println("\nEventi trovati: " + programmEventi
                    .cercaEventiDallaData(LocalDate.of(2023, 12, 31)));

            System.out.println("\nNumero di eventi nel programma: " + programmEventi.numeroEventi());

            System.out.println("\n" + programmEventi.eventiOrdinatiPerData());

            programmEventi.svuotaEventi();

            System.out.println("\nProgramma eventi svuotato:" + "\n"
                    + programmEventi.toString());

        } catch (IllegalArgumentException e) {
            System.out.println("\n- Errore: " + e.getMessage());
        }

        System.out.println("\n==============================");

        // Prova della classe Concerto

        System.out.println("\n- Nuovo concerto");

        try {
            Concerto concerto = new Concerto(
                    "Concerto di prova",
                    LocalDate.of(2030, 12, 31),
                    100,
                    LocalTime.of(23, 59, 59),
                    new BigDecimal("10.99")
            );

            System.out.println("\n- Concerto creato con successo !");

            System.out.println(concerto);
        } catch (IllegalArgumentException e) {
            System.out.println("\n- Errore: " + e.getMessage());
        }

        System.out.println("\n==============================");

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

                } catch (NumberFormatException e) {
                    System.out.println("\n- Errore: Numero non valido");
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
