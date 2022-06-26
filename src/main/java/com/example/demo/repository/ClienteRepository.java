package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

/*@Repository
public class ClienteRepository {
	
	private static List<ClienteDTO> bancoCliente = new ArrayList<>();
	private static int id = 0;
	
	static {
		id++;
		ClienteDTO c = new ClienteDTO();
		c.setId(id);
		c.setNome("Paulo");
		
		id++;
		ClienteDTO c2 = new ClienteDTO();
		c2.setId(id);
		c2.setNome("João");
		
		id++;
		ClienteDTO c3 = new ClienteDTO();
		c3.setId(id);
		c3.setNome("Vitor");
		
		bancoCliente.add(c);
		bancoCliente.add(c2);
		bancoCliente.add(c3);
	}
	
	public ClienteDTO save(ClienteDTO cliente) {
		id++;
		cliente.setId(id);
		bancoCliente.add(cliente);
		return cliente;
	}
	
	public ClienteDTO getById(int id) {
		for (ClienteDTO cliente : bancoCliente) {
			if (id == cliente.getId()) {
				return cliente;
			}
		}
		return null;
	}

	public List<ClienteDTO> getAll() {
		return bancoCliente;
	}

}*/
