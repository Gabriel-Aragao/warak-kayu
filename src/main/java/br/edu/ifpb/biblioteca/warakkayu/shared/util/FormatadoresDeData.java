package br.edu.ifpb.biblioteca.warakkayu.shared.util;

import java.time.format.DateTimeFormatter;

public final class FormatadoresDeData {

    private FormatadoresDeData() {}

    public static final DateTimeFormatter FORMATADOR_BRASILEIRO =
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

}