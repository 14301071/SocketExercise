package webServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
	
	public static void main(String[] args)throws IOException{
		Socket connection = new Socket("127.0.0.1",3333);
		PrintWriter out = new PrintWriter(connection.getOutputStream(),true);
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Input 'exit' to quit it.");
		try{
			while(true){
				System.out.println("request: ");
				String r = wt.readLine();
				out.println(r);
				out.flush();
				if(r.equals("exit")){
					break;
				}
				String rp = in.readLine();
				System.out.println("response: \n"+rp+"\n");
			}
		}catch(SocketException e){
			System.out.println("a problem occur in the server: "+e.getMessage());
		}finally{
			connection.close();
		}
	}
}
