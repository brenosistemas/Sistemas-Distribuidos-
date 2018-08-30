import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
public class Cliente extends Thread {
	
	public static void main(String args[]){
		try{
			Socket conexao = new Socket("localhost",1234);
			Thread t = new Cliente(conexao);
			t.start();
		}catch(IOException e){
			System.out.println("IOException"+e);
		}
	}
	
	private Socket conexao;
	public Cliente(Socket s){//recebe o valor do socket enviado na thread
		conexao = s;
	}
	public void run(){
		try{// recebe as informacoes enviadas do servidor
			PrintStream saida = new PrintStream(conexao.getOutputStream());
			// buffer  receber informacoes digitadas
			BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));
			System.out.println("Entre com Dados:");
			String digito = teclado.readLine();
			saida.println(digito);
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			entrada.readLine();				
		
		}catch(IOException e){
			System.out.println("IOException"+e);
		}finally{
			try {
				conexao.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}