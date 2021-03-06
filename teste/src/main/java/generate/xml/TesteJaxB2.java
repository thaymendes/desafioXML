package generate.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import xml.model.Contato;
import xml.model.Endereco;
import xml.model.Telefone;

public class TesteJaxB2 {
	private static Marshaller marshaller = null;
	private static Unmarshaller unmarshaller = null;

	public static void main(String[] args) {
		Endereco end = new Endereco();
		end.setLogradouro("Rua Venancio Aires");
		end.setNumero(654);
		end.setComplemento("Ap.03A");
		end.setBairro("Centro");
		end.setCidade("Santa Maria");
		end.setCep("97050-100");
		end.setId(1);

		Collection<Telefone> collection = new ArrayList<>();

		Telefone f1 = new Telefone();
		f1.setId(1);
		f1.setDdd(55);
		f1.setNumero(21210022);
		collection.add(f1);

		Telefone f2 = new Telefone();
		f2.setId(2);
		f2.setDdd(54);
		f2.setNumero(91910022);
		collection.add(f2);

		Contato contato = new Contato();
		contato.setId(1);
		contato.setNome("Ana Maria");
		contato.setEmail("ana.maria@email.com");
		contato.setEndereco(end);
		contato.setTelefones(collection);
		// cria o xml a partir de objetos
		String xml = TesteJaxB2.marshal(contato);
		System.out.println(xml);
		// cria o arquivo xml na pasta
		new TesteJaxB2().marshalToFile(contato, "C:\\teste\\contato-jaxb.xml");
		// cria objetos a partir do xml
		String objetoXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<Contato>"
				+ "    <id>1</id>" + "    <nome>Ana Maria</nome>" + "    <email>ana.maria@email.com</email>"
				+ "    <Endereco>" + "        <id>1</id>" + "        <logradouro>Rua Venancio Aires</logradouro>"
				+ "        <bairro>Centro</bairro>" + "        <cep>97050-100</cep>"
				+ "        <cidade>Santa Maria</cidade>" + "        <complemento>Ap.03A</complemento>"
				+ "        <numero>654</numero>" + "    </Endereco>" + "    <Telefones>" + "        <Telefone>"
				+ "            <id>1</id>" + "            <ddd>55</ddd>" + "            <numero>21210022</numero>"
				+ "        </Telefone>" + "        <Telefone>" + "            <id>2</id>" + "            <ddd>54</ddd>"
				+ "            <numero>91910022</numero>" + "        </Telefone>" + "    </Telefones>" + "</Contato>";

		Contato c1 = (Contato) new TesteJaxB2().unmarshal(Contato.class, objetoXml);

		System.out.println(c1.toString());
		Contato c2 = (Contato) new TesteJaxB2().unmarshalFromFile(Contato.class, "C:\\teste\\contato-jaxb.xml");
		System.out.println(c2.toString());

	}

	public Object unmarshalFromFile(Class<?> clazz, String fileXml) {
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(clazz);
			unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new FileInputStream(fileXml));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converte um string com estrutura XML em um objeto.
	 * 
	 * @param clazz     classe referente ao tipo do objeto a ser retornado.
	 * @param stringXml string com o conteudo XML a ser convertido em objeto.
	 * @return retorna um novo objeto de clazz.
	 */
	public Object unmarshal(Class<?> clazz, String stringXml) {
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(clazz);
			unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new StreamSource(new StringReader(stringXml)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converte o objeto em uma String com estrutura XML.
	 * 
	 * @param object objeto a ser convertido em XML.
	 * @return String contendo a estrutura XML.
	 */
	public static String marshal(Object object) {
		final StringWriter out = new StringWriter();
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(object.getClass());
			marshaller = context.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, new StreamResult(out));
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	public String marshalToFile(Object object, String fileName) {
		final StringWriter out = new StringWriter();
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(object.getClass());
			marshaller = context.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, new StreamResult(out));
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		Writer writer = null;
		try {
			writer = new FileWriter(fileName);
			marshaller.marshal(object, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return out.toString();
	}

}
