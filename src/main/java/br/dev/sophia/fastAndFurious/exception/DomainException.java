
package br.dev.sophia.fastAndFurious.exception;

//Classe que ajuda no envio de mensagens de erro
public class DomainException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public DomainException(String message) {
        super (message);
    }
}
