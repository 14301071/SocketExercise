package webServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

	private Socket newSocket;
	
	public Server(Socket s){
		newSocket = s;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
			PrintWriter out = new PrintWriter(newSocket.getOutputStream(),true);
			System.out.println(Thread.currentThread().getName()+" is connected!");
			
			while(true){
				String request = in.readLine();
				System.out.println("øÕªß∂À"+Thread.currentThread().getName()+" ‰»Î£∫"+request);
				if(request.equals("exit")){
					System.out.println(Thread.currentThread().getName()+" is unconnected!");
					break;
				}
				Thread.sleep(500);
				StringBuffer sb = new StringBuffer(request);
				String response = sb.reverse().toString();
				out.println(response);
				out.flush();
			}
			newSocket.close();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public static void main(String[] args)throws IOException{
		ServerSocket server=new ServerSocket(3333); 
		try{
			while(true){       
				Server c = new Server(server.accept()); 		
				c.start();
			}
		}finally{
			server.close();
		}
	}
}
