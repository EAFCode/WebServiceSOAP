# WebServiceSOAP

Projecto WebService SOAP permite fazer um CRUD de clientes.

## Requisitos:
- Java Version: JDK 1.8, 
- Editor: VS code
- Maven - Gerenciador de dependências
- Spring-boot – Framework para desenvolver o WS
- wsdl4j – Biblioteca para publicar o WSDL
- JAXB maven plugin – Biblioteca para  vincular XML  Schema e classes Java,
- SOAP-UI – Software para testar o nosso Serviço Web

### Começando com Spring Initializr
Para todos os aplicativos Spring, você deve começar com o Spring Initializr . O Initializr oferece uma maneira rápida de obter todas as 
dependências de que você precisa para um aplicativo e faz grande parte da configuração para você. Este exemplo precisa das dependências 
Spring Web e Spring Web Services.

Para inicializar o Spring Initializr no editor VS code presse CTR+ALT+P e escolha a opção [Spring boot Initializr: Create a maven project](https://code.visualstudio.com/docs/java/java-spring-boot).

A seguir você deve escolher as dependência do pom.xml:
```
<? xml version = "1.0" encoding = "UTF-8"?>
<project xmlns = "http://maven.apache.org/POM/4.0.0" xmlns: xsi = "http://www.w3.org/2001/XMLSchema-instance"
	xsi: schemaLocation = "http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion> 4.0.0 </modelVersion>
	<parent>
		<groupId> org.springframework.boot </groupId>
		<artifactId> spring-boot-starter-parent </artifactId>
		<version> 2.3.2.RELEASE </version>
		<relativePath /> <! - lookup parent from repository ->
	</ pai>
	<groupId> com.example </groupId>
	<artifactId> production-web-service </artifactId>
	<version> 0.0.1-SNAPSHOT </version>
	<name> production-web-service </name>
	<description> Projeto de demonstração para Spring Boot </description>

	<propriedades>
		<java.version> 1.8 </java.version>
	</properties>

	<dependências>
		<dependência>
			<groupId> org.springframework.boot </groupId>
			<artifactId> spring-boot-starter-web </artifactId>
		</dependency>
		<dependência>
			<groupId> org.springframework.boot </groupId>
			<artifactId> spring-boot-starter-web-services </artifactId>
		</dependency>

		<dependência>
			<groupId> org.springframework.boot </groupId>
			<artifactId> spring-boot-starter-test </artifactId>
			<scope> teste </scope>
			<exclusões>
				<exclusão>
					<groupId> org.junit.vintage </groupId>
					<artifactId> junit-vintage-engine </artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId> org.springframework.boot </groupId>
				<artifactId> spring-boot-maven-plugin </artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

## Adicione as dependências 
Edite o teu pom.xml e adiciona a dependência para o teu projecto.
```
		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.0-b170201.1204</version>
		</dependency>

		<dependency>
			<groupId>javax.activation</groupId>
			<artifactId>activation</artifactId>
			<version>1.1</version>
		</dependency>

		<dependency>
			<groupId>org.glassfish.jaxb</groupId>
			<artifactId>jaxb-runtime</artifactId>
			<version>2.3.0-b170127.1453</version>
		</dependency>

		<dependency>
			<groupId>wsdl4j</groupId>
			<artifactId>wsdl4j</artifactId>
			<version>1.6.1</version>
		</dependency>
```

## Crie um esquema XML para definir o domínio

O domínio de serviço da web é definido em um arquivo de esquema XML (XSD) que Spring-WS exportará automaticamente como WSDL.
Crie um ficheiro src/main/resources/clientes.xsd neste ficheiro definiremos os nossos modelos de dados.

```
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" 
           xmlns:tns="http://proit.com/unitel/domain"
           targetNamespace="http://proit.com/unitel/domain"
           elementFormDefault="qualified">

    <xs:element name="addClienteRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="nome" type="xs:string"></xs:element>
                <xs:element name="telefone" type="xs:int"></xs:element>
                <xs:element name="endereco" type="xs:string"></xs:element>
                <xs:element name="email" type="xs:string"></xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element> 

    <xs:element name="addClienteResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="cliente" type="tns:cliente"></xs:element>
            </xs:sequence>
        </xs:complexType>  
    </xs:element>

     <xs:element name="editarClienteRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="cliente" type="tns:cliente"></xs:element>
            </xs:sequence>
        </xs:complexType>  
    </xs:element>

    <xs:element name="editarClienteResponse">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="cliente" type="tns:cliente"></xs:element>
            </xs:sequence>
        </xs:complexType>  
    </xs:element>

     <xs:element name="removeClienteRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="id" type="xs:long"></xs:element>
            </xs:sequence>
        </xs:complexType> 
    </xs:element>

    <xs:element name="listClienteRequest">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="size" type="xs:int"></xs:element>
            </xs:sequence>
        </xs:complexType> 
    </xs:element>

    <xs:element name="listClienteResponse">
        <xs:complexType>
            <xs:sequence  minOccurs="0" maxOccurs="unbounded">
                <xs:element name="clientes" type="tns:cliente"></xs:element>
            </xs:sequence>
        </xs:complexType> 
    </xs:element>

    <xs:complexType name="cliente">
        <xs:sequence>
            <xs:element name="id" type="xs:long"></xs:element>
            <xs:element name="nome" type="xs:string"></xs:element>
            <xs:element name="telefone" type="xs:int"></xs:element>
            <xs:element name="endereco" type="xs:string"></xs:element>
            <xs:element name="email" type="xs:string"></xs:element>
        </xs:sequence>
    </xs:complexType>   

</xs:schema>
```

## Gerar classes de domínio com base em um esquema XML

Usaremos jaxb2-maven-plugin para gerar as classes de domínio de forma eficiente. Precisamos agora adicionar o plug-in maven abaixo à seção de plug-ins do arquivo pom.xml do projeto.

```
<plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>jaxb2-maven-plugin</artifactId>
      <version>1.6</version>
      <executions>
          <execution>
              <id>xjc</id>
              <goals>
                  <goal>xjc</goal>
              </goals>
          </execution>
      </executions>
      <configuration>
          <schemaDirectory>${project.basedir}/src/main/resources/</schemaDirectory>
          <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
          <clearOutputDir>false</clearOutputDir>
      </configuration>
 </plugin>
```


Agora execute o comando `maven compile` para gerar o código java do XSD.


## criar o repositório Cliente
Para gerenciador os dados do webService crie uma classe ClienteRespository e adicione o seguinte:

```
package com.proit.unitel.repository;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.proit.unitel.configuration.NotFoundException;
import com.proit.unitel.domain.Cliente;

import org.springframework.stereotype.Repository;



@Repository
public class ClienteRepository {
    
    //DataSource
    ArrayList<Cliente> tableCliente = new ArrayList();

    //save cliente
    public Cliente save(Cliente cliente) {
        cliente.setId(tableCliente.size()+1);
        tableCliente.add(cliente);
        return cliente;
    }

    //find all clientes
    public ArrayList<Cliente> findAll(int size){
        return(ArrayList)  tableCliente.stream().limit(size)
        .collect(Collectors.toList());
    }

    //remove cliente
    public void removeById(long id ){
        System.out.println("Entrou " + id);
        for(Cliente cliente: tableCliente){
            if (cliente.getId() == id) {
                System.out.println("apagado = " + id);
                tableCliente.remove(cliente);
                return;
            }  
        }

        throw new NotFoundException("Id "+ id + " Não foi encontrado");
            
    }


    //update cliente
    public Cliente update(Cliente clienteUpdate) {
        for (Cliente cliente : tableCliente) {
            if (cliente.getId() == clienteUpdate.getId()) {
                cliente.setNome(clienteUpdate.getNome());
                cliente.setEmail(clienteUpdate.getEmail());
                cliente.setTelefone(clienteUpdate.getTelefone());
                cliente.setEndereco(clienteUpdate.getEndereco());
                return cliente;
            }
        }

        throw new NotFoundException("Cliente id " + clienteUpdate.getId() + " Não foi encontrado");
    }

}
```
## criar a classe ClienteService

Para manipular os dados recebidos na requisição e processar para a resposta no cliente. Crie uma classe ClienteService.java e adicione:

```
package com.proit.unitel.service;

import java.util.List;
import java.util.stream.Collectors;

import com.proit.unitel.domain.AddClienteRequest;
import com.proit.unitel.domain.AddClienteResponse;
import com.proit.unitel.domain.Cliente;
import com.proit.unitel.domain.EditarClienteRequest;
import com.proit.unitel.domain.EditarClienteResponse;
import com.proit.unitel.domain.ListClienteRequest;
import com.proit.unitel.domain.ListClienteResponse;
import com.proit.unitel.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    public AddClienteResponse add( AddClienteRequest clienteRequest ) {

        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setEndereco(clienteRequest.getEndereco());
        cliente.setTelefone(clienteRequest.getTelefone());

        AddClienteResponse response = new AddClienteResponse();
        response.setCliente(repository.save(cliente));
        return response;
    }

    public ListClienteResponse list(ListClienteRequest request) {

        int size = request.getSize();

       List<Cliente> clientes = repository.findAll(size);

        ListClienteResponse response = new ListClienteResponse();
        response.getClientes().addAll(clientes);
        return response;
    }

    public void deleteById(long id){
        repository.removeById(id);
    }

    public EditarClienteResponse update(EditarClienteRequest clienteRequest) {
       Cliente cliente = repository.update(clienteRequest.getCliente());

       EditarClienteResponse response = new EditarClienteResponse();
       response.setCliente(cliente);
       return response;
    }
}
```
## Criar a endpoint 

Para receber e responder as solicitação do webService SOAP, cria uma class ClienteEndpoint.java.
```
package com.proit.unitel.controller;

import com.proit.unitel.domain.AddClienteRequest;
import com.proit.unitel.domain.AddClienteResponse;
import com.proit.unitel.domain.EditarClienteRequest;
import com.proit.unitel.domain.EditarClienteResponse;
import com.proit.unitel.domain.ListClienteRequest;
import com.proit.unitel.domain.ListClienteResponse;
import com.proit.unitel.domain.RemoveClienteRequest;
import com.proit.unitel.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClienteEndpoint {
   
    @Autowired
    private ClienteService clienteService;
    private final String NAMESPACE = "http://proit.com/unitel/domain";
    
    /**
     * Operation to Save cliente
     * @param request
     * @return
     */
    @PayloadRoot(
        namespace  = NAMESPACE,
        localPart = "addClienteRequest"
    )
    @ResponsePayload
    public AddClienteResponse saveCliente(@RequestPayload  AddClienteRequest request) {
        return clienteService.add(request);
    }


    /**
     * Operation to list all clientes saved
     * @param listRequest
     * @return
     */
    @PayloadRoot(
        namespace = NAMESPACE,
        localPart = "listClienteRequest"
    )
    @ResponsePayload
    public ListClienteResponse listCliente(@RequestPayload ListClienteRequest listRequest) {
        return clienteService.list(listRequest);
    }

    /**
     * Operation to remove cliente
     * @param removeClienteRequest
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "removeClienteRequest")
    public void removeCliente(@RequestPayload RemoveClienteRequest removeClienteRequest) {
         clienteService.deleteById(removeClienteRequest.getId());
    }
    

    /**
     * Operation to update Cliente
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "editarClienteRequest")
    @ResponsePayload
    public EditarClienteResponse UpdateCliente(@RequestPayload EditarClienteRequest request) {
       return clienteService.update(request);
    }
}
```

## Configurar Web Service Beans

Crei uma classe `SOAPConfig.java` para configurar o projecto como Spring-WS.
```
package com.proit.unitel.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "clientes")
    public DefaultWsdl11Definition defaultWsdl11Definition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ClientePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://proit.com/unitel/domain");
        wsdl11Definition.setSchema(clienteSchema());
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema clienteSchema() {
        return new SimpleXsdSchema(new ClassPathResource("clientes.xsd"));
    }

}
```

* SOAPConfig extends WsConfigurerAdapterque configura o modelo de programação Spring-WS orientado por anotação.
* MessageDispatcherServlet- Spring-WS usa para lidar com solicitações SOAP. Precisamos injetar ApplicationContextneste servlet para que Spring-WS encontre outros beans. Ele também declara o mapeamento de URL para as solicitações.
* DefaultWsdl11Definitionexpõe um padrão WSDL 1.1 usando XsdSchema. O nome do bean studentDetailsWsdlserá o nome wsdl que será exposto. Ele estará disponível em http://localhost:8080/ws/clientes.wsdl. Esta é a abordagem mais simples para expor o wsdl do contrato no Spring boot.

## Executando o nosso WebServiceSOAP

Para Executar o nosso WebService execute o comando  `maven clean package` depois execute o comando `java -jar target\web-service-soap.jar`.

1 - Agora vá para `http://localhost:8089/ws/clientes.wsdl`para aceder ao WSDL correctamente.

2- Abra o SOAPUI clique na operação SOAP, adicione link do WSDL no campo `initial WSDL` e clica em OK.

3-Agora, você pode fazer as tuas requisições  no WebService.

![alt text](https://appaws.s3.us-east-2.amazonaws.com/Capturar.PNG)
