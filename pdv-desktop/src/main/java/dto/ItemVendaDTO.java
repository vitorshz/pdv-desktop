/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import br.unipar.models.Produto;
import java.math.BigDecimal;

/**
 *
 * @author lucia
 */
public class ItemVendaDTO {
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private Long produtoid;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Long getProdutoid() {
        return produtoid;
    }

    public void setProdutoid(Long produtoid) {
        this.produtoid = produtoid;
    }

    public ItemVendaDTO(Integer quantidade, BigDecimal valorUnitario, Long produtoid) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.produtoid = produtoid;
    }

   
    @Override
    public String toString() {
        return "ItemVendaDTO{" + "quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + ", produtoid=" + produtoid + '}';
    }
    
}
