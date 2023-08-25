import java.util.Scanner;

 public class Principal {
	
	public static void main(String[] args) 
	{
		
		DAO dao = new DAO();		
		dao.conectar();
        int num = 0;
        Scanner scanner =  new Scanner(System.in);
        num = scanner.nextInt();
        while(num!=20)
        {       	
            System.out.println("Escolha uma opção");
            System.out.println("1-Inserir");
            System.out.println("2-Excluir");
            System.out.println("3-Atualizar");
            System.out.println("4-Listar");
            System.out.println("5-Sair");
            num = scanner.nextInt();
        	Usuario usuario = new Usuario(1, "a", "a", 'M');
        	Usuario[] usuarios = dao.getUsuariosMasculinos();
        	if(num == 1)
        	{
        		System.out.println("Digite o nome");
        		String[] temp = new String[3];
        		temp[0] = scanner.nextLine();
        		System.out.println("Digite o login");
        		temp[1] = scanner.nextLine();
        		System.out.println("Digite o Sexo");
        		temp[2] = scanner.nextLine();
        		char a = temp[2].charAt(0);
        	usuario = new Usuario(11, temp[0], temp[1], a);
    		if(dao.inserirUsuario(usuario) == true) {
    			System.out.println("Inserção com sucesso -> " + usuario.toString());
    		}
        	}
    		
        	else if( num == 2)
        	{
        		System.out.println("Digite a senha");
        	String p = scanner.nextLine();
    		usuario.setSenha(p);
    		dao.atualizarUsuario(usuario);
        	}
    		
        	else if ( num == 3)
        	{
        		System.out.println("Digite o codigo do usuario a ser removido");
        		int j = scanner.nextInt();
    		dao.excluirUsuario(j);
        	}
        	else if (num == 4)
        	{
    		usuarios = dao.getUsuarios();
    		System.out.println("==== Mostrar usuários === ");		
    		for(int i = 0; i < usuarios.length; i++) {
    			System.out.println(usuarios[i].toString());
    		}	
        	}
        	else if(num == 5)
        	{
        		break;
        	}
        	else 
        	System.out.println("Numero invalido");
        }	
		scanner.close();
		dao.close();
	}
}
