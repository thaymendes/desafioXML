package xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Endereco")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "https://mballem.com/")
public class Endereco {
    @XmlElement(name = "id", required = true)
    private int id;

    @XmlElement(name = "logradouro", required = true)
    private String logradouro;

    @XmlElement(name = "bairro", required = true)
    private String bairro;

    @XmlElement(name = "cep", required = true)
    private String cep;

    @XmlElement(name = "cidade", required = true)
    private String cidade;

    @XmlElement(name = "complemento", required = false)
    private String complemento;

    @XmlElement(name = "numero", required = true)
    private int numero;

    

    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getLogradouro() {
		return logradouro;
	}



	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getCidade() {
		return cidade;
	}



	public void setCidade(String cidade) {
		this.cidade = cidade;
	}



	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cep=" + cep + ", cidade="
				+ cidade + ", complemento=" + complemento + ", numero=" + numero + "]";
	}



	
}
