package classes;

import java.util.List;

public class Blog {
	public List<Post> listaDePosts(){
		return null;
		
	}

	public void deleta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Object objeto) {
	    if (!(objeto instanceof Blog)) {
	           return false;
	    }
	    Blog outra = (Blog) objeto;
	    //DEVE-SE TER MAIS COMPARADORES.
	    return getNome() == outra.getNome();
	
	    }

	private String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
	}
