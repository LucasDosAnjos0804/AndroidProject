package Classes_Modelo;

/**
 * Created by Eu II on 15/11/2017.
 */

public class Orcamento {
    private String nome, descricao, abrangencia, usuEmail;
    private int catId;
    private double valor;

    public Orcamento(){
    }

    public Orcamento(String nome, String descricao, String abrangencia, String usuEmail, int catId, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.abrangencia = abrangencia;
        this.usuEmail = usuEmail;
        this.catId = catId;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(String abrangencia) {
        this.abrangencia = abrangencia;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
