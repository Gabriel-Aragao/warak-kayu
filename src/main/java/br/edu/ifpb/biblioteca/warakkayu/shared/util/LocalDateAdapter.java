package br.edu.ifpb.biblioteca.warakkayu.shared.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            // Se o objeto for nulo, escreve null no JSON
            out.nullValue();
        } else {
            // Converte a data para String (formato AAAA-MM-DD) e escreve
            out.value(value.format(FormatadoresDeData.FORMATADOR_BRASILEIRO));
        }
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        // Se o valor no JSON for "null", retorna null
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        // Lê a String do JSON
        String dateString = in.nextString();
        // Converte a String (formato AAAA-MM-DD) para um objeto LocalDate
        return LocalDate.parse(dateString, FormatadoresDeData.FORMATADOR_BRASILEIRO);
    }
}