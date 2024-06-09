/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOItemVneda;

/**
 *
 * @author EDUARDO
 */
public class ItemVendaDTOTest {
    private Long produtoId;
    private Double valorunitario;
    private int quantidade;

    // Construtores, getters e setters
    public ItemVendaDTOTest() {}

  
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ItemVendaDTOTest(Long produtoId, Double valorunitario, int quantidade) {
        this.produtoId = produtoId;
        this.valorunitario = valorunitario;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemVendaDTOTest{" + "produtoId=" + produtoId + ", valorunitario=" + valorunitario + ", quantidade=" + quantidade + '}';
    }


}