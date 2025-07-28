package br.edu.ifpb.biblioteca.warakkayu.pagamento.util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.UUID;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.StatusEmprestimo;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.MetodoPagamento;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.Pagamento;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;

public class PagamentoTypeAdapter implements JsonSerializer<Pagamento>, JsonDeserializer<Pagamento> {

    private final EmprestimoService emprestimoService;
    private final UsuarioService usuarioService;

    public PagamentoTypeAdapter(EmprestimoService emprestimoService, UsuarioService usuarioService) {
        this.emprestimoService = emprestimoService;
        this.usuarioService = usuarioService;
    }

    @Override
    public JsonElement serialize(Pagamento pagamento, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", pagamento.getId().toString());
        jsonObject.addProperty("valor", pagamento.getValor());
        jsonObject.add("data", context.serialize(pagamento.getData()));
        jsonObject.add("metodoPagamento", context.serialize(pagamento.getMetodoPagamento()));
        jsonObject.addProperty("pagante", pagamento.getPagante().getId().toString());
        jsonObject.addProperty("recebedor", pagamento.getRecebedor().getId().toString());
        jsonObject.addProperty("emprestimo", pagamento.getEmprestimo().getId().toString());

        return jsonObject;
    }

    @Override
    public Pagamento deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        UUID paganteId = UUID.fromString(jsonObject.get("pagante").getAsString());
        UUID recebedorId = UUID.fromString(jsonObject.get("recebedor").getAsString());
        UUID emprestimoId = UUID.fromString(jsonObject.get("emprestimo").getAsString());

        try {
            Usuario pagante = usuarioService.findById(paganteId);
            Usuario recebedor = usuarioService.findById(recebedorId);
            Emprestimo emprestimo = emprestimoService.findById(emprestimoId);
            
            UUID id = UUID.fromString(jsonObject.get("id").getAsString());
            double valor = Double.parseDouble(jsonObject.get("valor").getAsString());
            LocalDate data = context.deserialize(jsonObject.get("data"), LocalDate.class);
            MetodoPagamento metodoPagamento = context.deserialize(jsonObject.get("metodoPagamento"), MetodoPagamento.class);


            
            Pagamento pagamento = new Pagamento(id, valor, data, metodoPagamento, 
                    pagante, recebedor, emprestimo);
            return pagamento;

        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}