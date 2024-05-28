package br.unipar.services;

import br.unipar.models.Cliente;
import br.unipar.models.ItemVenda;
import br.unipar.models.Venda;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    // Endpoints para clientes
    @GET("clientes")
    Call<List<Cliente>> listarClientes();

    // Endpoints para produtos
    @GET("produtos")
    Call<List<Cliente>> listarProdutos();

    // Endpoint para criar uma venda
    @POST("vendas")
    Call<Venda> criarVenda(@Body Venda venda);

    // Endpoint para adicionar um item de venda
    @POST("itens-venda")
    Call<ItemVenda> adicionarItemVenda(@Body ItemVenda itemVenda);
}
