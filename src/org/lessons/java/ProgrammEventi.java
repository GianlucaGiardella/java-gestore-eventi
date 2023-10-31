package org.lessons.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProgrammEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammEventi(String titolo) {
        checkStringha(titolo, "titolo");

        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    // Getters

    public String getTitolo() {
        return titolo;
    }

    // Setters

    public void setTitolo(String titolo) {
        checkStringha(titolo, "titolo");
        this.titolo = titolo;
    }

    // Controlli

    private void checkStringha(String stringa, String nomeStringa) throws IllegalArgumentException {
        if (stringa == null || stringa.isBlank()) {
            throw new IllegalArgumentException(nomeStringa + " è null o vuoto");
        }
    }

    // Utilità

    public void aggiungiEvento(Evento evento) {
        if (!eventi.contains(evento)){
        this.eventi.add(evento);
        }
    }

    public List cercaEventiDallaData(LocalDate data) {
        return eventi
                .stream()
                .filter(d -> Objects.equals(d.getData(), data))
                .toList();
    }

    public int numeroEventi() {
        return eventi.size();
    }

    public void svuotaEventi() {
        this.eventi.clear();
    }

    public String eventiOrdinatiPerData() {
        return getTitolo() + "\n" +
                eventi
                .stream()
                .sorted(Comparator.comparing(Evento::getData))
                .map(Evento::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "titolo: " + titolo  + "\n" +
                "Lista eventi: " + eventi;
    }
}
