import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;


class Server{

	private static final int port = 1234; 

	public static void main(String[] args){
			
		try{
			//init server
			ServerSocket server = new ServerSocket(port);
			System.out.println("server started on port: "+port);

			// accept streams from client 
			Socket clientStream = server.accept();

			// give stream to buffer to print request
			InputStream inputStream = clientStream.getInputStream();
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream)); 
			
			// PRINT REQUEST
			System.out.println("REQUEST HEADERS:");

			String bufferContent = bufferReader.readLine();
			while(!bufferContent.isEmpty()){
				System.out.println(bufferContent);
				bufferContent = bufferReader.readLine();
			}
			// after reading headers close buffer
			bufferReader.close();

			// RESPONSE
			String response = "response from server to the client";
			//get data from input stream sent from client
			OutputStream outS = clientStream.getOutputStream();

			// convert String to Bytes to send a response back
			outS.write(response.getBytes("UTF-8"));

		}catch(IOException err){
			System.out.println(err);
		}

	}
}
