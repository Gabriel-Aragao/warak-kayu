package br.edu.ifpb.biblioteca.warakkayu.obra.util;

import com.google.gson.*;

import br.edu.ifpb.biblioteca.warakkayu.obra.model.Artigo;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Revista;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.TipoObra;

import java.lang.reflect.Type;

public class ObraDeserializer implements JsonDeserializer<Obra> {

    @Override
    public Obra deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException 
    {
        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement tipoElement = jsonObject.get("tipoObra");
        if (tipoElement == null) {
            throw new JsonParseException("O campo 'tipoObra' não foi encontrado no JSON.");
        }
        
        TipoObra tipo = context.deserialize(tipoElement, TipoObra.class);

        Class<? extends Obra> classeConcreta;
        switch (tipo) {
            case LIVRO:
                classeConcreta = Livro.class;
                break;
            case ARTIGO:
                classeConcreta = Artigo.class;
                break;
            case REVISTA:
                classeConcreta = Revista.class;
                break;
            default:
                throw new JsonParseException("Tipo de obra desconhecido: " + tipo);
        }

        return context.deserialize(jsonObject, classeConcreta);
    }
}