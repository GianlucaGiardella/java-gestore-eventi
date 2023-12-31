package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Evento {
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException {
        checkStringha(titolo, "titolo");
        checkData(data);
        checkPostiPositivi(postiTotali, "posti totali");


        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;

        this.postiPrenotati = 0;
    }

    // Getters

    public String getTitolo() {
        return titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    // Setters

    public void setTitolo(String titolo) throws IllegalArgumentException {
        checkStringha(titolo, "titolo");
        this.titolo = titolo;
    }

    public void setData(LocalDate data) throws IllegalArgumentException {
        checkData(data);
        this.data = data;
    }

    // Controlli

    private void checkStringha(String stringa, String nomeStringa) throws IllegalArgumentException {
        if (stringa == null || stringa.isBlank()) {
            throw new IllegalArgumentException(nomeStringa + " è null o vuoto");
        }
    }

    private static void checkData(LocalDate data) throws IllegalArgumentException {
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("la data è minore di quella ordierna");
        }
    }

    private static void checkPostiPositivi(int posti, String nomePosti) throws IllegalArgumentException {
        if (posti <= 0) {
            throw new IllegalArgumentException("numero di " + nomePosti + " è minore o uguale a 0");
        }
    }

    private void checkPostiMinoriDelTotale(int posti) throws IllegalArgumentException {
        if (posti + this.postiPrenotati > this.postiTotali) {
            throw new IllegalArgumentException("i posti prenotati superaro quelli totali");
        }
    }

    private void checkDisdetteMinoriDellePrenotazioni(int posti) throws IllegalArgumentException {
        if (posti > this.postiPrenotati) {
            throw new IllegalArgumentException("i posti da disdire sono maggiori di quelli prenotati");
        }
    }

    // Utilità

    public String dataFormattata() {
        return getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void prenota(int posti) throws IllegalArgumentException {
        checkData(this.data);
        checkPostiPositivi(posti, "posti prenotati");
        checkPostiMinoriDelTotale(posti);

        this.postiPrenotati += posti;
    }

    public void disdici(int posti) throws IllegalArgumentException {
        checkData(this.data);
        checkPostiPositivi(posti, "posti da disdire");
        checkDisdetteMinoriDellePrenotazioni(posti);

        this.postiPrenotati -= posti;
    }

    @Override
    public String toString() {
        return dataFormattata() + " - " + getTitolo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(titolo, evento.titolo) && Objects.equals(data, evento.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titolo);
    }
}
