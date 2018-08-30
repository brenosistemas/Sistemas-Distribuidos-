import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread{
	public static void main(String args[]){
		
		ServerSocket s = null;
		
		try{
			s = new ServerSocket(1234);//
			while(true){
	            //abre uma porta e aguarda a conexão de um cliente 
	            Socket conexao = s.accept();
				Thread t = new Servidor(conexao);
				t.start();
			}
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}
	
	private Socket conexao;
	
	public Servidor(Socket s){//recebe o valor do socket enviado na thread
		conexao = s;
	}
	public void run(){
		try{
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            // Cria uma stream de sáida para retorno das informações ao cliente
            DataOutputStream outToClient = new DataOutputStream(conexao.getOutputStream());
            String EscritaCliente = inFromClient.readLine();
            // Imprime a String modificada no console do servidor
            System.out.println("Voce Digitou: "+EscritaCliente.length()+" Digitos");
            // Imprime a String modificada no console do cliente
            outToClient.writeBytes("Voce Digitou: "+EscritaCliente.length()+" Digitos");
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}
}