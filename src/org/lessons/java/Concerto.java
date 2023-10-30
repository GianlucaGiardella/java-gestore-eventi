package org.lessons.java;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {
    private LocalTime ora;
    private BigDecimal prezzo;

    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) throws IllegalArgumentException {
        super(titolo, data, postiTotali);

        checkOra(ora);

        this.ora = ora;
        this.prezzo = prezzo;
    }

    // Getters

    public LocalTime getOra() {
        return ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    // Setters

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    // Controlli

    private void checkOra(LocalTime ora) throws IllegalArgumentException {
        if (ora == null) {
            throw new IllegalArgumentException("ora non valida");
        }
    }

    // Utilità

    public String oraFormattata() {
        return getOra().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
    }

    public String dataEOraFormattata() {
        return dataFormattata() + " " + oraFormattata();

    }

    public String prezzoFormattato() {
        return NumberFormat.getCurrencyInstance().format(getPrezzo());
    }

    @Override
    public String toString() {
        return  dataEOraFormattata() + " - " +
                getTitolo() + " - " +
                prezzoFormattato();
    }
}
