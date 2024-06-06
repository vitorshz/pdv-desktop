package br.unipar.services;

import br.unipar.models.Cliente;
import br.unipar.models.ItemVenda;
import br.unipar.models.Produto;
import br.unipar.models.Venda;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface ApiService {

    @GET("clientes")
    Call<List<Cliente>> listarClientes();

    @GET("produtos")
    Call<List<Produto>> listarProdutos();

    @POST("vendas")
    Call<Venda> criarVenda(@Body Venda venda);

    @POST("itens-venda")
    Call<ItemVenda> adicionarItemVenda(@Body ItemVenda itemVenda);
    // falta ver como recuperar o id da url
    @GET("itens-venda/venda/{id}")
    Call<List<ItemVenda>> listarItemVendaDaVenda(@Path("id") Long id);
    @DELETE("vendas/{id}")
    void deletarVenda(@Path("id") Long id);
    @PUT("vendas/{id}")
    Call<Venda> updateVenda(@Path("id") Long id,@Body Venda venda);
    @GET("vendas/{id}")
    Call<Venda> getByIdVenda(@Path("id") Long id);
    @GET("vendas")
    Call<List<Venda>> listarVendas();
    @DELETE("produtos/{id}")
    void deletarProduto(@Path("id") Long id);
    @PUT("produtos/{id}")
    Call<Produto> updateProduto( @Path("id") Long id, @Body Produto produto);
    @POST("produtos")
    Call<Produto> criarProduto(@Body Produto produto);
    @GET("produtos/{id}")
    Call<Produto> getByIdProduto(@Path("id") Long id);
    @DELETE("clientes/{id}")
    void deletarCliente(@Path("id") Long id);
    @PUT("clientes/{id}")
    Call<Cliente> updateCliente(@Path("id") Long id, @Body Cliente cliente);
    @GET("clientes/{id}")
    Call<Cliente> getByIdCliente(@Path("id") Long id);
    @POST("clientes")
    Call<Cliente> criarCliente(@Body Cliente cliente);



}
