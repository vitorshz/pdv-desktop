package br.unipar.services;

import br.unipar.models.Cliente;
import br.unipar.models.ItemVenda;
import br.unipar.models.Produto;
import br.unipar.models.Venda;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {

    @GET("clientes")
    Call<List<Cliente>> listarClientes();

    @GET("produtos/")
    Call<List<Produto>> listarProdutos();

    @POST("vendas")
    Call<Venda> criarVenda(@Body Venda venda);

    @POST("itens-venda")
    Call<ItemVenda> adicionarItemVenda(@Body ItemVenda itemVenda);
}
