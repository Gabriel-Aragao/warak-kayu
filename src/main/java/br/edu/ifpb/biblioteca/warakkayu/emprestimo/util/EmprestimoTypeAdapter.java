package br.edu.ifpb.biblioteca.warakkayu.emprestimo.util;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.StatusEmprestimo;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.UUID;

public class EmprestimoTypeAdapter implements JsonSerializer<Emprestimo>, JsonDeserializer<Emprestimo> {

    private final UsuarioService usuarioService;
    private final ObraService obraService;

    public EmprestimoTypeAdapter(UsuarioService usuarioService, ObraService obraService) {
        this.usuarioService = usuarioService;
        this.obraService = obraService;
    }

    @Override
    public JsonElement serialize(Emprestimo emprestimo, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", emprestimo.getId().toString());
        jsonObject.add("dataEmprestimo", context.serialize(emprestimo.getDataEmprestimo()));
        jsonObject.add("dataPrevistaDevolucao", context.serialize(emprestimo.getDataPrevistaDevolucao()));
        jsonObject.add("statusEmprestimo", context.serialize(emprestimo.getStatusEmprestimo()));
        if (emprestimo.getDataRealDevolucao() != null) {
            jsonObject.add("dataRealDevolucao", context.serialize(emprestimo.getDataRealDevolucao()));
        }

        jsonObject.addProperty("usuarioId", emprestimo.getUsuario().getId().toString());
        jsonObject.addProperty("obraId", emprestimo.getObra().getId().toString());

        return jsonObject;
    }

    @Override
public Emprestimo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();

    UUID usuarioId = UUID.fromString(jsonObject.get("usuarioId").getAsString());
    UUID obraId = UUID.fromString(jsonObject.get("obraId").getAsString());

    try {
        Usuario usuario = usuarioService.findById(usuarioId);
        Obra obra = obraService.findById(obraId);
        
        // --- VALIDAÇÃO ADICIONADA ---
        // Se o usuário não for encontrado, o programa vai quebrar AGORA com uma mensagem clara.
        if (usuario == null) {
            throw new JsonParseException("Não foi possível encontrar o usuário com ID: " + usuarioId 
                + ". Verifique se este usuário existe no seu arquivo usuarios.json.");
        }
        // Se a obra não for encontrada, o programa vai quebrar AGORA com uma mensagem clara.
        if (obra == null) {
            throw new JsonParseException("Não foi possível encontrar a obra com ID: " + obraId
                + ". Verifique se esta obra existe no seu arquivo obras.json.");
        }
        
        UUID id = UUID.fromString(jsonObject.get("id").getAsString());
        LocalDate dataEmprestimo = context.deserialize(jsonObject.get("dataEmprestimo"), LocalDate.class);
        LocalDate dataPrevistaDevolucao = context.deserialize(jsonObject.get("dataPrevistaDevolucao"), LocalDate.class);
        StatusEmprestimo status = context.deserialize(jsonObject.get("statusEmprestimo"), StatusEmprestimo.class);

        LocalDate dataRealDevolucao = null;
        if (jsonObject.has("dataRealDevolucao")) {
            dataRealDevolucao = context.deserialize(jsonObject.get("dataRealDevolucao"), LocalDate.class);
        }
        
        Emprestimo emprestimo = new Emprestimo(id, usuario, obra, dataEmprestimo, 
                dataPrevistaDevolucao, dataRealDevolucao, status);
        return emprestimo;

    } catch (Exception e) {
        // Agora, se outro erro acontecer, ele será encapsulado pela JsonParseException
        throw new JsonParseException(e.getMessage());
    }
}
}