// Classe que modela os itens que compõem o pedido, utilizando a classe produto. 
package br.dev.sophia.fastAndFurious.domain.model;

public class itemPedido {

    private Long id;
    private Pedido pedido;

    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    

}
