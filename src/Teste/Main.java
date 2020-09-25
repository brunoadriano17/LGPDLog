package Teste;

import Teste.Usuario;

public class Main {
    public static void main(String[] args) throws Exception {
        //Bruno faz login e o usuário é carregado para a variavel bruno
        Usuario bruno = Usuario.doLogin("bruno@teste.com");
        //Bruno consulta dados do joão
        Usuario joao = Usuario.find("joao@teste.com", bruno);
        //Bruno consulta dados da maria
        Usuario maria = Usuario.find("maria@teste.com", bruno);
    }  
}
